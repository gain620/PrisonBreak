package com.teamcriminals.Item;

import java.awt.Graphics2D;

import com.teamcriminals.Skill.Z;

public class Item_2 extends Item {
	
	private final int MAX_COUNT = 10;
	
	public Item_2() {
		setCount(MAX_COUNT);
	}

	public int attack() {

		/*
		 *  �����ؾ� �� �κ�
		 */
		
		setCount(getCount() - 1);
		if(getCount() < 1) {
			Z.setSTATUS(null);	// Z�� item_2�� ������ ��� �Ҹ��Ű�� ��� >> �̰� �� �ָ���
		}
		return 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
