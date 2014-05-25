package com.teamcriminals.skill;

public abstract class Attack {
	
	// �Ӽ�����. ���ݷ�, ���ݹ���, ���ݼӵ�, ����������
	protected int damage;
	protected int range;
	protected int speed;
	protected boolean use;
	
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
	
	public abstract void attack();
}
