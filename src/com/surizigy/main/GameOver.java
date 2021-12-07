package com.surizigy.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.surizigy.graphics.UI;

public class GameOver extends Menu {

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
				showMessageGameOver = true;}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 100)); //opacidade
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		//g2.setFont(new Font("calibri", Font.BOLD, 50));
		//g2.setColor(Color.white);
		//g2.drawString("GAME OVER", ((WIDTH*SCALE)/2) - 115, ((HEIGHT*SCALE)/2) - 30);
		g.drawImage(UI.GAME_OVER, ((Game.WIDTH*Game.SCALE) / 2) - 33*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 2) - 90, 66*Game.SCALE, 25*Game.SCALE, null);
		//g2.setFont(new Font("calibri", Font.BOLD, 30));
		//g2.setColor(Color.white);
		if(showMessageGameOver) {
			g.setFont(new Font("calibri", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(">Press SPACE to continue<", ((Game.WIDTH*Game.SCALE)/2) - 165, ((Game.HEIGHT*Game.SCALE)/2) + 40);
			g.setFont(new Font("calibri", Font.BOLD, 30));
			g.setColor(Color.white);
			g.drawString(">Press SPACE to continue<", ((Game.WIDTH*Game.SCALE)/2) - 166, ((Game.HEIGHT*Game.SCALE)/2) + 39);
		}
	}
	
}
