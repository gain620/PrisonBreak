package com.teamcriminals.Entity;


import java.util.ArrayList;
import javax.imageio.ImageIO;
import com.teamcriminals.Motion.Motion;
import com.teamcriminals.TileMap.TileMap;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TestVerCharacter extends MapObject {
	

	// ĳ���� �Ӽ�
	private int health;
	private int maxHealth;
	private int fire;
	private int maxFire;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;
	
	// ȭ����
	private boolean firing;
	private int fireCost;
	private int fireBallDamage;
	// ȭ���� �����°� ���� �̿ϼ� ��
	//private ArrayList<FireBall> fireBalls;
	
	// ������
	private boolean scratching;
	private int scratchDamage;
	private int scratchRange;
	
	// �۶��̵�
	private boolean gliding;
	
	// ���
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		2, 8, 1, 2, 4, 2, 5
	};
	
	// ��� ����Ʈ
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int FIREBALL = 5;
	private static final int SCRATCHING = 6;
	
	public TestVerCharacter (TileMap tm) {
		
		super(tm);
		
		width = 30;
		height = 30;
		cWidth = 20;
		cHeight = 20;
		
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		faceRight = true;
		
		health = maxHealth = 5;
		fire = maxFire = 2500;
		
		fireCost = 200;
		fireBallDamage = 5;
		// ȭ���� �����°� ���� �ϼ����� ��
		//fireBalls = new ArrayList<FireBall>();
		
		scratchDamage = 8;
		scratchRange = 40;
		
		// ��������Ʈ �ҷ�����
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Character/playersprites.gif"
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 7; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != 6) {
						bi[j] = spritesheet.getSubimage(
								j * width,
								i * height,
								width,
								height
						);
					}
					else {
						bi[j] = spritesheet.getSubimage(
								j * width * 2,
								i * height,
								width,
								height
						);
					}
					
				}
				
				sprites.add(bi);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		motion = new Motion();
		currentMotion = IDLE;
		motion.setFrames(sprites.get(IDLE));
		motion.setDelay(400);
		
	}
	
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public int getFire() { return fire; }
	public int getMaxFire() { return maxFire; }
	
	public void setFiring() { 
		firing = true;
	}
	public void setScratching() {
		scratching = true;
	}
	public void setGliding(boolean b) { 
		gliding = b;
	}
	
	private void getNextPosition() {
		
		// �̵�
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		
		// ���� ���߿��� �̵��� �Ұ���, �� ���� ���϶��� ����
		if(
		(currentMotion == SCRATCHING || currentMotion == FIREBALL) &&
		!(jump || fall)) {
			dx = 0;
		}
		
		// ����
		if(jump && !fall) {
			dy = jumpStart;
			fall = true;	
		}
		
		// ����
		if(fall) {
			
			if(dy > 0 && gliding) dy += fallSpeed * 0.1;
			else dy += fallSpeed;
			
			if(dy > 0) jump = false;
			if(dy < 0 && !jump) dy += stopJumpSpeed;
			
			if(dy > maxFallSpeed) dy = maxFallSpeed;
			
		}
		
	}
	
	public void update() {
		
		// ��ġ ������Ʈ
		getNextPosition();
		collideTile();
		setPosition(xTemp, yTemp);
		
		// ��� ����
		if(scratching) {
			if(currentMotion != SCRATCHING) {
				currentMotion = SCRATCHING;
				motion.setFrames(sprites.get(SCRATCHING));
				motion.setDelay(50);
				width = 60;
			}
		}
		else if(firing) {
			if(currentMotion != FIREBALL) {
				currentMotion = FIREBALL;
				motion.setFrames(sprites.get(FIREBALL));
				motion.setDelay(100);
				width = 30;
			}
		}
		else if(dy > 0) {
			if(gliding) {
				if(currentMotion != GLIDING) {
					currentMotion = GLIDING;
					motion.setFrames(sprites.get(GLIDING));
					motion.setDelay(100);
					width = 30;
				}
			}
			else if(currentMotion != FALLING) {
				currentMotion = FALLING;
				motion.setFrames(sprites.get(FALLING));
				motion.setDelay(100);
				width = 30;
			}
		}
		else if(dy < 0) {
			if(currentMotion != JUMPING) {
				currentMotion = JUMPING;
				motion.setFrames(sprites.get(JUMPING));
				motion.setDelay(-1);
				width = 30;
			}
		}
		else if(left || right) {
			if(currentMotion != WALKING) {
				currentMotion = WALKING;
				motion.setFrames(sprites.get(WALKING));
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
		if(currentMotion != SCRATCHING && currentMotion != FIREBALL) {
			if(right) faceRight = true;
			if(left) faceRight = false;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		
		// ĳ���� �׷���
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
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