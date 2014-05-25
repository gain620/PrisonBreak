package com.teamcriminals.skill;

public class LegShot extends X {
	
	public LegShot() {
		setCoolDown(12);
	}

	public void attack() {

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
			return;
	}
	
}