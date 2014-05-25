package com.teamcriminals.skill;

public abstract class Z extends Attack {
	private static Attack STATUS;
	
	public static Attack getSTATUS() {
		return STATUS;
	}
	public static void setSTATUS(Attack _STATUS) {
		STATUS = _STATUS;
	}
	
	public abstract void init();
	public abstract void toItem_1();
	public abstract void toItem_2();
	public abstract void toItem_3();
	
}
