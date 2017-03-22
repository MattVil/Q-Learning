package Environment;

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

	public void setQValueAverage(){
		double summe = 0;
		
		for(int i=0; i<listAction.size(); i++){
			summe += listAction.get(i).getValue();
		}
		
		QValueAverage = summe/4;
	}
	
	public double getQValueAverage(){
		return QValueAverage;
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
		return "("+x+";"+y+") QV Average : "+(int)QValueAverage*10;
	}
	
}
