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
		 *  추가 구현
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
		
		// 위치 갱신
		getNextPosition();
		collideTile();
		setPosition(xTemp, yTemp);

		if(dx == 0)
			x = (int)x;
		
		// flinching 지속
		if(flinching) {
			flinchCount++;
			if(flinchCount > 120) {
				flinching = false;
			}
		}
		
		// 공격 모션
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
		 * 스킬부분 update 구현해야합니다
		 * 
		 * 
		 * 
		 * 
		 */
		
		// 적과의 상호작용 검사
		
		/* Enemy 정의 없이는 정의할 수 없음.. 그리고 캐릭터가 Enemy 배열 가지고있어야할듯?
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
			
		// 적과 충돌
		
		/* Enemy 설정해야 할 수 있는부분
		 * 
		if(!e.isDead() && intersects(e) && !charging) {
			hit(e.getDamage());
		}
			
		if(e.isDead()) {
			
		}
		
		*/
		
		// 모션 설정
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
				
		// 위치 방향 결정
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
			// if(flinchCount % 10 < 5) return; 아티팩트에 있던거
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
