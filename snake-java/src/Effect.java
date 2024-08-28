
public class Effect implements Steppable{
	private int timetolive;
	private int speed;
	public Effect(int n, int s) {
		timetolive=n;
		speed=s;
	}
	public void step() {
		timetolive--;
		if(timetolive<=0)
		{
			MyTimer.getInstance().remove(this);
		}
	}
	public int Effects(){
		return speed;
	}
	public boolean isValid() {
		if(timetolive>0)
			return true;
		return false;
	}
}
