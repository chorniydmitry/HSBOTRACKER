package media;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;

import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class VLCTest {

	public static void main(String[] args) {
		JDialog dialog = new JDialog();
		
		EmbeddedMediaPlayerComponent component = new EmbeddedMediaPlayerComponent() {
			private static final long serialVersionUID = 8102179024730716211L;

			@Override
			public void finished(MediaPlayer mediaPlayer) {
				super.finished(mediaPlayer);
				dialog.dispose();
			}
		};

		dialog.setContentPane(component);
		dialog.setSize(new Dimension(1235, 720));
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setAlwaysOnTop(true);
		
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				component.release();
				dialog.dispose();
			}
		});
		
		dialog.setVisible(true);
		
		component.mediaPlayer().media().play("src/main/resources/videos/wow.mp4");
		
	}

}
