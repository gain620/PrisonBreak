package com.teamcriminals.Skill;

public class ThrowBat extends X {
	
	public ThrowBat() {
		setCoolDown(13);
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
			return 0;
		return 0;
	}
	
}