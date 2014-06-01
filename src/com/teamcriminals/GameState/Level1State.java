package com.teamcriminals.GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.teamcriminals.Character.CharacterFactory;
import com.teamcriminals.Entity.TestVerCharacter;
import com.teamcriminals.Game.GamePanel;
import com.teamcriminals.TileMap.Background;
import com.teamcriminals.TileMap.TileMap;

public class Level1State extends GameState {
	// CharacterFactory cf = new CharacterFactory();

	private TileMap tilemap;
	private Background bg;
	private TestVerCharacter character;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	/*
	 * 테스트 public void setCharacterState() { cf.getCharacter(characterNum);
	 * 
	 * }
	 */

	public void init() {

		tilemap = new TileMap(30);
		tilemap.loadTiles("/Tilesets/Stage1Tileset.png");
		tilemap.loadMap("/Maps/Stage1.map");
		tilemap.setPosition(0, 0);
		tilemap.setTween(1);

		//character = new TestVerCharacter(tilemap);
		//character.setPosition(100, 100);

	}

	public void update() {

		// 캐릭터 업데이트
		//character.update();
		//tilemap.setPosition(GamePanel.WIDTH / 2 - character.getX(),
				//GamePanel.HEIGHT / 2 - character.getY());

	}

	public void draw(Graphics2D g) {

		// 화면 정리
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		// 백그라운드 draw
		//bg.draw(g);

		// 타일맵 draw
		tilemap.draw(g);
		
		// 캐릭터 draw
		//character.draw(g);
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) character.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) character.setRight(true);
		if(k == KeyEvent.VK_UP) character.setUp(true);
		if(k == KeyEvent.VK_DOWN) character.setDown(true);
		if(k == KeyEvent.VK_S) character.setJump(true);
		if(k == KeyEvent.VK_D) character.setGliding(true);
		if(k == KeyEvent.VK_Z) character.setScratching();
		if(k == KeyEvent.VK_X) character.setFiring();
		if(k == KeyEvent.VK_ESCAPE) gsm.setState(GameStateManager.MENUSTATE);

	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) character.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) character.setRight(false);
		if(k == KeyEvent.VK_UP) character.setUp(false);
		if(k == KeyEvent.VK_DOWN) character.setDown(false);
		if(k == KeyEvent.VK_W) character.setJump(false);
		if(k == KeyEvent.VK_E) character.setGliding(false);
	}

}
