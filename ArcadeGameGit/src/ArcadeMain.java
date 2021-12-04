import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class ArcadeMain {

	private static Hero mainCharacter = new Hero("mainJoustHero_StaticRight.png");
	private static Components gameComponent;
	private static ArrayList<Platform> platforms = new ArrayList<Platform>();
	private static ArrayList<Monster> monsters = new ArrayList<>();
	private static ArrayList<Monster> monstersToRemove = new ArrayList<>();
	private static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private static boolean alreadyHitBullet = false;
	private static boolean alreadyHitMonster = false;

	public static void main(String[] args) {
		JFrame startScreen = new JFrame();
		startScreen.setSize(640, 500);
		startScreen.setTitle("Arcade Game");
		ImageIcon image = new ImageIcon("JoustScreen.png");
		JLabel picture = null;
		picture = new JLabel();
		picture.setIcon(image);
		JLabel welcomeMessage = new JLabel("Welcome to Joust!", SwingConstants.CENTER);
		welcomeMessage.setFont(new Font(welcomeMessage.getName(), Font.PLAIN, 30));
		startScreen.add(welcomeMessage, BorderLayout.NORTH);
		JPanel panel = new JPanel();
		JButton startGame = new JButton("Start Game");
		ActionListener startEar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startScreen.dispose();
				normalGame();
			}
		};
		startGame.addActionListener(startEar);
		panel.add(startGame, BorderLayout.SOUTH);
		panel.add(picture, BorderLayout.CENTER);
		JButton costimizeLevel = new JButton("Costimize A Level");
		ActionListener costimizeEar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startScreen.dispose();
				costimizeGame();
			}
		};
		costimizeLevel.addActionListener(costimizeEar);
		panel.add(costimizeLevel, BorderLayout.SOUTH);
		startScreen.add(panel, BorderLayout.SOUTH);

		startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startScreen.setVisible(true);
	}

	public static void costimizeGame() {
		JFrame costFrame = new JFrame();
		costFrame.setSize(1390, 800);
		costFrame.setTitle("Arcade Game");
		JPanel additionPanel = new JPanel();
		additionPanel.setLayout(new BoxLayout(additionPanel, BoxLayout.PAGE_AXIS));
		JButton begin = new JButton("Start Game");
		begin.setFont(new Font("Arial", Font.PLAIN, 20));
		ActionListener beginEar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				costFrame.remove(additionPanel);
				costFrame.setSize(1200, 800);
				gameComponent.removeMouseListener(gameComponent.getMouseListeners()[0]);
				KeyBoardListener costEar = new KeyBoardListener(mainCharacter, gameComponent);
				costFrame.addKeyListener(costEar);
				costFrame.setFocusable(true);
				costFrame.requestFocusInWindow();
				updateGame();
			}
		};
		begin.addActionListener(beginEar);
		JButton reset = new JButton("Reset");
		reset.setFont(new Font("Arial", Font.PLAIN, 20));
		ActionListener resetEar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((CostimizeComponent) gameComponent).reset();
			}
		};
		reset.addActionListener(resetEar);

		ButtonGroup group = new ButtonGroup();
		JRadioButton rb1 = new JRadioButton("Shooter Monster", true);
		rb1.setFont(new Font("Arial", Font.PLAIN, 20));
		JRadioButton rb2 = new JRadioButton("Follower Monster");
		rb2.setFont(new Font("Arial", Font.PLAIN, 20));
		JRadioButton rb3 = new JRadioButton("Platform");
		rb3.setFont(new Font("Arial", Font.PLAIN, 20));
		group.add(rb1);
		group.add(rb2);
		group.add(rb3);
		JLabel space = new JLabel(" ");
		space.setFont(new Font("Arial", Font.PLAIN, 200));
		additionPanel.add(rb1, BorderLayout.NORTH);
		additionPanel.add(rb2);
		additionPanel.add(rb3);
		additionPanel.add(space);
		additionPanel.add(reset, BorderLayout.SOUTH);
		additionPanel.add(begin, BorderLayout.SOUTH);
		costFrame.add(additionPanel, BorderLayout.EAST);

		gameComponent = new CostimizeComponent(mainCharacter, platforms, monsters, bullets);
		((CostimizeComponent) gameComponent).drawGame();
		MouseListener pressEar = new ClickListener(gameComponent, rb1, rb2, rb3, mainCharacter);
		MouseMotionListener moveEar = new ClickListener(gameComponent, rb1, rb2, rb3, mainCharacter);
		gameComponent.addMouseListener(pressEar);
		gameComponent.addMouseMotionListener(moveEar);
		costFrame.add(gameComponent);

		costFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		costFrame.setVisible(true);
	}

	public static void normalGame() {
		JFrame frame = new JFrame();
		frame.setSize(1200, 800);
		frame.setTitle("Arcade Game");

		gameComponent = new NormalGameComponent(mainCharacter, platforms, monsters, bullets);
		KeyBoardListener ear = new KeyBoardListener(mainCharacter, gameComponent);
		frame.addKeyListener(ear);
		frame.add(gameComponent);
		((NormalGameComponent) gameComponent).loadFile();

		updateGame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void updateGame() {
		ActionListener movePlayer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainCharacter.move();
				mainCharacter.gravity();
				gameComponent.repaint();
				boolean paltformCollision = false;
				boolean bulletCollision = false;
				Rectangle playerCollision = mainCharacter.getFullBounds();
				Rectangle playerBottomCollision = mainCharacter.getBottomBounds();
				for (Platform plat : platforms) {
					if (plat.checkForIntersection(playerBottomCollision) == true) {
						paltformCollision = true;
					}
				}
				if (paltformCollision == true) {
					mainCharacter.stopGravity();
				} else {
					mainCharacter.startGravity();
				}
				for (Bullet bul : bullets) {
					if (bul.checkForIntersectionBullet(playerCollision)) {
						bulletCollision = true;
					}
				}
				if (bulletCollision == true) {
					if (alreadyHitBullet == false) {
						mainCharacter.heroHealth(25);
					}
					alreadyHitBullet = true;
				} else {
					alreadyHitBullet = false;
				}
			}
		};

		ActionListener moveMonster = new ActionListener() {
			int monstersHit = 0;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Monster monster : monsters) {
					moveMonster(monster);
					boolean monsterCollision = false;
					Rectangle monsterBottomCollision = monster.getBottomBounds();
					for (Platform plat : platforms) {
						if (plat.checkForIntersection(monsterBottomCollision) == true) {
							monsterCollision = true;
							break;
						}
					}
					if (monsterCollision == false) {
						monster.startGravity();
					} else {
						monster.stopGravity();
					}
					if (monster.getIsEgg() == true) {
						monster.incrementEggCount();
						if (monster.getCount() == 200) {
							if (monster.getMonsterType() == "Shooter") {
								monster.becomeMonster("JoustEnemy_WalkingLeft_1.png");
							} else
								monster.becomeMonster("JoustEnemy2_StaticRight.png");
						}

					}
					if (monster.checkForIntersection(mainCharacter.getFullBounds())) {
						if(monster.getIsEgg() == false)
							monstersHit++;
						if (monster.getIsEgg() == true) {
							monstersToRemove.add(monster);
						}
					}
				}
				if (monstersHit >= 1) {
					if (alreadyHitMonster == false) {
						mainCharacter.heroHealth(50);
					}
					alreadyHitMonster = true;
				} else {
					alreadyHitMonster = false;
				}
				for (Monster mons : monstersToRemove) {
					monsters.remove(mons);
					gameComponent.incrementScore(50);
				}
				monstersToRemove.clear();
			}
		};

		ActionListener shootBullet = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shootBullets();
			}
		};

		Timer playerTimer = new Timer(20, movePlayer);
		playerTimer.start();

		Timer monsterTimer = new Timer(20, moveMonster);
		monsterTimer.start();

		Timer bulletTimer = new Timer(2000, shootBullet);
		bulletTimer.start();
	}
	
	protected static void moveMonster(Monster monster) {
		int xCord = monster.getXCord();
		int yCord = monster.getYCord();

		if (yCord >= 550) {
			monster.setLocation(xCord, 0);
		}
		if (yCord <= 0) {
			monster.setLocation(xCord, 550);
		} else {
			monster.executeStrategy(monster.getStrategy());
		}
		monster.move();
		monster.repaint();
	}

	protected static void shootBullets() {
		for (Monster mons : monsters) {
			if (mons.getStrategy() == "basicLeft" || mons.getStrategy() == "basicRight") {
				if (mons.getIsEgg() == false) {
					Bullet bullet = new Bullet(mons.getXCord(), mons.getYCord(), 4);
					bullets.add(bullet);
				}
			}
		}
		if (monsters.isEmpty()) {
			((NormalGameComponent) gameComponent).changeLevel("inc");
			((NormalGameComponent) gameComponent).loadFile();
		}
	}
}
