import javax.swing.SwingUtilities;

import chernyj.swing.dialogs.expansionStats.ExpansionStatisticsDialog;
import observer.ButtonReadyObserver;

public class FullStatisticsDialogTest  {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new ExpansionStatisticsDialog(800, 600, "Статистика");
			}
		});

	}


}
