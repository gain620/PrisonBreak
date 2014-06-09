package com.teamcriminals.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.teamcriminals.Motion.Motion;

public class Death {
	
	private int x ,y ;
	private int width, height;
	private int xMap, yMap;
	private Motion motion;
	private BufferedImage[] sprites;
	private boolean remove;
	
	
	public Death(int x , int y) {
		
		this.x = x;
		this.y = y;
		
		width = 30;
		height = 30;
		
		try{
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"/Sprites/Enemy/blood.jpg"));
			
			sprites = new BufferedImage[6];
			
			for(int i = 0; i<sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
						i*width,0,width,height);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		motion = new Motion();
		motion.setFrames(sprites);
		motion.setDelay(70);
		
	}
	
	public void update() {
		motion.update();
		if(motion.hasPlayedOnce()){
			remove = true;
		}
	}
	
	public boolean shouldRemove() {
		return remove;
	}
	
	public void setMapPosition(int x, int y) {
		xMap = x;
		yMap = y;
	}
	
	public void draw(Graphics2D g ) {
		g.drawImage(motion.getImage(), x + xMap - width / 2, 
				y + yMap - height / 2, null);
	}

}
