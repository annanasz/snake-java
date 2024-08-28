import java.awt.Color;

public abstract class Thing {
	private Cell cell;
	private Color c;
	public Thing() {
		
	}
	public void setColor(Color color) {
		c=color;
	}
	public void collideWith(Snake s) {
	}
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell c) {
		cell=c;
	}
	public Color getColor() {
		return c;
	}
}
