package com.teamcriminals.GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import com.teamcriminals.Audio.AudioPlayer;
import com.teamcriminals.Character.CharacterFactory;
import com.teamcriminals.Character.Zero;
import com.teamcriminals.Enemy.Stage2Boss;
import com.teamcriminals.Entity.Death;
import com.teamcriminals.Entity.Enemy;
import com.teamcriminals.Entity.HUD;
import com.teamcriminals.Game.GamePanel;
import com.teamcriminals.TileMap.Background;
import com.teamcriminals.TileMap.TileMap;

public class Level3State extends GameState {
	private HUD hud;
	private CharacterFactory cf;
	private TileMap tilemap;
	private Background bg;
	private Zero character;

	private ArrayList<Enemy> enemy;
	private ArrayList<Death> death;
	private HashMap<String, AudioPlayer> sfx;

	private AudioPlayer bgm;

	// 이벤트 변수
	private int eventCount;

	public Level3State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		cf = new CharacterFactory();

		bg = new Background("/Background/Stage2.png", 0);
		tilemap = new TileMap(30);
		tilemap.loadTiles("/Tilesets/stage2tileset.jpg");
		tilemap.loadMap("/Maps/level3.map");
		tilemap.setPosition(0, 0);
		tilemap.setTween(0.07);

		//character = cf.setCharacter(gsm.getCharacter(), tilemap);
		// 임시방편
		character =new Zero(tilemap);

		character.setPosition(200.0, 400.0);

		hud = new HUD(character);

		populateEnemy();

		death = new ArrayList<Death>();

		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("squish", new AudioPlayer("/SFX/squish.mp3"));
		sfx.put("zombiedie", new AudioPlayer("/SFX/zombiedie.mp3"));
		sfx.put("death", new AudioPlayer("/SFX/death.mp3"));

		bgm = new AudioPlayer("/Music/level3.mp3");
		bgm.play();

	}

	private void populateEnemy() {
		enemy = new ArrayList<Enemy>();

		Stage2Boss boss;

		Point[] points = new Point[] {
				new Point(450, 400)
		};

		for (int i = 0; i < points.length; i++) {
			boss = new Stage2Boss(tilemap);
			boss.setPosition(points[i].x, points[i].y);
			enemy.add(boss);
		}
	}

	public void update() {
		// 사망(체력0되면 or 낙하사) & 게임오버 변환
		if (character.getHealth() <= 0
				|| character.getY() > tilemap.getHeight())
			eventDead();

		// 캐릭터업데이트
		character.update();
		tilemap.setPosition(GamePanel.WIDTH / 2 - character.getX(),
				GamePanel.HEIGHT / 2 - character.getY());

		// 에너미업데이트
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

		// 공격판정
		character.checkAttack(enemy);

	}

	public void draw(Graphics2D g) {
		// 백그라운드
		bg.draw(g);

		// 타일맵
		tilemap.draw(g);

		// 캐릭터
		character.draw(g);

		// HUD
		hud.draw(g);

		// 에너미
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

	// ////////////특수 이벤트 판정 /////////////////

	// 캐릭터 사망 이벤트
	private void eventDead() {
		eventCount++;
		if (eventCount == 1) {
			bgm.close();
			character.stop();
			sfx.get("death").play();
		}
		if (eventCount >= 200) {
			bgm.close();
			gsm.setState(GameStateManager.GAMEOVER);
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
		if (k == KeyEvent.VK_ESCAPE) {
			bgm.close();
			gsm.setState(GameStateManager.MENUSTATE);
		}

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

