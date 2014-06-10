package com.teamcriminals.Skill;

import java.awt.Graphics2D;
import com.teamcriminals.Entity.Character;
import com.teamcriminals.Projectile.Butt;

public class Dash extends X {
	
	/*
	 * ��� ���Ÿ��� �ƴ϶� �뽬�ϴ°Ŷ� ���� ��ų���� �� �ٸ��� �����ؾ���
	 * ��ü�� ���� �ɸ��͵� �Բ� �̵���Ű�°�?
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
		projectile = new Butt(c.getTileMap(), c.isFaceRight());
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