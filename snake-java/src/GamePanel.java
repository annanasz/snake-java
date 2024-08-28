import java.awt.Color;
import java.util.ArrayList;
import java.lang.Boolean;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener, ActionListener{
	protected Snake s;
	protected Field f;
	public GamePanel(JLabel label,ArrayList<Boolean> list, Color c) {
		super(new GridLayout(20,40));
		int x=20;
		int y=40;
		f=new Field(x,y, list);
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				this.add(f.get(i, j));
			}
		}
		s=new Snake(label,c);
		f.addSnake(s,1);
	}
	public GamePanel(JLabel label) {
		super(new GridLayout(20,40));
		int x=20;
		int y=40;
		f=new Field(x,y);
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				this.add(f.get(i, j));
			}
		}
		s=new Snake(label,Color.magenta);
		f.addSnake(s,1);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
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
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public Integer getPoints() {
		return Integer.parseInt(s.getPoints());
	}

}
