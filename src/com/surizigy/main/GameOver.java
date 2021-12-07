package com.surizigy.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameOver extends Menu {

	public static BufferedImage GAME_OVER = Game.spritesheet.getSprite(128, 192, 66, 25);
	public static BufferedImage BOARD = Game.spritesheet.getSprite(0, 265, 106, 72);
	
	private boolean showMessageGameOver = true;
	private int framesGameOver = 0;	
	
	public void tick() {
		Sounds.musicBackground.stop(0);
		framesGameOver++;
		if(framesGameOver == 30) {
			framesGameOver = 0;
			if(showMessageGameOver)
				showMessageGameOver = false;
			else
				showMessageGameOver = true;
		}	
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 100)); //opacidade
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		//g2.setFont(new Font("calibri", Font.BOLD, 50));
		//g2.setColor(Color.white);
		//g2.drawString("GAME OVER", ((WIDTH*SCALE)/2) - 115, ((HEIGHT*SCALE)/2) - 30);
		g.drawImage(GAME_OVER, ((Game.WIDTH*Game.SCALE) / 2) - 33*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 4) - 90, 66*Game.SCALE, 25*Game.SCALE, null);
		//g2.setFont(new Font("calibri", Font.BOLD, 30));
		//g2.setColor(Color.white);
		g.drawImage(BOARD, ((Game.WIDTH*Game.SCALE) / 2) - 53*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 2) - 36*Game.SCALE, 106*Game.SCALE, 72*Game.SCALE, null);
		if(showMessageGameOver) {
			g.setFont(new Font("calibri", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(">Press SPACE to continue<", ((Game.WIDTH*Game.SCALE)/2) - 165, Game.HEIGHT*Game.SCALE - 120);
			g.setFont(new Font("calibri", Font.BOLD, 30));
			g.setColor(Color.white);
			g.drawString(">Press SPACE to continue<", ((Game.WIDTH*Game.SCALE)/2) - 166, Game.HEIGHT*Game.SCALE - 121);
		}
	}
	
}
