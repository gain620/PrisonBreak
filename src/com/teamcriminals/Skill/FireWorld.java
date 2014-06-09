package com.teamcriminals.Skill;

import java.awt.Graphics2D;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.Projectile.Fire;

public class FireWorld extends C {
	
	public FireWorld(Character c) {
		super(c);
		init();
		bomb = maxBomb = 3;
		damage = 100;
	}
	
	@Override
	public void init() {
		p = new Fire(c.getTileMap(), c.isFaceRight());
	}
	
	public void reset() {
		bomb = maxBomb;
	}

	@Override
	public void update() {
		if(use && c.getCurrentMotion() != Character.CATTACK) {
			if(bomb > 0 && p != null) {
				--bomb;
				p.setPosition(c.getX(), c.getY());
				p.update();
			}
			else if(bomb> 0 && p == null) {
				--bomb;
				init();
				p.setPosition(c.getX(), c.getY());
				p.update();
			}
			else
				return;
		}
		
		if(use) {
			if(c.getCurrentMotion() != Character.CATTACK) {
				c.setCurrentMotion(Character.CATTACK);
				c.getMotion().setFrames(c.getSprites().get(Character.CATTACK));
				c.getMotion().setDelay(100);
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		p.draw(g);
		if(p.shouldRemove())
			p = null;
	}
}