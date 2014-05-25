package com.teamcriminals.skill;

public class ThrowFire extends X {
	
	public ThrowFire() {
		setCoolDown(9);
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