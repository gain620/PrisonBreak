package com.teamcriminals.Skill;

import java.util.ArrayList;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.Projectile.Projectile;

public abstract class C extends Attack {
	
	protected Character c;
	protected ArrayList<Projectile> projectile;
	protected int bomb;
	protected int maxBomb;
	protected boolean use;
	
	public C(Character c) {
		this.c = c;
	}

	public ArrayList<Projectile> getProjectile() { return projectile;}
	public int getBomb() { return bomb; }
	public int getMaxBomb() { return maxBomb; }
	public boolean isUse() { return use; }
	
	public void setBomb(int bomb) { this.bomb = bomb; }
	public void setMaxBomb(int maxBomb) { this.maxBomb = maxBomb; }
	public void setUse(boolean b) { this.use = b; }

}