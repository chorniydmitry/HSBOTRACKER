import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Box.Filler;

import com.google.gson.JsonSyntaxException;

import chernyj.utils.JSONParser;
import model.Expansion;

public class ExpansionsParsiongTest {
	
	static ArrayList<Expansion> allExpansionsList1 = new ArrayList<>();
	static ArrayList<Expansion> allExpansionsList2 = new ArrayList<>();
	static ArrayList<Expansion> allExpansionsList3 = new ArrayList<>();

	public static void main(String[] args) {
		fillExpansionsList(allExpansionsList1);
		fillExpansionsList(allExpansionsList2);
//		fillExpansionsList(allExpansionsList3);
		
		System.out.println(allExpansionsList1.size());
		System.out.println(allExpansionsList2.size());
//		System.out.println(allExpansionsList3.size());
	}
	
	private void parseTest() {
		JSONParser loader = new JSONParser();
		Collection<Expansion> expansions = null;
		try {
			expansions = loader.getExpansions();
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}

		if (expansions != null)
			for (Expansion expansion : expansions) {
				System.out.println(expansion.getIconPath() + " " + expansion.getName() + " " + expansion.getSetName());
				int[] cards = expansion.getCardsAmount();
				if (cards != null)
					for (int card : cards) {
						System.out.print(card + " ");
					}
				System.out.println();
			}
	}
	
	
	private static void fillExpansionsList(ArrayList<Expansion> allExpansionsList) {
		try {
			allExpansionsList.addAll(new JSONParser().getExpansions());
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
	}

}
