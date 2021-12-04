import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sun.awt.RepaintArea;

public class KeyBoardListener  implements KeyListener {

	private Hero mainCharacter;
	private Components gameComponent;
	
	public KeyBoardListener(Hero h, Components g){
		this.mainCharacter = h;
		this.gameComponent = g;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'u' || e.getKeyChar() == 'U') {
			((NormalGameComponent) gameComponent).changeLevel("inc");
			((NormalGameComponent) gameComponent).loadFile();
		}
		if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
			((NormalGameComponent) gameComponent).changeLevel("dec");
			((NormalGameComponent) gameComponent).loadFile();
		}else {
			mainCharacter.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		mainCharacter.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
