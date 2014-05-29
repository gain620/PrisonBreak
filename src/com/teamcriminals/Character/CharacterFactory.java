package com.teamcriminals.Character;

import com.teamcriminals.Entity.Character;

public class CharacterFactory {

	//�켱 ĳ���� �����ؼ� �׿� �´� int �� ���޷� ����
	//���߿� �Ű������κ��� ��ĥ ����
	public Character getCharacter(int num){
		
		if(num==0){
		   return new Caesar(1,1,1,1,1); //1�� ���ý� ����
		}
		else if(num==1){
			return new Zero(1,1,1,1,1); //2�� ���ý� ����
		}
		else if(num==2){
			return new Fyro(1,1,1,1,1); //3�� ���ý� ���̷�
		}
		else if(num==3){
			return new Draco(1,1,1,1,1); //4�� ���ý� �����
		}
		else{
			return null;//�츮 ������ ����� �ƹ��͵� �����ϴ°� ���̾ȵ�.���� �ϴ� �ΰ����� ����
		}
		
	}
}
