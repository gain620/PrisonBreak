package com.teamcriminals.Projectile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.teamcriminals.Motion.Motion;
import com.teamcriminals.TileMap.TileMap;

public class Bat extends Projectile {

	public Bat(TileMap tm, boolean right) {
		
		super(tm, right);
	
		moveSpeed = 3.8;
		if(right)
			dx = moveSpeed;
		else
			dx = -moveSpeed;
		
		width = 30;
		height = 30;
		cWidth = 14;
		cHeight = 14;
		
		// 스프라이트 로드
		try{
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream("/Sprites/Projectiles/fireball.gif")
							);
			
			sprites = new BufferedImage[4];
			for(int i = 0; i < sprites.length; i++)
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
					
			hitSprites = new BufferedImage[3];
			for(int i = 0; i < hitSprites.length; i++)
				hitSprites[i] = spritesheet.getSubimage(i * width, height, width, height);
			
			motion = new Motion();
			motion.setFrames(sprites);
			motion.setDelay(70);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setHit() {
		if(hit)	return;
		hit  = true;
		motion.setFrames(hitSprites);
		motion.setDelay(70);
		dx = 0;
	}
	
	public boolean shouldRemove(){
		return remove;
	}
	
	public void update() {
		collideTile();
		setPosition(xTemp, yTemp);
		if(dx == 0 && !hit)
			setHit();
		motion.update();
		if(hit && motion.hasPlayedOnce())
			remove = true;
	}
	
	public void draw(Graphics2D g){
		
		setMapPosition();
		
		if(faceRight) {
			g.drawImage(
				motion.getImage(),
				(int)(x + xMap - width / 2),
				(int)(y + yMap - height / 2),
				null
			);
		}
		else {
			g.drawImage(
				motion.getImage(),
				(int)(x + xMap - width / 2 + width),
				(int)(y + yMap - height / 2),
				-width,
				height,
				null
			);
			
		}
		
	}
	
}