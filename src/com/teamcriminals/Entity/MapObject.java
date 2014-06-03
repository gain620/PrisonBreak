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