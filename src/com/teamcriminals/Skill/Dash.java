package com.teamcriminals.Skill;

public class Dash extends X {
	
	public Dash() {
		setCoolDown(10);
	}

	public int attack() {
		
		if(!isUsed()) {
			
			/*
			 *  ���ݺκ� ����
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