package com.surizigy.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.surizigy.entities.Entity;

public class Menu {

	public static BufferedImage TITLE = Game.spritesheet.getSprite(48, 192, 76, 36);
	public static BufferedImage[] SPACE = {Game.spritesheet.getSprite(200, 224, 24, 8), Game.spritesheet.getSprite(200, 232, 24, 8)};
	public static BufferedImage[] ESC = {Game.spritesheet.getSprite(192, 224, 8, 8), Game.spritesheet.getSprite(192, 232, 8, 8)};
	
	public String[] options = {">Press SPACE<", ">Pause/Exit Game<"};
		
	public int currentOption = 0;
	public int maxOption = options.length - 1;
			
	public boolean up, down, space, esc;
			
	public static boolean pause = false;
	
	public int frames = 0, maxFrames = 25, index1 = 0, index2 =0, maxIndex = 1;
	
	public int frames_w = 0, maxFrames_w = 1, index_w = 0, maxIndex_w = 7;
	public boolean isWaiting = true;
	
	public void tick() {		
		
		if(up) {
			up = false;
			currentOption--;
			if(currentOption < 0)
				currentOption = maxOption;
			}
		if(down) {
			down = false;
			currentOption++;
			if(currentOption > maxOption) 
				currentOption = 0;
			}
		if(space) {
			Sounds.musicBackground.loop(0.25);	
			space = false;
			if(options[currentOption] == ">Press SPACE<") {
				Game.gameState = "GET_READY";
				//pause = false;				
			}if(pause) {				
				if(options[currentOption] == ">Press SPACE<") {
					Game.gameState = "PLAYING";
					pause = false;	
				}
			}						
		}
		if(esc) {
			esc = false;
			if(options[currentOption] == ">Pause/Exit Game<") {
				System.exit(1);
			}
		}		
		if(options[currentOption] == ">Press SPACE<") {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index1++;
			}
			if (index1 > maxIndex) {
				index1 = 0;
			}
		}else if(options[currentOption] == ">Pause/Exit Game<") {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index2++;
			}
			if (index2 > maxIndex) {
				index2 = 0;
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
		if(pause) {
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);	
		}		
		
		g.drawImage(TITLE, ((Game.WIDTH*Game.SCALE) / 2) - 38*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 4) - 90, 76*Game.SCALE, 36*Game.SCALE, null);
		g.drawImage(SPACE[index1], ((Game.WIDTH*Game.SCALE) / 2) + 70, ((Game.HEIGHT*Game.SCALE) / 2) + 50, 24*Game.SCALE, 8*Game.SCALE, null);
		g.drawImage(ESC[index2], Game.WIDTH*Game.SCALE - 50, Game.HEIGHT*Game.SCALE - 50, 8*Game.SCALE, 8*Game.SCALE, null);
		if(isWaiting && !pause) {
			g.drawImage(Entity.PLAYER_IS_FLYING[index_w], Game.WIDTH*Game.SCALE/4, Game.HEIGHT*Game.SCALE/2, 37*Game.SCALE, 32*Game.SCALE, null);
		}
		
		//Opções		
		g.setColor(Color.black);
		g.setFont(new Font("calibri", Font.BOLD, 25));
		g.drawString(">Press SPACE<", ((Game.WIDTH*Game.SCALE) / 2) + 30, ((Game.HEIGHT*Game.SCALE) / 2) + 97);
		g.drawString(">Pause/Exit Game<", Game.WIDTH*Game.SCALE - 260, Game.HEIGHT*Game.SCALE - 30);
		
		g.setColor(Color.white);
		g.setFont(new Font("calibri", Font.BOLD, 25));
		g.drawString(">Press SPACE<", ((Game.WIDTH*Game.SCALE) / 2) + 29, ((Game.HEIGHT*Game.SCALE) / 2) + 96);
		g.drawString(">Pause/Exit Game<", Game.WIDTH*Game.SCALE - 261, Game.HEIGHT*Game.SCALE - 31);
			
		g.setColor(Color.black);
		g.setFont(new Font("calibri", Font.BOLD, 25));
			if(options[currentOption] == ">Press SPACE<") {
				g.drawString(">", ((Game.WIDTH*Game.SCALE) / 2) + 20, ((Game.HEIGHT*Game.SCALE) / 2) + 97);
			}
			else if (options[currentOption] == ">Pause/Exit Game<") {
				g.drawString(">", Game.WIDTH*Game.SCALE - 270, Game.HEIGHT*Game.SCALE -30);
			}			
		
	}
	
}
		

