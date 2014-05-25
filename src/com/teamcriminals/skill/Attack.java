package com.teamcriminals.skill;

public abstract class Attack {
	
	// 속성정의. 공격력, 공격범위, 공격속도, 공격중인지
	private int damage;
	private int range;
	private int speed;
	private boolean use;
	
	public int getDamage() {
		return this.damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getRange() {
		return this.range;
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public boolean isUsed() {
		return this.use;
	}
	
	public void using(boolean result) {
		this.use = result;
	}
	
	public abstract void attack();
	
}
