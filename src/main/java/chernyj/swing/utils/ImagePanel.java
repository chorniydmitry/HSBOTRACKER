package chernyj.swing.utils;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * @author Chernyj Dmitry
 *
 */
public class ImagePanel extends JComponent {
	private static final long serialVersionUID = -6910153898712516671L;

	private ImageIcon image;
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image == null)
        	return;
        g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
        
		this.revalidate();
		this.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width = Math.max(image.getIconWidth(), size.width);
        size.height = Math.max(image.getIconHeight(), size.height);

        return size;
    }
	
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	

}
