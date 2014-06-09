package com.teamcriminals.Skill;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.Projectile.SilverBullet;

public class LegShot extends X<SilverBullet> {
	
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
		objs = new ArrayList<SilverBullet>();
	}
	

	@Override
	public void update() {

		obj +=1;
		if(obj > maxObj)
			obj = maxObj;
		if(throwing && c.getCurrentMotion() != Character.XATTACK) {
			if(obj > objUse) {
				obj -= objUse;
				
				SilverBullet b = new SilverBullet(c.getTileMap(), c.isFaceRight());
				b.setPosition(c.getX(), c.getY());
				objs.add(b);
			}
		}
		
		for(int i = 0; i < objs.size(); i++){
			objs.get(i).update();	// (Character)(objs.get(i)).update();였었던거
			if(((SilverBullet)objs.get(i)).shouldRemove()){
				objs.remove(i);
				i--;
			}
		}

		if(throwing) {
			if(c.getCurrentMotion() != Character.XATTACK) {
				c.setCurrentMotion(Character.XATTACK);
				c.getMotion().setFrames(c.getSprites().get(Character.XATTACK));
				c.getMotion().setDelay(100);
			}
		}

	}

	@Override
	public void draw(Graphics2D g) {
		for(int i = 0; i < objs.size(); i++)
			((SilverBullet)objs.get(i)).draw(g);
	}
}