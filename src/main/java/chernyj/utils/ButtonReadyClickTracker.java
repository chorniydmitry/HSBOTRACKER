package chernyj.utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import observer.ButtonReadyObserver;
import observer.ButtonReadySubject;
/**
 * @author Chernyj Dmitry
 *
 */
public class ButtonReadyClickTracker implements Runnable, ButtonReadySubject {
	private boolean isRunning = true;
	private Rectangle rectangleToTrack;
	
	private ArrayList<ButtonReadyObserver> observers = new ArrayList<>();
	
	private static final int MINIMUM_WHITES = 600;
	private static final int MAXIMUM_WHITES = 630;
	
	public ButtonReadyClickTracker() {
		this.rectangleToTrack = initRectangleToTrack();
	}
	
	private Rectangle initRectangleToTrack() {
		int x = Integer.parseInt(ApplicationConfiguration.getItem("buttonready.x"));
		int y = Integer.parseInt(ApplicationConfiguration.getItem("buttonready.y"));
		int width = Integer.parseInt(ApplicationConfiguration.getItem("buttonready.width"));
		int height = Integer.parseInt(ApplicationConfiguration.getItem("buttonready.height"));
		
		return new Rectangle(x, y, width, height);
	}

	public void startThread() {
		isRunning = true;
	}

	public void stopThread() {
		isRunning = false;
	}
	
	private boolean passingPixelTest(int amountOfWhites) {
		if(amountOfWhites > MINIMUM_WHITES && amountOfWhites < MAXIMUM_WHITES)
			return true;
		return false;
	}

	public boolean buttonIsShowing() {
		BufferedImage image = null;
		try {
			image = new Robot().createScreenCapture(rectangleToTrack);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int amountOfWhite = 0;

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				if (image.getRGB(x, y) == -1)
					amountOfWhite++;
			}
		}

		if (passingPixelTest(amountOfWhite))
			return true;
		return false;
	}

	@Override
	public void run() {
		try {
			while (isRunning) {
				if (buttonIsShowing())
					notifyObservers();
				Thread.sleep(Constants.BTNREADY_UPDATE_TIME);
			}
		} catch (Exception e) {
			e.printStackTrace();
			stopThread();
		}

	}

	@Override
	public void register(ButtonReadyObserver o) {
		observers.add(o);
		
	}

	@Override
	public void unregister(ButtonReadyObserver o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (ButtonReadyObserver o : observers) {
			o.update();
		}
	}
}
