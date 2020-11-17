package chernyj.swing.utils;

import java.util.ArrayList;

import chernyj.swing.dialogs.currentStats.CurrentStatisticsController;
import chernyj.swing.dialogs.currentStats.CurrentStatisticsDialog;
import chernyj.swing.dialogs.expansionStats.ExpansionStatisticsController;
import chernyj.swing.dialogs.expansionStats.ExpansionStatisticsDialog;
import chernyj.swing.dialogs.leaderboard.LeaderboardController;
import chernyj.swing.dialogs.leaderboard.LeaderboardDialog;
import chernyj.swing.dialogs.newContest.NewContestController;
import chernyj.swing.dialogs.newContest.NewContestDialog;
import chernyj.swing.dialogs.notifications.NotificationController;
import chernyj.swing.dialogs.notifications.NotificationDialog;
import chernyj.swing.dialogs.vacantNumbers.UserRegistrationController;
import chernyj.swing.dialogs.vacantNumbers.UserRegistrationDialog;
import chernyj.utils.BoosterOpenedAnalyser;
import model.Contestant;

/**
 * @author Chernyj Dmitry
 *
 */
public class DialogBuilder {
	private static final int START_NEW_CONTEST_WIDTH = 400;
	private static final int START_NEW_CONTEST_HEIGHT = 200;

	private static final int VACANT_NUBERS_WIDTH = 500;
	private static final int VACANT_NUMBERS_HEIGHT = 500;

	private static final int CURRENT_STATISTICS_WIDTH = 430;
	private static final int CURRENT_STATISTICS_HEIGHT = 580;

	private static final int FULL_STATISTICS_WIDTH = 660;
	private static final int FULL_STATISTICS_HEIGHT = 660;

	private static final int LEADERBOARD_WIDTH = 300;
	private static final int LEADERBOARD_HEIGHT = 600;

	private static final int NOTIFICATION_WIDHT = 640;//1280;
	private static final int NOTIFICATION_HEIGHT = 360;//720;

	private static final String VACANT_NUMBERS_TITLE = "Сетка занятых номеров";

	private static final String START_NEW_CONTEST_TITLE = "Конфигурация нового конкурса";

	private static final String CURRENT_STATISTICS_TITLE = "Текущая статистика";

	private static final String EXPANSION_STATISTICS_TITLE = "Статистика по дополнениям";

	private static final String LEADERBOARD_TITLE = "Таблица лидеров";

	private static final String NOTIFICATION_TITLE = "ОПОВЕЩЕНИЕ!";

	public NewContestController getNewContestController() {
		return new NewContestController(
				new NewContestDialog(START_NEW_CONTEST_WIDTH, START_NEW_CONTEST_HEIGHT, START_NEW_CONTEST_TITLE));
	}

	public UserRegistrationController getVacantNumbersController(int amountOfPaks, int amountOfNums,
			boolean firstTenAllowed) {

		return new UserRegistrationController(new UserRegistrationDialog(VACANT_NUBERS_WIDTH, VACANT_NUMBERS_HEIGHT,
				VACANT_NUMBERS_TITLE, amountOfPaks), amountOfPaks, amountOfNums, firstTenAllowed);
	}

	public CurrentStatisticsController getCurrentStatisticsDialog(BoosterOpenedAnalyser boa) {

		return new CurrentStatisticsController(new CurrentStatisticsDialog(CURRENT_STATISTICS_WIDTH,
				CURRENT_STATISTICS_HEIGHT, CURRENT_STATISTICS_TITLE), boa);
	}

	public ExpansionStatisticsController getFullStatisticsDialog(BoosterOpenedAnalyser boa) {
		return new ExpansionStatisticsController(new ExpansionStatisticsDialog(FULL_STATISTICS_WIDTH,
				FULL_STATISTICS_HEIGHT, EXPANSION_STATISTICS_TITLE), boa);
	}

	public LeaderboardController getLeaderBoardController(ArrayList<Contestant> contestants,
			BoosterOpenedAnalyser boAnalyser) {

		return new LeaderboardController(
				new LeaderboardDialog(LEADERBOARD_WIDTH, LEADERBOARD_HEIGHT, LEADERBOARD_TITLE), contestants,
				boAnalyser);

	}

	public NotificationController getNotificationController(String filePathToPlay) {
		return new NotificationController(
				new NotificationDialog(NOTIFICATION_WIDHT, NOTIFICATION_HEIGHT, NOTIFICATION_TITLE), filePathToPlay);
	}

}
