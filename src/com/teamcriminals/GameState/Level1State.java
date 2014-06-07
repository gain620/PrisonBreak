package com.teamcriminals.GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.teamcriminals.Character.CharacterFactory;
import com.teamcriminals.Enemy.ZombieSoldier;
import com.teamcriminals.Entity.Enemy;
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
	
	private ArrayList<Enemy> enemy;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	/*
	 * �׽�Ʈ public void setCharacterState() { cf.getCharacter(characterNum); }
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
		
		
		enemy = new ArrayList<Enemy>();
		ZombieSoldier zombie1;
		zombie1 = new ZombieSoldier(tilemap);
		zombie1.setPosition(100, 300);
		enemy.add(zombie1);

	}

	public void update() {

		// ĳ���� ������Ʈ
		testCharacter.update();
		tilemap.setPosition(GamePanel.WIDTH / 2 - testCharacter.getX(),
				GamePanel.HEIGHT / 2 - testCharacter.getY());
		


		
		// �� ���� ������Ʈ
		for(int i = 0 ;i<enemy.size(); i++){
			enemy.get(i).update();
			if(enemy.get(i).isDead()) {
				enemy.remove(i);
				i --;
			}
		}
		
		// �� ����
		testCharacter.checkAttack(enemy);
		
	}

	public void draw(Graphics2D g) {
		// ��׶��� draw
		bg.draw(g);

		// Ÿ�ϸ� draw
		tilemap.draw(g);
		
		// ĳ���� draw
		testCharacter.draw(g);
		
		// HUD draw
		hud.draw(g);
		
		// �� draw
		for(int i = 0; i< enemy.size(); i++) {
			enemy.get(i).draw(g);
		}
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) testCharacter.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) testCharacter.setRight(true);
		if(k == KeyEvent.VK_UP) testCharacter.setUp(true);
		if(k == KeyEvent.VK_DOWN) testCharacter.setDown(true);
		if(k == KeyEvent.VK_S) testCharacter.setJump(true);
		if(k == KeyEvent.VK_D) testCharacter.setGliding(true);
		if(k == KeyEvent.VK_Z) testCharacter.setPunch();
		if(k == KeyEvent.VK_X) testCharacter.setFiring();
		if(k == KeyEvent.VK_ESCAPE) gsm.setState(GameStateManager.MENUSTATE);

	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) testCharacter.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) testCharacter.setRight(false);
		if(k == KeyEvent.VK_UP) testCharacter.setUp(false);
		if(k == KeyEvent.VK_DOWN) testCharacter.setDown(false);
		if(k == KeyEvent.VK_W) testCharacter.setJump(false);
		if(k == KeyEvent.VK_E) testCharacter.setGliding(false);
	}

}