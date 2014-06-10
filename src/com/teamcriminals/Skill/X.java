package com.teamcriminals.Skill;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.Projectile.Projectile;

public abstract class X extends Attack {
	
	protected int cooldown;
	protected Character c;
	protected int obj;
	protected int maxObj;
	protected int objUse;
	protected Projectile projectile;
	protected boolean throwing;
	
	public X(Character c) {
		this.c = c;
	}

	public int getObj() { return obj; }
	public int getMaxObj() { return maxObj; }
	public int getObjUse() { return objUse; }
	public Projectile getProjectile() { return projectile; }
	public boolean isThrowing() { return throwing; }
	public int getCoolDown() { return this.cooldown; }
	
	public void setObj(int obj) { this.obj = obj; }
	public void setMaxObj(int maxObj) { this.maxObj = maxObj; }
	public void setObjUse(int objUse) { this.objUse = objUse; }
	public void setProjectile(Projectile p) { this.projectile = p; }
	public void setThrowing(boolean throwing) { this.throwing = throwing; }
	public void setCoolDown(int cooldown) { this.cooldown = cooldown; }
}
