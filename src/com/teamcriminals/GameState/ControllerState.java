package com.teamcriminals.GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import com.teamcriminals.TileMap.Background;

public class ControllerState extends GameState {
	private Background bg;


	public ControllerState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {
		bg = new Background("/Background/controllerState.png", 0);
	}

	public void update() {

	}

	public void draw(Graphics2D g) {	
		bg.draw(g);

	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}

	}

	public void keyReleased(int k) {}
}

