import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

abstract class Monster extends Character {
	
	protected String Strategy;
	
	private boolean isEgg;
	private int countInEgg = 0;
	private String monsterType;
	
	public abstract void executeStrategy(String strat);
	public abstract void setEggCollected(boolean value);
	public abstract boolean getEggCollected();
	
	public  void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract boolean checkForIntersection(Rectangle collision);
	
	public String getStrategy() {
		return this.Strategy;
	}
	
	public void becomeEgg() {	
		try {
			this.image = ImageIO.read(new File("Egg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		isEgg = true;
		
	}
	
	public void becomeMonster(String filename){
	try{
		this.image = ImageIO.read(new File(filename));
	} catch (IOException e){
		e.printStackTrace();
	}
	isEgg = false;
	}
	
	public void incrementEggCount(){
		this.countInEgg++;
	}
	
	public int getCount(){
		return this.countInEgg;
	}
	
	public void move() {
		if(isEgg != true){
			super.move();
		}
		y += gravityConst;
	}
	
	public boolean getIsEgg(){
		return this.isEgg;
	}
	
	public void setMonsterType(String type){
		this.monsterType = type;
	}
	
	public String getMonsterType(){
		return this.monsterType;
	}
	
}
