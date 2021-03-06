package com.teamcriminals.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.teamcriminals.TileMap.Background;

public class OptionState  extends GameState  {

	public OptionState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	private Background bg;

	private String[] options = { "*", "*" };

	private int currentSelection = 0;

	
	public void init() {
		bg = new Background("/Background/optionState.png", 0);
	}

	public void update() {

	}

	public void draw(Graphics2D g) {	
		bg.draw(g);
		
		for(int i = 0; i < options.length;i++){
			if(i == currentSelection){
				g.setColor(Color.BLACK);
			}else {
				g.setColor(Color.WHITE);
			}
			
			g.setFont(new Font("Rosewood Std Regular", Font.PLAIN, 40));
			g.drawString(options[i], 180, 285 + i*100);
		}

	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_DOWN) {
			currentSelection = (currentSelection + 1) % options.length;
		} else if (k == KeyEvent.VK_UP) {
			if (currentSelection == 0)
				currentSelection = options.length - 1;
			else
				currentSelection -= 1;
		}

		if (k == KeyEvent.VK_ENTER) {			
			if (currentSelection == 0) {
				gsm.setState(GameStateManager.CONTROLLER);
			} else if (currentSelection == 1) {
				gsm.setState(GameStateManager.STAGESELECT);
			}
		}
		
		if(k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}

	}

	
	public void keyReleased(int k) {}
}