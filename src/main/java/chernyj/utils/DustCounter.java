package chernyj.utils;

import model.Booster;
import model.Card;

/**
 * @author Chernyj Dmitry
 *
 */
public class DustCounter {
	public static final int[] DUST_FOR_RARITY = { Constants.COMMON_DUST, Constants.RARE_DUST, Constants.EPIC_DUST,
			Constants.LEGENDARY_DUST };

	public static final int[] DUST_FOR_GOLDEN_RARITY = { Constants.G_COMMON_DUST, Constants.G_RARE_DUST,
			Constants.G_EPIC_DUST, Constants.G_LEGENDARY_DUST };

	public static final int[] DUST_FOR_CRAFT = { Constants.COMMON_DUST_CRAFT, Constants.RARE_DUST_CRAFT,
			Constants.EPIC_DUST_CRAFT, Constants.LEGENDARY_DUST_CRAFT };

	public static int getDust(Card card) {
		if (card == null)
			return 0;

		int rarityIndex = card.getRarity().ordinal();

		if (card.isGolden())
			return DUST_FOR_GOLDEN_RARITY[rarityIndex];
		else
			return DUST_FOR_RARITY[rarityIndex];
	}

	public static int getDust(Booster booster) {
		int totalDust = 0;

		for (Card card : booster.getCards())
			totalDust += getDust(card);

		return totalDust;
	}

	public static int getDust(Card.Rarity rarity, boolean isGolden) {
		return isGolden ? DUST_FOR_GOLDEN_RARITY[rarity.ordinal()] : DUST_FOR_RARITY[rarity.ordinal()];
	}

}
