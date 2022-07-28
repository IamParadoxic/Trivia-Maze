package Controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		switch(code) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			upPressed = true;
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			leftPressed = true;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			downPressed = true;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			rightPressed = true;
			break;
		case KeyEvent.VK_ENTER:
			enterPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		switch(code) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			upPressed = false;
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			leftPressed = false;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			downPressed = false;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			rightPressed = false;
			break;
		case KeyEvent.VK_ENTER:
			enterPressed = false;
		}

	}


}
