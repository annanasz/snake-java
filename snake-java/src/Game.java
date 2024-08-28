import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.lang.Boolean;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Game extends JFrame implements ActionListener,PropertyChangeListener{
	private static final int WIDTH=1000;
	private static final int HEIGHT=650;
	private boolean singleplayer=false;
	private JTextField name;
	private JPanel panel;
	private CardLayout cards;
	private CardLayout controls;
	private GamePanel gamepanel;
	private FieldPlanner fp;
	private JPanel controlPanel;
	private SettingsPanel sp;
	private JPanel gameover;
	private JPanel leaderboardPanel;
	private HashMap<String,Integer> leaderboard=new HashMap<String,Integer>();
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("DEAD"))
		{
			if(singleplayer)
				{
					gameover.getComponent(1).setVisible(true);
					gameover.getComponent(2).setVisible(true);
					gameover.getComponent(3).setVisible(true);
					gameover.getComponent(4).setVisible(false);
				}
			else {
				gameover.getComponent(1).setVisible(false);
				gameover.getComponent(2).setVisible(false);
				gameover.getComponent(3).setVisible(false);
				gameover.getComponent(4).setVisible(true);
			}
			cards.show(panel, "GAMEOVER");
			controlPanel.setVisible(false);
		}		
	}
	public Game() {
		super("Snake Game");
		this.setLayout(new BorderLayout(20,20));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(WIDTH, HEIGHT);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setLocationRelativeTo(null);
		controls=new CardLayout();
		controlPanel=new JPanel(controls);
		cards=new CardLayout();
		panel=new JPanel(cards);
		this.add(panel, BorderLayout.CENTER);
		this.add(controlPanel, BorderLayout.NORTH);
		JPanel menuPanel=new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.Y_AXIS));
		menuPanel.setAlignmentX(CENTER_ALIGNMENT);
		JButton spButton=new JButton("Singleplayer");
		menuPanel.add(Box.createRigidArea(new Dimension(0,10)));
		JButton mpButton=new JButton("Multiplayer");
		JButton lbButton=new JButton("Leaderboard");
		JButton cButton=new JButton("Close");
		BufferedImage Snakeimage=null;
		try {
			Snakeimage = ImageIO.read(new File("snake.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel Snakepicture=new JLabel(new ImageIcon(Snakeimage));
		Snakepicture.setAlignmentX(Component.CENTER_ALIGNMENT);
		spButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		mpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		spButton.setFocusable(false);
		mpButton.setFocusable(false);
		lbButton.setFocusable(false);
		cButton.setFocusable(false);
		lbButton.addActionListener(this);
		spButton.addActionListener(this);
		cButton.addActionListener(this);
		mpButton.addActionListener(this);
		spButton.setBackground(Color.green.darker().darker());
		mpButton.setBackground(Color.yellow);
		lbButton.setBackground(Color.pink);
		cButton.setBackground(Color.gray);
		menuPanel.add(Snakepicture);
		menuPanel.add(Box.createRigidArea(new Dimension(0,50)));
		menuPanel.add(spButton);
		menuPanel.add(Box.createRigidArea(new Dimension(0,20)));
		menuPanel.add(mpButton);
		menuPanel.add(Box.createRigidArea(new Dimension(0,20)));
		menuPanel.add(lbButton);
		menuPanel.add(Box.createRigidArea(new Dimension(0,20)));
		menuPanel.add(cButton);
		menuPanel.setVisible(true);
		menuPanel.setBackground(Color.white);
		panel.add(menuPanel,"MENU");
		this.setVisible(true);
		controlPanel.setVisible(false);
		Settings();
		PlanField();
		MyTimer t=MyTimer.getInstance();
		t.addPropertyChangeListener(this);
	    gameover=new JPanel();
		gameover.setLayout(null);
		gameover.setBackground(Color.black);
		JButton cancel=new JButton("Cancel");
		cancel.setBackground(Color.red);
		cancel.setBounds(450, 500, 100, 20);
		cancel.addActionListener(this);
		BufferedImage image=null;
		try {
			image = ImageIO.read(new File("gameover.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picture=new JLabel(new ImageIcon(image));
		gameover.add(picture);
		picture.setBounds(300, 50, 400, 300);
		name=new JTextField();
		JLabel txt=new JLabel();
		txt.setFont(new Font("Serif", Font.BOLD, 14));
		txt.setText("Name:");
		txt.setForeground(Color.white);
		txt.setBounds(400,350,50,30);
		name.setBounds(450, 350, 100, 30);
		gameover.add(name);
		gameover.add(txt);
		name.addActionListener(this);
		panel.add(gameover,"GAMEOVER");
		JButton ok=new JButton("Ok");
		ok.setBackground(Color.red);
		ok.setBounds(570, 350, 50, 30);
		ok.addActionListener(this);
		gameover.add(ok);
		gameover.add(cancel);
		ok.setFocusable(true);
		Leaderboard();
	}
	public void Singleplayer(ArrayList<Boolean> list) {
		JButton pauseButton=new JButton("Pause");
		pauseButton.setBackground(Color.yellow);
		JButton cancelButton=new JButton("Cancel");
		cancelButton.setBackground(Color.pink);
		JLabel pointsLabel=new JLabel("Points:");
		pointsLabel.setFocusable(false);
		cancelButton.setFocusable(false);
		pauseButton.setFocusable(false);
		JLabel point=new JLabel("0");
		JPanel controlPanelGame=new JPanel(new FlowLayout());
		controlPanelGame.setBackground(Color.white);
		pauseButton.addActionListener(this);
		cancelButton.addActionListener(this);
		controlPanelGame.add(cancelButton);
		controlPanelGame.add(pointsLabel);
		controlPanelGame.add(point);
		controlPanelGame.add(pauseButton);
		controlPanel.add(controlPanelGame,"GAMECONTROL");
		GamePanel gp=new GamePanel(point,list,sp.getColor());
		gp.setPreferredSize(new Dimension(WIDTH-100,HEIGHT-100));
		gp.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));
		gp.setBackground(Color.GREEN);
		this.addKeyListener(gp);
		panel.add(gp,"GAMEPANEL");
		gamepanel=gp;
		pack();
		this.setVisible(true);
	}
	public void PlanField() {
		FieldPlanner fieldplanner=new FieldPlanner();
		fp=fieldplanner;
		panel.add(fp,"FIELDPLANNER");
		JPanel controlPanelField=new JPanel(new FlowLayout());
		controlPanelField.setBackground(Color.white);
		JButton start=new JButton("Done");
		start.setBackground(Color.yellow);
		start.addActionListener(this);
		start.setFocusable(false);
		controlPanelField.add(start);
		controlPanel.add(controlPanelField,"FIELDCONTROL");
	}
	public void Settings() {
		SettingsPanel s=new SettingsPanel();
		sp=s;
		JPanel controlPanelSettings=new JPanel(new FlowLayout());
		controlPanelSettings.setBackground(Color.white);
		JButton ok=new JButton("OK");
		ok.addActionListener(this);
		ok.setFocusable(false);
		ok.setBackground(Color.pink);
		controlPanelSettings.add(ok);
		controlPanel.add(controlPanelSettings,"SETTINGSCONTROL");
		panel.add(sp,"SETTINGSPANEL");
	}
	public void Leaderboard() {
		FileInputStream f = null;
		try {
			f = new FileInputStream("leaderboard");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leaderboard=(HashMap<String, Integer>) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		leaderboardPanel=new JPanel();
		panel.add(leaderboardPanel,"LEADERBOARDPANEL");
		JPanel c=new JPanel();
		JButton back=new JButton("Back");
		back.addActionListener(this);
		c.add(back);
		back.setFocusable(false);
		controlPanel.add(c,"LEADERBOARDCONTROL");
		String[] column= {"PLACE","NAME", "POINTS"};
		Integer place=1;
		int i=0,j=0;
		String[][] data=new String[10][3];
		for (Entry<String, Integer> entry : leaderboard.entrySet()) {
			data[i][j++]=place.toString();
			place++;
			data[i][j++]=entry.getKey();
			data[i++][j++]= entry.getValue().toString();
			j=0;
		}
		JTable table=new JTable(data,column);
		table.setCellSelectionEnabled(false);
		leaderboardPanel.add(table);
		leaderboardPanel.setBackground(Color.pink);
		back.setBackground(Color.pink);
		table.setEnabled(false);
	}
	public  HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
	    {
	        List<Map.Entry<String, Integer> > list
	            = new LinkedList<Map.Entry<String, Integer> >(
	                hm.entrySet());
	        Collections.sort(
	            list,
	            (i1,
	             i2) -> i1.getValue().compareTo(i2.getValue()));
	        Collections.reverse(list);
	        HashMap<String, Integer> temp
	            = new LinkedHashMap<String, Integer>();
	        for (Map.Entry<String, Integer> aa : list) {
	            temp.put(aa.getKey(), aa.getValue());
	        }
	        return temp;
	    }
	public void updateLeaderboard(String name, Integer points) {
		leaderboard.put(name, points);
		HashMap<String, Integer> temp=new HashMap<String, Integer>();
		temp=sortByValue(leaderboard);
		leaderboard=temp;
		if(leaderboard.size()>10) {
			Object firstKey = leaderboard.keySet().toArray()[10];
			leaderboard.remove(firstKey);
		}
		String[] column= {"PLACE","NAME", "POINTS"};
		Integer place=1;
		int i=0,j=0;
		String[][] data=new String[10][3];
		for (Entry<String, Integer> entry : leaderboard.entrySet()) {
			data[i][j++]=place.toString();
			place++;
			data[i][j++]=entry.getKey();
			data[i++][j++]= entry.getValue().toString();
			j=0;
		}
		JTable table=new JTable(data,column);
		leaderboardPanel.remove(0);
		leaderboardPanel.update(getGraphics());
		leaderboardPanel.add(table);
		table.setCellSelectionEnabled(false);
		table.setEnabled(false);
		saveLeaderboard();
	}
	public void saveLeaderboard() {
		FileOutputStream f = null;
		try {
			f = new FileOutputStream("leaderboard");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.writeObject(leaderboard);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Multiplayer() {
		JButton pauseButton=new JButton("Pause");
		pauseButton.setBackground(Color.yellow);
		JButton cancelButton=new JButton("Cancel");
		cancelButton.setBackground(Color.pink);
		JLabel pointsLabel1=new JLabel("Points Snake1:");
		JLabel pointsLabel2=new JLabel("Points Snake2:");
		pointsLabel1.setFocusable(false);
		pointsLabel2.setFocusable(false);
		cancelButton.setFocusable(false);
		pauseButton.setFocusable(false);
		JLabel point1=new JLabel("0");
		JLabel point2=new JLabel("0");
	    JPanel controlPanelMultiplayer=new JPanel(new FlowLayout());
		pauseButton.addActionListener(this);
		cancelButton.addActionListener(this);
		controlPanelMultiplayer.add(cancelButton);
		controlPanelMultiplayer.add(pointsLabel1);
		controlPanelMultiplayer.add(point1);
		controlPanelMultiplayer.add(pointsLabel2);
		controlPanelMultiplayer.add(point2);
		controlPanelMultiplayer.add(pauseButton);
		controlPanel.add(controlPanelMultiplayer,"MULTIPLAYERCONTROL");
		GamePanelMultiplayer gpm=new GamePanelMultiplayer(point1,point2);
		gpm.setPreferredSize(new Dimension(WIDTH-100,HEIGHT-100));
		gpm.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		gpm.setBackground(Color.GREEN);
		this.addKeyListener(gpm);
		gamepanel=gpm;
		panel.add(gpm,"MULTILPLAYERPANEL");
		pack();
	}
	public static void main(String[] args) {
		Game g=new Game();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		MyTimer t=MyTimer.getInstance();
		switch(e.getActionCommand()) {
		case "Pause":
			t.stop();
			JButton b=((JButton)e.getSource());
			b.setText("Resume");
			break;
		case "Resume":
			t.start();
			JButton bt=((JButton)e.getSource());
			bt.setText("Pause");
			break;
		case "Cancel":
			t.cancel();
			cards.show(panel,"MENU");
			controlPanel.setVisible(false);
			singleplayer=false;
			break;
		case "Done":
			Singleplayer(fp.getObstacles());
			cards.show(panel, "GAMEPANEL");
			controls.show(controlPanel,"GAMECONTROL");
			break;
		case "Singleplayer":
			singleplayer=true;
			controlPanel.setVisible(true);
			fp.clear();
			t.start();
			cards.show(panel, "SETTINGSPANEL");
			controls.show(controlPanel, "SETTINGSCONTROL");
			break;
		case "Multiplayer":
			t.start();
			Multiplayer();
			controlPanel.setVisible(true);
			controls.show(controlPanel, "MULTIPLAYERCONTROL");
			cards.show(panel, "MULTILPLAYERPANEL");
			break;
		case "Leaderboard":
			cards.show(panel, "LEADERBOARDPANEL");
			controls.show(controlPanel,"LEADERBOARDCONTROL");
			controlPanel.setVisible(true);
			break;
		case "OK":
			if(sp.getLevel()==null)
			{
				cards.show(panel, "FIELDPLANNER");
				controls.show(controlPanel, "FIELDCONTROL");
			}
			else {
				Singleplayer(sp.getLevel());
				cards.show(panel, "GAMEPANEL");
				controls.show(controlPanel,"GAMECONTROL");
			}
			break;
		case "Back":
			cards.show(panel, "MENU");
			controlPanel.setVisible(false);
			break;
		case "Ok":
			cards.show(panel, "MENU");
			this.requestFocusInWindow();
			//name.transferFocus();
			updateLeaderboard(name.getText(),gamepanel.getPoints());
			name.setText("");
			break;
		case "Close":
			this.setVisible(false);
			this.dispose();
			break;
		}
		
	}
}
