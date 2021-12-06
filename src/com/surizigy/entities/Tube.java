package com.surizigy.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.surizigy.main.Game;
import com.surizigy.main.Sounds;

public class Tube extends Entity {

	public Tube(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		//initializing Loop			
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
	
	public void render(Graphics g) {
		//rendering Tube
		super.render(g);
		/*if(sprite != null) {
			g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, width, height, null);
		} else {
		
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, width, height);
		}*/
		
	}
	
	
}
