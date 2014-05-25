package com.teamcriminals.Character;


import com.teamcriminals.Entity.Character;

public class Zero extends Character {
	
	//생성자
			public Zero(int h, int mh, int l, int sp, int bo){
				super.start(h, mh, l, sp, bo); //super의 start를 이용
			}
			
	//제로의 스킬사용 우선 콘솔로구현 나중에 스킬 클래스 가지게되면 그거 사용
			
			public void skillZ(){
				System.out.println("제로의 스킬 Z사용");
			}
			
			public void skillX(){
				System.out.println("제로의 스킬 X사용");
			}
			
			public void skillC(){
				System.out.println("제로의 스킬 C사용");
			}


}
