package com.teamcriminals.Item;

import com.teamcriminals.Skill.Attack;

public abstract class Item extends Attack {
	
	private int count;
	
	public int getCount() {
		return this.count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
}
