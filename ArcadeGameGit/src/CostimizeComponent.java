import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * This is the class for the customize screen. It can draw game which simply
 * sets the location of the hero and has a reset method which removes all
 * platforms, monsters, and bullets.
 * 
 * @author rogerscj
 *
 */
public class CostimizeComponent extends Components {

	public CostimizeComponent(Hero h, ArrayList<Platform> p, ArrayList<Monster> monsters,
			ArrayList<Bullet> bulletsList) {
		this.mainCharacter = h;
		this.platforms = p;
		this.monsters = monsters;
		this.bullets = bulletsList;
		this.score = score;
	}

	protected void drawGame() {
		mainCharacter.setLocation(500, 250);
	}

	protected void reset() {
		this.platforms.clear();
		this.monsters.clear();
		this.bullets.clear();
		repaint();
	}
}
