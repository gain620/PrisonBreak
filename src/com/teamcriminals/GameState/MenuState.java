package com.teamcriminals.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.teamcriminals.Game.GamePanel;
import com.teamcriminals.TileMap.Background;

public class MenuState extends GameState{
	
	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = { "Start" , "Option" , "Quit"};
	
	private Font font;
	
	public MenuState(GameStateManager gsm){
		this.gsm = gsm;
		
		try {
			
			bg = new Background("/Background/prisonbreakMenu.jpg", 0);
			
			font = new Font("Rosewood Std Regular", Font.PLAIN, 30);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public void init() {
		
	}

	
	public void update() {
	}

	
	public void draw(Graphics2D g) {
		
		// 배경 그려주기
		bg.draw(g);
		
		// 메뉴 선택
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], GamePanel.WIDTH/2 - 40, 320 + i * 40);
		}
	}
	
	private void select() {
		if(currentChoice == 0){
			gsm.setState(GameStateManager.SELECTSTATE);
		}
		if(currentChoice == 1){
			gsm.setState(GameStateManager.OPTONSTATE);
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}

	public void handleInput() {}
	
	public void keyPressed(int k) {
		if( k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice<0){
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice ++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	
	public void keyReleased(int k) {}
	

}