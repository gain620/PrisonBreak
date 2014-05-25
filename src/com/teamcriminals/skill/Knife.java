package com.teamcriminals.skill;

public class Knife extends Z {
	
	public Knife() {
		setSTATUS(this);
	}

	public void attack() {
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
