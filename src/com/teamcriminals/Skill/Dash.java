package com.teamcriminals.Skill;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.Projectile.Butt;
import com.teamcriminals.Projectile.Projectile;

public class Dash extends X {
	
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
		damage = 20;
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
				Projectile b = new Butt(c.getTileMap(), c.isFaceRight());
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