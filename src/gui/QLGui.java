package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QLGui extends JFrame{

	private JPanel fond = new JPanel();
	private JPanel[][] map;
	
	public QLGui(int taille){
		
		map = new JPanel[taille][taille];
		this.getContentPane().add(fond);
		initLayout(taille);
		initMap(taille);
		printMap(taille);
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
	
	public void setCase(int x, int y, double coef){
		
		JPanel p = new JPanel();
		p.setBackground(new Color(0,0,(int)Math.floor(coef*200)));
		p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		p.add(new JLabel("[" + x + "," + y + "]", JLabel.CENTER));
		map[x][y] = p;
	}
	
	public static void main(String[] args) {
		QLGui fenetre = new QLGui(10);
		
	}
}
