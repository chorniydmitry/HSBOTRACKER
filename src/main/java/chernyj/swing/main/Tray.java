package chernyj.swing.main;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

import chernyj.utils.Constants;
import chernyj.utils.Resources;

/**
 * @author Chernyj Dmitry
 *
 */
public class Tray {
	private static final ImageIcon ICON = Resources.EXPERT1_ICON;

	private static final String MI_LOCATE_DONE_BUTTON = "Определить позицию кнопки Готово";
	private static final String MI_BEGIN_NEW_CONTEST = "Конкурс";
	private static final String MI_CURRENT_STATISTICS = "Текущая статистика";
	private static final String MI_FULL_STATISTICS = "Статистика по дополнениям";
	private static final String MI_LEADERBOARD = "Таблица лидеров";
	private static final String MI_EXIT = "Закрыть";

	private MenuItem miLocateDoneButton = new MenuItem(MI_LOCATE_DONE_BUTTON);
	private MenuItem miNewContest = new MenuItem(MI_BEGIN_NEW_CONTEST);
	private MenuItem miСurrentStatistics = new MenuItem(MI_CURRENT_STATISTICS);
	private MenuItem miFullStatistics = new MenuItem(MI_FULL_STATISTICS);
	private MenuItem miLeagerBoard = new MenuItem(MI_LEADERBOARD);
	private MenuItem miExit = new MenuItem(MI_EXIT);

	private PopupMenu puTrayMenu = new PopupMenu();

	private TrayIcon trayIcon;

	private List<MenuItem> menuItems = new LinkedList<MenuItem>();
	
	public void showTray() {
		fillMenuItems();
		initTrayIcon();
		initTray();
	}

	private void fillMenuItems() {
		menuItems.add(miLocateDoneButton);
		menuItems.add(miNewContest);
		menuItems.add(miСurrentStatistics);
		menuItems.add(miFullStatistics);
		menuItems.add(miLeagerBoard);
		menuItems.add(miExit);
	}

	private void initTrayIcon() {

		trayIcon = new TrayIcon(ICON.getImage(), null, puTrayMenu);
		trayIcon.setImageAutoSize(true);
	}

	private void initTray() {
		if (!SystemTray.isSupported())
			return;

		for (MenuItem mi : menuItems) {
			puTrayMenu.add(mi);
		}

		SystemTray tray = SystemTray.getSystemTray();
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public void setInfoMessageInTray(String message) {
		trayIcon.displayMessage(Constants.APPLICATION_NAME, message, TrayIcon.MessageType.INFO);
	}

	public void setErrorMessageInTray(String message) {
		trayIcon.displayMessage(Constants.APPLICATION_NAME, message, TrayIcon.MessageType.ERROR);
	}

	public MenuItem getMiLocateDoneButton() {
		return miLocateDoneButton;
	}

	public MenuItem getMiNewContest() {
		return miNewContest;
	}

	public MenuItem getMiСurrentStatistics() {
		return miСurrentStatistics;
	}

	public MenuItem getMiFullStatistics() {
		return miFullStatistics;
	}

	public MenuItem getMiLeagerBoard() {
		return miLeagerBoard;
	}

	public MenuItem getMiExit() {
		return miExit;
	}
	
}
