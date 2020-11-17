package chernyj.utils;

import java.awt.Color;
import java.util.Random;
/**
 * @author Chernyj Dmitry
 *
 */
public class Utils {
	public static final int MAXIMUM_COLOR_IN_HEX = 0xffffff;
	private static final int SWITCH_COLOR = 150;
	
	public static final Color getRandomColor() {
		Random rnd = new Random();
		return new Color(rnd.nextInt(MAXIMUM_COLOR_IN_HEX));
	}
	
	public static final Color getForeground(Color background) {
		if(background.getRed() < SWITCH_COLOR && 
				background.getGreen() < SWITCH_COLOR && 
				background.getBlue() < SWITCH_COLOR)
			return Color.WHITE;
		return Color.BLACK;
			
	}
}
