import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
public class Field {
	int h;
	int w;
	Cell cells[][];
	public Field(int n, int m,ArrayList<Boolean> list) {
		cells=new Cell [n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				cells[i][j]=new Cell(i,j,this);
				if(list.get(m*i+j)) {
					cells[i][j].setThing(new Obstacle());
				}
			}
		}
		h=n;
		w=m;
		generateNewFruit();
	}
	public Field(int n, int m) {
		cells=new Cell [n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				cells[i][j]=new Cell(i,j,this);
				}
		}
		h=n;
		w=m;
		generateNewFruit();
	}
	public JPanel get(int n, int m) {
		return this.cells[n][m].getJPanel();
	}
	public Cell getCell(Direction d, Position pos) {
		Cell c;
		switch(d) {
			case up:
				if(pos.x-1>=0)
					c = cells[pos.x-1][pos.y];
				else c=cells[h-1][pos.y];
				break;
			case down:
				if(pos.x+1<h)
					c =  cells[pos.x+1][pos.y];
				else c=cells[0][pos.y];
				break;
			case left:
				if(pos.y-1>=0)
					c = cells[pos.x][pos.y-1];
				else c=cells[pos.x][w-1];
				break;
			case right:
				if(pos.y+1<w)
					c = cells[pos.x][pos.y+1];
				else c=cells[pos.x][0];
				break;
			default: c=null;
		}
		return c;
	}
	public void generateNewFruit() {
		Random r=new Random();
		int n = r.nextInt(h);
		int m = r.nextInt(w);
		while(cells[n][m].getThing().getClass()!=Simple.class) {
			n = r.nextInt(h);
			m = r.nextInt(w);
		}
		int fruit=r.nextInt(4);
		Thing f;
		switch(fruit) {
			case 0: f=new Apple();
			break;
			case 1: f=new Lemon();
			break;
			case 2: f=new Orange();
			break;
			case 3: f=new Strawberry();
			break;
			default: f=new Apple();
		}
		cells[n][m].add(f);
	}
	public void addSnake(Snake s,int n) {
		if(n==1)
		{	
			cells[1][2].accept(s);
			s.grow(1);
		}
		else if(n==2)
		{
			cells[10][1].accept(s);
			s.grow(n);
		}
	}
}
