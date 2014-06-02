package com.teamcriminals.Character;

import java.awt.Graphics2D;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.TileMap.TileMap;

public class Caesar extends Character {
	
	public Caesar(TileMap tm) {
		
		super(tm);
		
		/*
		 *  추가 구현
		 */
		
	}

	
	/*public void init(ArrayList<Enemy> enemies, Boss boss) {
		
		this.enemies = enemies;
		this.boss = boss;
		
	}*/

	
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
		
		// 공격 체크
		if(currentMotion == ZATTACK || currentMotion == XATTACK || currentMotion == CATTACK) {
			if(motion.hasPlayedOnce()) {
				attacking = false;
			}
		}
		
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
		
		
		// 행동설정. 우선순위순으로 정렬함
		
		/*
		 * 텔레포트 부분 뺏음
		 */
		
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

		if(attacking) {
			if(currentMotion != ZATTACK) {
				setMotion(ZATTACK);
				ar.y = (int)y - 6;
				if(faceRight)
					ar.x = (int)x + 10;
				else
					ar.x = (int)x - 40;
			}
			else if(currentMotion != XATTACK) {
				setMotion(XATTACK);
				ar.y = (int)y - 6;
				if(faceRight)
					ar.x = (int)x + 10;
				else
					ar.x = (int)x - 40;
			}
			else if(currentMotion != CATTACK) {
				setMotion(CATTACK);
				ar.y = (int)y - 6;
				if(faceRight)
					ar.x = (int)x + 10;
				else
					ar.x = (int)x - 40;
			}
		}

		else if(dy < 0) {
			if(currentMotion != JUMP) {
				setMotion(JUMP);
			}
		}
		else if(dy > 0) {
			if(currentMotion != FALL) {
				setMotion(FALL);
			}
		}
		else if(left || right) {
			if(currentMotion != WALK) {
				setMotion(WALK);
			}
		}
		else if(currentMotion != IDLE) {
			setMotion(IDLE);
		}
		
		motion.update();
		
		// set direction
		if(!attacking && !knockback) {
			if(right) faceRight = true;
			if(left) faceRight = false;
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		// 맞을 경우
		if(flinching && !knockback) {
			if(flinchCount % 10 < 5) return;
		}
		
		super.draw(g);
		
	}


	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
