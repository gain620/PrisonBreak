package com.teamcriminals.Skill;

import java.awt.Graphics2D;

import com.teamcriminals.Item.Item_1;
import com.teamcriminals.Item.Item_2;
import com.teamcriminals.Item.Item_3;

public class Bottle extends Z {
	
	public Bottle() {
		setSTATUS(this);
	}

	public int attack() {
		if(getSTATUS() != null) {
			if(getSTATUS() == this) {
				/*
				 * 구현해야 할 부분
				 */
			}
			else
				getSTATUS().attack();
		}
		else {
			init();
			attack();
		}
		return 0;	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
	
}
