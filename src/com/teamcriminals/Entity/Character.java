package com.teamcriminals.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import com.teamcriminals.Audio.AudioPlayer;
import com.teamcriminals.Skill.C;
import com.teamcriminals.Skill.X;
import com.teamcriminals.Skill.Z;
import com.teamcriminals.TileMap.TileMap;

public abstract class Character extends MapObject {
	
	// Score
	protected int score;
	public void earnScore(int scorePoint) {
		this.score += scorePoint;
	}
	
	// ����
	protected HashMap<String, AudioPlayer> sfx;

	// ĳ���� �Ӽ�
	protected int health;
	protected int maxHealth;
	protected int life;
	protected long flinchCount;
	
	// ĳ���� ��ų
	protected Z skillZ;
	protected X skillX;
	protected C skillC;
	
	// ĳ���� ����
	protected boolean Zattacking;
	protected boolean Xattacking;
	protected boolean Cattacking;
	protected boolean flinching;
	
	// Motion ����
	protected ArrayList<BufferedImage[]> sprites;
	protected final int[] numFrames = {2, 4, 1, 1, 2, 2, 2};
	
	// Motion ����Ʈ (��� �߰��ؾߵ�)
	public static final int IDLE = 0;
	public static final int WALK = 1;
	public static final int JUMP = 2;
	public static final int FALL = 3;
	public static final int ZATTACK = 4;
	public static final int XATTACK = 5;
	public static final int CATTACK = 6;
	
	public Character(TileMap tm) {
		super(tm);
	}

	// Get �޼ҵ�
	public int getHealth()							{ return this.health;		}
	public int getMaxHealth()						{ return this.maxHealth;	}
	public int getLife()							{ return this.life;			}
	public int getScore()                           { return this.score;        }
	public long getFlinchCount()					{ return this.flinchCount;	}
	public Z getSkillZ()							{ return this.skillZ;		}
	public X getSkillX()							{ return this.skillX;		}
	public C getSkillC()							{ return this.skillC;		}
	public boolean isZattacking()					{ return this.Zattacking;	}
	public boolean isXattacking()					{ return this.Xattacking;	}
	public boolean isCattacking()					{ return this.Cattacking;	}
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
	public void setFlinching(boolean b)				{ this.flinching = b;				}
	public void setZattacking()						{ this.Zattacking = true; }
	public void setXattacking()						{ this.Xattacking = true; }
	public void setCattacking()						{ this.Cattacking = true; }
	public void setMotion(int i) {
		currentMotion = i;
		motion.setFrames(sprites.get(currentMotion));
	}
	
	// Get Set ����� �׳� abstract�� �ѱ�?
	public abstract void hit(int damage);
	public abstract void reset();
	public abstract void stop();
	public abstract void dead();
	public abstract void checkAttack(ArrayList<Enemy> enemy);
	public abstract void getNextPosition();
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
}
