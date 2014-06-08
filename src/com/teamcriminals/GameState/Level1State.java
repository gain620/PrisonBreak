package com.teamcriminals.GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.teamcriminals.Handlers.Keys;
import com.teamcriminals.Character.*;
import com.teamcriminals.Enemy.*;
import com.teamcriminals.Entity.*;
import com.teamcriminals.Entity.Character;
import com.teamcriminals.Game.GamePanel;
import com.teamcriminals.TileMap.Background;
import com.teamcriminals.TileMap.TileMap;

public class Level1State extends GameState {
	
	CharacterFactory cf = new CharacterFactory();
	
	private HUD hud;

	private TileMap tilemap;
	private Background bg;
	private Character character;
	
	private ArrayList<Enemy> enemy;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		bg = new Background("/Background/Stage1.png", 0);
		tilemap = new TileMap(30);
		tilemap.loadTiles("/Tilesets/Stage1Tileset.png");
		tilemap.loadMap("/Maps/Stage1.map");
		tilemap.setPosition(0, 0);
		tilemap.setTween(0.07);

		character = cf.setCharacter(gsm.getCharacter(), tilemap);
		character.setPosition(300, 200);
		
		hud = new HUD(character);
		
		
		enemy = new ArrayList<Enemy>();
		ZombieSoldier zombie1;
		zombie1 = new ZombieSoldier(tilemap);
		zombie1.setPosition(100, 300);
		enemy.add(zombie1);

	}

	public void update() {

		// 캐릭터 업데이트
		character.update();
		tilemap.setPosition(GamePanel.WIDTH / 2 - character.getX(),
				GamePanel.HEIGHT / 2 - character.getY());
		


		
		// 적 정보 업데이트
		for(int i = 0 ;i<enemy.size(); i++){
			enemy.get(i).update();
			if(enemy.get(i).isDead()) {
				enemy.remove(i);
				i --;
			}
		}
		
		// 적 공격
		character.checkAttack(enemy);
		
	}

	public void draw(Graphics2D g) {
		// 백그라운드 draw
		bg.draw(g);

		// 타일맵 draw
		tilemap.draw(g);
		
		// 캐릭터 draw
		character.draw(g);
		
		// HUD draw
		hud.draw(g);
		
		// 적 draw
		for(int i = 0; i< enemy.size(); i++) {
			enemy.get(i).draw(g);
		}
	}

	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) gsm.setState(GameStateManager.MENUSTATE);//gsm.setPaused(true);
		if(/*blockInput ||*/ character.getHealth() == 0) return;
		character.setUp(Keys.keyState[Keys.UP]);
		character.setLeft(Keys.keyState[Keys.LEFT]);
		character.setDown(Keys.keyState[Keys.DOWN]);
		character.setRight(Keys.keyState[Keys.RIGHT]);
		character.setJump(Keys.keyState[Keys.SPACE]);
		if(Keys.isPressed(Keys.Z)) character.setZattacking();
		if(Keys.isPressed(Keys.X)) character.setXattacking();
		if(Keys.isPressed(Keys.C)) character.setCattacking();
		
	}
	
	public void keyPressed(int k) {
		/* Keys덕분에 필요없을듯한데
		if(k == KeyEvent.VK_LEFT) character.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) character.setRight(true);
		if(k == KeyEvent.VK_UP) character.setUp(true);
		if(k == KeyEvent.VK_DOWN) character.setDown(true);
		if(k == KeyEvent.VK_S) character.setJump(true);
		//if(k == KeyEvent.VK_D) character.setGliding(true);
		//if(k == KeyEvent.VK_Z) character.setPunch();
		//if(k == KeyEvent.VK_X) character.setFiring();
		if(k == KeyEvent.VK_ESCAPE) gsm.setState(GameStateManager.MENUSTATE);
		 */
	}

	public void keyReleased(int k) {
		/*
		if(k == KeyEvent.VK_LEFT) character.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) character.setRight(false);
		if(k == KeyEvent.VK_UP) character.setUp(false);
		if(k == KeyEvent.VK_DOWN) character.setDown(false);
		if(k == KeyEvent.VK_W) character.setJump(false);
		//if(k == KeyEvent.VK_E) character.setGliding(false);
		 */
	}
	
}