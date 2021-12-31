package com.surizigy.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.surizigy.main.Game;

public class UI {

	//initializing Variables
	//images
	public static BufferedImage CONGRATS = Game.spritesheet.getSprite(128, 311, 65, 36);
	public static BufferedImage BOARD_ACKNOW = Game.spritesheet.getSprite(205, 273, 127, 94);
	public static BufferedImage THANKS = Game.spritesheet.getSprite(224, 192, 64, 25);
	
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
		if(Game.gameState == "PLAYING" && Game.score < 99) {						
			String digits = ""+(int)Game.score;			
			for (int i = 0; i < digits.length(); i++) {				
				if(i != digits.length() - 1) {				
					g.drawImage(BIG_NUMBER[digits.charAt(i) - '0'], Game.WIDTH*Game.SCALE/2 - 18*Game.SCALE - 1, Game.HEIGHT*Game.SCALE/4, 18*2, 25*2, null);
				}else {
					g.drawImage(BIG_NUMBER[digits.charAt(i) - '0'], Game.WIDTH*Game.SCALE/2 - 18, Game.HEIGHT*Game.SCALE/4, 18*2, 25*2, null);					
				}				
			}			
		//rendering Congratulations
		}else if(Game.gameState == "PLAYING" && Game.score >= 99){
			g.drawImage(CONGRATS, ((Game.WIDTH*Game.SCALE) / 2) - 32*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 4) - 90, 65*Game.SCALE, 36*Game.SCALE, null);
			g.drawImage(BOARD_ACKNOW, ((Game.WIDTH*Game.SCALE) / 2) - 62*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 2) + 22*Game.SCALE, 127*Game.SCALE, 94*Game.SCALE, null);
			g.drawImage(THANKS, ((Game.WIDTH*Game.SCALE) / 2) - 32*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 2) - 50, 64*Game.SCALE, 25*Game.SCALE, null);
		}		
	}
	
}
