package chernyj.swing.dialogs.leaderboard;

import java.util.ArrayList;
import java.util.Collections;

import chernyj.utils.BoosterOpenedAnalyser;
import model.Contestant;
import observer.ButtonReadyObserver;

/**
 * @author Chernyj Dmitry
 *
 */
public class LeaderboardController implements ButtonReadyObserver {
	
	private LeaderboardDialog dialog;
	private ArrayList<Contestant> contestants;
	private BoosterOpenedAnalyser boAnalyser;
	private int lastUpdatedForPack = -1;
	
	public LeaderboardController(LeaderboardDialog dialog, ArrayList<Contestant> contestants, BoosterOpenedAnalyser boAnalyser) {
		this.dialog = dialog;
		this.contestants = contestants;
		this.boAnalyser = boAnalyser;
		update();
	}

	@Override
	public void update() {
		//NOT TESTED
		if(lastUpdatedForPack == boAnalyser.getCurrentBoosterNum())
			return;
		
		for (Contestant contestant : contestants) {
			
			if(contestant.getNumbersChosenList() == null || 
					contestant.getNumbersChosenList().size() == 0)
				contestant.setScore(0);
			else
				contestant.setScore(boAnalyser.getDustForIndexes(contestant.getNumbersChosenList()));
		}
		
		ArrayList<Contestant> sortedContestants = sortContestants();

		dialog.updateLabels(sortedContestants);
		
		//NOT TESTED
		lastUpdatedForPack = boAnalyser.getCurrentBoosterNum();
	}

	private ArrayList<Contestant> sortContestants() {
		ArrayList<Integer> contestantsScores = new ArrayList<>();
		ArrayList<Contestant> sortedContestants = new ArrayList<>();
		
		for (Contestant contestant : contestants) 
			contestantsScores.add(contestant.getScore());
		
		Collections.sort(contestantsScores);
		Collections.reverse(contestantsScores);
		
		for (Integer score : contestantsScores)
			for(Contestant contestant : contestants) 
				if(contestant.getScore() == score)
					if(sortedContestants.indexOf(contestant) == -1)
						sortedContestants.add(contestant);
		
		return sortedContestants;
	}
	
	public void showDialog() {
		dialog.setVisible(true);
		
	}

}
