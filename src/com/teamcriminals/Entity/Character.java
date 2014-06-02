package com.teamcriminals.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.teamcriminals.Skill.C;
import com.teamcriminals.Skill.X;
import com.teamcriminals.Skill.Z;
import com.teamcriminals.TileMap.TileMap;

public abstract class Character extends MapObject {

	// ĳ���� �Ӽ�
	protected int health;
	protected int maxHealth;
	protected int life;
	protected int jumpHeight;
	protected long flinchCount;
	
	// ĳ���� ��ų
	protected Z skillZ;
	protected X skillX;
	protected C skillC;
	
	// ĳ���� ����
	protected boolean attacking;
	protected boolean knockback;
	protected boolean flinching;

	// �浹 �簢���ε�?
	protected Rectangle ar;
	protected Rectangle aur;
	protected Rectangle cr;
	
	// Motion ����
	protected ArrayList<BufferedImage[]> sprites;
	protected final int[] numFrames = {
			2, 8, 1, 2, 4, 4, 1
	};
	
	/* �̰� ���� �츰 �ʿ����??
	protected final int[] FRAMEWIDTHS = {
			40, 40, 80, 40, 40, 40, 80, 40, 40, 40, 40
	};
	protected final int[] FRAMEHEIGHTS = {
			40, 40, 40, 40, 40, 80, 40, 40, 40, 40, 40
	};
	protected final int[] SPRITEDELAYS = {
			-1, 3, 2, 6, 5, 2, 2, 2, 1, -1, 1
	};
	*/
	
	
	// Motion ����Ʈ
	protected static final int IDLE = 0;
	protected static final int WALK = 1;
	protected static final int JUMP = 2;
	protected static final int FALL = 3;
	protected static final int ZATTACK = 4;
	protected static final int XATTACK = 5;
	protected static final int CATTACK = 6;
	protected static final int KNOCKBACK = 7;
	protected static final int DEAD = 8;
	
	// ������
	public Character(TileMap tm) {
	
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
		
		
		// sprites �ε�
		try {
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"/Sprites/Character/.gif"
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
		
	}

	// Get �޼ҵ�
	public int getHealth()			{ return this.health;		}
	public int getMaxHealth()		{ return this.maxHealth;	}
	public int getLife()			{ return this.life;			}
	public int getJumpHeight()		{ return this.jumpHeight;	}
	public long getFlinchCount()	{ return this.flinchCount;	}
	public Z getSkillZ()			{ return this.skillZ;		}
	public X getSkillX()			{ return this.skillX;		}
	public C getSkillC()			{ return this.skillC;		}
	public boolean isAttacking()	{ return this.attacking;	}
	public boolean isKnokback()		{ return this.knockback;	}
	public boolean isFlinching()	{ return this.flinching;	}
	
	// Set �޼ҵ�
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
	public void setAttacking() {
		
		if(knockback) return;
		attacking = true;
		
	}
	public void setJumping() {
		
		if(knockback) return;
		jump = true;
		
	}
	
	
	/*
	 *  Sprite ���� �޼ҵ� �����ؾ���
	 */
	
	
	protected void setMotion(int i) {
		currentMotion = i;
		motion.setFrames(sprites.get(currentMotion));
		/*
		motion.setDelay(SPRITEDELAYS[currentMotion]);
		width = FRAMEWIDTHS[currentMotion];
		height = FRAMEHEIGHTS[currentMotion];
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
		flinchCount = 0;	// �̰� �� �ִ��� �𸣰ڳ�
		
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
		// currentAction = -1; ??
		stop();
		
	}
	
	// ��� �ൿ�� ���߰� ��
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
		
		// double maxSpeed = this.maxSpeed; �̰͵� ���ִ°ɱ�?
		
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
		if(attacking &&	!(jump || fall)) {
			
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
	
	public abstract void init();
	public abstract void update();
	public void draw(Graphics2D g) {
		
		setMapPosition();
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
