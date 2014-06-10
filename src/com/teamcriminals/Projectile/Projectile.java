package com.teamcriminals.Projectile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.teamcriminals.Entity.MapObject;
import com.teamcriminals.TileMap.TileMap;

public abstract class Projectile extends MapObject{
	
	protected boolean hit;
	protected boolean remove;
	protected BufferedImage[] sprites;
	protected BufferedImage[] hitSprites;
	
	public Projectile(TileMap tm, boolean right) {
		super(tm);
	}
	
	public abstract void setHit();
	public abstract boolean shouldRemove();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	
}