package com.teamcriminals.Skill;

import java.awt.Graphics2D;

import com.teamcriminals.Item.*;

public class Bottle extends Z {
	
	public Bottle() {
		
		setSTATUS(this);
		damage = 10;
		range = 40;
	
	}

	public void init() {
		setSTATUS(this);
	}

	public void toItem_1() {
		setSTATUS(new Item_1());
	}

	public void toItem_2() {
		setSTATUS(new Item_2());
	}

	public void toItem_3() {
		setSTATUS(new Item_3());
	}

	@Override
	public void update() {
		if(getSTATUS() != null) {
			if(getSTATUS() == this) {
				/*
				 * 구현해야 할 부분
				 */
			}
			else
				getSTATUS().update();
		}
		else {
			init();
			update();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		
	}
	
}
