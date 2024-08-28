import java.awt.Color;

public class Head extends Thing{
	public Head() {
		setColor(Color.black);
	}
	public void collideWith(Snake s) {
		s.die();
	}
}
