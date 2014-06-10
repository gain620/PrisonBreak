package com.teamcriminals.Item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.teamcriminals.Entity.ItemTest;
import com.teamcriminals.Motion.Motion;
import com.teamcriminals.TileMap.TileMap;

	public class HealthUp extends ItemTest {

		private BufferedImage[] sprites;
		
		public HealthUp(TileMap tm) {
			super(tm);
			
			width = 30;
			height = 30;
			cWidth = 20;
			cHeight = 20;
			
			
			// 스프라이트 불러오기
			try{
				
				BufferedImage spritesheet = ImageIO.read(
						getClass().getResourceAsStream(
								"/Sprites/Items/healthUp.jpg"
								)
								);
				
				sprites = new BufferedImage[1];
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
			motion.setDelay(200);
			
		}
		
		
		public void update() {
			// 위치 업데이트
			collideTile();
			setPosition(xTemp,yTemp);
			
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
			
				
			}
			
		}
