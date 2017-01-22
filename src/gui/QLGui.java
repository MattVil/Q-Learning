package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import agent.Agent;
import behavior.Behavior;

public class QLGui extends JFrame{

	private JPanel fond = new JPanel();
	private JPanel[][] map;
	
	private Behavior behavior;
	
	public QLGui(int size){
		
		behavior = new Behavior(size);
		
		map = new JPanel[size][size];
		this.getContentPane().add(fond);
		initLayout(size);
		initMap(size);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void initLayout(int size){
		fond.setLayout(new GridLayout(size, size));
	}
	
	public void initMap(int size){
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				JPanel p = new JPanel();
				p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				p.add(new JLabel("[" + i + "," + j + "]", JLabel.CENTER));
				
				map[i][j] = p;
			}
		}
	}
	
	public void refreshMap(int size){
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				//coloration des case sans reward
				if(behavior.getState(i, j).getReward() == 0 ){
					this.setCase(i, j, new Color(175,175,175));
					System.out.println("("+i+"-"+j+") -> "+behavior.getState(i, j).getReward());
				}
				
				//coloration de l'agent
				if(behavior.getAgentQL().getPosX() == i && behavior.getAgentQL().getPosY() == j){
					this.setCase(i, j, new Color(0,0,255));
					System.out.println("Agent : ("+i+"-"+j+") -> "+behavior.getState(i, j).getReward());
				}
				
				//coloration du/des reward(s)
				if(behavior.getState(i, j).getReward()>0){
					this.setCase(i, j, new Color(0,255,0));
					System.out.println("reward : ("+i+"-"+j+") ->"+behavior.getState(i, j).getReward());
				}
				else if(behavior.getState(i, j).getReward()<0){
					this.setCase(i, j, new Color(255,0,0));
					System.out.println("reward : ("+i+"-"+j+") ->"+behavior.getState(i, j).getReward());
				}
			}
		}
	}
	
	
	public void printMap(int size){
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				fond.add(map[i][j]);
			}
		}
	}
	
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
	
		fenetre.behavior.addGoal(5, 7, 100);
		fenetre.behavior.addGoal(0, 9, -10);
		
		fenetre.refreshMap(sizeOfMap);
		fenetre.printMap(sizeOfMap);
	}
}
