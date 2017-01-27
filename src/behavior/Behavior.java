package behavior;

import java.util.ArrayList;
import java.util.Random;

import action.ActionQL;
import agent.Agent;

/**
 * 
 * This class represente the behavior of a agent. it is a gridy environement 
 * @author matthieu
 *
 */
public class Behavior {
	private State[][] behavior;
	private int size;
	
	private Agent agentQL;
	
	private static double learnFactor = 0.5;
	private static double discountedFactor = 0.5;
	
	/**
	 * Create a behavior of size*size
	 * @param size
	 */
	public Behavior(int size){
		this.size = size;
		behavior = new State[size][size];
		agentQL = new Agent(0, 0);
		initBehavior();
	}
	
	
	/**
	 * initialize a simple behavior.
	 * -create all states
	 * -create all actions between all states
	 */
	public void initBehavior(){
		//state initialisation
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				State newState = new State(i, j);	
				behavior[i][j] = newState;
				
				//initialisation de l'etat de l'agent
				if(agentQL.getPosX() == i && agentQL.getPosY() == j)
					agentQL.setCurrentState(newState);
			}
		}
		
		//Action initialisation
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				ArrayList<ActionQL> list = new ArrayList<ActionQL>();
				//verification de l'existance des voisins
				if(i>0){
					ActionQL actionDown = new ActionQL(behavior[i][j], behavior[i-1][j]);
					list.add(actionDown);
				}
				if(i<(size-1)){
					ActionQL actionTop = new ActionQL(behavior[i][j], behavior[i+1][j]);
					list.add(actionTop);
				}
				if(j>0){
					ActionQL actionLeft = new ActionQL(behavior[i][j], behavior[i][j-1]);
					list.add(actionLeft);
				}
				if(j<(size-1)){
					ActionQL actionRight = new ActionQL(behavior[i][j], behavior[i][j+1]);
					list.add(actionRight);
				}
					
				behavior[i][j].setListAction(list);	
			}
		}
		
	}
	
	/**
	 * this methode say to the action the right action to do
	 * @return action to do
	 */
	public ActionQL QLDecision(){
		ActionQL action;
		/*liste des actions possibles*/
		ArrayList<ActionQL> choiseList = agentQL.getCurrentState().getListAction();
		
		/*creation et remplissage d'une liste contenant le/les actions maximale(s)*/
		ArrayList<ActionQL> listMax = new ArrayList<ActionQL>();
		double max = choiseList.get(0).getValue();
		for(int i=0; i<choiseList.size(); i++){
			if(choiseList.get(i).getValue() > max){
				listMax.clear();
				listMax.add(choiseList.get(i));
				max = choiseList.get(i).getValue();
			}
			else if(choiseList.get(i).getValue() == max){
				listMax.add(choiseList.get(i));
			}
		}
		
		if(listMax.size()>1)
			action = randomActionChoise(listMax);
		else
			action = listMax.get(0);
		
		return action;
	}
	
	/**
	 * this methode choose a random action in a list of actions
	 * @param list of actions
	 * @return random action
	 */
	public ActionQL randomActionChoise(ArrayList<ActionQL> list){
		Random rand = new Random();
		int choise;
		
		choise = rand.nextInt(list.size());
		
		return list.get(choise);
	}
	
	/**
	 * this methode set the value a action using the Q-learning equation
	 * @param action
	 */
	public void setActionQValue(ActionQL action){
		double newValue;
		
		double maxFutureQValue = maxQValue(action.getNextState());
		
		//Q-Learning actualization
		newValue = action.getValue() + learnFactor * (action.getNextState().getReward() + discountedFactor * maxFutureQValue - action.getValue());
		
		action.setValue(newValue);
	}
	
	/**
	 * this methode found the the action with the biggest QValue of a state and return
	 * the biggest QValue
	 * @param state
	 * @return biggest QValue
	 */
	public double maxQValue(State state){
		double max = state.getListAction().get(0).getValue();
		
		for(int i=0; i<state.getListAction().size(); i++){
			if(state.getListAction().get(i).getValue() > max){
				max = state.getListAction().get(i).getValue();
			}
		}
		
		return max;
	}
	
	/**
	 * this methode change the state of the agent using a action
	 * @param nextState
	 */
	public void moveAgent(ActionQL action){
		
		agentQL.setCurrentState(action.getNextState());
		setActionQValue(action);
		
		//verification que l'etat suivant n'est pas un reward
		int posX = agentQL.getCurrentState().getX();
		int posY = agentQL.getCurrentState().getY();
		
		if(behavior[posX][posY].getReward() != 0){
			agentQL.setCurrentState(this.getState(0, 0));
		}
		
	}
	
	/**
	 * this methode change the state of the agent using a String of direction
	 * @param direction
	 */
	public void moveAgent(String direction){
		State actualState = agentQL.getCurrentState();
		switch(direction){
		case "up":
			if(actualState.getX() > 0)
				agentQL.setCurrentState(this.getState(actualState.getX()-1, actualState.getY()));
			break;
		case "down":
			if(actualState.getX() < (size-1))
				agentQL.setCurrentState(this.getState(actualState.getX()+1, actualState.getY()));
			break;
		case "right":
			if(actualState.getY() < (size-1))
				agentQL.setCurrentState(this.getState(actualState.getX(), actualState.getY()+1));
			break;
		case "left":
			if(actualState.getY() > 0)
				agentQL.setCurrentState(this.getState(actualState.getX(), actualState.getY()-1));
			break;
		}
	}
	
	
	/**
	 * the methode add a reward of the state (x, y)
	 * @param x
	 * @param y
	 * @param reward
	 */
	public void addGoal(int x, int y, double reward){
		behavior[x][y].setReward(reward);
	}
	
	/**
	 * this methode return the first goal found
	 * @return
	 */
	public State getGoal(){
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(behavior[i][j].getReward()>0){
					return behavior[i][j];
				}
			}
		}
		return null;
	}
	
	public State getState(int x, int y){
		return behavior[x][y];
	}

	public Agent getAgentQL() {
		return agentQL;
	}

	public void setAgentQL(Agent agentQL) {
		this.agentQL = agentQL;
	}
	
	
}
