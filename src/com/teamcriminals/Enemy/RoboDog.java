package com.teamcriminals.Enemy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.teamcriminals.Entity.Enemy;
import com.teamcriminals.Motion.Motion;
import com.teamcriminals.TileMap.TileMap;


public class RoboDog extends Enemy {

	private BufferedImage[] sprites;
	
	public RoboDog(TileMap tm) {
		super(tm);
	
		// �� �Ӽ� ����
		moveSpeed = 0.5;
		maxSpeed = 0.3;
		fallSpeed = 0.2;
		
		width = 30;
		height = 30;
		cWidth = 20;
		cHeight = 15;
		
		health = maxHealth = 5;
		damage = 5;
		
		// ��������Ʈ �ҷ�����
		try{
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"/Sprites/Enemy/ZombieSoldier.gif"
							)
							);
			
			sprites = new BufferedImage[3];
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
		motion.setDelay(300);
		
		right = true;
		faceRight = true;
		
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
		
		// ����
		if(fall) {
			dy += fallSpeed;
		}
		
	}
	
	public void update() {
		
		// ��ġ ������Ʈ
		getNextPosition();
		collideTile();
		setPosition(xTemp,yTemp);
		
		// ��������
		if(flinch) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) {
				flinch = false;
			}
		}
		
		// �Դ� ����
		if(right && dx == 0) {
			right = false;
			left = true;
			faceRight = false;
		}else if(left && dx == 0) {
			right = true;
			left = false;
			faceRight = true;
		}
		
		// ��� ������Ʈ
		
		
	}
	
	public void draw(Graphics2D g) {
		
		// �� ȭ�鿡 ������ ���� ��� �̸� �׷��ִ� ���� �����Ͽ�, �޸� ����
		if(onScreen()) return ;
		
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
