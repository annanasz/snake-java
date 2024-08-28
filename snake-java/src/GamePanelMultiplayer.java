import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JLabel;

public class GamePanelMultiplayer extends GamePanel{
	private Snake s2;
	public GamePanelMultiplayer(JLabel label1,JLabel label2,ArrayList<Boolean> list) {
		super(label1,list,Color.magenta);
		s2=new Snake(label2,Color.cyan);
		f.addSnake(s2,2);
	}
	public GamePanelMultiplayer(JLabel label1,JLabel label2) {
		super(label1);
		s2=new Snake(label2,Color.cyan);
		f.addSnake(s2,2);
	}
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_DOWN:
			if(s.getDirection()!=Direction.up) s.setDirection(Direction.down);
			break;
		case KeyEvent.VK_UP:
			if(s.getDirection()!=Direction.down) s.setDirection(Direction.up);
			break;
		case KeyEvent.VK_LEFT:
			if(s.getDirection()!=Direction.right) s.setDirection(Direction.left);
			break;
		case KeyEvent.VK_RIGHT:
			if(s.getDirection()!=Direction.left) s.setDirection(Direction.right);
			break;
		case KeyEvent.VK_W:
			if(s2.getDirection()!=Direction.down) s2.setDirection(Direction.up);
			break;
		case KeyEvent.VK_S:
			if(s2.getDirection()!=Direction.up) s2.setDirection(Direction.down);
			break;
		case KeyEvent.VK_D:
			if(s2.getDirection()!=Direction.left) s2.setDirection(Direction.right);
			break;
		case KeyEvent.VK_A:
			if(s2.getDirection()!=Direction.right) s2.setDirection(Direction.left);
			break;
		}
		
	}
}
