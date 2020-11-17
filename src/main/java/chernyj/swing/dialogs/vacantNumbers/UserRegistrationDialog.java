package chernyj.swing.dialogs.vacantNumbers;
/**
 * @author Chernyj Dmitry
 *
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chernyj.utils.Utils;

public class UserRegistrationDialog extends JDialog {
	
	private static final long serialVersionUID = 662697834131128142L;
	private static final int NUMBERS_IN_ROW = 10;
	
	private JPanel pnlNumbers = new JPanel();
	private JPanel pnlLegend = new JPanel();
	
	private JLabel[][] lblsNums;
	private List<JLabel> lblsLegend = new ArrayList<>();
	private int amountOfPacks;
	

	public UserRegistrationDialog(int width, int height, String title, int amountOfPacks) {
		this.setSize(new Dimension(width, height));
		this.setTitle(title);
		
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		
		this.amountOfPacks = amountOfPacks;
		
		initComponents();
		layoutNumbersPanel();
		layoutLegendPanel();
		
		this.add(pnlNumbers, BorderLayout.CENTER);
		this.add(pnlLegend, BorderLayout.EAST);
		
		this.setVisible(true);
	}

	private void layoutNumbersPanel() {
		lblsNums = new JLabel[NUMBERS_IN_ROW][(int)(amountOfPacks/NUMBERS_IN_ROW)+1];
		
		pnlNumbers.setLayout(new GridLayout(lblsNums[0].length, lblsNums.length ));
		
		int lblNum = 0;
		
		for(int y = 0; y < lblsNums[0].length; y++) {
			for(int x = 0; x < lblsNums.length; x++) {
				lblsNums[x][y] = new JLabel(String.valueOf(++lblNum));
				
				lblsNums[x][y].setHorizontalAlignment(JLabel.CENTER);
				lblsNums[x][y].setOpaque(true);
				
				lblsNums[x][y].setBackground(Color.WHITE);

				if(lblNum > amountOfPacks)
					lblsNums[x][y].setText("");
				
				pnlNumbers.add(lblsNums[x][y]);
			}
		}
	}
	
	public void updateLegend() {
		layoutLegendPanel();
	}
	
	private void layoutLegendPanel() {
		if(lblsLegend.size() == 0)
			return;
		pnlLegend.setLayout(new GridLayout(lblsLegend.size(), 0));
		
		for (JLabel lblLeg : lblsLegend) {
			pnlLegend.add(lblLeg);
		}
		
		pnlLegend.repaint();
		pnlLegend.revalidate();
		repaint();
		revalidate();
	}
	
	private Color getLegendColor(String legendName) {
		for (JLabel leg : lblsLegend)
			if(leg.getText().equalsIgnoreCase(legendName))
				return leg.getBackground();
		
		return null;
	}
	
	
	public void setNumForLegend(int num, String legendName) {
		Color background = getLegendColor(legendName);
		Color foreground = Utils.getForeground(background);
		getLblByNum(num).setBackground(background);
		getLblByNum(num).setForeground(foreground);
	}
	
	public void setNumsForLegend(ArrayList<Integer> nums, String legendName) {
		for (Integer num : nums)
			setNumForLegend(num, legendName);
	}
	
	public boolean checkIfColored(int num) {
		return getLblByNum(num).getBackground() == Color.WHITE ? false : true;
	}
	
	private JLabel getLblByNum(int num) {
		num-=1;
		int x = (num % (NUMBERS_IN_ROW));
		int y = Math.round(num / NUMBERS_IN_ROW);
		
		return lblsNums[x][y];
	}
	
	public void addLegend(String title, Color color) {
		JLabel newLabel = new JLabel(title);
		newLabel.setOpaque(true);
		newLabel.setBackground(color);
		newLabel.setForeground(Utils.getForeground(color));
		
		lblsLegend.add(newLabel);
	}
	
	public void removeLegend(String title) {
		//TODO
	}
	
	public void removeLegend(int index) {
		//TODO
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		
	}

}
