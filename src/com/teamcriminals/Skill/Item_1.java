package com.teamcriminals.Skill;

public class Item_1 extends Item {
	
	private final int MAX_COUNT = 10;
	
	public Item_1() {
		setCount(MAX_COUNT);
	}

	public void attack() {
		
		/*
		 *  �����ؾ� �� �κ�
		 */
		
		setCount(getCount() - 1);
		if(getCount() < 1) {
			Z.setSTATUS(null);	// Z�� item_1�� ������ ��� �Ҹ��Ű�� ��� >> �̰� �� �ָ���
		}
		
	}
	
}
