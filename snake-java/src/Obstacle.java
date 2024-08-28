import java.awt.Color;

public class Obstacle extends Thing{
	public Obstacle() {
		setColor(Color.gray);
	}
	public void collideWith(Snake s) {
		s.die();
	}
}
