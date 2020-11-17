package chernyj.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Chernyj Dmitry
 *
 */
public class ButtonReadyPositionSetter extends JFrame {
	private static final long serialVersionUID = -444800860140531649L;
	private Rectangle rectangleToTrack = new Rectangle();
	private int x;
	private int y;
	private int width;
	private int height;
	private JPanel panel = new JPanel();
	
	
	private static final Color BACKGROUND = new Color(0x770077);
	
	public ButtonReadyPositionSetter() {
		initDefaults();
		this.setLocation(x,y);
		this.setUndecorated(true);
		panel.setBackground(BACKGROUND);
		panel.setOpaque(true);
		panel.setVisible(true);
		this.add(panel);
		this.getRootPane().setOpaque(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(width,height));
		this.setAlwaysOnTop(true);
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					setLocation((int)getLocation().getX() - 1, (int)getLocation().getY());
				}
				
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					setLocation((int)getLocation().getX() + 1, (int)getLocation().getY());
				}
				
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					setLocation((int)getLocation().getX(), (int)getLocation().getY() - 1);
				}
				
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					setLocation((int)getLocation().getX(), (int)getLocation().getY() + 1);
				}
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					rectangleToTrack = new Rectangle(getX(),getY(),getWidth(),getHeight());
					saveNewDefaults(rectangleToTrack);
					setVisible(false);
					dispose();
				}
				
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					setVisible(false);
					dispose();
				}
			}
		});
		
		if(translucencySupported())
			this.setOpacity(0.5f);
		this.setVisible(true);
	}
	
	private void saveNewDefaults(Rectangle rectangle) {
		int newX = (int) rectangle.getX();
		int newY = (int) rectangle.getY();
		int newWidth = (int) rectangle.getWidth();
		int newHeight = (int) rectangle.getHeight();
		
		ApplicationConfiguration.saveItem("buttonready.x", String.valueOf(newX));
		ApplicationConfiguration.saveItem("buttonready.y", String.valueOf(newY));
		ApplicationConfiguration.saveItem("buttonready.width", String.valueOf(newWidth));
		ApplicationConfiguration.saveItem("buttonready.height", String.valueOf(newHeight));
	}
	
	private void initDefaults() {
		x = Integer.parseInt(ApplicationConfiguration.getItem("buttonready.x"));
		y = Integer.parseInt(ApplicationConfiguration.getItem("buttonready.y"));
		width = Integer.parseInt(ApplicationConfiguration.getItem("buttonready.width"));
		height = Integer.parseInt(ApplicationConfiguration.getItem("buttonready.height"));
	}
	
	public static boolean translucencySupported() {
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        return gd.isWindowTranslucencySupported(
                GraphicsDevice.WindowTranslucency.TRANSLUCENT);
    }
}
