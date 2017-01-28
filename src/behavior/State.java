package behavior;

import java.util.ArrayList;

import action.ActionQL;


/**
 * this class represente the state whitch compose the behavior
 * @author matthieu
 *
 */
public class State {

	private double reward;
	
	private int x;
	private int y;
	
	private ArrayList<ActionQL> listAction;
	
	private double QValueAverage;
	
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

	public double getQValueAverage(){
		double summe = 0;
		double average;
		
		for(int i=0; i<listAction.size(); i++){
			summe += listAction.get(i).getValue();
		}
		
		average = summe/4;
		return average;
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
