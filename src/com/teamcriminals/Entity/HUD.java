package com.teamcriminals.Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HUD {
	
	private Character character;
	private BufferedImage hudImage1 , hudImage2;
	private Font font;
	
	public HUD(Character character){
		this.character = character;
		
		try {
			hudImage1 = ImageIO.read(getClass().getResourceAsStream("/HUD/hud1.png"));
			hudImage2 = ImageIO.read(getClass().getResourceAsStream("/HUD/hud2.jpg"));
			font = new Font("Arial", Font.PLAIN, 14);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	public void draw(Graphics2D g) {
		g.drawImage(hudImage1, 0, 0, null);
		g.drawImage(hudImage2, 520, 0, null);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(character.getHealth() + "/" + character.getMaxHealth(), 52, 20);
		g.drawString(Integer.toString(character.score), 600, 20);
		//g.drawString(testCharacter.getFire() / 100 + "/"	+ testCharacter.getMaxFire() / 100 , 58, 82);

	}

}
