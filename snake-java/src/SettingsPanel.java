import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingsPanel extends JPanel implements ActionListener{
	private Color color=Color.blue;
	private JComboBox<?> cbc;
	private JComboBox<?> cbl;
	private ArrayList<Boolean> level;
	public SettingsPanel() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBackground(Color.white);
		JLabel txt1=new JLabel();
		JLabel txt2=new JLabel();
		txt2.setText("Choose the difficulty:");
		txt2.setAlignmentX(Container.CENTER_ALIGNMENT);
		txt2.setFont(new Font("Serif", Font.BOLD, 20));
		txt1.setText("Choose the color of your snake:");
		txt1.setAlignmentX(Container.CENTER_ALIGNMENT);
		txt1.setFont(new Font("Serif", Font.BOLD, 20));
		this.add(Box.createRigidArea(new Dimension(0,150)));
		this.add(txt1);
		this.add(Box.createRigidArea(new Dimension(0,10)));
		String[] colors= {"Cyan", "Blue", "Green", "Purple"};
		cbc=new JComboBox(colors);
		cbc.setPreferredSize(new Dimension(100,40));
		cbc.setSize(cbc.getPreferredSize());
		cbc.setMaximumSize(cbc.getPreferredSize() );
		cbc.setBackground(Color.white);
		cbc.setFocusable(false);
		cbc.setSelectedIndex(1);
		cbc.setBounds(50, 50,90,20);
		cbc.addActionListener(this);
		cbc.setAlignmentX(Container.CENTER_ALIGNMENT);
		this.add(cbc);
		this.add(Box.createRigidArea(new Dimension(0,50)));
		this.add(txt2);
		this.add(Box.createRigidArea(new Dimension(0,10)));
		String[] levels= {"Easy", "Custom", "Hard", "Medium"};
		cbl=new JComboBox(levels);
		cbl.setBackground(Color.white);
		cbl.setAlignmentX(Container.CENTER_ALIGNMENT);
		cbl.setPreferredSize(new Dimension(100,40));
		cbl.setSize(cbl.getPreferredSize());
		cbl.setMaximumSize(cbl.getPreferredSize());
		cbl.setFocusable(false);
		cbl.setBounds(50, 50,10,20);
		cbl.addActionListener(this);
		this.add(cbl);
		this.setVisible(true);
		level=null;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch((String)cbc.getSelectedItem()) {
		case "Cyan":
			color=Color.cyan;
			break;
		case "Blue":
			color=Color.blue.brighter();
			break;
		case "Green":
			color=Color.green.darker().darker();
			break;
		case "Purple":
			color=Color.magenta;
			break;
		}
		switch((String)cbl.getSelectedItem()) {
		case "Easy":
			setLevel("easy");
			break;
		case "Medium":
			setLevel("medium");
			break;
		case "Hard":
			setLevel("hard");
			break;
		case "Custom":
			level=null;
			break;
		default:
			level=null;
			break;
		}
	}
	public void setLevel(String s) {
		FileInputStream f = null;
		level=new ArrayList<Boolean>();
		try {
			f = new FileInputStream(s);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ObjectInputStream in=new ObjectInputStream(f);
			try {
				level=(ArrayList<Boolean>)in.readObject();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public ArrayList<Boolean> getLevel(){
		return level;
	}
	public Color getColor() {
		return color;
	}
}
