package com.teamcriminals.Entity;

import java.awt.*;
import com.teamcriminals.TileMap.TileMap;

public abstract class MapObject {
		
	// 위치, 벡터 정보
	protected double x, y;
	protected double dx, dy;
	
	// 크기 정보
	protected int width, height;
	
	// 타일 정보
	protected TileMap tileMap;
	protected int tileSize;
	protected double xMap , yMap;
	
	// 충돌 정보
	protected int cWidth, cHeight;
	protected int currRow, currCol;
	protected double xDest, yDest;
	protected double xTemp, yTemp;
	protected boolean topLeft, topRight, bottomLeft, bottomRight;
	
	// 모션
	protected Motion motion;
	protected int currentMotion;
	protected int prviousMotion;
	protected boolean faceRight;
	
	// 이동 정보
	protected boolean left, right;
	protected boolean up, down;
	protected boolean jump , fall;
	
	// 물리 정보
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
	
	public boolean intere
	
	
}
