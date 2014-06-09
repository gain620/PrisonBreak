package com.teamcriminals.Skill;

import java.awt.Graphics2D;

public abstract class Attack {
	
	// 속성정의. 공격력, 공격범위, 공격속도, 공격중인지, 맞았는지
	protected int damage;
	protected int range;
	protected boolean attack;
	
	public int getDamage() { return this.damage; }
	public int getRange() { return this.range; }
	public boolean isAttack() { return this.attack; }
	
	public void setDamage(int damage) { this.damage = damage; }
	public void setRange(int range) { this.range = range; }
	public void using(boolean b) { this.attack = b; }
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	
}
