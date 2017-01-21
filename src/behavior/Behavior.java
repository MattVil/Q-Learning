package behavior;

import java.util.ArrayList;

import action.ActionQL;

public class Behavior {
	private State[][] behavior;
	
	public Behavior(int size){
		behavior = new State[size][size];
		initBehavior(size);
	}
	
	public void initBehavior(int size){
		//state initialisation
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				State newState = new State(i, j);	
				behavior[i][j] = newState;
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
	
	public State getState(int x, int y){
		return behavior[x][y];
	}
}
