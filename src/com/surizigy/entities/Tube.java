package com.surizigy.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.surizigy.main.Game;
import com.surizigy.main.Sounds;

public class Tube extends Entity {
	
	//initializing Tube
	public Tube(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}

	//initializing Loop
	public void tick() {			
		//moving
		x--;		
		if(x == Game.WIDTH/2 - 32) {
			Sounds.points.play(0.5);			
			Game.score+=0.5;
			return;
		}else if(x + Game.WIDTH < 0){
			Game.entities.remove(this);
			return;
		}
		
		if(Player.isHit == true) {
			x++;
		}			
		
	}
	
	//rendering Tube
	public void render(Graphics g) {		
		super.render(g);		
	}
		
}
