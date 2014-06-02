package com.teamcriminals.Skill;

import java.awt.Graphics2D;

public class LegShot extends X {
	
	public LegShot() {
		setCoolDown(12);
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