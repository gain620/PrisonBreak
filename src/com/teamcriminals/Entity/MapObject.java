package com.teamcriminals.Entity;

import java.awt.*;

import com.teamcriminals.TileMap.*;
import com.teamcriminals.Game.GamePanel;
import com.teamcriminals.Motion.Motion;

public abstract class MapObject {

	// ��ġ, ���� ����
	protected double x, y;
	protected double dx, dy;

	// ũ�� ����
	protected int width, height;

	// Ÿ�� ����
	protected TileMap tileMap;
	protected int tileSize;
	protected double xMap, yMap;

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
	protected boolean jump, fall;

	// ���� ����
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;

	// �� ������Ʈ ������, Ÿ�ϸ� �ҷ���
	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}

	// Get �޼ҵ�
	public int getX() 											{ return (int)x; }
	public int getY() 											{ return (int)y;}
	public int getDX() 											{ return (int)dx; }
	public int getDY() 											{ return (int)dy;}
	public int getWidth() 										{ return width; }
	public int getHeight() 										{ return height; }
	public int getCWidth() 										{ return cWidth; }
	public int getCHeight() 									{ return cHeight; }
	public TileMap getTileMap() 								{ return tileMap; }
	public int getTileSize() 									{ return tileSize; }
	public Motion getMotion() 									{ return motion; }
	public int getCurrentMotion() 								{ return currentMotion; }
	public int getPrviousMotion() 								{ return prviousMotion; }
	public boolean isFaceRight() 								{ return faceRight; }
	public double getxMap() 									{ return xMap; }
	public double getyMap() 									{ return yMap; }
	public int getCurrRow() 									{ return currRow; }
	public int getCurrCol() 									{ return currCol; }
	public double getxDest() 									{ return xDest; }
	public double getyDest() 									{ return yDest; }
	public double getxTemp() 									{ return xTemp; }
	public double getyTemp() 									{ return yTemp; }
	public boolean isTopLeft() 									{ return topLeft; }
	public boolean isTopRight() 								{ return topRight; }
	public boolean isBottomLeft() 								{ return bottomLeft; }
	public boolean isBottomRight() 								{ return bottomRight; }
	public boolean isFall() 									{ return fall; }
	public double getMoveSpeed() 								{ return moveSpeed; }
	public double getMaxSpeed() 								{ return maxSpeed; }
	public double getStopSpeed() 								{ return stopSpeed; }
	public double getFallSpeed() 								{ return fallSpeed; }
	public double getMaxFallSpeed() 							{ return maxFallSpeed; }
	public double getJumpStart() 								{ return jumpStart; }
	public double getStopJumpSpeed() 							{ return stopJumpSpeed; }
	public boolean isLeft() 									{ return left; }
	public boolean isRight() 									{ return right; }
	public boolean isUp() 										{ return up; }
	public boolean isDown() 									{ return down; }
	public boolean isJump() 									{ return jump; }
	
	// Set �޼ҵ�
	public void setX(double x)									{ this.x = x; }
	public void setY(double y) 									{ this.y = y; }
	public void setDx(double dx) 								{ this.dx = dx;}
	public void setDy(double dy)								{ this.dy = dy;}
	public void setTileSize(int tileSize) 						{ this.tileSize = tileSize; }
	public void setxMap(double xMap) 							{ this.xMap = xMap; }
	public void setyMap(double yMap)							{ this.yMap = yMap; }
	public void setcWidth(int cWidth) 							{ this.cWidth = cWidth; }
	public void setcHeight(int cHeight) 						{ this.cHeight = cHeight; }
	public void setCurrRow(int currRow) 						{ this.currRow = currRow; }
	public void setCurrCol(int currCol) 						{ this.currCol = currCol; }
	public void setxDest(double xDest) 							{ this.xDest = xDest; }
	public void setyDest(double yDest) 							{ this.yDest = yDest; }
	public void setxTemp(double xTemp)							{ this.xTemp = xTemp; }
	public void setyTemp(double yTemp) 							{ this.yTemp = yTemp; }
	public void setTopLeft(boolean topLeft) 					{ this.topLeft = topLeft; }
	public void setTopRight(boolean topRight) 					{ this.topRight = topRight; }
	public void setBottomLeft(boolean bottomLeft) 				{ this.bottomLeft = bottomLeft; }
	public void setBottomRight(boolean bottomRight) 			{ this.bottomRight = bottomRight; }
	public void setPrviousMotion(int prviousMotion) 			{ this.prviousMotion = prviousMotion; }
	public void setFall(boolean fall) 							{ this.fall = fall; }
	public void setMaxSpeed(double maxSpeed) 					{ this.maxSpeed = maxSpeed; }
	public void setMoveSpeed(double moveSpeed)					{ this.moveSpeed = moveSpeed; }
	public void setStopSpeed(double stopSpeed) 					{ this.stopSpeed = stopSpeed; }
	public void setFallSpeed(double fallSpeed) 					{ this.fallSpeed = fallSpeed; }
	public void setMaxFallSpeed(double maxFallSpeed) 			{ this.maxFallSpeed = maxFallSpeed; }
	public void setJumpStart(double jumpStart) 					{ this.jumpStart = jumpStart; }
	public void setStopJumpSpeed(double stopJumpSpeed) 			{ this.stopJumpSpeed = stopJumpSpeed; }
	public void setWidth(int width) 							{ this.width = width; }
	public void setHeight(int height) 							{ this.height = height; }
	public void setTileMap(TileMap tileMap) 					{ this.tileMap = tileMap; }
	public void setMotion(Motion motion) 						{ this.motion = motion; }
	public void setCurrentMotion(int currentMotion) 			{ this.currentMotion = currentMotion; }
	public void setFaceRight(boolean faceRight) 				{ this.faceRight = faceRight; }
	
	
	// ��ǥ �޼ҵ�
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
	
	// �� ������Ʈ�� ��ũ���� �����ϴ��� ����
	public boolean onScreen() {
		return x + xMap + width < 0 || 
			   x + xMap - width >GamePanel.WIDTH ||
			   y + yMap + height <0 ||
			   y + yMap - height > GamePanel.HEIGHT;
	}

	// 62�� ���� �������� ���� ��� �浹 �Լ� ����

	// �浹 ���� �Լ�(�簢�� ���)
	public boolean collide(MapObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}

	// �浹 ���� ���� �ҷ�����
	public Rectangle getRectangle() {
		return new Rectangle((int) x - cWidth, (int) y - cHeight, cWidth,
				cHeight);
	}

	// Ÿ�ϰ��� �浹 ���� �Լ�
	public void collideTile() {

		currCol = (int) x / tileSize;
		currRow = (int) y / tileSize;

		xDest = x + dx;
		yDest = y + dy;

		xTemp = x;
		yTemp = y;

		// y �� ���� �浹 ����
		calculateCorners(x, yDest);

		// ���� ���� ��
		if (dy < 0) {
			if (topLeft || topRight) {
				dy = 0;
				yTemp = currRow * tileSize + cHeight / 2;
			} else {
				yTemp += dy;
			}
		}

		// �Ʒ��� ���� ��
		if (dy > 0) {
			if (bottomLeft || bottomRight) {
				dy = 0;
				fall = false;
				// 1�ȼ� �� (�� ����)
				yTemp = (currRow + 1) * tileSize + cHeight / 2;
			} else {
				yTemp += dy;
			}
		}

		// ���� ����
		// ���� ���°� �ƴ� ���� ����
		if (!fall) {
			calculateCorners(x, yDest + 1);

			// ����
			if (!bottomLeft && !bottomRight) {
				fall = true;
			}
		}

		// x�� ���� �浹 ����
		calculateCorners(xDest, y);

		// �������� �� ��
		if (dx < 0) {
			if (topLeft || bottomLeft) {
				dx = 0;
				xTemp = currCol * tileSize + cWidth / 2;
			} else {
				xTemp += dx;
			}

		}

		// ���������� �� ��
		if (dx > 0) {
			if (topRight || bottomRight) {
				dx = 0;
				xTemp = (currCol + 1) * tileSize - cWidth / 2;
			} else {
				xTemp += dx;
			}
		}

	}

	// Ÿ�� �浹 ������ ���� ���� �𼭸� Ÿ�� ����
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