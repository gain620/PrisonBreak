package com.teamcriminals.Skill;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.Projectile.Bat;

public class ThrowBat extends X {
	
	private Character c;
	private int bat;
	private int maxBat;
	private int batUse;
	private int batDamage;
	private ArrayList<Bat> bats;
	
	private boolean throwing;
	
	public ThrowBat(Character c) {
		
		this.c = c;
		
		cooldown = 13;
		
		bat = maxBat = 60 * cooldown;
		batUse = 60 * cooldown;
		batDamage = 5;
		bats = new ArrayList<Bat>();
		
	}

	@Override
	public void update() {

		bat +=1;
		if(bat > maxBat)
			bat = maxBat;
		if(throwing && c.getCurrentMotion() != c.XATTACK) {
			if(bat > batUse) {
				bat -= batUse;
				
				Bat b = new Bat(c.getTileMap(), c.isFaceRight());
				b.setPosition(c.getX(), c.getY());
				bats.add(b);
			}
		}
		
		for(int i = 0; i < bats.size(); i++){
			bats.get(i).update();
			if(bats.get(i).shouldRemove()){
				bats.remove(i);
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

		for(int i = 0; i < bats.size(); i++)
			bats.get(i).draw(g);
						
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	
}