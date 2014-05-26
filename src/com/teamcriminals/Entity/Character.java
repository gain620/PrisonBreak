package com.teamcriminals.Entity;

/*package com.teamcriminals.Entity;

import java.util.ArrayList;

import com.teamcriminals.Audio.AudioPlayer;
import com.teamcriminals.TileMap.TileMap;

public class Character extends MapObject {
   
   // references
   private ArrayList<Enemy> enemies;
   
   // player stuff
   private int lives;
   private int health;
   private int maxHealth;
   private int damage;
   private int chargeDamage;
   private boolean knockback;
   private boolean flinching;
   private long flinchCount;
   
   // actions
   private boolean attacking;
   
   // animation actions
   private static final int IDLE = 0;
   private static final int WALKING = 1;
   private static final int ATTACKING = 2;
   private static final int JUMPING = 3;
   private static final int FALLING = 4;
   private static final int KNOCKBACK = 8;
   private static final int DEAD = 9;
      
   public Character(TileMap tm) {
      
      super(tm);
      
      
      moveSpeed = 1.6;
      maxSpeed = 1.6;
      stopSpeed = 1.6;
      fallSpeed = 0.15;
      maxFallSpeed = 4.0;
      jumpStart = -4.8;
      stopJumpSpeed = 0.3;
      
      damage = 10;
      chargeDamage = 5;
      
      facingRight = true;
      
      lives = 3;
      health = maxHealth = 5;
               
   }
      

   public int getHealth() { return health; }
   public int getMaxHealth() { return maxHealth; }

   public void setJumping(boolean b) {
      if(knockback) return;
      jumping = b;
   }
   public void setAttacking() {
      if(knockback) return;
      else attacking = true;
   }
   
   public void setDead() {
      health = 0;
      stop();
   }
   
   public void setHealth(int i) { health = i; }
   public void setLives(int i) { lives = i; }
   public void gainLife() { lives++; }
   public void loseLife() { lives--; }
   public int getLives() { return lives; }
      
   public void hit(int damage) {
      if(flinching) return;
      stop();
      health -= damage;
      if(health < 0) health = 0;
      flinching = true;
      flinchCount = 0;
      if(facingRight) dx = -1;
      else dx = 1;
      dy = -3;
      knockback = true;
      falling = true;
      jumping = false;
   }
   
   public void reset() {
      health = maxHealth;
      facingRight = true;
      currentAction = -1;
      stop();
   }
   
   public void stop() {
      left = right = up = down = flinching = jumping = attacking = false;
   }
   
   private void getNextPosition() {
      
      if(knockback) {
         dy += fallSpeed * 2;
         if(!falling) knockback = false;
         return;
      }
      
      double maxSpeed = this.maxSpeed;
      
      // movement
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
      
      // cannot move while attacking, except in air
      if(attacking && !(jumping || falling)) {
         dx = 0;
      }
      
      // jumping
      if(jumping && !falling) {
         //sfx.get("jump").play();
         dy = jumpStart;
         falling = true;
         JukeBox.play("playerjump");
      }
      
      // falling
      if(falling) {
         dy += fallSpeed;
         if(dy < 0 && !jumping) dy += stopJumpSpeed;
         if(dy > maxFallSpeed) dy = maxFallSpeed;
      }
      
   }
}
*/

//캐릭터 클래스 추상클래스

public abstract class Character {

	//캐릭터의 변수들
	private int health; //HP
	private int maxHealth; //max HP
	private int life; //목숨
	private int speed; //속도
	private int bomb; //초기궁극기설정갯수
	private boolean knuckback; //뒤로밀리는것
	private boolean jumping; //점프
	
	//캐릭터 변수의 getter들 setter는 changeState로 가능
	public int getHealth(){return this.health;}
	public int getLife(){return this.life;}
	public int getSpeed(){return this.speed;}
	public int getBomb(){return this.bomb;}
	
	//캐릭터의 스킬 메소드들
	public abstract void skillZ();
	public abstract void skillX();
	public abstract void skillC();
	
	//행동후 변화량 반환 - 몬스터와 부딪히거나 키입력에따라 변화 우선 int주는걸로 구현 수정이필요한부분
	//dx, dy가 있어야 정확한 이동을 효과를 줄수있을듯
	
	public int action(){
		
		if(this.knuckback){
			System.out.println("넉백효과 일어난다");
			return -2;
		}
		else if(this.jumping){
			System.out.println("점프효과 일어난다");
			return 20;
		}
		else{
			return 0;
		}
	}
	
	
	
	//캐릭터의 설정 메소드들
	
	//시작시 설정부분
	public void start(int h, int mh, int l, int sp, int bo){
		this.health=h;
		this.maxHealth=mh;
		this.life=l;
		this.speed=sp;
		this.bomb=bo;
		
	}
	
	//상태변화부분 setter의 역활도 가능
	public void changeState(int health, int life, int speed, int bomb){
		this.health=health;
		this.life=life;
		this.speed=speed;
		this.bomb=bomb;
	}
	
	//life감소, 체력다시 풀피되게 재설정
	public void reset(){
		this.health=this.maxHealth;
		this.life--;
	}
	
	//죽었을경우
	public void dead(){
		//life는 남았고 health가 0 되면 리셋된다. life, health모두 0이면 게임종료
		if(health==0 && life!=0){
			this.reset();
		}
		else if(health==0 && life==0){
			System.out.println("게임 종료");
		}
	}
	

}
