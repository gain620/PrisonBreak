package com.teamcriminals.Character;

import com.teamcriminals.Entity.Character;

public class CharacterFactory {

	//우선 캐릭터 선택해서 그에 맞는 int 값 전달로 구현
	//나중에 매개변수부분은 고칠 예정
	public Character getCharacter(int num){
		
		if(num==0){
		   return new Caesar(1,1,1,1,1); //1번 선택시 시저
		}
		else if(num==1){
			return new Zero(1,1,1,1,1); //2번 선택시 제로
		}
		else if(num==2){
			return new Fyro(1,1,1,1,1); //3번 선택시 파이로
		}
		else if(num==3){
			return new Draco(1,1,1,1,1); //4번 선택시 드라코
		}
		else{
			return null;//우리 게임의 진행상 아무것도 선택하는건 말이안됩.ㅅㅂ 일단 널값으로 구현
		}
		
	}
}
