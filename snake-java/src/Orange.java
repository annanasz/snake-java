import java.awt.Color;

public class Orange extends Fruit{
	public Orange() {
		setColor(Color.orange);
	}
	public void collideWith(Snake s) {
		Effect e=new Effect(20, -1);
		s.addEffect(e);
		getCell().eaten();
	}
}
