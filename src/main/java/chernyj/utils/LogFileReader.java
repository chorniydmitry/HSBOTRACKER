package chernyj.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import observer.LogFileObserver;
import observer.LogFileSubject;

/**
 * @author Chernyj Dmitry
 *
 */
public class LogFileReader implements Runnable, LogFileSubject {

	private static final int UPDATE_TIME = 100;

	private boolean debug = true;

	private boolean isRunning = true;
	private File logFile = null;

	private ArrayList<LogFileObserver> observers = new ArrayList<LogFileObserver>();

	public LogFileReader(String myFile) {
		logFile = new File(myFile);
	}

	private void printLine(String message) {
		System.out.println(message);
	}

	private boolean isCardGainedLine(String message) {
		if (message.contains("NotifyOfCardGained"))
			return true;
		else
			return false;
	}

	private String findCardID(String message) {
		return message.split("cardId=")[1].split(" ")[0];
	}

	private String findCardFlavor(String message) {
		return message.split("] ")[1].split(" ")[0];
	}

	public void stopRunning() {
		isRunning = false;
	}

	public void run() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(logFile));
			String line;

			while (isRunning) {
				line = br.readLine();

				if (line != null && isCardGainedLine(line)) {
					notifyObserver(new String[] { this.findCardID(line), this.findCardFlavor(line) });
				} else {
					Thread.sleep(UPDATE_TIME);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			stopRunning();

		}
		if (debug)
			this.printLine("Exit the program...");

	}

	@Override
	public void register(LogFileObserver o) {
		observers.add(o);
	}

	@Override
	public void unregister(LogFileObserver o) {
		observers.remove(o);
	}

	@Override
	public void notifyObserver(String[] params) {
		for (LogFileObserver observer : observers) {
			observer.update(params);
		}
	}

}