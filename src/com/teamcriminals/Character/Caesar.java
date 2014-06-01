package com.teamcriminals.Character;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.TileMap.TileMap;

public class Caesar extends Character {
	
	/*생성자
	public Caesar(int h, int mh, int l, int sp, int bo){
		super.start(h, mh, l, sp, bo); //super의 start를 이용
	}
	*/
	
	//시저의 스킬사용 우선 콘솔로구현 나중에 스킬 클래스 가지게되면 그거 사용
	
	public Caesar(TileMap tm) {
		super(tm);
		// TODO Auto-generated constructor stub
	}

	public void skillZ(){
		System.out.println("시저의 스킬 Z사용");
	}
	
	public void skillX(){
		System.out.println("시저의 스킬 X사용");
	}
	
	public void skillC(){
		System.out.println("시저의 스킬 C사용");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
