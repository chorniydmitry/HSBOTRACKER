package model;

/**
 * @author Chernyj Dmitry
 *
 */
public class Expansion {
	private String name;
	private String setName;
	private String iconPath;
	private String logoPath;
	private String backgroundPath;
	private int[] cardsAmount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
	}

	public int[] getCardsAmount() {
		return cardsAmount;
	}

	public void setCardsAmount(int[] cardsAmount) {
		this.cardsAmount = cardsAmount;
	}

}
