package com.teamcriminals.Character;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.teamcriminals.Entity.Character;
import com.teamcriminals.Entity.Enemy;
import com.teamcriminals.Entity.MapObject;
import com.teamcriminals.Motion.Motion;
import com.teamcriminals.Projectile.Projectile;
import com.teamcriminals.Skill.Knife;
import com.teamcriminals.Skill.SummonBats;
import com.teamcriminals.Skill.ThrowBat;
import com.teamcriminals.TileMap.TileMap;

public class Draco extends Character{
	
	public Draco(TileMap tm) {
		
		super(tm);
		
		init();
		
		width = 80;
		height = 80;
		cWidth = 35;
		cHeight = 40;
		
		health = maxHealth = 100;
		life = 3;

		moveSpeed = 0.3;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		faceRight = true;
		
		try {

			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream("/Sprites/Character/Caesar.gif")
			);
			
			for(int i = 0; i < 8; i++) {

				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++)
					bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
				
				sprites.add(bi);

			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		currentMotion = IDLE;
		motion.setFrames(sprites.get(IDLE));
		motion.setDelay(400);	// �̰� ������ ���ִ°���? �����ǹ���?
		
	}

	// ��ü ���� ����
	public void init() {
		
		skillZ = new Knife();
		skillX = new ThrowBat(this);
		skillC = new SummonBats();
		sprites = new ArrayList<BufferedImage[]>();
		motion = new Motion();
		
		/*
		this.enemies = enemies;
		this.boss = boss;
		*/
	}
	

	// ���ݴ��� ���
	public void hit(int damage) {
		
		if(flinching)
			return;
		
		stop();
		
		health -= damage;
		
		if(health < 0)
			health = 0;
		
		flinching = true;
		flinchCount = 0;
		
		if(faceRight)
			dx = -1;
		else
			dx = 1;
		
		dy = -3;
		
		knockback = true;
		fall = true;
		jump = false;
		
	}
	
	// �ʱ�ȭ
	public void reset() {
		
		health = maxHealth;
		faceRight = true;
		currentMotion = -1;
		stop();
		
	}
	
	// ��� �ൿ�� ���߰� ��
	public void stop() {
		
		up = down = left = right = flinching = jump = Zattacking = Xattacking = Cattacking = false;
	
	}
	
	public void dead() {
		
		health = 0;
		life--;
		stop();
	
	}

	public void checkAttack(ArrayList<Enemy> enemy) {
		
		// ��� ������ �� �ҷ�����
		for(int i = 0; i< enemy.size(); i++) {
			Enemy e = enemy.get(i);
			
			// Z ���� ����
			if(Zattacking) {
				if(faceRight) {
					if(e.getX() > x && 
						e.getX() < x + skillZ.getRange() &&
						e.getY() > y - height / 2 && 
						e.getY() < y + height / 2) {
						e.hit(skillZ.getDamage());
					}
				}
			} else {
				if(e.getX() < x && 
					e.getX() > x - skillZ.getRange() && 
					e.getY() > y - height / 2 && 
					e.getY() < y - height / 2) {
					e.hit(skillZ.getDamage());
				}
			}
			
			// ȭ���� ���� ����
			for(int j = 0; j < skillX.getObjs().size(); j++) {
				if(((MapObject)skillX.getObjs().get(i)).intersects(e)) {
					e.hit(skillX.getDamage());
					((Projectile)skillX.getObjs().get(j)).setHit();
					break;
				}
			}
			
			/*
			 *  �߰� ����
			 */
			
		}	
		
	}
	
	public void getNextPosition() {
		
		if(knockback) {
			
			dy += fallSpeed * 2;
			
			if(!fall)
				knockback = false;
			
			return;
		
		}
		
		double maxSpeed = this.maxSpeed;
		
		// �¿��̵�
		if(left) {
	
			dx -= moveSpeed;
	
			if(dx < -maxSpeed)
				dx = -maxSpeed;
		
		}
		else if(right) {
		
			dx += moveSpeed;
			
			if(dx > maxSpeed) 
				dx = maxSpeed;
			
		}
		else {
			if(dx > 0) {
				
				dx -= stopSpeed;
			
				if(dx < 0)
					dx = 0;
				
			}
			else if(dx < 0) {
				
				dx += stopSpeed;
				
				if(dx > 0)
					dx = 0;

			}
		}
		
		// �������϶� ������ �� ���� ������ ���������� ������
		if((Zattacking || Xattacking || Cattacking) && !(jump || fall)) {	// (currentMotion == ZATTACK ... �̷������� �ؾ��ϳ�?)
			
			dx = 0;
		
		}
		
		// ����
		if(jump && !fall) {
			
			dy = jumpStart;
			fall = true;
		
		}
		
		// ������ ��
		if(fall) {
			
			dy += fallSpeed;
			
			if(dy < 0 && !jump) 
				dy += stopJumpSpeed;
			
			
			if(dy > maxFallSpeed) 
				dy = maxFallSpeed;
			
		}
		
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
