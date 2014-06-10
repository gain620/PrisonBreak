package com.teamcriminals.Skill;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.Projectile.Bats;
import com.teamcriminals.Projectile.Projectile;

public class SummonBats extends C {
	
	public SummonBats(Character c) {
		super(c);
		init();
		maxBomb = 3;
		damage = 100;
		reset();
	}
	
	@Override
	public void init() {
		projectile = new ArrayList<Projectile>();
	}
	
	public void reset() {
		bomb = maxBomb;
	}

	@Override
	public void update() {
		if(use && c.getCurrentMotion() == Character.CATTACK) {
			if(bomb > 0) {
				bomb -= 1;
				Projectile b = new Bats(c.getTileMap(), c.isFaceRight());
				b.setPosition(c.getX(), c.getY());
				projectile.add(b);
			}
		}
		for(int i = 0; i < projectile.size(); i++) {
			projectile.get(i).update();
			if(projectile.get(i).shouldRemove())
				projectile.remove(i--);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		for(int i = 0; i < projectile.size(); i++)
			projectile.get(i).draw(g);
	}
}