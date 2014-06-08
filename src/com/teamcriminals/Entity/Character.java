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
import com.teamcriminals.Projectile.Projectile;

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
	protected boolean Zattacking;
	protected boolean Xattacking;
	protected boolean Cattacking;
	protected boolean knockback;
	protected boolean flinching;
	protected boolean dead;

	// �浹 �簢���ε�?
	protected Rectangle ar;
	protected Rectangle aur;
	protected Rectangle cr;
	
	// Motion ����
	protected ArrayList<BufferedImage[]> sprites;
	protected final int[] numFrames = {
			2, 4, 1, 1, 1, 2, 2, 2
	};
	
	// Motion ����Ʈ -----> PUBLIC���� ��������!
	public static final int IDLE = 0;
	public static final int WALK = 1;
	public static final int JUMP = 2;
	public static final int FALL = 3;
	public static final int ZATTACK = 4;
	public static final int XATTACK = 5;
	public static final int CATTACK = 6;
	public static final int KNOCKBACK = 7;
	public static final int DEAD = 8;
	
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
		
	}

	// Get �޼ҵ�
	public int getHealth()							{ return this.health;		}
	public int getMaxHealth()						{ return this.maxHealth;	}
	public int getLife()							{ return this.life;			}
	public int getJumpHeight()						{ return this.jumpHeight;	}
	public long getFlinchCount()					{ return this.flinchCount;	}
	public Z getSkillZ()							{ return this.skillZ;		}
	public X getSkillX()							{ return this.skillX;		}
	public C getSkillC()							{ return this.skillC;		}
	public boolean isZattacking()					{ return this.Zattacking;	}
	public boolean isXattacking()					{ return this.Xattacking;	}
	public boolean isCattacking()					{ return this.Cattacking;	}
	public boolean isKnokback()						{ return this.knockback;	}
	public boolean isFlinching()					{ return this.flinching;	}
	public ArrayList<BufferedImage[]> getSprites() 	{ return this.sprites;		}
	
	// Set �޼ҵ�
	public void setHealth(int health)				{ this.health = health;				}
	public void setMaxHealth(int maxHealth)			{ this.maxHealth = maxHealth; 		}
	public void setLife(int life)					{ this.life = life;					}
	public void setFlinchCount(long flinchCount)	{ this.flinchCount = flinchCount;	}
	public void setSkillZ(Z skillZ)					{ this.skillZ = skillZ;				}	
	public void setSkillX(X skillX)					{ this.skillX = skillX;				}
	public void setSkillC(C skillC)					{ this.skillC = skillC;				}
	public void setKnokback(boolean b)				{ this.knockback = b;				}
	public void setFlinching(boolean b)				{ this.flinching = b;				}
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
			
			// ��ġ ���� ����
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
			
		}	
		
	}
	
	protected void getNextPosition() {
		
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
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	
}
