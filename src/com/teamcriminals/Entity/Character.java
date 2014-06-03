package com.teamcriminals.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.teamcriminals.Motion.Motion;
import com.teamcriminals.Skill.C;
import com.teamcriminals.Skill.X;
import com.teamcriminals.Skill.Z;
import com.teamcriminals.TileMap.TileMap;

public abstract class Character extends MapObject {

	protected String name;
	
	// 캐릭터 속성
	protected int health;
	protected int maxHealth;
	protected int life;
	protected int jumpHeight;
	protected long flinchCount;
	
	// 캐릭터 스킬
	protected Z skillZ;
	protected X skillX;
	protected C skillC;
	
	// 캐릭터 상태
	protected boolean Zattacking;
	protected boolean Xattacking;
	protected boolean Cattacking;
	protected boolean knockback;
	protected boolean flinching;
	protected boolean dead;

	// 충돌 사각형인듯?
	protected Rectangle ar;
	protected Rectangle aur;
	protected Rectangle cr;
	
	// Motion 관련
	protected ArrayList<BufferedImage[]> sprites;
	protected final int[] numFrames = {
			2, 8, 1, 2, 4, 4, 1
	};
	
	// Motion 리스트
	protected static final int IDLE = 0;
	protected static final int WALK = 1;
	protected static final int JUMP = 2;
	protected static final int FALL = 3;
	protected static final int ZATTACK = 4;
	protected static final int XATTACK = 5;
	protected static final int CATTACK = 6;
	protected static final int KNOCKBACK = 7;
	protected static final int DEAD = 8;
	
	// 생성자
	public Character(TileMap tm) {
	
		super(tm);

		width = 30;
		height = 30;
		cWidth = 20;
		cHeight = 20;

		// 이부분은 각자 다르게할수도 있으나 우선은 그냥 놔두겠음
		
		moveSpeed = 0.3;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		faceRight = true;
		
		
		// sprites 로드
		try {
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"/Sprites/Character/" + name + ".gif"
							)
						);
			
			for(int i = 0; i < 7; i++) {

				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				
				for(int j = 0;j< numFrames[i];j++)
					bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
				
				sprites.add(bi);

			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		motion = new Motion();
		currentMotion = IDLE;
		motion.setFrames(sprites.get(IDLE));
		motion.setDelay(400);
		
	}

	// Get 메소드
	public int getHealth()			{ return this.health;		}
	public int getMaxHealth()		{ return this.maxHealth;	}
	public int getLife()			{ return this.life;			}
	public int getJumpHeight()		{ return this.jumpHeight;	}
	public long getFlinchCount()	{ return this.flinchCount;	}
	public Z getSkillZ()			{ return this.skillZ;		}
	public X getSkillX()			{ return this.skillX;		}
	public C getSkillC()			{ return this.skillC;		}
	public boolean isZattacking()	{ return this.Zattacking;	}
	public boolean isXattacking()	{ return this.Xattacking;	}
	public boolean isCattacking()	{ return this.Cattacking;	}
	public boolean isKnokback()		{ return this.knockback;	}
	public boolean isFlinching()	{ return this.flinching;	}
	
	// Set 메소드
	public void setHealth(int health)				{ this.health = health; }
	public void setMaxHealth(int maxHealth)			{ this.maxHealth = maxHealth; }
	public void setLife(int life)					{ this.life = life; }
	public void setJump(int jumpHeight)				{ this.jumpHeight = jumpHeight;	}
	public void setFlinchCount(long flinchCount)	{ this.flinchCount = flinchCount; }
	public void setSkillZ(Z skillZ)					{ this.skillZ = skillZ; }	
	public void setSkillX(X skillX)					{ this.skillX = skillX; }
	public void setSkillC(C skillC)					{ this.skillC = skillC; }
	public void setKnokback(boolean b)				{ this.knockback = b; }
	public void setFlinching(boolean b)				{ this.flinching = b; }
	public void setZattacking() {
		
		if(knockback) return;
		Zattacking = true;
		
	}
	public void setXattacking() {
		
		if(knockback) return;
		Xattacking = true;
		
	}
	public void setCattacking() {
	
		if(knockback) return;
		Cattacking = true;
	
	}
	public void setJumping() {
		
		if(knockback) return;
		jump = true;
		
	}

	protected void setMotion(int i) {
		
		currentMotion = i;
		motion.setFrames(sprites.get(currentMotion));
		
	}
	
	
	// 공격당할 경우
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
	
	// 초기화
	public void reset() {
		
		health = maxHealth;
		faceRight = true;
		currentMotion = -1;
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
	
	protected void getNextPosition() {
		
		if(knockback) {
			
			dy += fallSpeed * 2;
			
			if(!fall)
				knockback = false;
			
			return;
		
		}
		
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
		if((Zattacking || Xattacking || Cattacking) && !(jump || fall)) {	// (currentMotion == ZATTACK ... 이런식으로 해야하나?)
			
			dx = 0;
		
		}
		
				
		// 점프
		if(jump && !fall) {
			
			dy = jumpStart;
			fall = true;
		
		}
		
		// 떨어질 때
		if(fall) {
			
			dy += fallSpeed;
			
			if(dy < 0 && !jump) 
				dy += stopJumpSpeed;
			
			
			if(dy > maxFallSpeed) 
				dy = maxFallSpeed;
			
		}
		
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	
}
