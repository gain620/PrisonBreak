package com.teamcriminals.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.teamcriminals.TileMap.Background;

public class MenuState extends GameState{
	
	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = { "Start" , "Option" , "Quit"};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public MenuState(GameStateManager gsm){
		this.gsm = gsm;
		
		try {
			
			bg = new Background("/Background/prisonbreakMenu.gif", 0);
			
			titleColor = new Color(100, 0, 150);
			titleFont = new Font("Bauhaus 93",Font.BOLD, 60);
			
			font = new Font("Bauhaus 93", Font.PLAIN, 30);
			
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
		
		// 타이틀
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Prison Break", 330, 210);
		
		// 메뉴 선택
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], 480, 540 + i * 50);
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