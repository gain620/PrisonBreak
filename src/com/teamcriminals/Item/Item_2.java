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
		 *  구현해야 할 부분
		 */
		
		setCount(getCount() - 1);
		if(getCount() < 1) {
			Z.setSTATUS(null);	// Z의 item_2을 소진할 경우 소멸시키는 방법 >> 이거 좀 애매함
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
