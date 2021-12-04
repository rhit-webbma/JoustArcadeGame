import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class NormalGameComponent extends Components{

	private int levelNum = 1;
	
	public NormalGameComponent(Hero h, ArrayList<Platform> p, ArrayList<Monster> monsters, ArrayList<Bullet> bulletsList) {
		this.mainCharacter = h;
		this.platforms = p;
		this.monsters = monsters;
		this.bullets = bulletsList;
		this.score = score;
	}

	public void loadFile() {
		Scanner scanner;
		String fileName = "level" + levelNum + ".txt";
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
			e.printStackTrace();
			return;
		}
		
		int yPos = 0;
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			for(int i=0; i<line.length(); i++) {
				if(line.charAt(i) == '-') {
					Platform p = new Platform(i*20, yPos*20);
					this.platforms.add(p);
				}else if(line.charAt(i) == '_') {
				}else if(line.charAt(i) == 'H') {
					mainCharacter.setLocation(i*20, yPos*20);
				}else if(line.charAt(i) == 'S') {
					Monster newMonster = new ShooterMonster(i*20, yPos*20, 5, "basicRight");
					monsters.add(newMonster);
					newMonster.setLocation(i*20, yPos*20);
				}else if(line.charAt(i) == 'F') {
					Monster newMonster = new FollowerMonster(i*20, i*20, 5, "following", mainCharacter);
					monsters.add(newMonster);
					newMonster.setLocation(i*20, yPos*20);
				}
			}
			yPos++;
		}
	}

	protected void changeLevel(String incDec) {
		if(incDec == "inc") {
			platforms.clear();
			monsters.clear();
			bullets.clear();
			if(this.levelNum == 4) // 5 is the highest level 
				System.out.println("highest level!");
			else
				this.levelNum++;
		}
		else {
			platforms.clear();
			monsters.clear();
			bullets.clear();
			if(this.levelNum == 1) 
				System.out.println("lowest level!");
			else
				this.levelNum--;
		}
		paintComponent(g2);
	}

}