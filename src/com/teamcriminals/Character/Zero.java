package com.teamcriminals.Character;


import com.teamcriminals.Entity.Character;
import com.teamcriminals.TileMap.TileMap;

public class Zero extends Character {
	
	/*������
			public Zero(int h, int mh, int l, int sp, int bo){
				super.start(h, mh, l, sp, bo); //super�� start�� �̿�
			}*/
			
	//������ ��ų��� �켱 �ַܼα��� ���߿� ��ų Ŭ���� �����ԵǸ� �װ� ���
			
			public Zero(TileMap tm) {
		super(tm);
		// TODO Auto-generated constructor stub
	}

			public void skillZ(){
				System.out.println("������ ��ų Z���");
			}
			
			public void skillX(){
				System.out.println("������ ��ų X���");
			}
			
			public void skillC(){
				System.out.println("������ ��ų C���");
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
