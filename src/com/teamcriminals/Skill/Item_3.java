package com.teamcriminals.Skill;

import java.awt.Graphics2D;

public class Item_3 extends Item {
	
	private final int MAX_COUNT = 10;
	
	public Item_3() {
		setCount(MAX_COUNT);
	}

	public int attack() {

		/*
		 *  �����ؾ� �� �κ�
		 */
		
		setCount(getCount() - 1);
		if(getCount() < 1) {
			Z.setSTATUS(null);	// Z�� item_3�� ������ ��� �Ҹ��Ű�� ��� >> �̰� �� �ָ���
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
