package com.teamcriminals.GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.teamcriminals.Character.CharacterFactory;
import com.teamcriminals.Entity.HUD;
import com.teamcriminals.Entity.TestVerCharacter;
import com.teamcriminals.Game.GamePanel;
import com.teamcriminals.TileMap.Background;
import com.teamcriminals.TileMap.TileMap;

public class Level1State extends GameState {
	
	CharacterFactory cf = new CharacterFactory();
	
	private HUD hud;

	private TileMap tilemap;
	private Background bg;
	//private Character character;
	
	private TestVerCharacter testCharacter;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	/*
	 * 테스트 public void setCharacterState() { cf.getCharacter(characterNum); }
	 * 
	 * 
	 */

	public void init() {

		bg = new Background("/Background/Stage1.png", 0);
		tilemap = new TileMap(30);
		tilemap.loadTiles("/Tilesets/Stage1Tileset.png");
		tilemap.loadMap("/Maps/Stage1.map");
		tilemap.setPosition(0, 0);
		tilemap.setTween(0.07);

		//character = cf.setCharacter(gsm.getCharacter(), tilemap);
		
		testCharacter = new TestVerCharacter(tilemap);
		testCharacter.setPosition(300, 200);
		
		hud = new HUD(testCharacter);

	}

	public void update() {

		// 캐릭터 업데이트
		testCharacter.update();
		tilemap.setPosition(GamePanel.WIDTH / 2 - testCharacter.getX(),
				GamePanel.HEIGHT / 2 - testCharacter.getY());

	}

	public void draw(Graphics2D g) {
		// 백그라운드 draw
		bg.draw(g);

		// 타일맵 draw
		tilemap.draw(g);
		
		// 캐릭터 draw
		testCharacter.draw(g);
		
		// HUD draw
		hud.draw(g);
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) testCharacter.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) testCharacter.setRight(true);
		if(k == KeyEvent.VK_UP) testCharacter.setUp(true);
		if(k == KeyEvent.VK_DOWN) testCharacter.setDown(true);
		if(k == KeyEvent.VK_S) testCharacter.setJump(true);
		//if(k == KeyEvent.VK_D) character.setGliding(true);
		//if(k == KeyEvent.VK_Z) character.setScratching();
		//if(k == KeyEvent.VK_X) character.setFiring();
		if(k == KeyEvent.VK_ESCAPE) gsm.setState(GameStateManager.MENUSTATE);

	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) testCharacter.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) testCharacter.setRight(false);
		if(k == KeyEvent.VK_UP) testCharacter.setUp(false);
		if(k == KeyEvent.VK_DOWN) testCharacter.setDown(false);
		if(k == KeyEvent.VK_W) testCharacter.setJump(false);
		//if(k == KeyEvent.VK_E) character.setGliding(false);
	}

}