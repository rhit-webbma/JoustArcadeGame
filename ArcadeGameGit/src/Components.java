import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * This is the super class for the components (custimize and normal). It has a
 * paint method as well as a remove old bullets and increment score.
 * 
 * @author rogerscj
 *
 */
public class Components extends JComponent {

	protected Graphics2D g2;
	protected static Hero mainCharacter;
	protected ArrayList<Monster> monsters = new ArrayList<>();
	protected ArrayList<Platform> platforms = new ArrayList<Platform>();
	protected ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	protected ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
	protected ArrayList<Egg> eggs = new ArrayList<>();
	protected static boolean gameOver = false;
	protected int score;

	@Override
	protected void paintComponent(Graphics g) {
		g2 = (Graphics2D) g;

		Rectangle emptySpace = new Rectangle(0, 0, 1200, 800); // makes background black
		g2.setColor(Color.black);
		g2.fill(emptySpace);

		Rectangle lava = new Rectangle(0, 640, 1200, 150); // draws lava
		g2.setColor(new Color(206, 62, 47));
		g2.fill(lava);

		if (gameOver == true) {
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("serif", Font.BOLD, 150));
			g2.drawString("GAME OVER", 125, 350);
			g2.setFont(new Font("serif", Font.ITALIC, 50));
			g2.drawString("score: " + score, 490, 400);
			return;
		}

		int healthBar = mainCharacter.heroHealth(0);
		if (healthBar <= 0)
			gameOver = true;
		g2.setColor(Color.black);
		g2.setFont(new Font("serif", Font.BOLD, 32));
		g2.drawString("HEALTH", 1005, 750);
		g2.draw(new Rectangle(1000, 700, 150, 25));
		g2.setColor(Color.GREEN);
		g2.fillRect(1000, 700, healthBar, 25);

		mainCharacter.paintComponent(g2);
		for (Platform plat : platforms) {
			plat.draw(g2);
		}
		for (Monster mons : monsters) {
			mons.paintComponent(g2);
		}
		for (Bullet bull : bullets) {
			removeOldBullets();
			bull.moveBullet();
			bull.paintComponent(g2);
		}
		for (Bullet b : bulletsToRemove) {
			bullets.remove(b);
		}
	}

	private void removeOldBullets() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getXCord() <= -5) {
				bulletsToRemove.add(bullets.get(i));
			}
		}
	}

	public void incrementScore(int input) {
		score += input;
	}
}
