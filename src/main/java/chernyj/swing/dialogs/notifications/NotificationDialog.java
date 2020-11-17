package chernyj.swing.dialogs.notifications;

import java.awt.Dimension;

import javax.swing.JDialog;

public class NotificationDialog extends JDialog {
	private static final long serialVersionUID = 9139878746702042429L;

	public NotificationDialog(int width, int height, String title) {
		this.setSize(new Dimension(width, height));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
	
	
}
