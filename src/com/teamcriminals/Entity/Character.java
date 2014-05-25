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
