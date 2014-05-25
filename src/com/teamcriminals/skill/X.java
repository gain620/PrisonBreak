package com.teamcriminals.skill;

public abstract class X extends Attack {
	
	private long cooldown;
	
	public long getCoolDown() {
		return this.cooldown;
	}
	
	public void setCoolDown(long cooldown) {
		this.cooldown = cooldown;
	}
}
