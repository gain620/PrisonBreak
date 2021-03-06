package com.teamcriminals.Entity;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import com.teamcriminals.Motion.Motion;
import com.teamcriminals.TileMap.TileMap;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TestVerCharacter extends MapObject {

	// 캐릭터 속성
	private int health;
	private int maxHealth;
	private int fire;
	private int maxFire;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;

	// 화염구
	private boolean firing;
	private int fireCost;
	private int fireBallDamage;
	private ArrayList<FireBall> fireBalls;

	// 펀치
	private boolean punch;
	private int punchDamage;
	private int punchRange;

	// 글라이딩
	private boolean gliding;

	// 모션
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 1, 4, 1, 1, 1, 2, 2 };

	// 모션 리스트
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int FIRE = 5;
	private static final int PUNCH = 6;

	public TestVerCharacter(TileMap tm) {
		super(tm);

		width = 80;
		height = 80;
		cWidth = 45;
		cHeight = 40;

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

		fireCost = 100;
		fireBallDamage = 5;
		fireBalls = new ArrayList<FireBall>();

		punchDamage = 8;
		punchRange = 40;

		// 스프라이트 불러오기
		try {

			BufferedImage spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/Sprites/Character/caesar.gif"));

			sprites = new ArrayList<BufferedImage[]>();

			for (int i = 0; i < 7; i++) {

				BufferedImage[] bi = new BufferedImage[numFrames[i]];

				for (int j = 0; j < numFrames[i]; j++) {
					bi[j] = spritesheet.getSubimage(j * width, i * height,
							width, height);

				}

				sprites.add(bi);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		motion = new Motion();
		currentMotion = IDLE;
		motion.setFrames(sprites.get(IDLE));
		motion.setDelay(400);

	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getFire() {
		return fire;
	}

	public int getMaxFire() {
		return maxFire;
	}

	public void setFiring() {
		firing = true;
	}

	public void setPunch() {
		punch = true;
	}

	public void setGliding(boolean b) {
		gliding = b;
	}

	public void checkAttack(ArrayList<Enemy> enemy) {

		// 모든 가능한 적 불러오기
		for (int i = 0; i < enemy.size(); i++) {
			Enemy e = enemy.get(i);

			// 펀치 공격 판정
			if (punch) {
				if (faceRight) {
					if (e.getX() > x && e.getX() < x + punchRange
							&& e.getY() > y - height / 2
							&& e.getY() < y + height / 2) {
						e.hit(punchDamage);
					}
				}
			} else {
				if (e.getX() < x && e.getX() > x - punchRange
						&& e.getY() > y - height / 2
						&& e.getY() < y - height / 2) {
					e.hit(punchDamage);
				}
			}

			// 화염구 공격 판정
			for (int j = 0; j < fireBalls.size(); j++) {
				if (fireBalls.get(i).intersects(e)) {
					e.hit(fireBallDamage);
					fireBalls.get(j).setHit();
					break;
				}
			}

		}
		
		// 
		
	}

	private void getNextPosition() {

		// 이동
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}

		// 공격 도중에는 이동이 불가능, 단 점프 중일때는 가능
		if ((currentMotion == PUNCH || currentMotion == FIRE)
				&& !(jump || fall)) {
			dx = 0;
		}

		// 점프
		if (jump && !fall) {
			dy = jumpStart;
			fall = true;
		}

		// 낙하
		if (fall) {

			if (dy > 0 && gliding)
				dy += fallSpeed * 0.1;
			else
				dy += fallSpeed;

			if (dy > 0)
				jump = false;

			if (dy < 0 && !jump)
				dy += stopJumpSpeed;

			if (dy > maxFallSpeed)
				dy = maxFallSpeed;

		}

		/*
		 * if(fall) {
		 * 
		 * dy += fallSpeed;
		 * 
		 * if(dy < 0 && !jump) dy += stopJumpSpeed;
		 * 
		 * 
		 * if(dy > maxFallSpeed) dy = maxFallSpeed;
		 * 
		 * }
		 */

	}

	public void update() {

		// 위치 업데이트
		getNextPosition();
		collideTile();
		setPosition(xTemp, yTemp);

		// 공격 모션
		if (currentMotion == PUNCH) {
			if (motion.hasPlayedOnce()) {
				punch = false;
			}
		}

		if (currentMotion == FIRE) {
			if (motion.hasPlayedOnce()) {
				firing = false;
			}
		}

		// !!화염구 던지기!!
		fire += 1;
		if (fire > maxFire)
			fire = maxFire;
		if (firing && currentMotion != FIRE) {
			if (fire > fireCost) {
				fire -= fireCost;

				FireBall fb = new FireBall(tileMap, faceRight);
				fb.setPosition(x, y);
				fireBalls.add(fb);
			}
		}

		// 화염구 업데이트
		for (int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).update();
			if (fireBalls.get(i).shouldRemove()) {
				fireBalls.remove(i);
				i--;
			}
		}

		// 모션 설정
		if (punch) {
			if (currentMotion != PUNCH) {
				currentMotion = PUNCH;
				motion.setFrames(sprites.get(PUNCH));
				motion.setDelay(50);
			}
		} else if (firing) {
			if (currentMotion != FIRE) {
				currentMotion = FIRE;
				motion.setFrames(sprites.get(FIRE));
				motion.setDelay(100);
			}
		} else if (dy > 0) {
			if (gliding) {
				if (currentMotion != GLIDING) {
					currentMotion = GLIDING;
					motion.setFrames(sprites.get(GLIDING));
					motion.setDelay(100);
				}
			} else if (currentMotion != FALLING) {
				currentMotion = FALLING;
				motion.setFrames(sprites.get(FALLING));
				motion.setDelay(100);
			}
		} else if (dy < 0) {
			if (currentMotion != JUMPING) {
				currentMotion = JUMPING;
				motion.setFrames(sprites.get(JUMPING));
				motion.setDelay(-1);
			}
		} else if (left || right) {
			if (currentMotion != WALKING) {
				currentMotion = WALKING;
				motion.setFrames(sprites.get(WALKING));
				motion.setDelay(40);
			}
		} else {
			if (currentMotion != IDLE) {
				currentMotion = IDLE;
				motion.setFrames(sprites.get(IDLE));
				motion.setDelay(400);
			}
		}

		motion.update();

		// 위치 방향 결정
		if (currentMotion != PUNCH && currentMotion != FIRE) {
			if (right)
				faceRight = true;
			if (left)
				faceRight = false;
		}

	}

	public void draw(Graphics2D g) {

		setMapPosition();

		// 화염구 그림
		for (int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).draw(g);
		}

		// 캐릭터 그려줌
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0) {
				return;
			}
		}

		if (faceRight) {
			g.drawImage(motion.getImage(), (int) (x + xMap - width / 2),
					(int) (y + yMap - height / 2), this.width, height, null);
		} else {
			g.drawImage(motion.getImage(),
					(int) (x + xMap - width / 2 + width),
					(int) (y + yMap - height / 2), -this.width, height, null);

		}

	}

}