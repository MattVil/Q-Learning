package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	public void initLayout(int taille){
		fond.setLayout(new GridLayout(taille, taille));
	}
	
	public void initMap(int taille){
		for(int i=0; i<taille; i++){
			for(int j=0; j<taille; j++){
				JPanel p = new JPanel();
				p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				p.add(new JLabel("[" + i + "," + j + "]", JLabel.CENTER));
				map[i][j] = p;
			}
		}
	}
	
	public void printMap(int taille){
		int index = 0;
		for(int i=0; i<taille; i++){
			for(int j=0; j<taille; j++){
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
		QLGui fenetre = new QLGui(10);
		
		for(int i=0; i<10; i++){
			for(int j=0; j<10; j++){
				int sizeList = fenetre.behavior.getState(i, j).getListAction().size();
				if(sizeList == 2)
					fenetre.setCase(i, j, new Color(0,0,255));
				if(sizeList == 3)
					fenetre.setCase(i, j, new Color(0,255,0));
				if(sizeList == 4)
					fenetre.setCase(i, j, new Color(255,0,0));
				System.out.println("["+i+","+j+"] - "+sizeList);
			}
		}
		fenetre.printMap(10);
	}
}
