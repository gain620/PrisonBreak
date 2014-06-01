package com.teamcriminals.Entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.teamcriminals.Skill.C;
import com.teamcriminals.Skill.X;
import com.teamcriminals.Skill.Z;
import com.teamcriminals.TileMap.TileMap;

public abstract class Character2 extends MapObject {

	// 캐릭터 속성
	protected int health;
	protected int maxHealth;
	protected int life;
	protected int jumpHeight;
	protected long flinchCount;
	
	// 캐릭터 스킬
	protected Z z;
	protected X x;
	protected C c;
	
	// 캐릭터 상태
	protected boolean attacking;
	protected boolean knockback;
	protected boolean flinching;


	// Motion 관련
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
			2 ,  8 , 1 , 2, 4 , 4, 1
	};
	// Motion 리스트
	private static final int IDLE = 0;
	private static final int WALK = 1;
	private static final int JUMP = 2;
	private static final int FALL = 3;
	private static final int ZATTACK = 4;
	private static final int XATTACK = 5;
	private static final int CATTACK = 6;
	
	
	// 생성자
	public Character2(TileMap tm) {
	
		super(tm);

		width = 30;
		height = 30;
		cWidth = 20;
		cHeight = 20;
		
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
							"/Sprites/Character/.gif"
							)
							);
			for(int i = 0; i<;i++) {
				BufferedImage[numFrames[i]];
				for
			}
		}
		
	}

	// Get 메소드
	public int getHealth()			{ return this.health;		}
	public int getMaxHealth()		{ return this.maxHealth;	}
	public int getLife()			{ return this.life;			}
	public int getJumpHeight()		{ return this.jumpHeight;	}
	public long getFlinchCount()	{ return this.flinchCount;	}
	public Z getSkillZ()			{ return this.z;			}
	public X getSkillX()			{ return this.x;			}
	public C getSkillC()			{ return this.c;			}
	public boolean isAttacking()	{ return this.attacking;	}
	public boolean isKnokback()		{ return this.knockback;	}
	public boolean isFlinching()	{ return this.flinching;	}
	
	// Set 메소드
	public void setHealth(int health)				{ this.health = health; }
	public void setMaxHealth(int maxHealth)			{ this.maxHealth = maxHealth; }
	public void setLife(int life)					{ this.life = life; }
	public void setJump(int jumpHeight)				{ this.jumpHeight = jumpHeight;	}
	public void setFlinchCount(long flinchCount)	{ this.flinchCount = flinchCount; }
	public void setSkillZ(Z z)						{ this.z = z; }	
	public void setSkillX(X x)						{ this.x = x; }
	public void setSkillC(C c)						{ this.c = c; }
	public void setKnokback(boolean b)				{ this.knockback = b; }
	public void setFlinching(boolean b)				{ this.flinching = b; }
	public void setAttacking() {
		
		if(knockback) return;
		attacking = true;
		
	}
	public void setJumping() {
		
		if(knockback) return;
		jump = true;
		
	}
	
	
	/*
	 *  Sprite 관련 메소드 구현해야함
	 */
	
	
	// 공격당할 경우
	public void hit(int damage) {
		
		if(flinching)
			return;
		
		stop();
		
		health -= damage;
		
		if(health < 0)
			health = 0;
		
		flinching = true;
		flinchCount = 0;	// 이거 왜 있는지 모르겠네
		
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
		// currentAction = -1; ??
		stop();
		
	}
	
	// 모든 행동을 멈추게 함
	public void stop() {
		
		up = down = left = right = flinching = jump = attacking = false;
	
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
		
		// double maxSpeed = this.maxSpeed; 이것도 왜있는걸까?
		
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
		if(attacking &&	!(jump || fall)) {
			
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
	public abstract void draw();
}
