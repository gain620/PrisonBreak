package com.teamcriminals.GameState;

import java.awt.Graphics2D;

import com.teamcriminals.Audio.AudioPlayer;
import com.teamcriminals.TileMap.Background;

public class GameOver extends GameState {
	
	private Background bg;
    private AudioPlayer bgm;
	private String[] options = { "Caesar", "Zero", "Fyro", "Draco" };

	private int currentSelection = 0;
	
	public GameOver(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	
	public void init() {
		bg = new Background("/Background/gameover.png", 0);
	}

	
	public void update() {


	}

	
	public void draw(Graphics2D g) {


	}

	
	public void keyPressed(int k) {


	}

	
	public void keyReleased(int k) {}
}
