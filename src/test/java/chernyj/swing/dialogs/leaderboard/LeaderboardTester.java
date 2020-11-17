package chernyj.swing.dialogs.leaderboard;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import chernyj.utils.ApplicationConfiguration;
import chernyj.utils.BoosterOpenedAnalyser;
import chernyj.utils.ButtonReadyClickTracker;
import chernyj.utils.LogFileReader;
import model.Contestant;
import observer.LogFileObserver;

public class LeaderboardTester {
	private static final int LEADERBOARD_WIDTH = 300;
	private static final int LEADERBOARD_HEIGHT = 600;
	private static final String LEADERBOARD_TITLE = "Таблица лидеров";
	
	private static BoosterOpenedAnalyser boAnalyser = new BoosterOpenedAnalyser();
	private static ButtonReadyClickTracker brct = new ButtonReadyClickTracker();

	public static void main(String[] args) {

		TestLeaderBoard tlb = new TestLeaderBoard();
	}
	
	public static class TestLeaderBoard  implements LogFileObserver {
		
		public TestLeaderBoard() {
			startReadingLogFile(initLogFileReader());
			startButtonReadyClickTracker();
			
			new LeaderboardController(new LeaderboardDialog(LEADERBOARD_WIDTH, LEADERBOARD_HEIGHT, LEADERBOARD_TITLE), initContestants(), boAnalyser);
		}

		private ArrayList<Contestant> initContestants() {
			ArrayList<Contestant> contestants = new ArrayList<>();
			
			ArrayList<Integer> contestant1Numbers = new ArrayList<>();
			contestant1Numbers.add(1);
			contestant1Numbers.add(2);
			
			ArrayList<Integer> contestant2Numbers = new ArrayList<>();
			contestant2Numbers.add(1);
			contestant2Numbers.add(2);
			
			Contestant contestant1 = new Contestant("JohnCinema", contestant1Numbers ,Color.GREEN);
			Contestant contestant2 = new Contestant("c_off_ee", contestant2Numbers ,Color.RED);
			
			contestants.add(contestant1);
			contestants.add(contestant2);
			
			return contestants;
		}
		
		@Override
		public void update(String[] params) {
			boAnalyser.addNewCard(params);
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
		
	}
}


