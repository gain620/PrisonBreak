package com.teamcriminals.GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	private int currentCharacter;	// 팩토리 연결하기 위해 캐릭터 변수 생성
	
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
	
	public int getCharacter() {	// 팩토리에서 이걸로 캐릭터 생성
		return currentCharacter;
	}
	
	public void setCharacter(int selection) { // select state에서 선택하면 이걸로 캐릭터정보 저장
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
