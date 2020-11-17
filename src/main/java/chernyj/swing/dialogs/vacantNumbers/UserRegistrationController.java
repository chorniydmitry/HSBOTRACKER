package chernyj.swing.dialogs.vacantNumbers;
/**
 * @author Chernyj Dmitry
 *
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import chernyj.utils.Utils;
import model.Contestant;
import observer.RegisterObserver;
//FIXME: при указании числа, выходящего за границы выбивает ошибку, новое число не добавляется
public class UserRegistrationController implements RegisterObserver {
	private static final int FIRST_LEG_GUARANTEED_IN = 10;
	private static final Color COLOR_DISABLED = new Color(0x555555);
	private static final Color COLOR_VACANT = new Color(0xffffff);
	
	private static final String VACANT = "Свободны";
	private static final String DISABLED = "Запрещены";
	
	private int amountOfPacks;
	private int amountOfNums;
	private boolean firstTenAllowed;
	private UserRegistrationDialog dialog;

	private ArrayList<Contestant> contestants = new ArrayList<>();

	public UserRegistrationController(UserRegistrationDialog vacantNumbersDialog, int amountOfPaks, int amountOfNums,
			boolean firstTenAllowed) {
		this.amountOfPacks = amountOfPaks;
		this.amountOfNums = amountOfNums;
		this.dialog = vacantNumbersDialog;
		this.firstTenAllowed = firstTenAllowed;
		
		if(!firstTenAllowed) {
			ArrayList<Integer> firstTenArr = new ArrayList<>();
			for(int i = 0; i < FIRST_LEG_GUARANTEED_IN; i++) 
				firstTenArr.add(i+1);
			
			dialog.addLegend(DISABLED, COLOR_DISABLED);
			dialog.setNumsForLegend(firstTenArr, DISABLED);
		}
		
		dialog.addLegend(VACANT, COLOR_VACANT);
		dialog.updateLegend();

	}
	
	public void showDialog(boolean action) {
		dialog.setVisible(action);
	}
	
	public boolean isDialogShowing() {
		return dialog.isVisible();
	}

	private void addNumbers(ArrayList<Integer> to, ArrayList<Integer> from) {

		for (Integer newNum : from) {
			// Проверяем, остались ли свободные номера
			if(checkIfAllTaken())
				return;
			
			// Eсли нужно сгенерировать новое случайное число
			if(newNum == 0) 
				newNum = generateRandomNum();
			
			// Проверяем, занят ли уже номер
			if(dialog.checkIfColored(newNum))
				continue;
			// Проверям, учитываются ли первые 10 паков, и выбрал ли пользователь число,
			// которое в них входит
			if ((newNum <= FIRST_LEG_GUARANTEED_IN) && !firstTenAllowed)
				continue;
			// Если пользователь выбрал еще не все числа
			if (to.size() < amountOfNums) {
				//Если пользователь вводит правильные числа
				if(newNum > 0 && newNum <= amountOfPacks)
					to.add(newNum);
				else continue;
			} else
				return;
		}

	}
	
	private boolean checkIfAllTaken() {
		for(int i = 1; i <= amountOfPacks; i++) {
			if(!dialog.checkIfColored(i))
				return false;
		}
		return true;
	}
	
	private int generateRandomNum() {
		Random rnd = new Random();
		
		while(true) {
			int num = 1 + rnd.nextInt(amountOfPacks);
			boolean isTaken = false;
			
			for (Contestant contestant : contestants) 
				for (Integer numberTaken : contestant.getNumbersChosenList()) 
					if(num == numberTaken)
						isTaken = true;

			if(!isTaken)
				return num;
		}
	}
	
	@Override
	public void update(String userName, ArrayList<Integer> numbers) {

		for (Contestant user : contestants) {
		
			String uName = user.getNick();
			ArrayList<Integer> uNumbers = user.getNumbersChosenList();

			// Если уже зарегистрирован
			if (uName.equalsIgnoreCase(userName)) {
				addNumbers(uNumbers, numbers);
				dialog.setNumsForLegend(uNumbers, uName);
				return;
			}
		}
		//Если не зарегистрирован
			Color color = Utils.getRandomColor();
			contestants.add(new Contestant(userName, new ArrayList<Integer>(), color));
			dialog.addLegend(userName, color);
			dialog.updateLegend();
			update(userName, numbers);
		
	}

	public ArrayList<Contestant> getRegisteredUsersList() {
		return contestants;
	}

}
