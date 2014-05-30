package com.teamcriminals.Entity;

import java.awt.*;

import com.teamcriminals.Motion.Motion;
import com.teamcriminals.TileMap.TileMap;

public abstract class MapObject {
		
	// ��ġ, ���� ����
	protected double x, y;
	protected double dx, dy;
	
	// ũ�� ����
	protected int width, height;
	
	// Ÿ�� ����
	protected TileMap tileMap;
	protected int tileSize;
	protected double xMap , yMap;
	
	// �浹 ����
	protected int cWidth, cHeight;
	protected int currRow, currCol;
	protected double xDest, yDest;
	protected double xTemp, yTemp;
	protected boolean topLeft, topRight, bottomLeft, bottomRight;
	
	// ���
	protected Motion motion;
	protected int currentMotion;
	protected int prviousMotion;
	protected boolean faceRight;
	
	// �̵� ����
	protected boolean left, right;
	protected boolean up, down;
	protected boolean jump , fall;
	
	// ���� ����
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	
	
	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}
	
	//public boolean intere
	
	
}
