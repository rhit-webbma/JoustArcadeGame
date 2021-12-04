import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * This is the class for bullets. It has an x and a y coordinate as well as a
 * speed and a sprite. The bullet can be painted on a graphic with
 * paintComponent. The sprite will be painted to its x and y coordinate. The
 * bullet can also be move to the left with the method move. The rate at which
 * it moves depends on its speed. The x coordinate can be retrieved using
 * getXCord. A bullet can also check if it intersects with a given rectangle.
 * 
 * @author rogerscj
 *
 */
public class Bullet extends JComponent {

	private int x;
	private int y;
	private int dx;
	private BufferedImage bulletImage;

	public Bullet(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.dx = speed;

		try {
			this.bulletImage = ImageIO.read(new File("Bullet.png"));
		} catch (IOException e) {
			System.out.println("Couldn't Load " + "Bullet.png");
		}
	}

	void paintComponent(Graphics2D g2) {
		Graphics2D g = (Graphics2D) g2;
		g.drawImage(this.bulletImage, x, y, null);
	}

	public void moveBullet() {
		x -= dx;
	}

	public int getXCord() {
		return this.x;
	}

	public boolean checkForIntersectionBullet(Rectangle playerCollision) {
		Rectangle bulletRect = new Rectangle(x + 20, y + 30, 25, 30);
		if (bulletRect.intersects(playerCollision))
			return true;
		return false;
	}

}
