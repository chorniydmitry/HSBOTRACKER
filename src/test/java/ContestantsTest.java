import java.util.ArrayList;

import model.Contestant;
import chernyj.swing.dialogs.leaderboard.LeaderboardController;
import chernyj.swing.utils.DialogBuilder;
import chernyj.utils.Utils;

public class ContestantsTest {
	
	
	public static void main(String[] args) throws InterruptedException {
		ArrayList<Contestant> contestants = new ArrayList<>();
		ArrayList<Integer> cont1Nums = new ArrayList<>();
		cont1Nums.add(1);
		cont1Nums.add(2);
		cont1Nums.add(3);
		Contestant contestant1 = new Contestant("JohnCinema", cont1Nums, Utils.getRandomColor());
		contestant1.setScore(100);
		
		
		ArrayList<Integer> cont2Nums = new ArrayList<>();
		cont1Nums.add(12);
		cont1Nums.add(4);
		cont1Nums.add(6);
		Contestant contestant2 = new Contestant("c_off_ee", cont2Nums, Utils.getRandomColor());
		contestant2.setScore(85);
		
		ArrayList<Integer> cont3Nums = new ArrayList<>();
		cont1Nums.add(11);
		cont1Nums.add(15);
		cont1Nums.add(31);
		Contestant contestant3 = new Contestant("xXxNagibator228xXx", cont3Nums, Utils.getRandomColor());
		contestant3.setScore(228);
		
		
		ArrayList<Integer> cont4Nums = new ArrayList<>();
		cont1Nums.add(13);
		cont1Nums.add(21);
		cont1Nums.add(34);
		Contestant contestant4 = new Contestant("GladValakas", cont4Nums, Utils.getRandomColor());
		contestant4.setScore(1488);
		
		contestants.add(contestant1);
		contestants.add(contestant2);
		contestants.add(contestant3);
		contestants.add(contestant4);
		
		LeaderboardController controller = new DialogBuilder().getLeaderBoardController(contestants, null);
		
		controller.update();
		controller.showDialog();
		
		Thread.sleep(5000);
		contestant4.setScore(10);
		controller.update();
		
		Thread.sleep(5000);
		
		ArrayList<Integer> cont5Nums = new ArrayList<>();
		cont5Nums.add(12);
		cont5Nums.add(22);
		cont5Nums.add(33);
		Contestant contestant5 = new Contestant("GladAntharas", cont5Nums, Utils.getRandomColor());
		contestant5.setScore(488);
		
		contestants.add(contestant5);
		controller.update();
		
		
	}

}
