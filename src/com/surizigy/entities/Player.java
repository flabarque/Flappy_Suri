package com.surizigy.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.surizigy.main.Game;
import com.surizigy.main.Menu;
import com.surizigy.main.Sounds;
import com.surizigy.world.Camera;

public class Player extends Entity {

	//initializing Variables 
	private int frames = 0, maxFrames = 1, index = 0, maxIndex = 7;
	
	public boolean isFlying = false;
	public static boolean isHit = false;
	
	//initializing Player
	public Player(double x, double y, int width, int height, double speed, BufferedImage sprite) {		
		super(x, y, width, height, speed, sprite); 
	}

	//initializing Loop
	public void tick() {
		//flying Physics 
		depth = 2;
		speed = 1.5;			
		
		//velocity for difficulty
		if(Game.score < 75) {
			if(!isFlying) {
				y+=1.5*speed;
				}else {
					if(y > 0) {
					y-=1.25;
					}
				}
		}else {
			if(!isFlying) {	
				y+=2.5*speed;
				}else {
					if(y > 0) {
					y-=2.25;
					}
				}
		}
		
		if(isFlying) {
			Sounds.flyingSuri.play(1.5);
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
			}
			if (index > maxIndex) {
				index = 0;
			}
		}
					
		//ending Game
		if(y > Game.HEIGHT + 32) {
			Game.gameState = "GAME_OVER";			
			return;
		}
		
		//entity Collision
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if (e != this) {
				if(Entity.isColliding(this, e) && !isHit) {					
					Sounds.tubeHit.play(1);
					isHit = true;
					isFlying = false;					
					return;
				}else {
					Sounds.tubeHit.stop(0);					
				}				
			}
		}		
				
	}
	
	//rendering Player
	public void render(Graphics g) {					
		if(Game.gameState == "PLAYING" || Menu.pause) {
			if(isHit) {
				g.drawImage(Entity.PLAYER_HIT, this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
			if(isFlying) {
				g.drawImage(Entity.PLAYER_IS_FLYING[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if(!isFlying && !isHit) {
				g.drawImage(Entity.PLAYER_IS_FLYING[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
		}				
	}
	
}
