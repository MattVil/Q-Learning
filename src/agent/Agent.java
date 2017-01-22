package agent;

import behavior.State;

public class Agent {
	private int posX;
	private int posY;
	
	private State currentState;
	
	private static double learnFactor = 0.5;
	private static double discountedFactor = 0.5;
	
	public Agent(int x, int y){
		this.posX = x;
		this.posY = y;
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

	public static double getLearnFactor() {
		return learnFactor;
	}

	public static double getDiscountedFactor() {
		return discountedFactor;
	}

	public void setCurrentState(State currentState) {
		this.posX = currentState.getX();
		this.posY = currentState.getY();
		this.currentState = currentState;
	}
	
	
}
