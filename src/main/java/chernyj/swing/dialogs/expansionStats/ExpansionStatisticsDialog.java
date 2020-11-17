package chernyj.swing.dialogs.expansionStats;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;

import chernyj.swing.utils.ImagePanel;
import chernyj.utils.Constants;
import model.Card;

/**
 * @author Chernyj Dmitry
 *
 */
public class ExpansionStatisticsDialog extends JDialog {
	private static final long serialVersionUID = -7306508262742602487L;

	public static final String CHART_OPENED_TEXT = "ОТКРЫТО";
	public static final String CHART_TOTAL_TEXT = "ВСЕГО";
	
	private static final int CHART_WIDTH = 150;
	private static final int CHART_HEIGHT = 150;

	private JLabel lblLogo = new JLabel();
	private JLabel lblExtensionCapt = new JLabel("Дополнение:");
	private JComboBox<String> cbExtension = new JComboBox<>();
	private JLabel lblOpenedForExpCapt = new JLabel("открыто паков:");
	private JLabel lblOpenedForExpVal = new JLabel();

	private JLabel lblRarityCapt = new JLabel("Редк.");
	private JLabel lblOpenedCapt = new JLabel("Открыто");
	private JLabel lblOpenedGoldenCaptUp = new JLabel("Открыто");
	private JLabel lblOpenedGoldenCaptDown = new JLabel("(зол.)");
	private JLabel lblOpenedOfCapt = new JLabel("Из");
	private JLabel lblMissingCapt = new JLabel("Нехават.");
	private JLabel lblDustForMissingCaptUp = new JLabel("Нужно");
	private JLabel lblDustForMissingCaptDown = new JLabel("пыли");
	private ArrayList<JLabel> lblsRarityNames = new ArrayList<>();
	private ArrayList<JLabel> lblsOpenedOfRarity = new ArrayList<>();
	private ArrayList<JLabel> lblsOpenedGoldOfRarity = new ArrayList<>();
	private ArrayList<JLabel> lblsTotalOfRarity = new ArrayList<>();
	private ArrayList<JLabel> lblsMissingOfRarity = new ArrayList<>();
	private ArrayList<JLabel> lblsDustNeededForRarity = new ArrayList<>();

	private JLabel lblDustForDisenchantCapt = new JLabel("Пыль за распыление копий:");
	private JLabel lblDustForDisenchantVal = new JLabel();

	private JLabel lblDustForGoldenCapt = new JLabel("Пыль за распыление золотых:");
	private JLabel lblDustForGoldenVal = new JLabel();

	private JPanel pnlLogo = new JPanel();
	private JPanel pnlUpper = new JPanel();
	private JPanel pnlLower = new JPanel();
	private JPanel pnlCenter = new JPanel();
	private JPanel pnlCharts = new JPanel();
	private ImagePanel mainPanel = new ImagePanel();
	
	private ArrayList<DefaultPieDataset> datasets = new ArrayList<>();
	
	public ExpansionStatisticsDialog(int width, int height, String title) {
		this.setSize(new Dimension(width, height));
		this.setTitle(title);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		this.add(mainPanel);

		initPanels();
		layoutDialog();

		this.setVisible(true);
	}

	private void initPanels() {
		initCenterPanel();
		initChartsPanel();
		initUpperPanel();
		initLowerPanel();
	}

	private void layoutDialog() {
		layoutUpperPanel();
		layoutLowerPanel();
		layoutCenterPanel();
		layoutLogoPanel();

		mainPanel.setLayout(new GridBagLayout());
		
		mainPanel.add(pnlLogo, new GridBagConstraints(0, 0, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
		
		mainPanel.add(pnlUpper, new GridBagConstraints(0, 1, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));

		mainPanel.add(pnlCenter, new GridBagConstraints(0, 2, GridBagConstraints.REMAINDER, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 1));

		mainPanel.add(pnlCharts,
				new GridBagConstraints(0, 3, GridBagConstraints.REMAINDER, 1, 1, 1,
						GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));

		mainPanel.add(pnlLower, new GridBagConstraints(0, 4, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1, 1));
	}
	
	
	private void layoutLogoPanel() {
		pnlLogo.add(lblLogo, BorderLayout.CENTER);
		pnlLogo.setBackground(Constants.FULL_TRANSPARENT_COLOR);
	}

	private void initUpperPanel() {
		ArrayList<JLabel> upperPanelLabels = new ArrayList<>();
		upperPanelLabels.add(lblExtensionCapt);
		upperPanelLabels.add(lblOpenedForExpCapt);
		upperPanelLabels.add(lblOpenedForExpVal);
		
		for (JLabel label : upperPanelLabels) {
			label.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);
			label.setFont(Constants.DEFAULT_FONT);
		}
		
		cbExtension.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);
		cbExtension.setFont(Constants.DEFAULT_FONT);

	}

	private void layoutUpperPanel() {
		pnlUpper.setBackground(Constants.DARK_TRANSPARENT_COLOR);
		pnlUpper.setLayout(new GridBagLayout());

		pnlUpper.add(lblExtensionCapt, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 1, 1));

		pnlUpper.add(cbExtension, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 1, 1));

		pnlUpper.add(lblOpenedForExpCapt, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 1, 1));

		pnlUpper.add(lblOpenedForExpVal, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 1, 1));

		pnlUpper.setVisible(true);
	}

	private void initLowerPanel() {
		ArrayList<JLabel> lowerPanelLabels = new ArrayList<>();
		lowerPanelLabels.add(lblDustForDisenchantCapt);
		lowerPanelLabels.add(lblDustForDisenchantVal);
		lowerPanelLabels.add(lblDustForGoldenCapt);
		lowerPanelLabels.add(lblDustForGoldenVal);
		
		for (JLabel label : lowerPanelLabels) {
			label.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);
			label.setFont(Constants.DEFAULT_FONT);
		}
	}

	private void layoutLowerPanel() {
		pnlLower.setBackground(Constants.DARK_TRANSPARENT_COLOR);
		pnlLower.setLayout(new GridBagLayout());
		
		pnlLower.add(lblDustForDisenchantCapt, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 1, 1));
		
		pnlLower.add(lblDustForDisenchantVal, new GridBagConstraints(1, 0, 1, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));
		
		pnlLower.add(lblDustForGoldenCapt, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 1, 1));
		
		pnlLower.add(lblDustForGoldenVal, new GridBagConstraints(1, 1, 1, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 1, 1));

		pnlLower.setVisible(true);
	}

	private void initChartsPanel() {
		pnlCharts.setBackground(Constants.DARK_TRANSPARENT_COLOR);
		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++) {
			DefaultPieDataset dataset = new DefaultPieDataset();
			dataset.setValue(CHART_TOTAL_TEXT, 0);
			dataset.setValue(CHART_OPENED_TEXT, 0);

			datasets.add(dataset);

			JFreeChart chart = ChartFactory.createPieChart(Card.Rarity.values()[i].toString(), dataset, false, false,
					false);

			chart.getTitle().setPaint(Constants.MAIN_FONT_LIGHT_COLOR);
			chart.getTitle().setFont(Constants.DEFAULT_FONT_BIGGER);
			Plot plot = chart.getPlot();
			plot.setBackgroundPaint(Constants.FULL_TRANSPARENT_COLOR);
			((PiePlot) plot).setSectionPaint(CHART_OPENED_TEXT, Constants.COLOR_FOR_RARITY[i]);
			((PiePlot) plot).setSectionPaint(CHART_TOTAL_TEXT, Color.LIGHT_GRAY);
			
			((PiePlot) chart.getPlot()).setLabelGenerator(null);
			plot.setOutlinePaint(null);

			chart.setBackgroundPaint(Constants.FULL_TRANSPARENT_COLOR);
			ChartPanel cp = new ChartPanel(chart);
			cp.setBackground(Constants.FULL_TRANSPARENT_COLOR);

			cp.setPreferredSize(new Dimension(CHART_WIDTH, CHART_HEIGHT));
			
			pnlCharts.add(cp);
			
		}

	}
	
	private void initCenterPanel() {
		pnlCenter.setBackground(Constants.DARK_TRANSPARENT_COLOR);
		
		ArrayList<JLabel> headerLabels = new ArrayList<>();

		headerLabels.add(lblRarityCapt);
		headerLabels.add(lblOpenedCapt);
		headerLabels.add(lblOpenedGoldenCaptUp);
		headerLabels.add(lblOpenedGoldenCaptDown);
		headerLabels.add(lblOpenedOfCapt);
		headerLabels.add(lblMissingCapt);
		headerLabels.add(lblDustForMissingCaptUp);
		headerLabels.add(lblDustForMissingCaptDown);
		
		
		for (JLabel label : headerLabels) {
			label.setForeground(Constants.MAIN_FONT_LIGHT_COLOR);
			label.setFont(Constants.DEFAULT_FONT);
		}
		
		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++) {
			lblsRarityNames.add(new JLabel());
			lblsOpenedOfRarity.add(new JLabel());
			lblsOpenedGoldOfRarity.add(new JLabel());
			lblsTotalOfRarity.add(new JLabel());
			lblsMissingOfRarity.add(new JLabel());
			lblsDustNeededForRarity.add(new JLabel());

			lblsOpenedOfRarity.get(i).setForeground(Constants.COLOR_FOR_RARITY[i]);
			lblsOpenedGoldOfRarity.get(i).setForeground(Constants.COLOR_FOR_RARITY[i]);
			lblsTotalOfRarity.get(i).setForeground(Constants.COLOR_FOR_RARITY[i]);
			lblsMissingOfRarity.get(i).setForeground(Constants.COLOR_FOR_RARITY[i]);
			lblsDustNeededForRarity.get(i).setForeground(Constants.COLOR_FOR_RARITY[i]);

			lblsOpenedOfRarity.get(i).setFont(Constants.DEFAULT_FONT);
			lblsOpenedGoldOfRarity.get(i).setFont(Constants.DEFAULT_FONT);
			lblsTotalOfRarity.get(i).setFont(Constants.DEFAULT_FONT);
			lblsMissingOfRarity.get(i).setFont(Constants.DEFAULT_FONT);
			lblsDustNeededForRarity.get(i).setFont(Constants.DEFAULT_FONT);
		}

	}

	private void layoutCenterPanel() {
		pnlCenter.setLayout(new GridBagLayout());

		pnlCenter.add(lblRarityCapt, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		pnlCenter.add(lblOpenedCapt, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		pnlCenter.add(lblOpenedGoldenCaptUp, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		pnlCenter.add(lblOpenedOfCapt, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		pnlCenter.add(lblMissingCapt, new GridBagConstraints(4, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		pnlCenter.add(lblDustForMissingCaptUp, new GridBagConstraints(5, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		pnlCenter.add(lblOpenedGoldenCaptDown, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		pnlCenter.add(lblDustForMissingCaptDown, new GridBagConstraints(5, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++) {
			pnlCenter.add(lblsRarityNames.get(i), new GridBagConstraints(0, i + 2, 1, 1, 1, 1,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			pnlCenter.add(lblsOpenedOfRarity.get(i), new GridBagConstraints(1, i + 2, 1, 1, 1, 1,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			pnlCenter.add(lblsOpenedGoldOfRarity.get(i), new GridBagConstraints(2, i + 2, 1, 1, 1, 1,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			pnlCenter.add(lblsTotalOfRarity.get(i), new GridBagConstraints(3, i + 2, 1, 1, 1, 1,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			pnlCenter.add(lblsMissingOfRarity.get(i), new GridBagConstraints(4, i + 2, 1, 1, 1, 1,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			pnlCenter.add(lblsDustNeededForRarity.get(i), new GridBagConstraints(5, i + 2, 1, 1, 1, 1,
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
	}

	public JLabel getLblExtensionCapt() {
		return lblExtensionCapt;
	}

	public JLabel getLblOpenedForExpVal() {
		return lblOpenedForExpVal;
	}

	public ArrayList<JLabel> getLblsRarityNames() {
		return lblsRarityNames;
	}

	public ArrayList<JLabel> getLblsOpenedOfRarity() {
		return lblsOpenedOfRarity;
	}

	public ArrayList<JLabel> getLblsOpenedGoldOfRarity() {
		return lblsOpenedGoldOfRarity;
	}

	public ArrayList<JLabel> getLblsTotalOfRarity() {
		return lblsTotalOfRarity;
	}

	public ArrayList<JLabel> getLblsMissingOfRarity() {
		return lblsMissingOfRarity;
	}

	public ArrayList<JLabel> getLblsDustNeededForRarity() {
		return lblsDustNeededForRarity;
	}

	public JLabel getLblDustForDisenchantVal() {
		return lblDustForDisenchantVal;
	}

	public JLabel getLblDustForGoldenVal() {
		return lblDustForGoldenVal;
	}

	public JComboBox<String> getCbExtension() {
		return cbExtension;
	}

	public JLabel getLblLogo() {
		return lblLogo;
	}

	public ImagePanel getMainPanel() {
		return mainPanel;
	}

	public ArrayList<DefaultPieDataset> getDatasets() {
		return datasets;
	}

}
