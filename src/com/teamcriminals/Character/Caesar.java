package com.teamcriminals.Character;

import java.awt.Graphics2D;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.TileMap.TileMap;

public class Caesar extends Character {
	
	public Caesar(TileMap tm) {
		
		super(tm);
		
		/*
		 *  �߰� ����
		 */
		
	}

	
	/*public void init(ArrayList<Enemy> enemies, Boss boss) {
		
		this.enemies = enemies;
		this.boss = boss;
		
	}*/

	
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
		
		// ���� üũ
		if(currentMotion == ZATTACK || currentMotion == XATTACK || currentMotion == CATTACK) {
			if(motion.hasPlayedOnce()) {
				attacking = false;
			}
		}
		
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
		
		
		// �ൿ����. �켱���������� ������
		
		/*
		 * �ڷ���Ʈ �κ� ����
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
		
		// ���� ���
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
