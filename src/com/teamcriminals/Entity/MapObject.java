package com.teamcriminals.Entity;

import java.awt.*;

import com.teamcriminals.TileMap.*;
import com.teamcriminals.Game.GamePanel;
import com.teamcriminals.Motion.Motion;

public abstract class MapObject {

	// 위치, 벡터 정보
	protected double x, y;
	protected double dx, dy;

	// 크기 정보
	protected int width, height;

	// 타일 정보
	protected TileMap tileMap;
	protected int tileSize;
	protected double xMap, yMap;

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
	protected boolean jump, fall;

	// 물리 정보
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;

	// 맵 오브젝트 생성자, 타일맵 불러옴
	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getCWidth() {
		return cWidth;
	}
	
	public int getCHeight() {
		return cHeight;
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition() {
		xMap = tileMap.getx();
		yMap = tileMap.gety();
	}
	
	public void setLeft(boolean b) {
		left = b;
	}
	
	public void setRight(boolean b) {
		right = b;
	}
	
	public void setUp(boolean b) {
		up = b;
	}
	
	public void setDown(boolean b) {
		down = b;
	}
	
	public void setJump(boolean b) {
		jump = b;
	}
	
	
	// 맵 오브젝트가 스크린에 존재하는지 판정
	public boolean onScreen() {
		return x + xMap + width < 0 || 
			   x + xMap - width >GamePanel.WIDTH ||
			   y + yMap + height <0 ||
			   y + yMap - height > GamePanel.HEIGHT;
	}

	// 62번 라인 기준으로 이하 모두 충돌 함수 관련

	// 충돌 판정 함수(사각형 모양)
	public boolean collide(MapObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}

	// 충돌 판정 상자 불러오기
	public Rectangle getRectangle() {
		return new Rectangle((int) x - cWidth, (int) y - cHeight, cWidth,
				cHeight);
	}

	// 타일과의 충돌 판정 함수
	public void collideTile() {

		currCol = (int) x / tileSize;
		currRow = (int) y / tileSize;

		xDest = x + dx;
		yDest = y + dy;

		xTemp = x;
		yTemp = y;

		// y 축 기준 충돌 판정
		calculateCorners(x, yDest);

		// 위로 향할 때
		if (dy < 0) {
			if (topLeft || topRight) {
				dy = 0;
				yTemp = currRow * tileSize + cHeight / 2;
			} else {
				yTemp += dy;
			}
		}

		// 아래로 향할 때
		if (dy > 0) {
			if (bottomLeft || bottomRight) {
				dy = 0;
				fall = false;
				// 1픽셀 위 (열 기준)
				yTemp = (currRow + 1) * tileSize + cHeight / 2;
			} else {
				yTemp += dy;
			}
		}

		// 낙하 판정
		// 낙하 상태가 아닐 때를 기준
		if (!fall) {
			calculateCorners(x, yDest + 1);

			// 낙하
			if (!bottomLeft && !bottomRight) {
				fall = true;
			}
		}

		// x축 기준 충돌 판정
		calculateCorners(xDest, y);

		// 왼쪽으로 갈 때
		if (dx < 0) {
			if (topLeft || bottomLeft) {
				dx = 0;
				xTemp = currCol * tileSize + cWidth / 2;
			} else {
				xTemp += dx;
			}

		}

		// 오른쪽으로 갈 때
		if (dx > 0) {
			if (topRight || bottomRight) {
				dx = 0;
				xTemp = (currCol + 1) * tileSize - cWidth / 2;
			} else {
				xTemp += dx;
			}
		}

	}

	// 타일 충돌 판정을 위해 쓰일 모서리 타일 정보
	private void calculateCorners(double x, double y) {
		int leftTile = (int) (x - cWidth / 2) / tileSize;
		int rightTile = (int) (x + cWidth / 2 - 1) / tileSize;
		int topTile = (int) (y - cHeight / 2) / tileSize;
		int bottomTile = (int) (y + cHeight / 2 - 1) / tileSize;

		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);

		topLeft = tl == Tile.BLOCK;
		topRight = tr == Tile.BLOCK;
		bottomLeft = bl == Tile.BLOCK;
		bottomRight = br == Tile.BLOCK;

	}

}