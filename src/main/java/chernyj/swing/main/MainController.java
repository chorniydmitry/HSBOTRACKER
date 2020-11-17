package chernyj.swing.main;
/**
 * @author Chernyj Dmitry
 *
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import chernyj.swing.dialogs.currentStats.CurrentStatisticsController;
import chernyj.swing.dialogs.expansionStats.ExpansionStatisticsController;
import chernyj.swing.dialogs.leaderboard.LeaderboardController;
import chernyj.swing.dialogs.newContest.NewContestController;
import chernyj.swing.utils.DialogBuilder;
import chernyj.utils.ApplicationConfiguration;
import chernyj.utils.BoosterLogFileWriter;
import chernyj.utils.BoosterOpenedAnalyser;
import chernyj.utils.ButtonReadyClickTracker;
import chernyj.utils.ButtonReadyPositionSetter;
import chernyj.utils.Constants;
import chernyj.utils.LogFileReader;
import observer.ButtonReadyObserver;
import observer.LogFileObserver;

public class MainController implements LogFileObserver, ButtonReadyObserver {
	private static final String SAVE_RESULTS_RU = "Сохранить результаты?";
	
	private Tray tray;
	private BoosterOpenedAnalyser boAnalyser = new BoosterOpenedAnalyser();
	private ButtonReadyClickTracker brct = new ButtonReadyClickTracker();

	private NewContestController newContest;
	private CurrentStatisticsController currentStat;
	private ExpansionStatisticsController fullStat;
	private LeaderboardController leaderBoard;
	
	private int currentBoosterNum = 0;
	private int notificationLastPlayedNum = 0;
	
	public MainController(Tray tray) {
		this.tray = tray;
		startReadingLogFile(initLogFileReader());
		startButtonReadyClickTracker();
		
		tray.showTray();
		
		setListeners();
		
		brct.register(this);
		
	}
	
	private LogFileReader initLogFileReader() {
		String filePath = ApplicationConfiguration.getItem("hslogfile.path");
		return new LogFileReader(filePath);
	}

	private void startReadingLogFile(LogFileReader reader) {
		reader.register(this);
		ExecutorService logExecutor = Executors.newFixedThreadPool(4);
		logExecutor.execute(reader);
	}
	
	private void startButtonReadyClickTracker() {
		ExecutorService logExecutor = Executors.newFixedThreadPool(4);
		logExecutor.execute(brct);
	}
	
	@Override
	public void update(String[] params) {
		boAnalyser.addNewCard(params);
	}
	
	
	private void setListeners() {
		tray.getMiLocateDoneButton().addActionListener(l -> doLocateDoneButtonAction());
		tray.getMiNewContest().addActionListener(l -> doNewContestAction());
		tray.getMiСurrentStatistics().addActionListener(l -> doCurrentStatisticsAction());
		tray.getMiFullStatistics().addActionListener(l -> doFullStatisticsAction());
		tray.getMiLeagerBoard().addActionListener(l -> doLeaderBoardAction());
		tray.getMiExit().addActionListener(l -> doExitAction());
	}
	
	private void doLeaderBoardAction() {
		if(leaderBoard == null) {
			try {
			leaderBoard = new DialogBuilder().getLeaderBoardController(newContest.getContestants(), boAnalyser);
			} catch(NullPointerException e) {
				System.err.println("Не добавлено ниодного участника!");
				return;
			}
			
		}
		leaderBoard.showDialog();
		brct.register(leaderBoard);
	}

	private void doLocateDoneButtonAction() {
		new ButtonReadyPositionSetter();
	}

	private void doNewContestAction() {
		if (newContest == null)
			newContest = new DialogBuilder().getNewContestController();
		newContest.showDialog();
	}

	private void doCurrentStatisticsAction() {
		if (currentStat == null)
			currentStat = new DialogBuilder().getCurrentStatisticsDialog(boAnalyser);
		currentStat.showDialog();
		
		brct.register(currentStat);
	}

	private void doFullStatisticsAction() {
		if(fullStat == null)
			fullStat = new DialogBuilder().getFullStatisticsDialog(boAnalyser);
		fullStat.showDialog();
		
		brct.register(fullStat);
	}

	private void doExitAction() {
		int i = JOptionPane.showConfirmDialog(null, SAVE_RESULTS_RU);
		if (i == 0) {
			new BoosterLogFileWriter(boAnalyser.getBoostersOpened());
			System.exit(0);
		} else if(i == 1)
			System.exit(0);
	}

	@Override
	public void update() {
		currentBoosterNum = boAnalyser.getCurrentBoosterNum();
		if(boAnalyser.getCurrentBoosterDust() > Constants.NOTIFICATION_WOW_DUST_MINIMUM && currentBoosterNum != notificationLastPlayedNum) {
			new DialogBuilder().getNotificationController(Constants.NOTIFICATION_WOW_PATH);
			notificationLastPlayedNum = boAnalyser.getCurrentBoosterNum();
		}
		
	}

}
