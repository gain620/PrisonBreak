package com.teamcriminals.Character;

import java.awt.Graphics2D;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.Skill.Bottle;
import com.teamcriminals.Skill.ThrowFire;
import com.teamcriminals.Skill.FireWorld;
import com.teamcriminals.TileMap.TileMap;

public class Fyro extends Character {
	
	public Fyro(TileMap tm) {
		
		super(tm);
		
		/*
		 *  �߰� ����
		 */
		
	}

	public void init() {
		
		skillZ = new Bottle();
		skillX = new ThrowFire();
		skillC = new FireWorld();
		/*
		this.enemies = enemies;
		this.boss = boss;
		*/
	}

	
	public void update() {
		
		// ��ġ ����
		getNextPosition();
		collideTile();
		setPosition(xTemp, yTemp);

		if(dx == 0)
			x = (int)x;
		
		// flinching ����
		if(flinching) {
			flinchCount++;
			if(flinchCount > 120) {
				flinching = false;
			}
		}
		
		// ���� ���
		if(currentMotion == ZATTACK){
			if(motion.hasPlayedOnce()){
				Zattacking = false;
			}
		}
				
		if(currentMotion == XATTACK){
			if(motion.hasPlayedOnce()){
				Xattacking = false;
			}
		}
				
		if(currentMotion == CATTACK){
			if(motion.hasPlayedOnce()){
				Cattacking = false;
			}
		}
		

		/*
		 * 
		 * ��ų�κ� update �����ؾ��մϴ�
		 * 
		 * 
		 * 
		 * 
		 */
		
		// ������ ��ȣ�ۿ� �˻�
		
		/* Enemy ���� ���̴� ������ �� ����.. �׸��� ĳ���Ͱ� Enemy �迭 �������־���ҵ�?
		 * 
		for(int i = 0; i < enemies.size(); i++) {
			
			Enemy e = enemies.get(i);
			
			// check attack
			if(currentMotion == ZATTACK && motion.getFrame() == 3 && motion.getCount() == 0) {
				if(e.intersects(ar)) {
					e.hit(skillZ.attack());
				}
			}
			else if(currentMotion == XATTACK && motion.getFrame() == 3 && motion.getCount() == 0) {
				if(e.intersects(ar)) {
					e.hit(skillZ.attack());
				}
			}
			else if(currentMotion == CATTACK && motion.getFrame() == 3 && motion.getCount() == 0) {
				if(e.intersects(ar)) {
					e.hit(skillZ.attack());
				}
			}
			
		*/
			
		// ���� �浹
		
		/* Enemy �����ؾ� �� �� �ִºκ�
		 * 
		if(!e.isDead() && intersects(e) && !charging) {
			hit(e.getDamage());
		}
			
		if(e.isDead()) {
			
		}
		
		*/
		
		// ��� ����
		if(knockback) {
			if(currentMotion != KNOCKBACK) {
				setMotion(KNOCKBACK);
			}
		}
		else if(health == 0) {
			if(currentMotion != DEAD) {
				setMotion(DEAD);
			}
		}
		else if(Zattacking) {
			if(currentMotion != ZATTACK) {
				currentMotion = ZATTACK;
				motion.setFrames(sprites.get(ZATTACK));
				motion.setDelay(50);
				width = 60;
				ar.y = (int)y - 6;
				if(faceRight)
					ar.x = (int)x + 10;
				else
					ar.x = (int)x - 40;
			}
		}
		else if(Xattacking) {
			if(currentMotion != XATTACK) {
				currentMotion = XATTACK;
				motion.setFrames(sprites.get(XATTACK));
				motion.setDelay(100);
				width = 30;
				ar.y = (int)y - 6;
				if(faceRight)
					ar.x = (int)x + 10;
				else
					ar.x = (int)x - 40;
			}
		}
		else if(Cattacking) {
			if(currentMotion != CATTACK) {
				currentMotion = CATTACK;
				motion.setFrames(sprites.get(CATTACK));
				motion.setDelay(100);
				width = 30;
				ar.y = (int)y - 6;
				if(faceRight)
					ar.x = (int)x + 10;
				else
					ar.x = (int)x - 40;
			}
		}
		else if(dy > 0) {
			if(currentMotion != FALL) {
				currentMotion = FALL;
				motion.setFrames(sprites.get(FALL));
				motion.setDelay(100);
				width = 30;
			}
		}
		else if(dy < 0) {
			if(currentMotion != JUMP) {
				currentMotion = JUMP;
				motion.setFrames(sprites.get(JUMP));
				motion.setDelay(-1);
				width = 30;
			}
		}
		else if(left || right) {
			if(currentMotion != WALK) {
				currentMotion = WALK;
				motion.setFrames(sprites.get(WALK));
				motion.setDelay(40);
				width = 30;
			}
		}
		else {
			if(currentMotion != IDLE) {
				currentMotion = IDLE;
				motion.setFrames(sprites.get(IDLE));
				motion.setDelay(400);
				width = 30;
			}
		}
			
		motion.update();
				
		// ��ġ ���� ����
		if(currentMotion != ZATTACK && currentMotion != XATTACK && currentMotion != CATTACK && !knockback) {
			
			if(right)
				faceRight = true;
			if(left)
				faceRight = false;
			
		}
	
	}

	@Override
	public void draw(Graphics2D g) {
		
		setMapPosition();
		
		if(flinching && !knockback) {
			// if(flinchCount % 10 < 5) return; ��Ƽ��Ʈ�� �ִ���
			long elapsed =
				(System.nanoTime() - flinchCount) / 1000000;
			if(elapsed / 100 % 2 == 0) {
				return;
			}
		}
		
		if(faceRight) {
			g.drawImage(
				motion.getImage(),
				(int)(x + xMap - width / 2),
				(int)(y + yMap - height / 2),
				null
			);
		}
		else {
			g.drawImage(
				motion.getImage(),
				(int)(x + xMap - width / 2 + width),
				(int)(y + yMap - height / 2),
				-width,
				height,
				null
			);
		}
		
	}

}
