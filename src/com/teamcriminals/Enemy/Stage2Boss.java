package com.teamcriminals.Enemy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.teamcriminals.Entity.Enemy;
import com.teamcriminals.Motion.Motion;
import com.teamcriminals.TileMap.TileMap;

public class Stage2Boss extends Enemy {

	private BufferedImage[] sprites;
	
	public Stage2Boss(TileMap tm) {
		super(tm);
	
		// 적 속성 설정
		moveSpeed = 0.5;
		maxSpeed = 3.7;
		fallSpeed = 0.2;
		
		width = 80;
		height = 80;
		cWidth = 40;
		cHeight = 45;
		
		health = maxHealth = 200;
		damage = 30;
		scorePoint = 100;
		
		// 스프라이트 불러오기
		try{
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"/Sprites/Enemy/Stage2Boss.png"
							)
							);
			
			sprites = new BufferedImage[4];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
						i * width , 0 , width, height
						
						);
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		motion = new Motion();
		motion.setFrames(sprites);
		motion.setDelay(150);
		
		right = true;
		faceRight = true;
		
	}
	
	private void getNextPosition() {
		
		// 이동
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
		
		// 낙하
		if(fall) {
			dy += fallSpeed;
		}
		
	}
	
	public void update() {
		
		// 위치 업데이트
		getNextPosition();
		collideTile();
		setPosition(xTemp,yTemp);
		
		// 깜빡깜빡
		if(flinch) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) {
				flinch = false;
			}
		}
		
		// 왔다 갔다
		if(right && dx == 0) {
			right = false;
			left = true;
			faceRight = false;
		}else if(left && dx == 0) {
			right = true;
			left = false;
			faceRight = true;
		}
		
		// 모션 업데이트
		motion.update();
		
	}
	
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
