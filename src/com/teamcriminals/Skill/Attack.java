package com.teamcriminals.Skill;

import java.awt.Graphics2D;

public abstract class Attack {
	
	// 속성정의. 공격력, 공격범위, 공격속도, 공격중인지, 맞았는지
	protected int damage;
	protected int range;
	protected int speed;
	protected boolean use;
	protected boolean hit;
	
	public int getDamage() { return this.damage; }
	public int getRange() { return this.range; }
	public int getSpeed() { return this.speed; }
	public boolean isUsed() { return this.use; }
	
	public void setDamage(int damage) { this.damage = damage; }
	public void setRange(int range) { this.range = range; }
	public void setSpeed(int speed) { this.speed = speed; }
	public void using(boolean result) { this.use = result; }
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	
}
