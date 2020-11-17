package chernyj.main;

/**
 * @author Chernyj Dmitry
 *
 */
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import chernyj.swing.main.MainController;
import chernyj.swing.main.Tray;

public class Application {

	public static void main(String[] args) {
//		String filePath = "C:\\games\\Hearthstone\\Logs\\Achievements.log";
//		String filePath = ApplicationConfiguration.getItem("hslogfile.path");
//		LogFileReader logFile = new LogFileReader(filePath, 500);
//		
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				new MainController(new MainDialog(330, 450), logFile);
//		 
//			}
//		});
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	//System.out.println(info.getName());
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}

		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainController(new Tray());
			}
		});
	}
	
	

}
