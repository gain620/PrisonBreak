package com.teamcriminals.Skill;

public class Item_2 extends Item {
	
	private final int MAX_COUNT = 10;
	
	public Item_2() {
		setCount(MAX_COUNT);
	}

	public void attack() {

		/*
		 *  �����ؾ� �� �κ�
		 */
		
		setCount(getCount() - 1);
		if(getCount() < 1) {
			Z.setSTATUS(null);	// Z�� item_2�� ������ ��� �Ҹ��Ű�� ��� >> �̰� �� �ָ���
		}
		
	}
}
