package com.surizigy.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.surizigy.main.Game;

public class UI {

	public static BufferedImage GAME_OVER = Game.spritesheet.getSprite(128, 192, 66, 25);
	
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
	
	public void render(Graphics g) {
		if(Game.gameState == "PLAYING") {				
			String digits = ""+(int)Game.score;			
			for (int i = 0; i < digits.length(); i++) {				
				if(i != digits.length() - 1) {				
					g.drawImage(BIG_NUMBER[digits.charAt(i) - '0'], Game.WIDTH*Game.SCALE/2 - 18*3 - 1, Game.HEIGHT*Game.SCALE/4, 18*2, 25*2, null);
				}else {
					g.drawImage(BIG_NUMBER[digits.charAt(i) - '0'], Game.WIDTH*Game.SCALE/2 - 18, Game.HEIGHT*Game.SCALE/4, 18*2, 25*2, null);
			//g.setColor(Color.white);
			//g.setFont(new Font("calibri", Font.BOLD, 50));
			//g.drawString(""+(int)Game.score, Game.WIDTH*Game.SCALE/2 - 15, Game.HEIGHT*Game.SCALE/4);		
				}				
			}
		}			
	}
	
}
