package agent;

import Environment.State;


/**
 * This class represente a intelligent agent
 * @author matthieu
 *
 */
public class Agent {
	private int posX;
	private int posY;
	
	private int initialPosX;
	private int initialPosY;
	
	private State currentState;
	
	public Agent(int x, int y){
		this.initialPosX = x;
		this.initialPosY = y;
		
		this.posX = x;
		this.posY = y;
	}

	public void backToStart(){
		posX = initialPosX;
		posY = initialPosY;
	}
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.posX = currentState.getX();
		this.posY = currentState.getY();
		this.currentState = currentState;
	}
	
	
}
