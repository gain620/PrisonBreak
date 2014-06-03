package com.teamcriminals.Skill;

public abstract class X extends Attack {
	
	protected int cooldown;
	
	public int getCoolDown() {
		return this.cooldown;
	}
	
	public void setCoolDown(int cooldown) {
		this.cooldown = cooldown;
	}
}
