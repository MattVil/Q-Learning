package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import agent.Agent;
import behavior.Behavior;


/**
 * this class is the display of the behavior with reward and agent
 * @author matthieu
 *
 */
public class QLGui extends JFrame{

	private JPanel fond = new JPanel();
	private JPanel[][] map;
	
	private Behavior behavior;
	private int size;
	
	private static int speed = 200;
	
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
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * initialization of the grid layout
	 */
	public void initLayout(){
		fond.setLayout(new GridLayout(size, size));
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
				fond.add(map[i][j]);
			}
		}
	}
	
	/**
	 * change the map to synchronize the map and the behavior
	 */
	public void refreshMap(){
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(behavior.getState(i, j).getReward() == 0){
					map[i][j].setBackground(new Color(192,192,192));
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
				fond.setLayout(new GridLayout(size, size));
				fond.add(map[i][j]);
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
	
	public static void main(String[] args) {
		
		int sizeOfMap = 10;
		
		QLGui fenetre = new QLGui(sizeOfMap);
		
		Agent agent = new Agent(0,0);
		
		fenetre.behavior.addGoal(5, 5, 100);
		fenetre.behavior.addGoal(2, 8, -10);
	
		fenetre.refreshMap();
		
		for(int i=0; i<5; i++){
			try{
				Thread.sleep(speed);
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
			fenetre.behavior.moveAgent("down");
			fenetre.refreshMap();
		}
		
		
	}
}
