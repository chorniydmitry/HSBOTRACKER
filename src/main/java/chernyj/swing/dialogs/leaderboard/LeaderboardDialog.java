package chernyj.swing.dialogs.leaderboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;

import chernyj.utils.Constants;
import chernyj.utils.Utils;
import model.Contestant;
/**
 * @author Chernyj Dmitry
 *
 */
public class LeaderboardDialog extends JDialog {
	private static final long serialVersionUID = 4720657380576785459L;
	
	private ArrayList<JLabel> userName = new ArrayList<>();
	private ArrayList<JLabel> userScore = new ArrayList<>();
	private ArrayList<Color> userColor = new ArrayList<>();
	
	private int amountOfLines = 0;
	
	public LeaderboardDialog(int width, int height, String title) {
		this.setSize(new Dimension(width, height));
		this.setTitle(title);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setAlwaysOnTop(true);
	
		layoutUsersPanel();
	}
	
	private void layoutUsersPanel() {
		if(userName.size() == 0)
			return;
		getContentPane().removeAll();
		getContentPane().setLayout(new GridLayout(userName.size(), 2));
		
		for (int i = 0; i < userName.size() ; i++) {
			add(userScore.get(i));
			add(userName.get(i));
			
			userScore.get(i).setOpaque(true);
			userName.get(i).setOpaque(true);
						
			userName.get(i).setForeground(Utils.getForeground(userColor.get(i)));
			userScore.get(i).setForeground(Utils.getForeground(userColor.get(i)));
			
			userScore.get(i).setBackground(userColor.get(i));
			userName.get(i).setBackground(userColor.get(i));
		}
		
		amountOfLines = userName.size();
		
		repaint();
		revalidate();
		//pack();
	}
	
	private void update() {
		layoutUsersPanel();
	}
	
	public void updateLabels(ArrayList<Contestant> contestants) {
		userName = new ArrayList<>();
		userScore = new ArrayList<>();
		userColor = new ArrayList<>();
		
		for (Contestant contestant: contestants) {
			JLabel lblName  = new JLabel(contestant.getNick());
			JLabel lblScore = new JLabel(String.valueOf(contestant.getScore())); 
			lblName.setFont(Constants.DEFAULT_FONT);
			lblScore.setFont(Constants.DEFAULT_FONT);
			userName.add(lblName);
			userScore.add(lblScore);
			userColor.add(contestant.getColor());
		}
		
		update();
	}

	public int getAmountOfLines() {
		return amountOfLines;
	}

}
