package com.teamcriminals.skill;

public class ThrowBat extends X {
	
	public ThrowBat() {
		setCoolDown(13);
	}

	public void attack() {

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