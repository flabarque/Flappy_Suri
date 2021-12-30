package com.surizigy.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.surizigy.graphics.UI;

public class GameOver extends Menu {

	//initializing Variables
	//images
	public static BufferedImage GAME_OVER = Game.spritesheet.getSprite(128, 192, 66, 25);
	public static BufferedImage BOARD = Game.spritesheet.getSprite(2, 267, 114, 82);
	public static BufferedImage[] MEDALS = {Game.spritesheet.getSprite(182, 288, 18, 18), Game.spritesheet.getSprite(164, 288, 18, 18), Game.spritesheet.getSprite(146, 288, 18, 18), Game.spritesheet.getSprite(128, 288, 18, 18)};
		
	private boolean showMessage = true;	
	private int finalScore;	
	private boolean getMedal = true;
	
	//initializing Loop
	public void tick() {		
		Sounds.musicBackground.stop(0);
		
		//showing Message
		frames++;
		if(frames == 30) {
			frames = 0;
			if(showMessage)
				showMessage = false;
			else
				showMessage = true;
		}							
		
		//adding new High Score
		if((int)Game.score > HighScore.bestScore()) {			
			HighScore.setNewBest((int)Game.score);
		}
					
		//adding Final Score
		finalScore++;
		Sounds.points.play(0.5);
		
		if(finalScore > (int)Game.score) {
			finalScore = (int)Game.score;							
			Sounds.points.stop(0);			
		}					
		
		//showing Medals
		if(getMedal) {				
			if(finalScore == 25) {
				getMedal = false;
				Sounds.medal.play(0.5);			
			}			
			if(finalScore == 50) {
				getMedal = false;
				Sounds.medal.play(0.5);
			}
			if(finalScore == 75) {
				getMedal = false;
				Sounds.medal.play(0.5);
			}
			if(finalScore == 100) {
				getMedal = false;
				Sounds.medal.play(0.5);
			}
		}
		
	}
	
	//rendering Game Over
	public void render(Graphics g) {		
		g.setColor(new Color(0, 0, 0, 100)); //darkness
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);		
		g.drawImage(GAME_OVER, ((Game.WIDTH*Game.SCALE) / 2) - 33*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 4) - 90, 66*Game.SCALE, 25*Game.SCALE, null);		
		g.drawImage(BOARD, ((Game.WIDTH*Game.SCALE) / 2) - 57*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 2) - 41*Game.SCALE, 114*Game.SCALE, 82*Game.SCALE, null);
		
		//rendering Message
		if(showMessage) {
			g.setFont(new Font("calibri", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(">Press SPACE to continue<", ((Game.WIDTH*Game.SCALE)/2) - 165, Game.HEIGHT*Game.SCALE - 120);
			g.setFont(new Font("calibri", Font.BOLD, 30));
			g.setColor(Color.white);
			g.drawString(">Press SPACE to continue<", ((Game.WIDTH*Game.SCALE)/2) - 166, Game.HEIGHT*Game.SCALE - 121);
		}
		
		//rendering Medals
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
		
		//rendering Final Score
		String digits = ""+finalScore;			
		for (int i = 0; i < digits.length(); i++) {					
			if(i != digits.length() - 1) {				
				g.drawImage(UI.SMALL_NUMBER[digits.charAt(i) - '0'], (Game.WIDTH*Game.SCALE - 153) - 11*Game.SCALE - 1, Game.HEIGHT*Game.SCALE/2 - 45, 11*Game.SCALE, 14*Game.SCALE, null);
			}else {
				g.drawImage(UI.SMALL_NUMBER[digits.charAt(i) - '0'], Game.WIDTH*Game.SCALE - 153, Game.HEIGHT*Game.SCALE/2 - 45, 11*Game.SCALE, 14*Game.SCALE, null);
			}				
		}
		
		//rendering High Score
		String digits_1 = ""+HighScore.bestScore;			
			for (int i = 0; i < digits_1.length(); i++) {				
				if(i != digits_1.length() - 1) {				
					g.drawImage(UI.SMALL_NUMBER[digits_1.charAt(i) - '0'], (Game.WIDTH*Game.SCALE - 153) - 11*Game.SCALE - 1, Game.HEIGHT*Game.SCALE/2 + 35, 11*Game.SCALE, 14*Game.SCALE, null);
				}else {
					g.drawImage(UI.SMALL_NUMBER[digits_1.charAt(i) - '0'], Game.WIDTH*Game.SCALE - 153, Game.HEIGHT*Game.SCALE/2 + 35, 11*Game.SCALE, 14*Game.SCALE, null);
				}					
		}
	}			
	
}
