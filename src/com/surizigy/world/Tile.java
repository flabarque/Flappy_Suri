package com.surizigy.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	//initializing variables 
	//public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 0, 16, 16);

	private BufferedImage spritesheet;
	private int x, y;
	
	public Tile(int x, int y, BufferedImage spritesheet) {
		//initializing Tile 
		this.x = x;
		this.y = y;
		this.spritesheet = spritesheet;
		
	}
	
	public void render(Graphics g) {
		//rendering Tile 
		g.drawImage(spritesheet, x - Camera.x, y - Camera.y, null);
		}
	
}
