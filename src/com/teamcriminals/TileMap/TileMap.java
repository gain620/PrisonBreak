package com.teamcriminals.TileMap;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import com.teamcriminals.Game.GamePanel;

public class TileMap {
	// 위치 설정
	private double x, y;

	// 경계 설정
	private int xMin;
	private int yMin;
	private int xMax;
	private int yMax;

	// 카메라 움직임
	private double tween;

	// 맵 배열 & 크기 설정
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;

	// 타일셋 정보
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;

	// 카메라를 통해 보이는 것만 그려준다
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;

	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;
		tween = 0.07;
	}

	// 타일 로드
	public void loadTiles(String s) {

		try {

			tileset = ImageIO.read(getClass().getResourceAsStream(s));

			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];

			BufferedImage subImage;
			for (int col = 0; col < numTilesAcross; col++) {
				subImage = tileset.getSubimage(col * tileSize, 0, tileSize,
						tileSize);

				tiles[0][col] = new Tile(subImage, Tile.AIR);

				subImage = tileset.getSubimage(col * tileSize, tileSize,
						tileSize, tileSize);

				tiles[1][col] = new Tile(subImage, Tile.BLOCK);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 맵 로드
	public void loadMap(String s) {

		try {

			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;

			xMin = GamePanel.WIDTH - width;
			xMax = 0;
			yMin = GamePanel.HEIGHT - height;
			yMax = 0;

			String delims = "\\s+";
			for (int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for (int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getTileSize() {
		return tileSize;
	}

	public double getx() {
		return x;
	}

	public double gety() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}
	
	public int getNumRows() { return numRows; }
	public int getNumCols() { return numCols; }

	public void setTween(double d) {
		tween = d;
	}

	public void setPosition(double x, double y) {

		System.out.println(this.x);
		System.out.println((x - this.x) * tween);

		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;

		System.out.println(this.x + "\n==========");

		fixBounds();

		colOffset = (int) -this.x / tileSize;
		rowOffset = (int) -this.y / tileSize;

	}

	private void fixBounds() {
		if (x < xMin)
			x = xMin;
		if (y < yMin)
			y = yMin;
		if (x > xMax)
			x = xMax;
		if (y > yMax)
			y = yMax;
	}

	public void draw(Graphics2D g) {

		for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {

			if (row >= numRows)
				break;

			for (int col = colOffset; col < colOffset + numColsToDraw; col++) {

				if (col >= numCols)
					break;

				if (map[row][col] == 0)
					continue;

				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;

				g.drawImage(tiles[r][c].getImage(), (int) x + col * tileSize,
						(int) y + row * tileSize, null);

			}

		}

	}

}