import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class MyTimer{
	private static MyTimer obj = new MyTimer();
	private List<Steppable> steppables;
	private Timer t;
	private int steps;
	private boolean end;
	protected PropertyChangeSupport propertyChangeSupport;
	private ActionListener a= new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				for(int i=0;i<steppables.size();i++)
				{
					if(!end)
					steppables.get(i).step();
				}
				if(steps==1) {
					t.stop();
					t.setInitialDelay(50);
					t.restart();
				}
				else if(steps==-1) {
					t.stop();
					t.setInitialDelay(300);
					t.restart();
				}
		}
		};
	private MyTimer() {
		propertyChangeSupport = new PropertyChangeSupport(this);
		steppables=new ArrayList<Steppable>();
		steps=0;
		t=new Timer(100,a);
		t.setInitialDelay(100);
		t.start();
		end=false;
	}
	public Effect getEffect() {
		return (Effect)steppables.get(steppables.size()-1);
	}
	public void remove(Steppable s) {
		steppables.remove(s);
	}
	public void add(Steppable s) {
		steppables.add(s);
	}
	public static MyTimer getInstance() {
		return obj;
	}
	public void setSteps(int n) {
		steps=n;
	}
	public void stop() {
		t.stop();
	}
	public void start() {
		end=false;
		t.start();
	}
	public void cancel() {
		end=true;
		for(int i=0;i<steppables.size();i++)
		{
			remove(steppables.get(i));
		}
		t.stop();
	}
	public void setEnd(boolean b) {
		end=b;
		propertyChangeSupport.firePropertyChange("DEAD",false,true);
		propertyChangeSupport.firePropertyChange("DEAD",true,true);
	}
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	public boolean getEnd() {
		return end;
	}
}