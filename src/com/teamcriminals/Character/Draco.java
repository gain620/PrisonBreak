package com.teamcriminals.Character;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.TileMap.TileMap;

public class Draco extends Character{
	
	/*������
		public Draco(int h, int mh, int l, int sp, int bo){
			super.start(h, mh, l, sp, bo); //super�� start�� �̿�
		}
		*/
		
	//������� ��ų��� �켱 �ַܼα��� ���߿� ��ų Ŭ���� �����ԵǸ� �װ� ���
		
		public Draco(TileMap tm) {
		super(tm);
		// TODO Auto-generated constructor stub
	}

		public void skillZ(){
			System.out.println("������� ��ų Z���");
		}
		
		public void skillX(){
			System.out.println("������� ��ų X���");
		}
		
		public void skillC(){
			System.out.println("������� ��ų C���");
		}
		
		public void kk(){
			
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
