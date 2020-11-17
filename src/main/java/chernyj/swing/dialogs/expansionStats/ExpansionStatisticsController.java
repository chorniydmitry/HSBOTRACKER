package chernyj.swing.dialogs.expansionStats;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import chernyj.swing.utils.ImagePanel;
import chernyj.utils.BoosterOpenedAnalyser;
import chernyj.utils.Constants;
import chernyj.utils.DustCounter;
import chernyj.utils.Resources;
import model.Card;
import model.Expansion;
import observer.ButtonReadyObserver;

/**
 * @author Chernyj Dmitry
 *
 */
public class ExpansionStatisticsController implements ButtonReadyObserver {

	private ExpansionStatisticsDialog dialog;
	private BoosterOpenedAnalyser boa;
	private ArrayList<Expansion> allExpansionList = new ArrayList<Expansion>();
	private Expansion showingExpansion;
	private int lastUpdatedPackNum = 0;

	public ExpansionStatisticsController(ExpansionStatisticsDialog fullStatisticsDialog, BoosterOpenedAnalyser boa) {
		this.dialog = fullStatisticsDialog;
		this.boa = boa;

		fillExpansionsList();
		setCbListener();

		updateShowingExp();
		fillTotalCardsForRarity();
		updateDialog(true);
	}

	private void setCbListener() {
		dialog.getCbExtension().addActionListener(l -> {
			updateShowingExp();
			fillTotalCardsForRarity();
			updateDialog(true);
		});
	}

	private void fillExpansionsList() {
		allExpansionList.addAll(boa.getAllExpansionList());
		for (Expansion e : allExpansionList) {
			dialog.getCbExtension().addItem(e.getName());
		}
	}

	private void updateShowingExp() {
		String extensionSelectedName = dialog.getCbExtension().getSelectedItem().toString();
		for (Expansion expansion : allExpansionList)
			if (expansion.getName().equals(extensionSelectedName))
				showingExpansion = expansion;
	}


    /** 
     * Процедура обновления формы открытых паков по дополнениям
     * @param force - выполнить обновление принудительно (необходимо при начальной инициализации, а так же при смене дополнения из списка 
     */
	private void updateDialog(boolean force) {
		// Если не открыт новый пак - форму обновлять и перерисовывать не нужно
		if(lastUpdatedPackNum == boa.getCurrentBoosterNum() && !force)
			return;
		
		fillLogo();

		fillBG();

		fillOpenedForExpansion();
		fillRarities();
		fillOpened();
		fillGoldenOpened();
		fillLackForExpansion();
		fillDustNeeded();

		fillDatasets();

		fillDustForDisenchant();
		fillDustForGoldens();
		
		lastUpdatedPackNum = boa.getCurrentBoosterNum();
	}

	private void fillDatasets() {

		for (int i = 0; i < dialog.getDatasets().size(); i++) {
			
			dialog.getDatasets().get(i).setValue(ExpansionStatisticsDialog.CHART_OPENED_TEXT,
					boa.getAmountsNoDublicates(Card.Rarity.values()[i], false, showingExpansion));
			
			dialog.getDatasets().get(i).setValue(ExpansionStatisticsDialog.CHART_TOTAL_TEXT,
					getTotalCardsForRarityForExpansion(Card.Rarity.values()[i]));
			
		}

	}

	private void fillBG() {
		ImagePanel mainPanel = dialog.getMainPanel();
		mainPanel.setImage(new ImageIcon(Resources.class.getResource(showingExpansion.getBackgroundPath())));
	}

	private void fillLogo() {
		JLabel lblLogo = dialog.getLblLogo();
		ImageIcon imIcon = new ImageIcon(Resources.class.getResource(showingExpansion.getLogoPath()));

		lblLogo.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(300, 180, Image.SCALE_DEFAULT)));
	}

	private void fillDustForDisenchant() {
		int dust = 0;
		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++) {
			int duples = boa.getDuplicates(Card.Rarity.values()[i], false, showingExpansion);
			dust += (duples * DustCounter.DUST_FOR_RARITY[i]);
		}
		dialog.getLblDustForDisenchantVal().setText(String.valueOf(dust));
	}

	private void fillDustForGoldens() {
		int dust = 0;
		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++)
			dust += (boa.getAmounts(Card.Rarity.values()[i], true, showingExpansion)
					* DustCounter.DUST_FOR_GOLDEN_RARITY[i]);

		dialog.getLblDustForGoldenVal().setText(String.valueOf(dust));
	}

	private void fillDustNeeded() {
		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++) {
			int needed = Integer.parseInt(dialog.getLblsMissingOfRarity().get(i).getText());
			int rarityMult = DustCounter.DUST_FOR_CRAFT[i];

			dialog.getLblsDustNeededForRarity().get(i).setText(String.valueOf(needed * rarityMult));
		}
	}

	private void fillLackForExpansion() {
		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++) {
			int opened = boa.getAmountsNoDublicates(Card.Rarity.values()[i], false, showingExpansion);
			int total = getTotalCardsForRarityForExpansion(Card.Rarity.values()[i]);

			dialog.getLblsMissingOfRarity().get(i).setText(String.valueOf(total - opened));
		}

	}

	private void fillOpenedForExpansion() {
		dialog.getLblOpenedForExpVal().setText(String.valueOf(boa.getOpenedForExpansion(showingExpansion)));
	}

	private void fillRarities() {
		final Icon icons[] = { Resources.RARITY_COMMON, Resources.RARITY_RARE, Resources.RARITY_EPIC,
				Resources.RARITY_LEGENDARY };

		ArrayList<JLabel> lblsRarities = dialog.getLblsRarityNames();
		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++)
			lblsRarities.get(i).setIcon(icons[i]);

	}

	private void fillOpened() {
		ArrayList<JLabel> lblsOpened = dialog.getLblsOpenedOfRarity();
		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++) {
			lblsOpened.get(i).setText(String.valueOf(boa.getAmountsNoDublicates(Card.Rarity.values()[i], false, showingExpansion)));
		}
	}

	private void fillGoldenOpened() {
		ArrayList<JLabel> lblsOpened = dialog.getLblsOpenedGoldOfRarity();
		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++)
			lblsOpened.get(i).setText(String.valueOf(boa.getAmounts(Card.Rarity.values()[i], true, showingExpansion)));
	}

	private void fillTotalCardsForRarity() {
		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++)
			dialog.getLblsTotalOfRarity().get(i)
					.setText(String.valueOf(getTotalCardsForRarityForExpansion(Card.Rarity.values()[i])));
	}

	private int getTotalCardsForRarityForExpansion(Card.Rarity rarity) {
		return rarity == Card.Rarity.LEGENDARY ? showingExpansion.getCardsAmount()[rarity.ordinal()]
				: (showingExpansion.getCardsAmount()[rarity.ordinal()] * 2);

	}

	@Override
	public void update() {
		updateDialog(false);

	}

	public void showDialog() {
		dialog.setVisible(true);
	}

}
