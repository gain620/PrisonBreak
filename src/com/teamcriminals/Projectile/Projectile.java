package com.teamcriminals.Projectile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.teamcriminals.Entity.MapObject;
import com.teamcriminals.TileMap.TileMap;

public class Projectile extends MapObject{
	
	protected boolean hit;
	protected boolean remove;
	protected BufferedImage[] sprites;
	protected BufferedImage[] hitSprites;
	
	public Projectile(TileMap tm, boolean right) {
		super(tm);
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