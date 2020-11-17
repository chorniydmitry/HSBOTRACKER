package model;

/**
 * @author Chernyj Dmitry
 *
 */
public class Card {
	private String id;
	private String name;
	private Rarity rarity;
	// для поддержки cards.collectible.json
	private String set;
	private boolean isGolden;

	private Expansion expansion;

	
	public Card(Card clone) {
		this.id = clone.id;
		this.name = clone.name;
		this.rarity = clone.rarity;
		this.set = clone.set;
		this.isGolden = clone.isGolden;
	}
	
	public enum Rarity {
		COMMON, RARE, EPIC, LEGENDARY
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public String getSet() {
		return set;
	}

	public void setSet(String set) {
		this.set = set;
	}

	public boolean isGolden() {
		return isGolden;
	}

	public void setGolden(boolean isGolden) {
		this.isGolden = isGolden;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", name=" + name + ", rarity=" + rarity + ", set=" + set + ", isGolden=" + isGolden
				+ ", expansion=" + expansion + "]";
	}

	public Expansion getExpansion() {
		return expansion;
	}

	public void setExpansion(Expansion expansion) {
		this.expansion = expansion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expansion == null) ? 0 : expansion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isGolden ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rarity == null) ? 0 : rarity.hashCode());
		result = prime * result + ((set == null) ? 0 : set.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	


}
