package com.teamcriminals.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import com.teamcriminals.TileMap.Background;

public class StageSelectState extends GameState {
	private Background bg;

	private String[] options = { "Stage1", "Stage2", "Stage3" };

	private int currentSelection = 0;

	public StageSelectState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {
		bg = new Background("/Background/SelectStage.png", 0);
	}

	public void update() {

	}

	public void draw(Graphics2D g) {	
		bg.draw(g);
		
		for(int i = 0; i < options.length;i++){
			if(i == currentSelection){
				g.setColor(Color.RED);
			}else {
				g.setColor(Color.BLACK);
			}
			
			g.setFont(new Font("Rosewood Std Regular", Font.PLAIN, 40));
			g.drawString(options[i], 40 + i * 210, 400);
		}

	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_RIGHT) {
			currentSelection = (currentSelection + 1) % options.length;
		} else if (k == KeyEvent.VK_LEFT) {
			if (currentSelection == 0)
				currentSelection = options.length - 1;
			else
				currentSelection -= 1;
		}

		if (k == KeyEvent.VK_ENTER) {			
			if (currentSelection == 0) {
				gsm.setCharacter(currentSelection+1);	// 캐릭터 정보 gsm에 저장
				gsm.setState(GameStateManager.LEVEL1STATE);
			} else if (currentSelection == 1) {
				gsm.setState(GameStateManager.LEVEL2STATE);
			} else if (currentSelection == 2) {
				gsm.setState(GameStateManager.LEVEL3STATE);
			}
		}
		
		if(k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}

	}

	public void keyReleased(int k) {}
}

