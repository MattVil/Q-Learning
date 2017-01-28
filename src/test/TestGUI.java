package test;

import agent.Agent;
import gui.QLGui;

public class TestGUI {

	public static void main(String[] args) {
		//initialisation of window and map
		int sizeOfMap = 10;
		QLGui fenetre = new QLGui(sizeOfMap);
		
		//agent initialization
		Agent agent = new Agent(0,0);
		
		//new goal add
		fenetre.getBehavior().addGoal(5, 5, 100);
		fenetre.getBehavior().addGoal(5, 3, -100);
		fenetre.getBehavior().addGoal(3, 5, -100);
	
		fenetre.refreshMap();
		
		while(true){
			fenetre.run();
		}
		
	}

}
