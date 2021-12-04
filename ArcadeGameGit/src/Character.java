import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/**
 * This is the super class for the character (monsters and hero). The full
 * bounds can be set and fetched and the bottom bonds can be fetched. The
 * location of the character can also be set and retrieved. Characters can move
 * (different depending on which type) and they can be painted on a graphic.
 * 
 * @author rogerscj
 *
 */
abstract class Character extends JComponent {

	protected int x;
	protected int y;
	protected BufferedImage image;
	protected int dx;
	protected int gravityConst = 10;
	protected Rectangle fullBounds;

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(this.image, x, y, null);
	}

	public void setFullBounds(int x, int y, int width, int height) {
		fullBounds = new Rectangle(x, y, width, height);
	}

	public Rectangle getFullBounds() {
		return this.fullBounds;
	}

	public Rectangle getBottomBounds() {
		return new Rectangle(this.x, this.y + 80, 65, 10);
	}

	public void stopGravity() {
		gravityConst = 0;
	}

	public void startGravity() {
		gravityConst = 3;
	}

	public int getXCord() {
		return this.x;
	}

	public int getYCord() {
		return this.y;
	}

	public void setLocation(int xCord, int yCord) {
		this.x = xCord;
		this.y = yCord;
	}

	public void move() {
		if (this.x >= 1200) {
			this.x = 10;
		} else if (x <= -10) {
			this.x = 1190;
		}
	}
}
