package action;

import behavior.State;


/**
 * This class represente a action between 2 states
 * @author matthieu
 *
 */
public class ActionQL {
	private State currentState;
	private State nextState;
	
	private double value;
	
	public ActionQL(State current, State next){
		this.currentState = current;
		this.nextState = next;
		
		this.value = 0;
	}
	
	public ActionQL(){
		this.value = 0;
	}
	
	public double getValue(){
		return this.value;
	}
	
	public void setValue(double newValue){
		this.value = newValue;
	}
	
	public State getNextState(){
		return this.nextState;
	}
	
	public String toString(){
		return "("+currentState.getX()+";"+currentState.getY()+") --> "+value+" --> ("+nextState.getX()+";"+nextState.getY()+")";
	}
}
