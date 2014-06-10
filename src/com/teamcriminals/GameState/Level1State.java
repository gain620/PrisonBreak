package com.teamcriminals.GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import com.teamcriminals.Audio.AudioPlayer;
import com.teamcriminals.Character.CharacterFactory;
import com.teamcriminals.Enemy.Core;
import com.teamcriminals.Enemy.ZombieSoldier;
import com.teamcriminals.Entity.Character;
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
	private HashMap<String, AudioPlayer> sfx;

	private AudioPlayer bgm;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		cf = new CharacterFactory();
		bg = new Background("/Background/Stage1.png", 0);
		tilemap = new TileMap(30);
		tilemap.loadTiles("/Tilesets/stage1tileset.jpg");
		tilemap.loadMap("/Maps/level1.map");
		tilemap.setPosition(0, 0);
		tilemap.setTween(0.07);

		character = cf.setCharacter(gsm.getCharacter(), tilemap);
		character.setPosition(100.0, 180.0);

		hud = new HUD(character);

		populateEnemy();

		death = new ArrayList<Death>();

		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("squish", new AudioPlayer("/SFX/squish.mp3"));
		sfx.put("zombiedie", new AudioPlayer("/SFX/zombiedie.mp3"));

		bgm = new AudioPlayer("/Music/level1.mp3");
		bgm.play();

	}

	private void populateEnemy() {
		enemy = new ArrayList<Enemy>();

		ZombieSoldier zs;
		Core core;

		Point[] points = new Point[] { new Point(715, 420),
				new Point(200, 100), new Point(850, 220), new Point(1620, 300),
				new Point(2346, 335), new Point(2408, 725),
				new Point(1925, 690), new Point(1390, 450),
				new Point(1544, 450), new Point(1000, 727), new Point(200, 693)

		};

		for (int i = 0; i < points.length-1; i++) {
			zs = new ZombieSoldier(tilemap);
			zs.setPosition(points[i].x, points[i].y);
			enemy.add(zs);
		}
		core = new Core(tilemap);
		core.setPosition(points[points.length-1].x, points[points.length-1].y);
		enemy.add(core);
	}

	public void update() {

		// ĳ���;�����Ʈ
		character.update();
		tilemap.setPosition(GamePanel.WIDTH / 2 - character.getX(),
				GamePanel.HEIGHT / 2 - character.getY());

		// ���ʹ̾�����Ʈ
		for (int i = 0; i < enemy.size(); i++) {
			Enemy e = enemy.get(i);
			e.update();
			if (e.isDead()) {
				enemy.remove(i);
				i--;
				character.earnScore(e.getScorePoint());
				death.add(new Death(e.getX(), e.getY()));
				sfx.get("squish").play();
				sfx.get("zombiedie").play();
			}
		}

		for (int i = 0; i < death.size(); i++) {
			death.get(i).update();
			if (death.get(i).shouldRemove()) {
				death.remove(i);
				i--;
			}
		}

		// ��������
		character.checkAttack(enemy);

	}

	public void draw(Graphics2D g) {
		// ��׶���
		bg.draw(g);

		// Ÿ�ϸ�
		tilemap.draw(g);

		// ĳ����
		character.draw(g);

		// HUD
		hud.draw(g);

		// ���ʹ�
		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).draw(g);
		}

		// death
		for (int i = 0; i < death.size(); i++) {
			death.get(i).setMapPosition((int) tilemap.getx(),
					(int) tilemap.gety());
			death.get(i).draw(g);
		}
		
		

	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_UP)
			character.setUp(true);
		if (k == KeyEvent.VK_DOWN)
			character.setDown(true);
		if (k == KeyEvent.VK_LEFT)
			character.setLeft(true);
		if (k == KeyEvent.VK_RIGHT)
			character.setRight(true);
		if (k == KeyEvent.VK_SPACE)
			character.setJump(true);
		if (k == KeyEvent.VK_Z)
			character.setZattacking();
		if (k == KeyEvent.VK_X) {
			character.setXattacking();
			character.getSkillX().setThrowing(true);
		}
		if (k == KeyEvent.VK_C) {
			character.setCattacking();
			character.getSkillC().setUse(true);
		}
		if (k == KeyEvent.VK_ESCAPE)
			gsm.setState(GameStateManager.MENUSTATE);
	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_UP)
			character.setUp(false);
		if (k == KeyEvent.VK_DOWN)
			character.setDown(false);
		if (k == KeyEvent.VK_LEFT)
			character.setLeft(false);
		if (k == KeyEvent.VK_RIGHT)
			character.setRight(false);
		if (k == KeyEvent.VK_SPACE)
			character.setJump(false);
		if (k == KeyEvent.VK_X)
			character.getSkillX().setThrowing(false);
		if (k == KeyEvent.VK_C)
			character.getSkillC().setUse(false);
	}

}