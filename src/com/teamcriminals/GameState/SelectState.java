package com.teamcriminals.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import com.teamcriminals.Character.*;

import com.teamcriminals.Game.GamePanel;

public class SelectState extends GameState {

	private String[] options = { "Caesar", "Zero", "Fyro", "Draco" };

	private int currentSelection = 0;

	public SelectState(GameStateManager gsm) {
		this.gsm = gsm;
	}

	public void init() {

	}

	public void update() {

	}

	public void draw(Graphics2D g) {
		
		g.setColor(new Color(250,250,250));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		for(int i = 0; i < options.length;i++){
			if(i == currentSelection){
				g.setColor(Color.RED);
			}else {
				g.setColor(Color.BLACK);
			}
			
			// g.drawLine(GamePanel.WIDTH/2, 0, GamePanel.WIDTH/2, GamePanel.HEIGHT); �߾Ӽ�
			g.setFont(new Font("Times", Font.PLAIN, 60));
			g.drawString(options[i], 40 + i*150, 200 + i*150);
		}

	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_RIGHT) {
			currentSelection++;
			if (currentSelection >= options.length) {
				currentSelection = 0;
			}
		} else if (k == KeyEvent.VK_LEFT) {
			currentSelection--;
			if (currentSelection < 0) {
				currentSelection = options.length - 1;
			}
		}

		if (k == KeyEvent.VK_ENTER) {
			
			CharacterFactory cf = new CharacterFactory();
			
			if (currentSelection == 0) {
				// Caesar �÷���
				cf.getCharacter(0);
				
			} else if (currentSelection == 1) {
				cf.getCharacter(1);
				
			} else if (currentSelection == 2) {
				cf.getCharacter(2);
				
			} else if (currentSelection == 3) {
				cf.getCharacter(3);
			}
		}
		
		if(k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}

	}




	
	public void keyReleased(int k) {}
}
