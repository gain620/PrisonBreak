package com.teamcriminals.GameState;

import java.awt.Color;
import java.awt.Graphics2D;

import com.teamcriminals.Game.GamePanel;
import com.teamcriminals.TileMap.TileMap;

public class Level1State extends GameState {

	private TileMap tilemap;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {
		
		tilemap = new TileMap(30);
		tilemap.loadTiles("Tilesets/testtileset.gif");
		tilemap.loadMap("Maps/level1.map");
		tilemap.setPosition(0, 0);
		
	}

	public void update() {

	}

	public void draw(Graphics2D g) {
		
		// 화면 정리
		g.setColor(Color.RED);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		// 타일맵 draw
		tilemap.draw(g);
	}

	public void keyPressed(int k) {

	}

	public void keyReleased(int k) {
	}

}
