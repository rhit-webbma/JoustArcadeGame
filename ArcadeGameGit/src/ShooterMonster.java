import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ShooterMonster extends Monster {

	private Rectangle Bounds;
	private Bullet bullet;
	private boolean collectedEgg;
	private int pointsWorth = 150;

	public ShooterMonster(int xCord, int yCord, int speed, String strategy) {
		x = xCord;
		this.y = yCord;
		this.dx = speed;
		this.Strategy = strategy;
		super.setMonsterType("Shooter");
		super.setFullBounds(this.x, this.y, 65, 90);

		try {
			this.image = ImageIO.read(new File("JoustEnemy_WalkingLeft_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void executeStrategy(String strat) {
		if (super.getIsEgg() == true) {
			super.setFullBounds(this.x + 20, this.y + 35, 25, 25);
		} else {
			if (strat == "basicLeft") {
				x -= dx;
				super.setFullBounds(this.x, this.y, 65, 90);
			}
			if (strat == "basicRight") {
				x += dx;
				super.setFullBounds(this.x, this.y, 65, 90);
			}
		}
	}

	@Override
	public boolean checkForIntersection(Rectangle collision) {

		Rectangle monsterBounds = super.getFullBounds();

		if (super.getIsEgg() == true) {
			if (monsterBounds.intersects(collision)) {
				collectedEgg = true;
				return true;
			}
		} else if (monsterBounds.intersects(collision)) {
			if (super.getYCord() < collision.getY())
				return true;
			else {
				super.becomeEgg();
				return false;
			}
		} else
			return false;
		return false;
	}

	@Override
	public void setEggCollected(boolean value) {
		this.collectedEgg = false;
	}

	@Override
	public boolean getEggCollected() {
		return this.collectedEgg;
	}

}
