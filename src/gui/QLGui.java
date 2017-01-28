package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	private Boolean stop;
	
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
		
		stop = false;
		
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
	}
	
	public void initButton(){
		JButton stopButton = new JButton("STOP");
		stopButton.addActionListener(new ActionStopLearning());
		settingPart.add(stopButton);
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
					map[i][j].setBackground(new Color(255,51,51));
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
		this.stop = bool;
	}
	
	public Boolean getStop(){
		return stop;
	}
	
	
	class ActionStopLearning implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setStop(true);
		}
	}
}
