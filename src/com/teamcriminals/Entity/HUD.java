package com.teamcriminals.Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HUD {
	
	private TestVerCharacter testCharacter;
	private BufferedImage hudImage;
	private Font font;
	
	public HUD(TestVerCharacter testCharacter){
		this.testCharacter = testCharacter;
		
		try{
			hudImage = ImageIO.read(getClass().getResourceAsStream("/HUD/hud.gif"));
			
			font = new Font("Arial", Font.PLAIN, 12);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(hudImage, 0, 30, null);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(testCharacter.getHealth() + "/" + testCharacter.getMaxHealth(), 30, 45);
		g.drawString(testCharacter.getFire()/100 +"/"+ testCharacter.getMaxFire()/100, 30, 65);
		
	}

}
