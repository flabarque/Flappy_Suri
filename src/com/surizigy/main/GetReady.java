package com.surizigy.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.surizigy.entities.Entity;

public class GetReady {

	public static BufferedImage GET_READY = Game.spritesheet.getSprite(48, 228, 67, 36);
	
	public String[] options = {">Press SPACE<"};
	public int currentOption = 0;
	
	private int frames = 0, maxFrames = 25, index = 0, maxIndex = 1;
	
	public boolean space;
	
	private int frames_w = 0, maxFrames_w = 1, index_w = 0, maxIndex_w = 7;
	public boolean isWaiting = true;
	
	public void tick() {				
		
		if(space) {
			space = false;
			if(options[currentOption] == ">Press SPACE<") {
				Game.gameState = "PLAYING";
				Menu.pause = false;				
			}		
		}		
		if(options[currentOption] == ">Press SPACE<") {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
			}
			if (index > maxIndex) {
				index = 0;
			}
		}
		if(isWaiting) {		
			frames_w++;
				if(frames_w == maxFrames_w) {
					frames_w = 0;
					index_w++;
				}
				if (index_w > maxIndex_w) {
					index_w = 0;
				}
			}
		
	}
	
	public void render(Graphics g) {		
		g.drawImage(GET_READY, ((Game.WIDTH*Game.SCALE) / 2) - 38*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 4) - 90, 76*Game.SCALE, 36*Game.SCALE, null);
		g.drawImage(Menu.SPACE[index], ((Game.WIDTH*Game.SCALE) / 2) + 70, ((Game.HEIGHT*Game.SCALE) / 2) + 50, 24*Game.SCALE, 8*Game.SCALE, null);
		if(isWaiting) {
			g.drawImage(Entity.PLAYER_IS_FLYING[index_w], Game.WIDTH*Game.SCALE/4, Game.HEIGHT*Game.SCALE/2, 37*Game.SCALE, 32*Game.SCALE, null);
		}	
		
		g.setColor(Color.black);
		g.setFont(new Font("calibri", Font.BOLD, 25));
		g.drawString(">Press SPACE<", ((Game.WIDTH*Game.SCALE) / 2) + 30, ((Game.HEIGHT*Game.SCALE) / 2) + 97);
		g.setColor(Color.white);
		g.setFont(new Font("calibri", Font.BOLD, 25));
		g.drawString(">Press SPACE<", ((Game.WIDTH*Game.SCALE) / 2) + 29, ((Game.HEIGHT*Game.SCALE) / 2) + 96);
			
		g.setColor(Color.black);
		g.setFont(new Font("calibri", Font.BOLD, 25));
		if(options[currentOption] == ">Press SPACE<") {
			g.drawString(">", ((Game.WIDTH*Game.SCALE) / 2) + 20, ((Game.HEIGHT*Game.SCALE) / 2) + 97);
			}
		}
}
