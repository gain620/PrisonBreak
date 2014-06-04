package com.teamcriminals.Entity;

import java.awt.Graphics2D;

import com.teamcriminals.TileMap.TileMap;

public abstract class Enemy extends MapObject {

	// �Ӽ�
	protected int health;
	protected int maxHealth;
	protected boolean dead;
	protected int damage;
	
	// ��������
	protected boolean flinch;
	protected long flinchTimer;
	
	public Enemy(TileMap tm) {
		super(tm);
		
	}
	
	public boolean isDead() {return dead;}
	
	public int getDamgage() {return damage;}
	
	public void hit(int damgage) {
		
		if(dead||flinch) return;
		
		if(health<=0) dead = true;
		
		flinch = true;
		
		flinchTimer = System.nanoTime();
		
		
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g) {
		
	}

}
