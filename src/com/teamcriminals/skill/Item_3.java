package com.teamcriminals.skill;

public class Item_3 extends Item {
	
	private final int MAX_COUNT = 10;
	
	public Item_3() {
		setCount(MAX_COUNT);
	}

	public void attack() {

		/*
		 *  구현해야 할 부분
		 */
		
		setCount(getCount() - 1);
		if(getCount() < 1) {
			Z.setSTATUS(null);	// Z의 item_3을 소진할 경우 소멸시키는 방법 >> 이거 좀 애매함
		}
		
	}
}
