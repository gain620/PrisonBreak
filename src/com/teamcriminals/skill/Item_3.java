package com.teamcriminals.skill;

public class Item_3 extends Item {
	
	private final int MAX_COUNT = 10;
	
	public Item_3() {
		setCount(MAX_COUNT);
	}

	public void attack() {

		/*
		 *  �����ؾ� �� �κ�
		 */
		
		setCount(getCount() - 1);
		if(getCount() < 1) {
			Z.setSTATUS(null);	// Z�� item_3�� ������ ��� �Ҹ��Ű�� ��� >> �̰� �� �ָ���
		}
		
	}
}
