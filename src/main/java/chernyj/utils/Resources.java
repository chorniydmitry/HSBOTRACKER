package chernyj.utils;

import java.io.InputStream;

import javax.swing.ImageIcon;

/**
 * @author Chernyj Dmitry
 *
 */
public class Resources {
	public static final ImageIcon BACKGROUND_IMAGE = new ImageIcon(Resources.class.getResource("/images/background.png"));
	public static final ImageIcon BACKGROUND_PAPIRUS = new ImageIcon(Resources.class.getResource("/images/papirus.png"));
	
	public static final ImageIcon RARITY_COMMON = new ImageIcon(Resources.class.getResource("/images/rarity_gems/common.png"));
	public static final ImageIcon RARITY_GCOMMON = new ImageIcon(Resources.class.getResource("/images/rarity_gems/gcommon.png"));
	public static final ImageIcon RARITY_RARE = new ImageIcon(Resources.class.getResource("/images/rarity_gems/rare.png"));
	public static final ImageIcon RARITY_GRARE = new ImageIcon(Resources.class.getResource("/images/rarity_gems/grare.png"));
	public static final ImageIcon RARITY_EPIC = new ImageIcon(Resources.class.getResource("/images/rarity_gems/epic.png"));
	public static final ImageIcon RARITY_GEPIC = new ImageIcon(Resources.class.getResource("/images/rarity_gems/gepic.png"));
	public static final ImageIcon RARITY_LEGENDARY = new ImageIcon(Resources.class.getResource("/images/rarity_gems/legendary.png"));
	public static final ImageIcon RARITY_GLEGENDARY = new ImageIcon(Resources.class.getResource("/images/rarity_gems/glegendary.png"));

	public static final InputStream CARDS_JSON = Resources.class.getResourceAsStream("/cards.collectible.json");
	public static final InputStream EXPANSIONS_JSON = Resources.class.getResourceAsStream("/expansions.json");
	
	public static final ImageIcon DUST_ICON = new ImageIcon(Resources.class.getResource("/images/dust.png"));

	public static final ImageIcon BOOMSDAY_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/boomsday.png"));
	public static final ImageIcon DALARAN_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/dalaran.png"));
	public static final ImageIcon DRAGONS_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/dragons.png"));
	public static final ImageIcon EXPERT1_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/expert1.png"));
	public static final ImageIcon GANGS_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/gangs.png"));
	public static final ImageIcon GILNEAS_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/gilneas.png"));
	public static final ImageIcon GVG_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/gvg.png"));
	public static final ImageIcon ICECROWN_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/icecrown.png"));
	public static final ImageIcon LOOTAPALOOZA_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/lootapalooza.png"));
	public static final ImageIcon OG_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/og.png"));
	public static final ImageIcon TGT_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/tgt.png"));
	public static final ImageIcon TROLL_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/troll.png"));
	public static final ImageIcon ULDUM_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/uldum.png"));
	public static final ImageIcon UNGORO_ICON = new ImageIcon(Resources.class.getResource("/images/boosters/ungoro.png"));
	
	public static final ImageIcon BOOMSDAY_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/boomsday.png"));
	public static final ImageIcon DALARAN_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/dalaran.png"));
	public static final ImageIcon DRAGONS_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/dragons.png"));
	public static final ImageIcon EXPERT1_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/expert1.png"));
	public static final ImageIcon GANGS_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/gangs.png"));
	public static final ImageIcon GILNEAS_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/gilneas.png"));
	public static final ImageIcon GVG_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/gvg.png"));
	public static final ImageIcon ICECROWN_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/icecrown.png"));
	public static final ImageIcon LOOTAPALOOZA_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/lootapalooza.png"));
	public static final ImageIcon OG_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/og.png"));
	public static final ImageIcon TGT_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/tgt.png"));
	public static final ImageIcon TROLL_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/troll.png"));
	public static final ImageIcon ULDUM_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/uldum.png"));
	public static final ImageIcon UNGORO_LOGO = new ImageIcon(Resources.class.getResource("/images/logos/ungoro.png"));
	
}
