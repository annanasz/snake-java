import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Cell {
	private JPanel jp;
	private Thing thing;
	private Field f;
	private Position pos;
	public Cell(int n, int m, Field field) {
		thing=new Simple();
		pos=new Position(n,m);
		f=field;
		jp=new JPanel();
		jp.setBackground(thing.getColor());
		jp.setBorder(BorderFactory.createLineBorder(Color.darkGray,1));
	}
	public void accept(Snake s) {
		thing.collideWith(s);
		boolean b = (MyTimer.getInstance().getEnd());
		if(!b)
		{
			add(s.getHead());
		}		
	}
	public void add(Thing t) {
		boolean b = (MyTimer.getInstance().getEnd());
		if(!b)
		{
			thing=t;
			t.setCell(this);
			jp.setBackground(thing.getColor());
		}
	}
	public Cell getNeighbor(Direction d) {
		return f.getCell(d, pos);
	}
	public void eaten() {
		f.generateNewFruit();
	}
	public Thing getThing() {
		return thing;
	}
	public JPanel getJPanel() {
		return this.jp;
	}
	public void setThing(Thing t) {
		thing=t;
		jp.setBackground(thing.getColor());
	}
}
