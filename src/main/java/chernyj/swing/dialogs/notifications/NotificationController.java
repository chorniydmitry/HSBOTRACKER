package chernyj.swing.dialogs.notifications;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import uk.co.caprica.vlcj.player.base.MediaPlayer;

public class NotificationController {
	
	private NotificationDialog dialog;
	private EmbeddedMediaPlayerComponent component;
	
	public NotificationController(NotificationDialog dialog, String filePath) {
		this.dialog = dialog;
		
		setEmbededComponentListener();
		setWindowListener();
		playVideo(filePath);
		
	}
	

	private void setEmbededComponentListener() {
		component = new EmbeddedMediaPlayerComponent() {
			private static final long serialVersionUID = 8102179024730716211L;

			@Override
			public void finished(MediaPlayer mediaPlayer) {
				super.finished(mediaPlayer);
				dialog.dispose();
			}
		};
		dialog.setContentPane(component);
		
		dialog.setVisible(true);
	}
	
	private void setWindowListener() {
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				component.release();
				dialog.dispose();
			}
		});
	}
	
	private void playVideo(String filePath) {
		component.mediaPlayer().media().play(filePath);
	}

}
