package com.teamcriminals.Skill;

import java.awt.Graphics2D;

public class Punch extends Z {
	
	public Punch() {
		
		setSTATUS(this);
		damage = 10;
	
	}

	/*
	 * �̹��� Sprites��  projectile ���� ������ �ؾ���
	 * 
	 */
	
	public int attack() {
		if(getSTATUS() != null) {
			if(getSTATUS() == this) {
				/*
				 * �����ؾ� �� �κ�
				 */
				return damage;
			}
			else
				getSTATUS().attack();
		}
		else {
			init();
			attack();
		}
		return 0;	
	}

	public void init() {
		setSTATUS(this);
	}

	public void toItem_1() {
		setSTATUS(new Item_1());
	}

	public void toItem_2() {
		setSTATUS(new Item_2());
	}

	public void toItem_3() {
		setSTATUS(new Item_3());
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		
	}
	
}
