package behavior;

import java.util.ArrayList;

import action.ActionQL;

public class State {

	private double reward;
	
	private int x;
	private int y;
	
	private ArrayList<ActionQL> listAction;
	
	public State(int x, int y){
		this.reward = 0;
		this.x=x;
		this.y=y;
	}
	
	
	
	public double getReward() {
		return reward;
	}



	public int getX() {
		return x;
	}



	public int getY() {
		return y;
	}



	public ArrayList<ActionQL> getListAction() {
		return listAction;
	}



	public void setReward(double reward) {
		this.reward = reward;
	}

	public void setListAction(ArrayList<ActionQL> listAction) {
		this.listAction = listAction;
	}
	
	public String toString(){
		return "("+x+";"+y+") reward : "+reward;
	}
	
}
