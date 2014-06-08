package com.teamcriminals.Skill;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.Projectile.Bat;

public class LegShot extends X {
	
	public LegShot(Character c) {
		
		super(c);
		
		cooldown = 13;
		
		obj = maxObj = 60 * cooldown;
		objUse = 60 * cooldown;
		objDamage = 5;
		objs = new ArrayList<Bat>();
		
	}



	@Override
	public void update() {
		obj +=1;
		if(obj > maxObj)
			obj = maxObj;
		if(throwing && c.getCurrentMotion() != c.XATTACK) {
			if(obj > objUse) {
				obj -= objUse;
				
				Bat b = new Bat(c.getTileMap(), c.isFaceRight());
				b.setPosition(c.getX(), c.getY());
				objs.add(b);
			}
		}
		
		for(int i = 0; i < objs.size(); i++){
			((Character) objs.get(i)).update();
			if(((Bat) objs.get(i)).shouldRemove()){
				objs.remove(i);
				i--;
			}
		}

		if(throwing) {
			if(c.getCurrentMotion() != c.XATTACK) {
				c.setCurrentMotion(c.XATTACK);
				c.getMotion().setFrames(c.getSprites().get(c.XATTACK));
				c.getMotion().setDelay(100);
				c.setWidth(30);
			}
		}

		
	}

	@Override
	public void draw(Graphics2D g) {
		for(int i = 0; i < objs.size(); i++)
			((Bat)objs.get(i)).draw(g);
	}
	
	public int attack() {
		return 0;
	}
	
	@Override
	public void init() {
		
	}
	
}