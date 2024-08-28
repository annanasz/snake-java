import java.util.LinkedList;
import javax.swing.JLabel;
import java.awt.Color;

public class Snake implements Steppable{
	private Head head;
	private JLabel points;
	private LinkedList<Body> body;
	private Direction d;
	private Effect effect;
	private Color c;
	public Snake(JLabel l,Color color) {
		head=new Head();
		body=new LinkedList<Body>();
		effect=null;
		d=Direction.right;
		c=color;
		MyTimer.getInstance().add(this);
		points=l;
	}
	public void addEffect(Effect e) {
		MyTimer t=MyTimer.getInstance();
		effect=e;
		t.add(e);
	}
	public void grow(int n) {
		if(body.size()==0) {
			Body b=new Body(c);
			b.setCell(head.getCell().getNeighbor(Direction.left));
			head.getCell().add(b);
			body.add(b);
		}
		else {
		Body b=new Body(c);
		b.setCell(body.getFirst().getCell().getNeighbor(Direction.down));
		body.addFirst(b);
		Integer p=Integer.parseInt(points.getText());
		p+=n;
		points.setText(p.toString());
		}
	}
	public void die() {
		MyTimer t=MyTimer.getInstance();
		t.setEnd(true);
		t.cancel();
	}
	public void calculateSpeed() {
		int s=0;
		if(effect!=null && effect.isValid())
		{
			s=effect.Effects();
		}
		MyTimer.getInstance().setSteps(s);
	}
	public void step() {
		move(d);
	}
	public void setDirection(Direction dir) {
		d=dir;
	}
	public Thing getHead() {
		return head;
	}
	public Direction getDirection() {
		return d;
	}
	public void move(Direction d) {
		calculateSpeed();
		Cell c = head.getCell();
		c.getNeighbor(d).accept(this);
		body.getFirst().getCell().add(new Simple());
		for(int i=0;i<body.size()-1;i++) {
			body.get(i).setCell(body.get(i+1).getCell());
		}
		body.getLast().setCell(c);
		c.add(body.getLast());
		for(int i=0;i<body.size()-1;i++) {
			body.get(i).getCell().add(body.get(i));
		}	
	}
	public String getPoints() {
		String p=points.getText();
		return p;
	}
	
}
