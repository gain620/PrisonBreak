package com.teamcriminals.Motion;

import java.awt.image.BufferedImage;

public class Motion {
	
	private BufferedImage[] frames;
	private int currentFrame;
	
	private int count;
	private long startTime;
	private long delay;
	
	private boolean playedOnce;
	
	public void Motion() {
		playedOnce = false;
	}
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
	}
	
	public void setDelay(long d) {
		delay = d;
	}
	
	public void setFrame(int i) {
		currentFrame = i;
	}
	
	public void update() {
		
		if(delay == -1){
			return;
		}
		
		count++;
		
		if(count == delay) {
			currentFrame++;
			count = 0;
		
		}
		
		
	}
	

}
