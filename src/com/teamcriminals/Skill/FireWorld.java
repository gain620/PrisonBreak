package com.teamcriminals.Skill;

import java.awt.Graphics2D;

public class FireWorld extends C {
	
	public FireWorld() {
		setBomb(3);
	}

	public int attack() {

		if(getBomb() > 0) {
			
			/*
			 * 공격부분 구현
			 */
			setBomb(getBomb() - 1);
		}
		else {
			return 0;
		}
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