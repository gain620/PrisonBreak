package com.teamcriminals.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.teamcriminals.Audio.AudioPlayer;
import com.teamcriminals.TileMap.Background;

public class GameOver extends GameState {

	private Background bg;
	private AudioPlayer bgm;
	private String[] options = { "*", "*"};

	private int currentSelection = 0;

	public GameOver(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {
		bgm = new AudioPlayer("/Music/gameover.mp3");
		bgm.play();
		bg = new Background("/Background/gameover.png", 0);
	}

	public void update() {

	}

	public void draw(Graphics2D g) {
		bg.draw(g);

		for (int i = 0; i < options.length; i++) {
			if (i == currentSelection) {
				g.setColor(Color.WHITE);
			} else {
				g.setColor(Color.BLACK);
			}

			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.drawString(options[i], 240, 400+ 65*i);
		}

	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_UP) {
			currentSelection = (currentSelection + 1) % options.length;
		} else if (k == KeyEvent.VK_DOWN) {
			if (currentSelection == 0)
				currentSelection = options.length - 1;
			else
				currentSelection -= 1;
		}

		if (k == KeyEvent.VK_ENTER) {
			if (currentSelection == 0) {
				bgm.close();
				gsm.setState(GameStateManager.LEVEL1STATE);
			} else if (currentSelection == 1) {
				bgm.close();
				gsm.setState(GameStateManager.MENUSTATE);
			}
		}

		if (k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}

	}

	public void keyReleased(int k) {
	}
}
