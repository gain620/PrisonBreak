package com.teamcriminals.Skill;

import java.util.ArrayList;

import com.teamcriminals.Entity.Character;

public abstract class X<T> extends Attack {
	
	protected int cooldown;
	protected Character c;
	protected int obj;
	protected int maxObj;
	protected int objUse;
	protected ArrayList<T> objs;
	protected boolean throwing;
	
	public X(Character c) {
		this.c = c;
	}

	public int getObj() { return obj; }
	public int getMaxObj() { return maxObj; }
	public int getObjUse() { return objUse; }
	public ArrayList<T> getObjs() { return objs; }
	public boolean isThrowing() { return throwing; }
	public int getCoolDown() { return this.cooldown; }
	
	public void setObj(int obj) { this.obj = obj; }
	public void setMaxObj(int maxObj) { this.maxObj = maxObj; }
	public void setObjUse(int objUse) { this.objUse = objUse; }
	public void setObjs(ArrayList<T> objs) { this.objs = objs; }
	public void setThrowing(boolean throwing) { this.throwing = throwing; }
	public void setCoolDown(int cooldown) { this.cooldown = cooldown; }
}
