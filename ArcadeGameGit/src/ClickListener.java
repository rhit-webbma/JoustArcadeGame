import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 * This is a mouse and mouse motion listener for custimize screen. When the
 * mouse is pressed it adds one monster to the game (depending on which monster
 * is selected). If platform is selected and the mouse is dragged a platform is
 * added to the game.
 * 
 * @author rogerscj
 *
 */
public class ClickListener implements MouseListener, MouseMotionListener {

	private Components gameComponent;
	private JRadioButton b1;
	private JRadioButton b2;
	private JRadioButton b3;
	private Hero mainCharacter;

	public ClickListener(Components gameComponent, JRadioButton rb1, JRadioButton rb2, JRadioButton rb3,
			Hero mainCharacter) {
		this.gameComponent = gameComponent;
		this.b1 = rb1;
		this.b2 = rb2;
		this.b3 = rb3;
		this.mainCharacter = mainCharacter;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (b1.isSelected())
			gameComponent.monsters.add(new ShooterMonster(e.getX(), e.getY(), 5, "basicRight"));
		if (b2.isSelected())
			gameComponent.monsters.add(new FollowerMonster(e.getX(), e.getY(), 5, "following", mainCharacter));
		gameComponent.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (b3.isSelected()) {
			gameComponent.platforms.add(new Platform(e.getX(), e.getY()));
		}
		gameComponent.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

}
