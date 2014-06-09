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

public class Draco extends Character {
	
	public Draco(TileMap tm) {
		
		super(tm);
		
		init();
		
		width = 80;
		height = 80;
		cWidth = 45;
		cHeight = 40;
		
		health = maxHealth = 100;
		life = 3;

		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		faceRight = true;
		
		try {

			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream("/Sprites/Character/draco.jpg")
			);
			
			for(int i = 0; i < 7; i++) {

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
		motion.setDelay(400);
		
	}
	
	// 객체 생성 전담
	public void init() {
		skillZ = new Knife();
		skillX = new ThrowBat(this);
		skillC = new SummonBats(this);
		sprites = new ArrayList<BufferedImage[]>();
		motion = new Motion();
	}
	
	// 공격당할 경우
	public void hit(int damage) {
		if(flinching) return;
		stop();
		health -= damage;
		if(health < 0)	health = 0;
		flinching = true;
		flinchCount = 0;
		if(faceRight)	dx = -1;
		else	dx = 1;
		dy = -3;
		fall = true;
		jump = false;
	}
	
	// 초기화
	public void reset() {
		health = maxHealth;
		faceRight = true;
		currentMotion = -1; // 이건 왜 -1로 주는거?
		stop();
	}
	
	// 모든 행동을 멈추게 함
	public void stop() {
		up = down = left = right = flinching = jump = Zattacking = Xattacking = Cattacking = false;
	}
	
	public void dead() {
		health = 0;
		life--;
		stop();
	}

	public void checkAttack(ArrayList<Enemy> enemy) {
		// 모든 가능한 적 불러오기
		for(int i = 0; i < enemy.size(); i++) {
			Enemy e = enemy.get(i);
			
			// Z 공격 판정
			if(Zattacking) {
				if(faceRight) {
					if( e.getX() > x && 
						e.getX() < x + skillZ.getRange() &&
						e.getY() > y - height / 2 && 
						e.getY() < y + height / 2) {
						e.hit(skillZ.getDamage());
					}
				}
				else {
					if( e.getX() < x && 
						e.getX() > x - skillZ.getRange() && 
						e.getY() > y - height / 2 && 
						e.getY() < y + height / 2) {
						e.hit(skillZ.getDamage());
					}
				}
			}
			
			// X 공격 판정
			for(int j = 0; j < skillX.getObjs().size(); j++) {
				if(((MapObject)skillX.getObjs().get(i)).intersects(e)) {
					e.hit(skillX.getDamage());
					((Projectile)skillX.getObjs().get(j)).setHit();
					break;
				}
			}

			// C 공격 판정
			if(skillC.getProjectile().intersects(e)) {
				e.hit(skillC.getDamage());
				skillC.getProjectile().setHit();
				break;
			}
			
			// 피격 판정
			if(intersects(e)) {
				hit(e.getDamgage());
			}
			
		}	
		
	}
	
	public void getNextPosition() {		
		double maxSpeed = this.maxSpeed;
		
		// 좌우이동
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
		
		// 공격중일땐 움직일 수 없고 점프나 떨어질때는 가능함
		if((Zattacking || Xattacking || Cattacking) && !(jump || fall))
			dx = 0;
		
		// 점프
		if(jump && !fall) {
			dy = jumpStart;
			fall = true;
		}
		
		// 떨어질 때
		if(fall) {
			dy += fallSpeed;
			if (dy > 0)
				jump = false;
			if(dy < 0 && !jump) 
				dy += stopJumpSpeed;
			if(dy > maxFallSpeed) 
				dy = maxFallSpeed;
		}
		
	}
	
	public void update() {
		
		// 위치 갱신
		getNextPosition();
		collideTile();
		setPosition(xTemp, yTemp);

		if(dx == 0)	// ???????????????? 왜 넣었을까
			x = (int)x;
		
		// flinching 지속
		if(flinching) {
			flinchCount++;
			if(flinchCount > 80) {
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
		
		// 모션 설정
		if(Zattacking) {
			if(currentMotion != ZATTACK) {
				currentMotion = ZATTACK;
				motion.setFrames(sprites.get(ZATTACK));
				motion.setDelay(50);
			}
		}
		else if(Xattacking) {
			if(currentMotion != XATTACK) {
				currentMotion = XATTACK;
				motion.setFrames(sprites.get(XATTACK));
				motion.setDelay(100);
			}
		}
		else if(Cattacking) {
			if(currentMotion != CATTACK) {
				currentMotion = CATTACK;
				motion.setFrames(sprites.get(CATTACK));
				motion.setDelay(100);
			}
		}
		else if(dy > 0) {
			if(currentMotion != FALL) {
				currentMotion = FALL;
				motion.setFrames(sprites.get(FALL));
				motion.setDelay(100);
			}
		}
		else if(dy < 0) {
			if(currentMotion != JUMP) {
				currentMotion = JUMP;
				motion.setFrames(sprites.get(JUMP));
				motion.setDelay(-1);
			}
		}
		else if(left || right) {
			if(currentMotion != WALK) {
				currentMotion = WALK;
				motion.setFrames(sprites.get(WALK));
				motion.setDelay(40);
			}
		}
		else {
			if(currentMotion != IDLE) {
				currentMotion = IDLE;
				motion.setFrames(sprites.get(IDLE));
				motion.setDelay(400);
			}
		}
			
		motion.update();
				
		// 위치 방향 결정
		if(currentMotion != ZATTACK && currentMotion != XATTACK && currentMotion != CATTACK) {
			if(right)
				faceRight = true;
			if(left)
				faceRight = false;
		}
	
	}

	@Override
	public void draw(Graphics2D g) {
		
		setMapPosition();
		
		if(flinching) {
			long elapsed = (System.nanoTime() - flinchCount) / 1000000;
			if(elapsed / 100 % 2 == 0)
				return;
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
