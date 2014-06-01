package com.teamcriminals.GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	private int currentCharacter;
	
	public static final int MENUSTATE = 0;
	public static final int SELECTSTATE = 1;
	public static final int LEVEL1STATE = 2;
	public static final int LEVEL2STATE = 3;
	public static final int LEVEL3STATE = 4;
	public static final int LEVEL4STATE = 5;
	public static final int LEVEL5STATE = 6;
	public static final int CREDITSTATE = 7;
	public static final int PAUSESTATE = 8;
	public static final int OPTONSTATE = 9;
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new SelectState(this));
		gameStates.add(new Level1State(this));
		
	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public int getCharacter() {
		return currentCharacter;
	}
	
	public void setCharacter(int selection) {
		currentCharacter = selection;
	}
	
	public void update(){
		gameStates.get(currentState).update();
	}
	
	public void draw(Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void KeyPressed(int k){
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void KeyReleased(int k){
		gameStates.get(currentState).keyReleased(k);
	}

}
