package com.teamcriminals.Character;


import com.teamcriminals.Entity.Character;
import com.teamcriminals.Entity.Character2;

public class Zero extends Character2 {
	
	//������
			public Zero(int h, int mh, int l, int sp, int bo){
				super.start(h, mh, l, sp, bo); //super�� start�� �̿�
			}
			
	//������ ��ų��� �켱 �ַܼα��� ���߿� ��ų Ŭ���� �����ԵǸ� �װ� ���
			
			public void skillZ(){
				System.out.println("������ ��ų Z���");
			}
			
			public void skillX(){
				System.out.println("������ ��ų X���");
			}
			
			public void skillC(){
				System.out.println("������ ��ų C���");
			}


}
