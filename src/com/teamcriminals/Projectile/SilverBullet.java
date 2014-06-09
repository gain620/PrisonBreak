package com.teamcriminals.Projectile;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.teamcriminals.Motion.Motion;
import com.teamcriminals.TileMap.TileMap;

public class SilverBullet extends Projectile{


	public SilverBullet(TileMap tm, boolean right) {
		
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
	
}
