import java.awt.Color;

public class Body extends Thing {
	public Body(Color c) {
		setColor(c);
	}
	public Cell getCell() {
		return super.getCell();
	}
	public void collideWith(Snake s) {
		s.die();
	}
}
