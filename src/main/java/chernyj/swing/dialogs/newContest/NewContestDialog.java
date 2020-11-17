package chernyj.swing.dialogs.newContest;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
/**
 * @author Chernyj Dmitry
 *
 */
public class NewContestDialog extends JDialog {

	private static final long serialVersionUID = 4870353233582545311L;
	
	private static final String BTN_FINISH_REGISTRATION_CAPTION = "Завершить регистрацию";
	private static final String BTN_BEGIN_REGISTRATION_CAPTION = "Запустить регистрацию";
	private static final String BTN_SHOW_VACANT_CAPTION = "Показать сетку свободных номеров";
	private static final String CB_ALLOW_FIRST_TEN_CAPTION = "Первые 10 комплектов участвуют?";
	private static final String LBL_AMOUNT_CAPTION = "Количество паков";
	private static final String LBL_GUESS_AMOUNT_CAPTION = "Количество номеров на участника";
	
	private static final int PACK_GUESS_AMOUNT_DEF = 3;
	private static final int PACK_GUESS_AMOUNT_MIN = 1;
	private static final int PACK_GUESS_AMOUNT_MAX = 5;
	private static final int PACK_GUESS_AMOUNT_STEP = 1;
	
	private static final int PACK_AMOUNT_DEF = 50;
	private static final int PACK_AMOUNT_MIN = 2;
	private static final int PACK_AMOUNT_MAX = 1000;
	private static final int PACK_AMOUNT_STEP = 1;
	
	private JButton btnFinishRegistration = new JButton(BTN_FINISH_REGISTRATION_CAPTION);
	private JButton btnBeginRegistration = new JButton(BTN_BEGIN_REGISTRATION_CAPTION);
	private JButton btnShowVacant = new JButton(BTN_SHOW_VACANT_CAPTION);
	private JLabel lblAmount = new JLabel(LBL_AMOUNT_CAPTION);
	private JLabel lblGuessAmount = new JLabel(LBL_GUESS_AMOUNT_CAPTION);
	private JSpinner spnAmount = new JSpinner();
	private JSpinner spnGuessAmount = new JSpinner();
	private JCheckBox cbAllowFirstTen = new JCheckBox(CB_ALLOW_FIRST_TEN_CAPTION);
	
	public NewContestDialog(int width, int height, String title) {
		setSize(new Dimension(width, height));
		setTitle(title);
		
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		initComponents();
		layoutDialog();
		
		setVisible(true);
	}
	
	private void initComponents() {
		btnFinishRegistration.setEnabled(false);
		
		SpinnerModel modelAmount = new SpinnerNumberModel(PACK_AMOUNT_DEF, 
				PACK_AMOUNT_MIN, PACK_AMOUNT_MAX, PACK_AMOUNT_STEP);
		spnAmount.setModel(modelAmount);
		
		SpinnerModel modelGuess = new SpinnerNumberModel(PACK_GUESS_AMOUNT_DEF, 
				PACK_GUESS_AMOUNT_MIN, PACK_GUESS_AMOUNT_MAX, PACK_GUESS_AMOUNT_STEP);
		spnGuessAmount.setModel(modelGuess);
	}
	
	private void layoutDialog() {
		setLayout(new GridBagLayout());
		
		add(lblAmount, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(spnAmount, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		add(lblGuessAmount, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(spnGuessAmount, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		add(cbAllowFirstTen, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		add(btnBeginRegistration, new GridBagConstraints(0, 3, 2, 1, 0, 0, GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		add(btnShowVacant, new GridBagConstraints(0, 4, 2, 1, 0, 0, GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		add(btnFinishRegistration, new GridBagConstraints(0, 5, 2, 1, 0, 0, GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	}
	
	public void setUIFieldsBlocked(boolean action) {
		spnAmount.setEnabled(!action);
		spnGuessAmount.setEnabled(!action);
		cbAllowFirstTen.setEnabled(!action);
	}

	public JButton getBtnFinishRegistration() {
		return btnFinishRegistration;
	}

	public void setFinishRegistration(JButton btnFinishRegistration) {
		this.btnFinishRegistration = btnFinishRegistration;
	}

	public JButton getBtnBeginRegistration() {
		return btnBeginRegistration;
	}

	public void setBtnBeginRegistration(JButton btnBeginRegistration) {
		this.btnBeginRegistration = btnBeginRegistration;
	}

	public JButton getBtnShowVacant() {
		return btnShowVacant;
	}

	public void setBtnShowVacant(JButton btnShowVacant) {
		this.btnShowVacant = btnShowVacant;
	}

	public JSpinner getSpnAmount() {
		return spnAmount;
	}

	public void setSpnAmount(JSpinner spnAmount) {
		this.spnAmount = spnAmount;
	}

	public JSpinner getSpnGuessAmount() {
		return spnGuessAmount;
	}

	public void setSpnGuessAmount(JSpinner spnGuessAmount) {
		this.spnGuessAmount = spnGuessAmount;
	}

	public JCheckBox getCbAllowFirstTen() {
		return cbAllowFirstTen;
	}

	public void setCbAllowFirstTen(JCheckBox cbAllowFirstTen) {
		this.cbAllowFirstTen = cbAllowFirstTen;
	}
}
