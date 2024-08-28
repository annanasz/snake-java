import java.awt.Color;

public class Strawberry extends Fruit{
	public Strawberry() {
		setColor(Color.pink);
	}
	public void collideWith(Snake s) {
		getCell().eaten();
		Effect e=new Effect(60, 1);
		s.addEffect(e);
	}
}
