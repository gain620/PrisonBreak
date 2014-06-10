package com.teamcriminals.Skill;

import java.awt.Graphics2D;
import com.teamcriminals.Entity.Character;
import com.teamcriminals.Projectile.SilverBullet;

public class LegShot extends X {
	
	public LegShot(Character c) {
		super(c);
		init();
		cooldown = 13;
		obj = maxObj = 60 * cooldown;
		objUse = 60 * cooldown;
		damage = 5;
		
	}
	
	@Override
	public void init() {
		projectile = new SilverBullet(c.getTileMap(), c.isFaceRight());
	}
	

	@Override
	public void update() {

		obj +=1;
		if(obj > maxObj)
			obj = maxObj;
		if(throwing && c.getCurrentMotion() != Character.XATTACK) {
			if(obj >= objUse) {
				obj -= objUse;
				init();
				projectile.setPosition(c.getX(), c.getY());
			}
		}
		
		projectile.update();
		if(projectile.shouldRemove()) {
			projectile = null;
		}

	}

	@Override
	public void draw(Graphics2D g) {

		if(projectile != null)
			projectile.draw(g);
						
	}

}