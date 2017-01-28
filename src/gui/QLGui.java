package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import action.ActionQL;
import behavior.Behavior;


/**
 * this class is the display of the behavior with reward and agent
 * @author matthieu
 *
 */
public class QLGui extends JFrame{

	private JPanel fond = new JPanel();
	private JPanel mapPart = new JPanel();
	private JPanel settingPart = new JPanel();
	private JPanel[][] map;
	
	private Boolean startStop;
	JButton startStopButton = new JButton("START");
	
	private int speed = 500;
	private JLabel speedLabel = new JLabel("Speed : ");
	private JButton speedButton = new JButton("Change");
	
	private Behavior behavior;
	private int size;
	
	/**
	 * creation of the window and the representation of the behavior
	 * @param size
	 */
	public QLGui(int size){
		
		this.size = size;
		behavior = new Behavior(size);
		
		map = new JPanel[size][size];
		this.getContentPane().add(fond);
		initLayout();
		initMap();
		initButton();
		initSpeedPart();
		
		startStop = false;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 600);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * initialization of the grid layout
	 */
	public void initLayout(){
		fond.setLayout(new GridLayout(1, 2));
		fond.add(mapPart);
		fond.add(settingPart);
		mapPart.setLayout(new GridLayout(size, size));
		settingPart.setLayout(new BoxLayout(settingPart, BoxLayout.PAGE_AXIS));
	}
	
	public void initSpeedPart(){
		JPanel speedPart = new JPanel();
		speedPart.setLayout(new FlowLayout());
		speedPart.add(speedLabel);
		speedPart.add(new JTextField(String.valueOf(speed)));
		//speedButton.addActionListener(new ActionChangeSpeed());
		speedPart.add(speedButton);
		
		settingPart.add(speedPart);
	}
	
	public void initButton(){
		startStopButton.addActionListener(new ActionStopLearning());
		settingPart.add(startStopButton);
	}
	
	/**
	 * initialization of the map according to the behavior
	 */
	public void initMap(){
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				JPanel p = new JPanel();
				p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				p.add(new JLabel("[" + i + "," + j + "]", JLabel.CENTER));
				
				map[i][j] = p;
				mapPart.add(map[i][j]);
			}
		}
	}
	
	public void run(){
		while(startStop){
			
			ActionQL actionChosen = behavior.QLDecision();
			behavior.moveAgent(actionChosen);
			
			refreshMap();
			
			try{
				Thread.sleep(speed);
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * change the map to synchronize the map and the behavior
	 */
	public void refreshMap(){
		
		mapPart.setLayout(new GridLayout(size, size));
		
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(behavior.getState(i, j).getReward() == 0){
					if(behavior.getState(i, j).getQValueAverage() > 0){
						int color = 250- ((int)behavior.getState(i, j).getQValueAverage()*10);
						map[i][j].setBackground(new Color(255,color,color));
					}
					else
						map[i][j].setBackground(new Color(255,255,255));
				}
				if(behavior.getState(i, j).getReward() > 0){
					map[i][j].setBackground(new Color(51,255,51));
				}
				if(behavior.getState(i, j).getReward() < 0){
					map[i][j].setBackground(new Color(255,255,0));
				}
				if(behavior.getAgentQL().getPosX() == i && behavior.getAgentQL().getPosY() == j){
					map[i][j].setBackground(new Color(0,128,255));
				}
				
				mapPart.add(map[i][j]);
			}
		}
	}

	/**
	 * change the case (x,y) on the map and put the color color
	 * @param x
	 * @param y
	 * @param color
	 */
	public void setCase(int x, int y, Color color){
		
		JPanel p = new JPanel();
		p.setBackground(color);
		p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		p.add(new JLabel("[" + x + "," + y + "]", JLabel.CENTER));
		map[x][y] = p;
	}
	
	public Behavior getBehavior() {
		return behavior;
	}
	
	public void setStop(Boolean bool){
		this.startStop = bool;
	}
	
	public Boolean getStop(){
		return startStop;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	class ActionStopLearning implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(startStop){
				startStop = false;
				startStopButton.setText("START");
			}
			else{
				startStop = true;
				startStopButton.setText("STOP");
			}
		}
	}
	
	class ActionChangeSpeed implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int value = Integer.parseInt(speedLabel.getText());
			speed = value;
			speedLabel.setText(String.valueOf(speed));
		}
	}
}
