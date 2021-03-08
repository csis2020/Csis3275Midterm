package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelWithBackgroundImage extends JPanel {
	  private Image backgroundImage;

	  
	/**
	 * 
	 * @param fileName 
	 * @throws IOException
	 */
	public JPanelWithBackgroundImage(String fileName) throws IOException {
	    backgroundImage = ImageIO.read(new File(fileName));
	  }

	/**
	 *
	 */
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Draw the background image.
	    g.drawImage(backgroundImage, 0, 0, this);
	}
}
