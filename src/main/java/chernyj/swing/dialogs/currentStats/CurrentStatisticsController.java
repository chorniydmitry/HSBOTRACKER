package chernyj.swing.dialogs.currentStats;

import java.awt.Image;

import javax.swing.ImageIcon;

import chernyj.swing.utils.ImagePanel;
import chernyj.utils.BoosterOpenedAnalyser;
import chernyj.utils.Constants;
import chernyj.utils.Resources;
import model.Card;
import model.Expansion;
import observer.ButtonReadyObserver;
/**
 * @author Chernyj Dmitry
 *
 */
public class CurrentStatisticsController implements ButtonReadyObserver {
	private CurrentStatisticsDialog dialog;

	private BoosterOpenedAnalyser boAnalyser;
	private int lastUpdatedBooster = 0;

	public CurrentStatisticsController(CurrentStatisticsDialog frame, BoosterOpenedAnalyser boAnalyser) {
		this.dialog = frame;
		this.boAnalyser = boAnalyser;
		fillBG();
		fillRaritiesPanel();
	}



	private void setCurrentIcon(Expansion expansion) {
		ImageIcon imIcon = new ImageIcon(Resources.class.getResource(expansion.getLogoPath()));
		dialog.getLblBusterIcon().setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(150, 90, Image.SCALE_DEFAULT)));
	}
	
	public void updateFrame() {
		// Если не открыт новый пак, обновлять и перерисовывать форму не нужно 
		if(lastUpdatedBooster == boAnalyser.getCurrentBoosterNum())
			return;
		
		fillBG();
		fillRaritiesPanel();
		
		setCurrentIcon(boAnalyser.getCurrentBoosterExp());
		dialog.getLblBusterNumValue().setText(String.valueOf(boAnalyser.getCurrentBoosterNum()));
		
		dialog.getLblBusterAfterLegNumValue().setText(String.valueOf(boAnalyser.getCurrentBoosterNumAfterLeg()));
		
		dialog.getLblCurrDust().setText(String.valueOf(boAnalyser.getCurrentBoosterDust()));
		
		dialog.getLblBestBoosterNumValue().setText(String.valueOf(boAnalyser.getBestBoosterNum()));
		dialog.getLblBestBoosterValue().setText(String.valueOf(boAnalyser.getBestBoosterDust()));
		
		for (int i = 0; i < Constants.RARITIES_AMOUNT; i++) {
			Card.Rarity rarity = Card.Rarity.values()[i];
			dialog.getLblsValuesList().get(i).setText(String.valueOf(boAnalyser.getAmounts(rarity, false)));
			dialog.getLblsDuplicatesList().get(i).setText(String.valueOf(boAnalyser.getDuplicates(rarity, false)));
			dialog.getLblsGoldenValuesList().get(i).setText(String.valueOf(boAnalyser.getAmounts(rarity, true)));
			dialog.getLblsGoldenDuplicatesList().get(i).setText(String.valueOf(boAnalyser.getDuplicates(rarity, true)));
		}
		
		dialog.getLblTotalDust().setText(String.valueOf(boAnalyser.getTotalDust()));
		dialog.getLblTotalDisenchantValue().setText(String.valueOf(boAnalyser.getTotalDustForDuplicates()));
		
		dialog.getLblLegendarySuccessRateValue().setText(boAnalyser.getLegendarySuccessRateString());

		dialog.getMainPanel().validate();
		dialog.getMainPanel().revalidate();
		dialog.getMainPanel().repaint();
		
		lastUpdatedBooster = boAnalyser.getCurrentBoosterNum();
	}
	
	private void fillBG() {
		ImagePanel mainPanel = dialog.getMainPanel();
		mainPanel.setImage(Resources.BACKGROUND_IMAGE);
	}
	
	private void fillRaritiesPanel() {
		dialog.getCardsRarityPanel().setImage(Resources.BACKGROUND_PAPIRUS);
	}

	public void showDialog() {
		dialog.setVisible(true);
	}

	@Override
	public void update() {
		updateFrame();
	}

}
