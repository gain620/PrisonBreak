package com.teamcriminals.Skill;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.Projectile.Projectile;
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
		projectile = new ArrayList<Projectile>();
	}
	
	@Override
	public void update() {
		obj +=1;
		if(obj > maxObj)
			obj = maxObj;
		if(throwing && c.getCurrentMotion() != Character.XATTACK) {
			if(obj >= objUse) {
				obj -= objUse;
				Projectile b = new SilverBullet(c.getTileMap(), c.isFaceRight());
				b.setPosition(c.getX(), c.getY());
				projectile.add(b);
			}
		}
		for (int i = 0; i < projectile.size(); i++) {
				projectile.get(i).update();
			if (projectile.get(i).shouldRemove())
				projectile.remove(i--);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		for (int i = 0; i < projectile.size(); i++)
				projectile.get(i).draw(g);
	}
}