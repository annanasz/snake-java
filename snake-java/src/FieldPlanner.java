import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.ArrayList;

public class FieldPlanner extends JPanel implements ActionListener, MouseListener{
	JButton[][] cells;
	boolean pressed;
	public FieldPlanner() {
		super(new GridLayout(20,40));
		cells= new JButton[20][40];
		for(int i=0;i<20;i++) {
			for(int j=0;j<40;j++) {
				cells[i][j]=new JButton();
				cells[i][j].setFocusable(false);
				cells[i][j].setBackground(Color.green);
				this.add(cells[i][j]);
				cells[i][j].addActionListener(this);
				cells[i][j].addMouseListener(this);
			}
		}
		this.setBackground(Color.white);
		this.setVisible(true);
		pressed=false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton) e.getSource();
		if(b.getBackground() == Color.green)
		{
			b.setBackground(Color.gray);
		}
		else if(b.getBackground() == Color.gray) {
			b.setBackground(Color.green);
		}
		
	}
	public ArrayList<Boolean> getObstacles(){
		ArrayList<Boolean> list=new ArrayList<Boolean>();
		for(int i=0;i<20;i++) {
			for(int j=0;j<40;j++) {
				if(cells[i][j].getBackground()==Color.gray) {
					list.add((Boolean)true);
				}
				else list.add(false);
			}
		}
		FileOutputStream f = null;
		try {
			f = new FileOutputStream("no");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectOutputStream out=null;
		try {
			out = new ObjectOutputStream(f);
			out.writeObject(list);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public void clear() {
		for(int i=0;i<20;i++) {
			for(int j=0;j<40;j++) {
				cells[i][j].setBackground(Color.green);
			}
		}
				
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		pressed=true;
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		pressed=false;
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(pressed) {
			JButton b=(JButton) e.getSource();
			if(b.getBackground() == Color.green)
			{
				b.setBackground(Color.gray);
			}
			else if(b.getBackground() == Color.gray) {
				b.setBackground(Color.green);
			}
		}
			
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
