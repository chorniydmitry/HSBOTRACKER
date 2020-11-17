package chernyj.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.JsonSyntaxException;

import model.Booster;
import model.Card;
import model.Expansion;

/**
 * @author Chernyj Dmitry
 *
 */
public class BoosterOpenedAnalyser {
	private static final int DUPLES_AVAILIBLE = 2;
	private static final int LEG_DUPLES_AVAILIBLE = 1;

	private int totalDust = 0;
	private int totalDustForDuplicates = 0;

	private ArrayList<Expansion> allExpansionList = new ArrayList<Expansion>();

	private ArrayList<Card> allCardList = new ArrayList<Card>();
	private ArrayList<Card> openedList = new ArrayList<Card>();
	private ArrayList<Card> openedGoldenList = new ArrayList<Card>();

	private ArrayList<Booster> boostersOpened = new ArrayList<>();
	private Booster currentBooster = new Booster();
	private Booster bestBooster;

	private int currentBoosterNumAfterLeg = 0;

	public BoosterOpenedAnalyser() {
		fillCardList();
		fillExpansionList();
	}

	private void fillCardList() {
		try {
			allCardList.addAll(new JSONParser().getCards());
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
	}

	private void fillExpansionList() {
		try {
			allExpansionList.addAll(new JSONParser().getExpansions());
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
	}

	private void updateInfo() {
		addCardsToOpenedLists();

		updateTotalDust();
		updatTotalDustForDuplicates();
		updateBestBooster();
		updateBoosterAfterLeg();

	}

	private void addCardsToOpenedLists() {
		for (Card card : currentBooster.getCards()) {
			if (card.isGolden()) {
				openedGoldenList.add(card);
			} else {
				openedList.add(card);
			}
		}
	}

	private void updateTotalDust() {
		totalDust += DustCounter.getDust(currentBooster);
	}

	private void updatTotalDustForDuplicates() {
		totalDustForDuplicates = 0;
		for (int i = 0; i < Card.Rarity.values().length; i++) {
			totalDustForDuplicates += getDuplicates(Card.Rarity.values()[i], openedList) * DustCounter.DUST_FOR_RARITY[i];
			totalDustForDuplicates += getDuplicates(Card.Rarity.values()[i], openedGoldenList) * DustCounter.DUST_FOR_GOLDEN_RARITY[i];
		}
	}

	private void updateBestBooster() {
		if (bestBooster == null) {
			bestBooster = currentBooster;
		} else if (DustCounter.getDust(currentBooster) > DustCounter.getDust(bestBooster)) {
			bestBooster = currentBooster;
		}
	}

	private void updateBoosterAfterLeg() {
		for (Card card : currentBooster.getCards()) {
			if (card.getRarity() == Card.Rarity.LEGENDARY) {
				currentBoosterNumAfterLeg = 0;
				return;
			}
		}

		currentBoosterNumAfterLeg++;
	}

	public void addNewCard(Card card) {
		for (Expansion expansion : allExpansionList)
			if (expansion.getSetName().equals(card.getSet()))
				card.setExpansion(expansion);
		currentBooster.addCard(card);
		
		if (currentBooster.getAmountOfCards() == Constants.CARDS_IN_BOOSTER) {
			boostersOpened.add(currentBooster);
			updateInfo();
			currentBooster = new Booster();
			return;
		}
	}

	public void addNewCard(String[] params) {
		String cardId = params[0];
		String flavor = params[1];

		Card cardFound = null;

		for (Card card : allCardList) {
			if (card.getId().equals(cardId)) {
				cardFound = new Card(card);
				break;
			}
		}
		if (cardFound != null)
			if (flavor.equals("GOLDEN"))
				cardFound.setGolden(true);
			else
				cardFound.setGolden(false);

		addNewCard(cardFound);
	}

	private ArrayList<Card> filterCardsByExpansion(ArrayList<Card> cardsToFilter, Expansion e) {
		ArrayList<Card> cardsFiltered = new ArrayList<>();

		for (Card card : cardsToFilter)
			if (card.getExpansion().getSetName().equals(e.getSetName()))
				cardsFiltered.add(card);

		return cardsFiltered;
	}

	public String getLegendarySuccessRateString() {
		int totalOpened = boostersOpened.size();
		int amountOfLegs = getAmounts(Card.Rarity.LEGENDARY, openedList) +
				getAmounts(Card.Rarity.LEGENDARY, openedGoldenList);
		
		if(amountOfLegs == 0 || totalOpened == 0)
			return "";
		
		double legInEveryN = Math.round((double) totalOpened / amountOfLegs);
		double legProb = Math.round((double) 100 *amountOfLegs / totalOpened);
		
		return "1 лега на ~" + (int) legInEveryN + " пак(а/ов) или " + (int) legProb + "%";
	}

	private ArrayList<Card> assignNeededList(Card.Rarity rarity, boolean isGolden, Expansion exp) {
		ArrayList<Card> cardList = new ArrayList<Card>();

		cardList = isGolden ? openedGoldenList : openedList;

		if (exp != null)
			cardList = filterCardsByExpansion(cardList, exp);

		return cardList;
	}

	private int getAmounts(Card.Rarity rarity, ArrayList<Card> cards) {
		int amounts = 0;
		for (Card card : cards)
			if (card.getRarity().equals(rarity))
				amounts++;
		return amounts;
	}

	public int getAmounts(Card.Rarity rarity, boolean isGolden) {
		return getAmounts(rarity, assignNeededList(rarity, isGolden, null));
	}

	public int getAmounts(Card.Rarity rarity, boolean isGolden, Expansion exp) {
		return getAmounts(rarity, assignNeededList(rarity, isGolden, exp));
	}
	
	public int getAmountsNoDublicates(Card.Rarity rarity, ArrayList<Card> cards) {
		return getAmounts(rarity, cards) - getDuplicates(rarity, cards);
	}
	
	public int getAmountsNoDublicates(Card.Rarity rarity, boolean isGolden) {
		return getAmountsNoDublicates(rarity, assignNeededList(rarity, isGolden, null));
	}
	
	public int getAmountsNoDublicates(Card.Rarity rarity, boolean isGolden, Expansion exp) {
		return getAmountsNoDublicates(rarity, assignNeededList(rarity, isGolden, exp));
	}

	private int getDuplicates(Card.Rarity rarity, ArrayList<Card> cards) {
		int duplicatesFound = 0;
		for (Card card : allCardList) {
			int freqOfCard = 0;
			int nonDuplicates = 0;
			//Базовые карты не имеют редкости
			
			if(card.getRarity() != null)
				nonDuplicates = (card.getRarity().equals(Card.Rarity.LEGENDARY)) ? LEG_DUPLES_AVAILIBLE
					: DUPLES_AVAILIBLE;
			if (rarity.equals(card.getRarity()))
				freqOfCard = Collections.frequency(cards, card);

			if (freqOfCard > nonDuplicates)
				duplicatesFound += (freqOfCard - nonDuplicates);
		}
		return duplicatesFound;
	}
	
	public int getDuplicates(Card.Rarity rarity, boolean isGolden) {
		return getDuplicates(rarity, assignNeededList(rarity, isGolden, null));
	}

	public int getDuplicates(Card.Rarity rarity, boolean isGolden, Expansion exp) {
		return getDuplicates(rarity, assignNeededList(rarity, isGolden, exp));
	}

	public int getCurrentBoosterNumAfterLeg() {
		return currentBoosterNumAfterLeg;
	}

	public int getTotalDust() {
		return totalDust;
	}

	public int getTotalDustForDuplicates() {
		return totalDustForDuplicates;
	}

	public int getBestBoosterNum() {
		return boostersOpened.indexOf(bestBooster) + 1;
	}

	public int getBestBoosterDust() {
		return DustCounter.getDust(bestBooster);
	}

	public int getCurrentBoosterNum() {
		return boostersOpened.size();
	}

//	public String getCurrentBoosterExp() {
//		return boostersOpened.get(boostersOpened.size() - 1).getCards().get(0).getSet();
//	}

	public Expansion getCurrentBoosterExp() {
		if(boostersOpened.size() == 0)
			return new Expansion();
		return boostersOpened.get(boostersOpened.size() - 1).getCards().get(0).getExpansion();
	}
	
	public int getCurrentBoosterDust() {
		if(boostersOpened.size() == 0)
			return 0;
		return DustCounter.getDust(boostersOpened.get(boostersOpened.size() - 1));
	}

	public int getDustForIndexes(ArrayList<Integer> indexes) {
		int total = 0;
		if(boostersOpened.size() == 0)
			return 0;
		for (Integer index : indexes) {
			if(boostersOpened.size() < index)
				continue;
			total += DustCounter.getDust(boostersOpened.get(index - 1)); //FIXME INDEX OUT OF BOUNDS 1 1
		}
		return total;
	}

	public ArrayList<Booster> getBoostersOpened() {
		return boostersOpened;
	}

	public int getOpenedForExpansion(Expansion showingExpansion) {
		int counter = 0;
		for (Booster booster : boostersOpened) {
			Card c = booster.getCards().get(0);
			if (c.getSet().equals(showingExpansion.getSetName()))
				counter++;
		}
		return counter;

	}
	
	public ArrayList<Expansion> getAllExpansionList() {
		return allExpansionList;
	}

}
