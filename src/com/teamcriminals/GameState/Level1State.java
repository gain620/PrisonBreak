package com.teamcriminals.GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import com.teamcriminals.Enemy.*;
import com.teamcriminals.Entity.Character;
import com.teamcriminals.Character.CharacterFactory;
import com.teamcriminals.Entity.Death;
import com.teamcriminals.Entity.Enemy;
import com.teamcriminals.Entity.HUD;
import com.teamcriminals.Game.GamePanel;
import com.teamcriminals.TileMap.Background;
import com.teamcriminals.TileMap.TileMap;

public class Level1State extends GameState {
	private HUD hud;
	private CharacterFactory cf;
	private TileMap tilemap;
	private Background bg;
	private Character character;
	
	private ArrayList<Enemy> enemy;
	private ArrayList<Death> death;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		cf = new CharacterFactory();
		bg = new Background("/Background/Stage1.png", 0);
		tilemap = new TileMap(30);
		tilemap.loadTiles("/Tilesets/Stage1Tileset.png");
		tilemap.loadMap("/Maps/Stage1.map");
		tilemap.setPosition(0, 0);
		tilemap.setTween(0.07);

		character = cf.setCharacter(gsm.getCharacter(), tilemap);
		character.setPosition(300.0, 200.0);
		
		hud = new HUD(character);
		
		populateEnemy();
		
		death = new ArrayList<Death>();

	}
	
	private void populateEnemy() {
		enemy = new ArrayList<Enemy>();
		
		ZombieSoldier zs;
		
		Point[] points = new Point[] {
				new Point(100, 200) ,
				new Point(200, 100)
		};
		
		for(int i = 0; i< points.length; i ++) {
			zs = new ZombieSoldier(tilemap);
			zs.setPosition(points[i].x, points[i].y);
			enemy.add(zs);
		}
	}
	
	public void update() {

		//ĳ���;�����Ʈ
		character.update();
		tilemap.setPosition(GamePanel.WIDTH / 2 - character.getX(),
				GamePanel.HEIGHT / 2 - character.getY());
		
		
		//���ʹ̾�����Ʈ
		for(int i = 0 ;i<enemy.size(); i++){
			Enemy e = enemy.get(i);
			e.update();
			if(e.isDead()) {
				enemy.remove(i);
				i--;
				death.add(new Death(e.getX() , e.getY()));
			}
		}
		
		for(int i = 0; i< death.size(); i++) {
			death.get(i).update();
			if(death.get(i).shouldRemove()) {
				death.remove(i);
				i--;
			}
		}
		
		//��������
		character.checkAttack(enemy);
		
	}

	public void draw(Graphics2D g) {
		//��׶���
		bg.draw(g);

		//Ÿ�ϸ�
		tilemap.draw(g);
		
		//ĳ����
		character.draw(g);
		
		//HUD
		hud.draw(g);
		
		//���ʹ�
		for(int i = 0; i< enemy.size(); i++) {
			enemy.get(i).draw(g);
		}
		
		//death
		for(int i = 0; i < death.size(); i ++) {
			death.get(i).setMapPosition(
					(int) tilemap.getx(),(int) tilemap.gety());
			death.get(i).draw(g);
		}
		
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_UP) character.setUp(true);
		if(k == KeyEvent.VK_DOWN) character.setDown(true);
		if(k == KeyEvent.VK_LEFT) character.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) character.setRight(true);
		if(k == KeyEvent.VK_SPACE) character.setJump(true);
		if(k == KeyEvent.VK_Z) character.setZattacking();
		if(k == KeyEvent.VK_X) character.setXattacking();
		if(k == KeyEvent.VK_C) character.setCattacking();
		if(k == KeyEvent.VK_ESCAPE) gsm.setState(GameStateManager.MENUSTATE);
	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_UP) character.setUp(false);
		if(k == KeyEvent.VK_DOWN) character.setDown(false);
		if(k == KeyEvent.VK_LEFT) character.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) character.setRight(false);
		if(k == KeyEvent.VK_SPACE) character.setJump(false);
	}
	
}