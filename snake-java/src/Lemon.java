import java.awt.Color;

public class Lemon extends Fruit {
	public Lemon() {
		setColor(Color.yellow);
	}
	public void collideWith(Snake s) {
		s.grow(5);
		getCell().eaten();
	}
}
