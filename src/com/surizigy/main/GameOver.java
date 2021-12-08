package com.surizigy.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.surizigy.graphics.UI;

public class GameOver extends Menu {

	public static BufferedImage GAME_OVER = Game.spritesheet.getSprite(128, 192, 66, 25);
	public static BufferedImage BOARD = Game.spritesheet.getSprite(2, 267, 114, 82);
	public static BufferedImage[] MEDALS = {Game.spritesheet.getSprite(182, 288, 18, 18), Game.spritesheet.getSprite(164, 288, 18, 18), Game.spritesheet.getSprite(146, 288, 18, 18), Game.spritesheet.getSprite(128, 288, 18, 18)};
		
	private boolean showMessage = true;
	//private int framesGameOver = 0;	
	
	private int finalScore = 0;
	
	//private boolean scoreGreater = false;
	
	public void tick() {
		Sounds.musicBackground.stop(0);
		
		frames++;
		if(frames == 30) {
			frames = 0;
			if(showMessage)
				showMessage = false;
			else
				showMessage = true;
		}
		
		finalScore++;
		if(Game.score == 0) {
			finalScore = 0;			
		}else {
			//finalScore++;
			Sounds.points.play(0.5);
			if(finalScore == (int)Game.score + 1) {
				finalScore--;
				Sounds.points.stop(0);
			}
		}
		
		if(finalScore == 25) {
			Sounds.medal.play(0.5);
		}
		if(finalScore == 50) {
			Sounds.medal.play(0.5);
		}
		if(finalScore == 75) {
			Sounds.medal.play(0.5);
		}
		if(finalScore == 100) {
			Sounds.medal.play(0.5);
		}
		
		/*for (int i = 0; i < (int)Game.score; i++) {	
			if (i < (int)Game.score) {
				Sounds.points.play(0.5);
			}else {
				i = 0;
			}		
		}*/
		/*if (finalScore < (int)Game.score) {
			Sounds.points.play(0.5);
			finalScore = (int)Game.score;
		}else if (finalScore == (int)Game.score) {
			finalScore = 0;
			}*/
					
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
		g.drawImage(BOARD, ((Game.WIDTH*Game.SCALE) / 2) - 57*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 2) - 41*Game.SCALE, 114*Game.SCALE, 82*Game.SCALE, null);
		
		if(showMessage) {
			g.setFont(new Font("calibri", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(">Press SPACE to continue<", ((Game.WIDTH*Game.SCALE)/2) - 165, Game.HEIGHT*Game.SCALE - 120);
			g.setFont(new Font("calibri", Font.BOLD, 30));
			g.setColor(Color.white);
			g.drawString(">Press SPACE to continue<", ((Game.WIDTH*Game.SCALE)/2) - 166, Game.HEIGHT*Game.SCALE - 121);
		}
		
		if(finalScore >= 25) {
			g.drawImage(MEDALS[0], ((Game.WIDTH*Game.SCALE) / 2) - 38*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 2) - 2*Game.SCALE, 18*Game.SCALE, 18*Game.SCALE, null);
		}
		if(finalScore >= 50) {
			g.drawImage(MEDALS[1], ((Game.WIDTH*Game.SCALE) / 2) - 38*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 2) - 2*Game.SCALE, 18*Game.SCALE, 18*Game.SCALE, null);
		}
		if(finalScore >= 75) {
			g.drawImage(MEDALS[2], ((Game.WIDTH*Game.SCALE) / 2) - 38*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 2) - 2*Game.SCALE, 18*Game.SCALE, 18*Game.SCALE, null);
		}
		if(finalScore >= 100) {
			g.drawImage(MEDALS[3], ((Game.WIDTH*Game.SCALE) / 2) - 38*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 2) - 2*Game.SCALE, 18*Game.SCALE, 18*Game.SCALE, null);
		}
		
		String digits = ""+finalScore;			
		for (int i = 0; i < digits.length(); i++) {				
			if(i != digits.length() - 1) {				
				g.drawImage(UI.SMALL_NUMBER[digits.charAt(i) - '0'], (Game.WIDTH*Game.SCALE - 153) - 11*Game.SCALE - 1, Game.HEIGHT*Game.SCALE/2 - 45, 11*Game.SCALE, 14*Game.SCALE, null);
			}else {
				g.drawImage(UI.SMALL_NUMBER[digits.charAt(i) - '0'], Game.WIDTH*Game.SCALE - 153, Game.HEIGHT*Game.SCALE/2 - 45, 11*Game.SCALE, 14*Game.SCALE, null);
			}				
		}			
		
	}
	
}
