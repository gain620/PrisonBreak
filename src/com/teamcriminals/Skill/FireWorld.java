package com.teamcriminals.Skill;

public class FireWorld extends C {
	
	public FireWorld() {
		setBomb(3);
	}

	public int attack() {

		if(getBomb() > 0) {
			
			/*
			 * ���ݺκ� ����
			 */
			setBomb(getBomb() - 1);
		}
		else {
			return 0;
		}
		return 0;
		
	}
	
}