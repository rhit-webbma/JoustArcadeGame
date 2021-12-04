import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Platform{
	
	private static int xPos;
	private static int yPos;
	Rectangle thisPlat;

	public Platform(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		this.thisPlat = new Rectangle(this.xPos, this.yPos, 20, 20);  //replace with graphic
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(new Color(121, 96, 76));
		g2.fill(this.thisPlat);  //replace with graphic
	}
	
	public boolean checkForIntersection(Rectangle collision) {
		Rectangle platform = new Rectangle((int)thisPlat.getX(), (int)thisPlat.getY(), 20, 3);
		if(platform.intersects(collision)){
			return true;
		}
		return false;
	}

}
