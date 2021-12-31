package com.surizigy.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.surizigy.main.Game;

public class UI {

	//initializing Variables
	//images
	public static BufferedImage GET_READY = Game.spritesheet.getSprite(48, 228, 67, 36);
	public static BufferedImage BOARD = Game.spritesheet.getSprite(2, 267, 114, 82);
	
	public static BufferedImage[] BIG_NUMBER = {Game.spritesheet.getSprite(167, 4, 18, 25),
												Game.spritesheet.getSprite(167, 36, 18, 25),
												Game.spritesheet.getSprite(167, 68, 18, 25),
												Game.spritesheet.getSprite(167, 100, 18, 25),
												Game.spritesheet.getSprite(167, 132, 18, 25),
												Game.spritesheet.getSprite(167, 164, 18, 25),
												Game.spritesheet.getSprite(199, 4, 18, 25),
												Game.spritesheet.getSprite(199, 36, 18, 25),
												Game.spritesheet.getSprite(199, 68, 18, 25),
												Game.spritesheet.getSprite(199, 100, 18, 25)};
		
	public static BufferedImage[] SMALL_NUMBER = {Game.spritesheet.getSprite(131, 257, 11, 14),
												  Game.spritesheet.getSprite(147, 257, 11, 14),
												  Game.spritesheet.getSprite(163, 257, 11, 14),
												  Game.spritesheet.getSprite(179, 257, 11, 14),
												  Game.spritesheet.getSprite(195, 257, 11, 14),
												  Game.spritesheet.getSprite(211, 257, 11, 14),
												  Game.spritesheet.getSprite(131, 273, 11, 14),
												  Game.spritesheet.getSprite(147, 273, 11, 14),
												  Game.spritesheet.getSprite(163, 273, 11, 14),
												  Game.spritesheet.getSprite(179, 273, 11, 14)};
	
	//rendering UI
	public void render(Graphics g) {
		//rendering Game Score
		if(Game.gameState == "PLAYING" && Game.score < 8) {						
			String digits = ""+(int)Game.score;			
			for (int i = 0; i < digits.length(); i++) {				
				if(i != digits.length() - 1) {				
					g.drawImage(BIG_NUMBER[digits.charAt(i) - '0'], Game.WIDTH*Game.SCALE/2 - 18*Game.SCALE - 1, Game.HEIGHT*Game.SCALE/4, 18*2, 25*2, null);
				}else {
					g.drawImage(BIG_NUMBER[digits.charAt(i) - '0'], Game.WIDTH*Game.SCALE/2 - 18, Game.HEIGHT*Game.SCALE/4, 18*2, 25*2, null);					
				}				
			}			
		//rendering Congratulations
		}else if(Game.gameState == "PLAYING" && Game.score >= 8){
			g.drawImage(GET_READY, ((Game.WIDTH*Game.SCALE) / 2) - 38*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 4) - 90, 76*Game.SCALE, 36*Game.SCALE, null);
			g.drawImage(BOARD, ((Game.WIDTH*Game.SCALE) / 2) - 57*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 2) + 25*Game.SCALE, 114*Game.SCALE, 82*Game.SCALE, null);
		}		
	}
	
}
