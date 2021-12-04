import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Class for eggs. Has x and y coordinates as well as an image. The egg can be
 * painted on a graphic.
 * 
 * 
 * @author rogerscj
 *
 */
class Egg extends JComponent {
	private int x;
	private int y;
	private BufferedImage eggImage;

	public Egg(int x, int y) {
		this.x = x;
		this.y = y;

		try {
			this.eggImage = ImageIO.read(new File("Egg.png"));
		} catch (IOException e) {
			System.out.println("Couldn't Load " + "Egg.png");
		}
	}

	void paintComponent(Graphics2D g2) {
		Graphics2D g = (Graphics2D) g2;
		g.drawImage(this.eggImage, x, y, null);
	}

}