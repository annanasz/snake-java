import java.awt.Color;

public class Apple extends Fruit {
	public Apple() {
		setColor(Color.red);
	}
	public void collideWith(Snake s) {
		s.grow(1);
		getCell().eaten();
	}
}
