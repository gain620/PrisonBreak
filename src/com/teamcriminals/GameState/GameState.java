package com.teamcriminals.GameState;

import java.awt.Graphics2D;

// ���� ������Ʈ�� �߻� Ŭ����
public abstract class GameState {
	
	// ��� ���� ������Ʈ�� ������Ʈ�� ���� �� �� �־�� �ϹǷ�
	// �⺻������ gsm ����
	protected GameStateManager gsm;

	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
}