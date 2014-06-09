package com.teamcriminals.Skill;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.Projectile.Butt;

public class Dash extends X<Butt> {
	
	/*
	 * 얘는 원거리가 아니라 대쉬하는거라 기존 스킬과는 좀 다르게 수정해야함
	 * 객체는 만들어서 케릭터도 함께 이동시키는거?
	 */
	
	public Dash(Character c) {
		super(c);
		init();
		cooldown = 13;
		obj = maxObj = 60 * cooldown;
		objUse = 60 * cooldown;
		damage = 5;
	}

	@Override
	public void init() {
		objs = new ArrayList<Butt>();
	}

	@Override
	public void update() {

		obj +=1;
		if(obj > maxObj)
			obj = maxObj;
		if(throwing && c.getCurrentMotion() != Character.XATTACK) {
			if(obj > objUse) {
				obj -= objUse;
				
				Butt b = new Butt(c.getTileMap(), c.isFaceRight());
				b.setPosition(c.getX(), c.getY());
				objs.add(b);
			}
		}
		
		for(int i = 0; i < objs.size(); i++){
			objs.get(i).update();	// (Character)(objs.get(i)).update();였었던거
			if(((Butt)objs.get(i)).shouldRemove()){
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
			((Butt)objs.get(i)).draw(g);
	}
}