package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chernyj Dmitry
 *
 */
public class Booster {
	public static final int CARDS_IN_BOOSTER = 5;
	
	private List<Card> cards = new ArrayList<Card>();
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public int getAmountOfCards() {
		return cards.size();
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	@Override
	public String toString() {
		return cards.toString();
	}

}
