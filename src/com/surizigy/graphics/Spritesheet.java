package com.surizigy.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	//initializing Variables 
	private BufferedImage spritesheet;
	
	//initializing Sprite Sheet
	public Spritesheet(String path) {		 
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//getting Sprite Sheet 
	public BufferedImage getSprite(int x, int y, int width, int height) {	
		return spritesheet.getSubimage(x, y, width, height);
		
	}
	
}
