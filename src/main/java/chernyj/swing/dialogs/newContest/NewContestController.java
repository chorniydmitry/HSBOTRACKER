package chernyj.swing.dialogs.newContest;

import java.util.ArrayList;

import chernyj.swing.dialogs.vacantNumbers.UserRegistrationController;
import chernyj.swing.utils.DialogBuilder;
import chernyj.utils.TwitchChatAnalyser;
import model.Contestant;
/**
 * @author Chernyj Dmitry
 *
 */
public class NewContestController {
	
	public enum State {
		REGISTRATION_NOT_STARTED, REGISTRATION_STARTED, REGISTRATION_FINISHED
	}
	
	private NewContestDialog dialog;
	private TwitchChatAnalyser twitch;
	private UserRegistrationController userRegController;
	private State state = State.REGISTRATION_NOT_STARTED;
	
	public NewContestController(NewContestDialog dialog) {
		this.dialog = dialog;
		setListeners();
	}
	
	private void setListeners() {
		dialog.getBtnBeginRegistration().addActionListener(l->doBeginRegistration());
		dialog.getBtnShowVacant().addActionListener(l->doShowVacant());
		dialog.getBtnFinishRegistration().addActionListener(l->doFinishRegistration());
	}
	
	private void doBeginRegistration() {
		twitch = new TwitchChatAnalyser();
		
		int amountOfPaks = (int)(dialog.getSpnAmount().getValue());
		int amountOfNums = (int)(dialog.getSpnGuessAmount().getValue());
		boolean firstTenAllowed = dialog.getCbAllowFirstTen().isSelected();
		
		userRegController = new DialogBuilder().getVacantNumbersController(amountOfPaks, amountOfNums, firstTenAllowed);
		twitch.register(userRegController);
		
		dialog.setUIFieldsBlocked(true);
		dialog.getBtnBeginRegistration().setEnabled(false);
		
		state = State.REGISTRATION_STARTED;
		dialog.getBtnFinishRegistration().setEnabled(true);
	}
	
	private void doShowVacant() {
		if(userRegController.isDialogShowing())
			return;
		userRegController.showDialog(true);

	}
	
	private void doFinishRegistration() {
		twitch.close();
		dialog.getBtnFinishRegistration().setEnabled(false);
		state = State.REGISTRATION_FINISHED;
		dialog.setVisible(false);
	}
	
	public State getState() {
		return state;
	}
	
	public ArrayList<Contestant> getContestants() {
		 return userRegController.getRegisteredUsersList();
	}
	
	public void showDialog() {
		dialog.setVisible(true);
	}

}
