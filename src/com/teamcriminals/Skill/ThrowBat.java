package com.teamcriminals.Skill;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.teamcriminals.Entity.FireBall;
import com.teamcriminals.Projectile.Bat;

public class ThrowBat extends X {
	
	private int bat;
	private int maxBat;
	private int batUse;
	private int batDamage;
	private ArrayList<Bat> bats;
	
	private boolean throwing;
	
	public ThrowBat() {
		
		cooldown = 13;
		
		bat = maxBat = 60 * cooldown;
		
		batUse = 60 * cooldown;
		batDamage = 5;
		bats = new ArrayList<Bat>();
		
		
	}

	public int attack() {

		if(!isUsed()) {
			
			/*
			 *  공격부분 구현
			 */
			
			using(true);
			
			try {
				this.wait(getCoolDown() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			
			}
			
			using(false);

		}
		else
			return 0;
		return 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
}