import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FollowerMonster extends Monster {

	private Hero hero;
	private int pointsWorth = 200;
	private boolean collectedEgg;

	public FollowerMonster(int x, int y, int speed, String strategy, Hero mainCharacter) {
		this.x = x;
		this.y = y;
		this.dx = speed;
		this.Strategy = strategy;
		this.hero = mainCharacter;
		super.setMonsterType("Follower");
		super.setFullBounds(this.x, this.y, 65, 90);

		try {
			this.image = ImageIO.read(new File("JoustEnemy2_StaticRight.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void executeStrategy(String strat) {

		if (super.getIsEgg() == true) {
			super.setFullBounds(this.x + 20, this.y + 35, 25, 25);
		} else {
			try {
				this.image = ImageIO.read(new File("JoustEnemy2_StaticRight.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (x < hero.getXCord()) {
				x += 2;
				super.setFullBounds(this.x, this.y, 65, 90);
			} else {
				x -= 2;
				super.setFullBounds(this.x, this.y, 65, 90);
			}
			if (y > hero.getYCord()) {
				y -= 5;
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
