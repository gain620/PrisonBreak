package com.teamcriminals.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.teamcriminals.Game.GamePanel;
import com.teamcriminals.TileMap.Background;

public class SelectState extends GameState {
	
	private Background bg;

	private String[] options = { "Caesar", "Zero", "Fyro", "Draco" };

	private int currentSelection = 0;

	public SelectState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {
		bg = new Background("/Background/menuState.png",0);
	}

	public void update() {

	}
	
	public void handleInput() {}

	public void draw(Graphics2D g) {
		//화면정리(굳이 필요한 코드는 아님!~)
		g.setColor(new Color(250,250,250));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		bg.draw(g);
		
		for(int i = 0; i < options.length;i++){
			if(i == currentSelection){
				g.setColor(Color.RED);
			}else {
				g.setColor(Color.BLACK);
			}
			
			//g.drawLine(GamePanel.WIDTH/2, 0, GamePanel.WIDTH/2, GamePanel.HEIGHT);
			g.setFont(new Font("Rosewood Std Regular", Font.PLAIN, 40));
			if(i==0){
				g.drawString(options[i], 10, 430);
				continue;
			}
			g.drawString(options[i], 30 + i * 155, 430);
		}

	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_RIGHT) {
			++currentSelection;
			if (currentSelection >= options.length) {
				currentSelection = 0;
			}
		} else if (k == KeyEvent.VK_LEFT) {
			--currentSelection;
			if (currentSelection < 0) {
				currentSelection = options.length - 1;
			}
		}

		if (k == KeyEvent.VK_ENTER) {			
			if (currentSelection == 0) {
				
				gsm.setState(GameStateManager.LEVEL1STATE);
				gsm.setCharacter(currentSelection);	// 캐릭터 정보 gsm에 저장
				
			} else if (currentSelection == 1) {

				gsm.setState(GameStateManager.LEVEL1STATE);
				gsm.setCharacter(currentSelection);
				
			} else if (currentSelection == 2) {

				gsm.setState(GameStateManager.LEVEL1STATE);
				gsm.setCharacter(currentSelection);
				
			} else if (currentSelection == 3) {

				gsm.setState(GameStateManager.LEVEL1STATE);
				gsm.setCharacter(currentSelection);
				
			}
		}
		
		if(k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}

	}




	
	public void keyReleased(int k) {}
}
