package com.teamcriminals.Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HUD {
	
	private Character character;
	private BufferedImage hudImage;
	private Font font;
	
	public HUD(Character character){
		this.character = character;
		
		try {
			hudImage = ImageIO.read(getClass().getResourceAsStream("/HUD/hud1.png"));
			font = new Font("Arial", Font.PLAIN, 16);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	public void draw(Graphics2D g) {
		g.drawImage(hudImage, 0, 30, null);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(character.getHealth() + "/" + character.getMaxHealth(), 68, 50);
		//g.drawString(testCharacter.getFire() / 100 + "/"	+ testCharacter.getMaxFire() / 100 , 58, 82);

	}

}
