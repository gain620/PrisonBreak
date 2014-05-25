package com.teamcriminals.TileMap;

import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;

import com.teamcriminals.Game.GamePanel;

public class Background {
	
	private BufferedImage image;
	
	private double x = 0;
	private double y = 0;


	public Background(String s) {
		
		try{
			
			image = ImageIO.read(new File(getClass().getResource(s).toURI()));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, (int)x, (int)y, null);
		
	}
}
