package com.teamcriminals.Skill;

import java.awt.Graphics2D;

public abstract class Attack {
	
	// �Ӽ�����. ���ݷ�, ���ݹ���, ���ݼӵ�, ����������, �¾Ҵ���
	protected int damage;
	protected int range;
	protected int speed;
	protected boolean use;
	protected boolean hit;
	
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
	
	public abstract int attack();
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	
}
