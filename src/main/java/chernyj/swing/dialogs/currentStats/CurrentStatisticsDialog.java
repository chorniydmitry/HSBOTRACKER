package chernyj.swing.dialogs.currentStats;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chernyj.swing.utils.ImagePanel;
import chernyj.utils.Constants;
import chernyj.utils.Resources;
import model.Card;
/**
 * @author Chernyj Dmitry
 *
 */
public class CurrentStatisticsDialog extends JDialog {
	private static final long serialVersionUID = 3413483742906843008L;

	private static final String LBL_TOTAL_DUST_CAPT = "Всего пыли";
	private static final String LBL_TOTAL_DISENCHANT_CAPT = "Всего лишней пыли";
	private static final String LBL_CURR_DUST_CAPT = "Пыли в текущем";
	private static final String LBL_BEST_BOOSTER_CAPT = "Лучший пак";
	private static final String LBL_GEM = "Редк";
	private static final String LBL_TOTAL = "Всего";
	private static final String LBL_DUBLES = "Лишних";
	private static final String LBL_BOOSER_NUM_CAPT = "Пак №";
	private static final String LBL_BOOSER_AFTER_LEG_CAPT = "Пак после леги №";

	private static final int XPOS = 1200;
	private static final int YPOS = 300;

	private ImagePanel mainPanel;
	private ImagePanel cardsRarityPanel;

	private JLabel lblTotalDustText = new JLabel(LBL_TOTAL_DUST_CAPT);
	private JLabel lblTotalDustIcon = new JLabel();
	private JLabel lblTotalDust = new JLabel();

	private JLabel lblTotalDisenchandText = new JLabel(LBL_TOTAL_DISENCHANT_CAPT);
	private JLabel lblTotalDisenchantIcon = new JLabel();
	private JLabel lblTotalDisenchantValue = new JLabel();

	private JLabel lblCurrDustText = new JLabel(LBL_CURR_DUST_CAPT);
	private JLabel lblCurrDustIcon = new JLabel();
	private JLabel lblCurrDustValue = new JLabel();

	private JLabel lblBestBoosterInfo = new JLabel(LBL_BEST_BOOSTER_CAPT);
	private JLabel lblBestBoosterNumValue = new JLabel();
	private JLabel lblBestBoosteDustIcon = new JLabel();
	private JLabel lblBestBoosterValue = new JLabel();

	private JLabel lblLegendarySuccessRateValue = new JLabel(" ");

	private JLabel lblGem = new JLabel(LBL_GEM);
	private JLabel lblTotal = new JLabel(LBL_TOTAL);
	private JLabel lblDubles = new JLabel(LBL_DUBLES);

	private JLabel lblBusterNumText = new JLabel(LBL_BOOSER_NUM_CAPT);
	private JLabel lblBusterNumValue = new JLabel();
	private JLabel lblBusterIcon = new JLabel();

	private JLabel lblBusterAfterLegNumText = new JLabel(LBL_BOOSER_AFTER_LEG_CAPT);
	private JLabel lblBusterAfterLegNumValue = new JLabel();

	private ArrayList<JLabel> lblsTextList;
	private ArrayList<JLabel> lblsValuesList;
	private ArrayList<JLabel> lblsDuplicatesList;

	private ArrayList<JLabel> lblsGoldenTextList;
	private ArrayList<JLabel> lblsGoldenValuesList;
	private ArrayList<JLabel> lblsGoldenDuplicatesList;

	private Icon icons[] = { Resources.RARITY_COMMON, Resources.RARITY_RARE, Resources.RARITY_EPIC,
			Resources.RARITY_LEGENDARY };

	private Icon gicons[] = { Resources.RARITY_GCOMMON, Resources.RARITY_GRARE, Resources.RARITY_GEPIC,
			Resources.RARITY_GLEGENDARY };

	public CurrentStatisticsDialog(int width, int height, String title) {
		this.initFrame(width, height);
		this.setTitle(title);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		initPanel();
		initComponents();
		layoutCardsRarityPanel();
		layoutPanel();
		colorLabels();
		
		this.add(mainPanel);
	}
	
	private void layoutCardsRarityPanel() {
		cardsRarityPanel = new ImagePanel();
		cardsRarityPanel.setLayout(new GridBagLayout());
		cardsRarityPanel.setBackground(Constants.FULL_TRANSPARENT_COLOR);

		cardsRarityPanel.add(lblGem, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(20, 10, 10, 1), 0, 0));

		cardsRarityPanel.add(lblTotal, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(20, 10, 10, 1), 0, 0));

		cardsRarityPanel.add(lblDubles, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(20, 10, 10, 1), 0, 0));

		for (int index = 1; index <= Constants.RARITIES_AMOUNT; index++) {
			int goldIndex = index + Constants.RARITIES_AMOUNT;

			cardsRarityPanel.add(lblsTextList.get(index - 1), new GridBagConstraints(0, index, 1, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1, 10, 1, 1), 0, 0));

			cardsRarityPanel.add(lblsValuesList.get(index - 1), new GridBagConstraints(1, index, 1, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1, 10, 1, 1), 0, 0));

			cardsRarityPanel.add(lblsDuplicatesList.get(index - 1), new GridBagConstraints(2, index, 1, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1, 10, 1, 1), 0, 0));

			cardsRarityPanel.add(lblsGoldenTextList.get(index - 1), new GridBagConstraints(0, goldIndex, 1, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 10, 1, 1), 0, 0));

			cardsRarityPanel.add(lblsGoldenValuesList.get(index - 1), new GridBagConstraints(1, goldIndex, 1, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1, 10, 1, 1), 0, 0));

			cardsRarityPanel.add(lblsGoldenDuplicatesList.get(index - 1), new GridBagConstraints(2, goldIndex, 1, 1, 0,
					0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1, 10, 1, 1), 0, 0));

			cardsRarityPanel.setVisible(true);

		}
		JPanel pnlBlank = new JPanel();
		pnlBlank.setBackground(Constants.FULL_TRANSPARENT_COLOR);
		
		cardsRarityPanel.add(pnlBlank, new GridBagConstraints(0, (Constants.RARITIES_AMOUNT * 2 + 1), GridBagConstraints.REMAINDER, 1, 0,
					0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 1), 0, 0));

	}

	private void initFrame(int width, int height) {
		this.setSize(new Dimension(width, height));
		this.setLocation(XPOS, YPOS);

		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);


		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}

	private void initPanel() {
		mainPanel = new ImagePanel();
		mainPanel.setBackground(Constants.CONTENT_PANE_DARKER_COLOR);
	}

	private void layoutPanel() {
		mainPanel.setLayout(new GridBagLayout());
//		  int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor,
//        int fill,  Insets insets, int ipadx, int ipady)

		mainPanel.add(lblBusterIcon, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(10, 1, 1, 1), 0, 0));

		mainPanel.add(lblBusterNumText, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

		mainPanel.add(lblBusterNumValue, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
//	2 ряд	
		mainPanel.add(lblBusterAfterLegNumText, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

		mainPanel.add(lblBusterAfterLegNumValue, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
// 3 ряд
		mainPanel.add(lblCurrDustText, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

		mainPanel.add(lblCurrDustValue, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

		mainPanel.add(lblCurrDustIcon, new GridBagConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
// 4 ряд
		mainPanel.add(lblBestBoosterInfo, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

		mainPanel.add(lblBestBoosterNumValue, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

		mainPanel.add(lblBestBoosterValue, new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

		mainPanel.add(lblBestBoosteDustIcon, new GridBagConstraints(3, 3, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
// 5 ряд
		mainPanel.add(lblLegendarySuccessRateValue, new GridBagConstraints(0, 4, GridBagConstraints.REMAINDER, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

// 6 ряд
		mainPanel.add(cardsRarityPanel, new GridBagConstraints(0, 5, GridBagConstraints.REMAINDER, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
// предпоследний ряд
		mainPanel.add(lblTotalDustText, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

		mainPanel.add(lblTotalDust, new GridBagConstraints(2, 6, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

		mainPanel.add(lblTotalDustIcon, new GridBagConstraints(3, 6, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

// последний ряд
		mainPanel.add(lblTotalDisenchandText, new GridBagConstraints(0, 7, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

		mainPanel.add(lblTotalDisenchantValue, new GridBagConstraints(2, 7, 1, 1, 0, 0, GridBagConstraints.NORTHEAST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

		mainPanel.add(lblTotalDisenchantIcon, new GridBagConstraints(3, 7, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

	}

	private void colorLabels() {
		lblBusterNumText.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);
		lblBusterNumValue.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);
		lblBusterIcon.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);

		lblBusterAfterLegNumValue.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);
		lblBusterAfterLegNumText.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);

		lblTotalDustText.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);
		lblTotalDust.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);

		lblTotalDisenchandText.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);
		lblTotalDisenchantValue.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);

		lblCurrDustText.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);
		lblCurrDustValue.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);

		lblBestBoosterInfo.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);
		lblBestBoosterNumValue.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);
		lblBestBoosterValue.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);

		lblLegendarySuccessRateValue.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);

		lblGem.setForeground(Constants.MAIN_FONT_COLOR);
		lblTotal.setForeground(Constants.MAIN_FONT_COLOR);
		lblDubles.setForeground(Constants.MAIN_FONT_COLOR);

		lblBusterNumText.setFont(Constants.DEFAULT_FONT_BIGGER);
		lblBusterNumValue.setFont(Constants.DEFAULT_FONT_BIGGER);

		lblBusterAfterLegNumText.setFont(Constants.DEFAULT_FONT);
		lblBusterAfterLegNumValue.setFont(Constants.DEFAULT_FONT);

		lblTotalDustText.setFont(Constants.DEFAULT_FONT);
		lblTotalDust.setFont(Constants.DEFAULT_FONT);

		lblTotalDisenchandText.setFont(Constants.DEFAULT_FONT);
		lblTotalDisenchantValue.setFont(Constants.DEFAULT_FONT);

		lblCurrDustText.setFont(Constants.DEFAULT_FONT);
		lblCurrDustValue.setFont(Constants.DEFAULT_FONT);

		lblBestBoosterInfo.setFont(Constants.DEFAULT_FONT);
		lblBestBoosterNumValue.setFont(Constants.DEFAULT_FONT);
		lblBestBoosterValue.setFont(Constants.DEFAULT_FONT);

		lblLegendarySuccessRateValue.setFont(Constants.DEFAULT_FONT);

		lblGem.setFont(Constants.DEFAULT_FONT);
		lblTotal.setFont(Constants.DEFAULT_FONT);
		lblDubles.setFont(Constants.DEFAULT_FONT);

		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++) {
			Color color = Constants.COLOR_FOR_RARITY[i];
			
			lblsTextList.get(i).setForeground(color);
			lblsValuesList.get(i).setForeground(color);
			lblsDuplicatesList.get(i).setForeground(color);

			lblsGoldenTextList.get(i).setForeground(color);
			lblsGoldenValuesList.get(i).setForeground(color);
			lblsGoldenDuplicatesList.get(i).setForeground(color);

			lblsTextList.get(i).setFont(Constants.DEFAULT_FONT);
			lblsValuesList.get(i).setFont(Constants.DEFAULT_FONT);
			lblsDuplicatesList.get(i).setFont(Constants.DEFAULT_FONT);

			lblsGoldenTextList.get(i).setFont(Constants.DEFAULT_FONT);
			lblsGoldenValuesList.get(i).setFont(Constants.DEFAULT_FONT);
			lblsGoldenDuplicatesList.get(i).setFont(Constants.DEFAULT_FONT);
		}
	}

	private void initComponents() {
		lblsTextList = new ArrayList<JLabel>(Constants.RARITIES_AMOUNT);
		lblsValuesList = new ArrayList<JLabel>(Constants.RARITIES_AMOUNT);
		lblsDuplicatesList = new ArrayList<JLabel>(Constants.RARITIES_AMOUNT);

		lblsGoldenTextList = new ArrayList<JLabel>(Constants.RARITIES_AMOUNT);
		lblsGoldenValuesList = new ArrayList<JLabel>(Constants.RARITIES_AMOUNT);
		lblsGoldenDuplicatesList = new ArrayList<JLabel>(Constants.RARITIES_AMOUNT);

		lblCurrDustIcon.setIcon(Resources.DUST_ICON);
		lblBestBoosteDustIcon.setIcon(Resources.DUST_ICON);
		lblTotalDustIcon.setIcon(Resources.DUST_ICON);
		lblTotalDisenchantIcon.setIcon(Resources.DUST_ICON);

		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++) {
			JLabel iconLabel = new JLabel();
			iconLabel.setIcon(icons[i]);
			lblsTextList.add(iconLabel);
			lblsValuesList.add(new JLabel());
			lblsDuplicatesList.add(new JLabel());

			JLabel gIconLabel = new JLabel();
			gIconLabel.setIcon(gicons[i]);
			lblsGoldenTextList.add(gIconLabel);
			lblsGoldenValuesList.add(new JLabel());
			lblsGoldenDuplicatesList.add(new JLabel());
		}
	}

	public JLabel getLblTotalDust() {
		return lblTotalDust;
	}

	public void setLblTotalDust(JLabel lblTotalDust) {
		this.lblTotalDust = lblTotalDust;
	}

	public JLabel getLblCurrDust() {
		return lblCurrDustValue;
	}

	public void setLblCurrDust(JLabel lblCurrDust) {
		this.lblCurrDustValue = lblCurrDust;
	}

	public JLabel getLblBestBoosterInfo() {
		return lblBestBoosterInfo;
	}

	public void setLblBestBoosterInfo(JLabel lblBestBoosterInfo) {
		this.lblBestBoosterInfo = lblBestBoosterInfo;
	}

	public ArrayList<JLabel> getLblsValuesList() {
		return lblsValuesList;
	}

	public void setLblsValuesList(ArrayList<JLabel> lblsValuesList) {
		this.lblsValuesList = lblsValuesList;
	}

	public ArrayList<JLabel> getLblsDuplicatesList() {
		return lblsDuplicatesList;
	}

	public void setLblsDuplicatesList(ArrayList<JLabel> lblsDuplicatesList) {
		this.lblsDuplicatesList = lblsDuplicatesList;
	}

	public ArrayList<JLabel> getLblsGoldenValuesList() {
		return lblsGoldenValuesList;
	}

	public void setLblsGoldenValuesList(ArrayList<JLabel> lblsGoldenValuesList) {
		this.lblsGoldenValuesList = lblsGoldenValuesList;
	}

	public ArrayList<JLabel> getLblsGoldenDuplicatesList() {
		return lblsGoldenDuplicatesList;
	}

	public void setLblsGoldenDuplicatesList(ArrayList<JLabel> lblsGoldenDuplicatesList) {
		this.lblsGoldenDuplicatesList = lblsGoldenDuplicatesList;
	}

	public ImagePanel getMainPanel() {
		return mainPanel;
	}

	public JLabel getLblBestBoosterValue() {
		return lblBestBoosterValue;
	}

	public void setLblBestBoosterValue(JLabel lblBestBoosterValue) {
		this.lblBestBoosterValue = lblBestBoosterValue;
	}

	public JLabel getLblBusterNumValue() {
		return lblBusterNumValue;
	}

	public void setLblBusterNumValue(JLabel lblBusterNumValue) {
		this.lblBusterNumValue = lblBusterNumValue;
	}

	public JLabel getLblBusterIcon() {
		return lblBusterIcon;
	}

	public void setLblBusterIcon(JLabel lblBusterIcon) {
		this.lblBusterIcon = lblBusterIcon;
	}

	public JLabel getLblTotalDisenchantValue() {
		return lblTotalDisenchantValue;
	}

	public void setLblTotalDisenchantValue(JLabel lblTotalDisenchantValue) {
		this.lblTotalDisenchantValue = lblTotalDisenchantValue;
	}

	public JLabel getLblBestBoosterNumValue() {
		return lblBestBoosterNumValue;
	}

	public void setLblBestBoosterNumValue(JLabel lblBestBoosterNumValue) {
		this.lblBestBoosterNumValue = lblBestBoosterNumValue;
	}

	public JLabel getLblLegendarySuccessRateValue() {
		return lblLegendarySuccessRateValue;
	}

	public void setLblLegendarySuccessRateValue(JLabel lblLegendarySuccessRateValue) {
		this.lblLegendarySuccessRateValue = lblLegendarySuccessRateValue;
	}

	public JLabel getLblBusterAfterLegNumValue() {
		return lblBusterAfterLegNumValue;
	}

	public void setLblBusterAfterLegNumValue(JLabel lblBusterAfterLegNumValue) {
		this.lblBusterAfterLegNumValue = lblBusterAfterLegNumValue;
	}

	public ImagePanel getCardsRarityPanel() {
		return cardsRarityPanel;
	}


}
