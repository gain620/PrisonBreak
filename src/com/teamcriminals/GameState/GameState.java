package com.teamcriminals.GameState;

import java.awt.Graphics2D;

// 게임 스테이트는 추상 클래스
public abstract class GameState {
	
	// 모든 게임 스테이트는 스테이트를 변경 할 수 있어야 하므로
	// 기본적으로 gsm 가짐
	protected GameStateManager gsm;

	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
}