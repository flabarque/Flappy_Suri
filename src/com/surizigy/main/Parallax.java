package com.surizigy.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Parallax {

	public BufferedImage BACKGROUNDS_0;
	public BufferedImage BACKGROUNDS_1;
	public BufferedImage BACKGROUNDS_2;
	public BufferedImage BACKGROUNDS_3;
	public BufferedImage BACKGROUNDS_4;
	public BufferedImage BACKGROUNDS_5;

	public int x_0 = 0;
	public int x_1 = Game.WIDTH*Game.SCALE;
	public int x_2 = 0;
	public int x_3 = Game.WIDTH*Game.SCALE;
	public int x_4 = 0;
	public int x_5 = Game.WIDTH*Game.SCALE;
	public double x_spd = 1;
	
	public Parallax() {
		try {
			BACKGROUNDS_0 = ImageIO.read(getClass().getResource("/hills_layer_00.png"));
			BACKGROUNDS_1 = ImageIO.read(getClass().getResource("/hills_layer_01.png"));
			BACKGROUNDS_2 = ImageIO.read(getClass().getResource("/hills_layer_02.png"));
			BACKGROUNDS_3 = ImageIO.read(getClass().getResource("/hills_layer_03.png"));
			BACKGROUNDS_4 = ImageIO.read(getClass().getResource("/hills_layer_04.png"));
			BACKGROUNDS_5 = ImageIO.read(getClass().getResource("/hills_layer_05.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		if(Game.score > 51) {
			x_spd=+2;
		}
		
		x_0-=x_spd;
		if(x_0 + Game.WIDTH*Game.SCALE <= 0) {
			x_0 = Game.WIDTH*Game.SCALE;
		}
		
		x_1-=x_spd;
		if(x_1 + Game.WIDTH*Game.SCALE <= 0) {
			x_1 = Game.WIDTH*Game.SCALE;
		}
		
		x_2-=x_spd*2;
		if(x_2 + Game.WIDTH*Game.SCALE <= 0) {
			x_2 = Game.WIDTH*Game.SCALE;
		}
		
		x_3-=x_spd*2;
		if(x_3 + Game.WIDTH*Game.SCALE <= 0) {
			x_3 = Game.WIDTH*Game.SCALE;
		}
		
		x_4-=x_spd*3;
		if(x_4 + Game.WIDTH*Game.SCALE <= 0) {
			x_4 = Game.WIDTH*Game.SCALE;
		}
		
		x_5-=x_spd*3;
		if(x_5 + Game.WIDTH*Game.SCALE <= 0) {
			x_5 = Game.WIDTH*Game.SCALE;
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(BACKGROUNDS_0, 0, 0, 512, 256, null);
		g.drawImage(BACKGROUNDS_1, x_0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT, null);
		g.drawImage(BACKGROUNDS_1, x_1, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT, null);
		g.drawImage(BACKGROUNDS_3, x_2, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT, null);
		g.drawImage(BACKGROUNDS_3, x_3, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT, null);
		g.drawImage(BACKGROUNDS_4, x_4, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT, null);
		g.drawImage(BACKGROUNDS_4, x_5, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT, null);
	}

}
