package chernyj.utils;

import java.awt.Color;
import java.awt.Font;
/**
 * @author Chernyj Dmitry
 *
 */
public class Constants {
	public static final Color MAIN_FONT_COLOR = new Color(0x002233); //0xd4cac3
	public static final Color MAIN_FONT_LIGHT_COLOR = new Color(0xFFDDCC); //0xd4cac3
	public static final Color COMMON_FONT_COLOR = new Color(0xaaaaaa);
	public static final Color RARE_FONT_COLOR = new Color(0x3133ff);
	public static final Color EPIC_FONT_COLOR = new Color(0x7c1769);
	public static final Color LEGENDARY_FONT_COLOR = new Color(0xb48e10);
	public static final Color CONTENT_PANE_COLOR = new Color(0xffffff);
	public static final Color CONTENT_PANE_DARKER_COLOR = new Color(0xcccccc);
	public static final Color FULL_TRANSPARENT_COLOR = new Color(0,0,0,0);
	public static final Color DARK_TRANSPARENT_COLOR = new Color(0,0,0,150);
	
	public static final Color[] COLOR_FOR_RARITY = {COMMON_FONT_COLOR, RARE_FONT_COLOR, 
			EPIC_FONT_COLOR, LEGENDARY_FONT_COLOR};
	
	public static final Font DEFAULT_FONT = new Font("Tahoma", Font.BOLD, 16);
	public static final Font DEFAULT_FONT_BIGGER = new Font("Tahoma", Font.BOLD, 20);
	
	public static final String APPLICATION_NAME = "HS Pack Openning Statistics";
	
	/*
	 * To generate a Twitch Irc password, visit 
	 * <a href="https://twitchapps.com/tmi/"> https://twitchapps.com/tmi/ </a>
	 *
     *
     *    солнышко <3

    *	
    *	  johncinema
	public static final String MY_PASS = "oauth:recni3h0jx68i94739y1o31unak1fc";
	public static final String CHANNEL = "#johncinema";
	public static final String MY_NICK = "JohnCinema";
	*
	*	xXxtester228xXx
	public static final String MY_PASS = "oauth:pd47wm5b8utz246gu110swv6wxeez7";
	public static final String CHANNEL = "#xxxtester228xxx";
	public static final String MY_NICK = "xXxtester228xXx";
	
	*/
	
	public static final String MY_PASS = "oauth:4xofft0dnc0gdi6xzezk6h1n4kuo3t";
	public static final String CHANNEL = "#c_off_ee";
    public static final String MY_NICK = "c_off_ee";

	public static final int LOGFILE_UPDATE_TIME = 500;
	public static final int BTNREADY_UPDATE_TIME = 500;
	
	public static final int RARITIES_AMOUNT = 4;
	
	public static final int CARDS_IN_BOOSTER = 5;
	
	public static final int COMMON_DUST = 5;
	public static final int RARE_DUST = 20;
	public static final int EPIC_DUST = 100;
	public static final int LEGENDARY_DUST = 400;
	
	public static final int COMMON_DUST_CRAFT = 40;
	public static final int RARE_DUST_CRAFT = 100;
	public static final int EPIC_DUST_CRAFT = 400;
	public static final int LEGENDARY_DUST_CRAFT = 1600;
	
	public static final int G_COMMON_DUST = 50;
	public static final int G_RARE_DUST = 100;
	public static final int G_EPIC_DUST = 400;
	public static final int G_LEGENDARY_DUST = 10600;
	
	public static final String NOTIFICATION_WOW_PATH = "notifications/wow228.mp4";
	public static final int NOTIFICATION_WOW_DUST_MINIMUM = 10600;
	
}
