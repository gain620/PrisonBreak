package com.teamcriminals.Skill;

import java.util.ArrayList;

import com.teamcriminals.Entity.Character;

public abstract class X<T> extends Attack {
	
	protected int cooldown;
	protected Character c;
	protected int obj;
	protected int maxObj;
	protected int objUse;
	protected int objDamage;
	protected ArrayList<T> objs;
	protected boolean throwing;
	
	public X(Character c) {
		
		this.c = c;
		
	}

	public int getObj() {
		return obj;
	}

	public void setObj(int obj) {
		this.obj = obj;
	}

	public int getMaxObj() {
		return maxObj;
	}

	public void setMaxObj(int maxObj) {
		this.maxObj = maxObj;
	}

	public int getObjUse() {
		return objUse;
	}

	public void setObjUse(int objUse) {
		this.objUse = objUse;
	}

	public int getObjDamage() {
		return objDamage;
	}

	public void setObjDamage(int objDamage) {
		this.objDamage = objDamage;
	}

	public ArrayList<T> getObjs() {
		return objs;
	}

	public void setObjs(ArrayList<T> objs) {
		this.objs = objs;
	}

	public boolean isThrowing() {
		return throwing;
	}

	public void setThrowing(boolean throwing) {
		this.throwing = throwing;
	}
	
	public int getCoolDown() {
		return this.cooldown;
	}
	
	public void setCoolDown(int cooldown) {
		this.cooldown = cooldown;
	}
}
