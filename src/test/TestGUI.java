package test;

import action.ActionQL;
import agent.Agent;
import gui.QLGui;

public class TestGUI {
	
	private static int speed = 500;

	public static void main(String[] args) {
		//initialisation of window and map
		int sizeOfMap = 10;
		QLGui fenetre = new QLGui(sizeOfMap);
		
		//agent initialization
		Agent agent = new Agent(0,0);
		
		//new goal add
		fenetre.getBehavior().addGoal(5, 5, 100);
	
		fenetre.refreshMap();
		
		while(!fenetre.getStop()){
			
			ActionQL actionChosen = fenetre.getBehavior().QLDecision();
			fenetre.getBehavior().moveAgent(actionChosen);
			
			fenetre.refreshMap();
			
			try{
				Thread.sleep(speed);
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
		
	}

}
