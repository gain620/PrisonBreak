package com.teamcriminals.Skill;

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
	
}
