import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hero extends Character implements KeyListener {

	private File file;
	private int movingLeft = 0;
	private int movingRight = 0;
	private int health = 150;
	private int points;

	public Hero(String filename) {
		try {
			super.setFullBounds(this.x, this.y, 65, 90);
			x = 0;
			y = 0;
			this.dx = 0;
			this.file = new File(filename);
			this.image = ImageIO.read(file);
		} catch (IOException e) {
			throw new RuntimeException("Could not load image file " + filename);
		}
	}

	@Override
	public void move() {
		super.move();
		x += dx;
		super.setFullBounds(this.x, this.y, 65, 90);
		if (movingLeft == 0 && movingRight == 0)
			dx = 0;
		if(y <= 0)
			y=0;
		if(y >= 600)
			this.health -=5;
	}

	public void addPoints(int value) {
		this.points += value;
	}
	
//	public int getPoints(){
//		return this.points;
//	}
	
	public void gravity() {
		y += gravityConst;
	}

	public Rectangle getTopBounds() {
		return new Rectangle(this.x + 50, this.y + 30, 100, 10);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			try {
				this.image = ImageIO.read(new File("mainJoustHero_StaticLeft.png"));
			} catch (IOException e1) {
				throw new RuntimeException("Could not load image file " + "mainJoustHero_StaticLeft.png");
			}
			dx = -5;
			super.setFullBounds(this.x, this.y, 65, 90);
			movingLeft = 1;
		}
		if (key == KeyEvent.VK_RIGHT) {
			try {
				this.image = ImageIO.read(new File("mainJoustHero_StaticRight.png"));
			} catch (IOException e1) {
				throw new RuntimeException("Could not load image file " + "mainJoustHero_StaticRight.png");
			}
			dx = 5;
			super.setFullBounds(this.x, this.y, 65, 90);
			movingRight = 1;
		}
		if (key == KeyEvent.VK_UP) {

			gravityConst = 0;
			y -= 40;
			super.setFullBounds(this.x, this.y, 65, 90);
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			if (movingRight == 0)
				dx = 0;
			movingLeft = 0;
		}
		if (key == KeyEvent.VK_RIGHT) {
			if (movingLeft == 0)
				dx = 0;
			movingRight = 0;
		}
		if (key == KeyEvent.VK_UP) {
			gravityConst = 3;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public int heroHealth(int amountToDecrease) {
		this.health -= amountToDecrease;
		return this.health;
	}
}
