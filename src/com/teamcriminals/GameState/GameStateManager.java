package com.teamcriminals.GameState;

import java.awt.Graphics2D;

public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentState;
	private int currentCharacter;	// 팩토리 연결하기 위해 캐릭터 변수 생성
	
	public static final int STATES = 16;
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
	public static final int GAMEOVER = 10;
	public static final int STAGESELECT = 11;
	public static final int CONTROLLER = 12;
	
	public GameStateManager() {
		
		gameStates = new GameState[STATES];
		
		currentState = MENUSTATE;
		
		loadState(currentState);
		
	}
	
	private void loadState(int state) {
		if(state == MENUSTATE) {
			gameStates[state] = new MenuState(this);
		}else if(state == SELECTSTATE) {
			gameStates[state] = new SelectState(this);
		}else if(state == LEVEL1STATE) {
			gameStates[state] = new Level1State(this);
		}else if(state == LEVEL2STATE) {
			gameStates[state] = new Level2State(this);
		}else if(state == LEVEL3STATE) {
			gameStates[state] = new Level3State(this);
		}else if(state == LEVEL4STATE) {
			gameStates[state] = new Level4State(this);
		}else if(state == LEVEL5STATE) {
			gameStates[state] = new Level5State(this);
		}else if(state == CREDITSTATE) {
			gameStates[state] = new CreditState(this);
		}else if(state == PAUSESTATE) {
			gameStates[state] = new PauseState(this);
		}else if(state == OPTONSTATE) {
			gameStates[state] = new OptionState(this);
		}else if(state == GAMEOVER) {
			gameStates[state] = new GameOver(this);
		}else if(state == STAGESELECT) {
			gameStates[state] = new StageSelectState(this);
		}else if(state == CONTROLLER) {
			gameStates[state] = new ControllerState(this);
		}
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}

	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	
	public int getCharacter() {	// 팩토리에서 이걸로 캐릭터 생성
		return currentCharacter;
	}
	
	public void setCharacter(int selection) { // select state에서 선택하면 이걸로 캐릭터정보 저장
		currentCharacter = selection;
	}
	
	public void update(){
		try{
		gameStates[currentState].update();
		}catch(Exception e) {
		}
	}
	
	public void draw(Graphics2D g) {
		try{
		gameStates[currentState].draw(g);
		}catch(Exception e) {
		}
	}
	
	public void KeyPressed(int k){
		gameStates[currentState].keyPressed(k);
	}
	
	public void KeyReleased(int k){
		gameStates[currentState].keyReleased(k);
	}
	
}
