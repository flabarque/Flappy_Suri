package com.surizigy.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.surizigy.main.Game;
import com.surizigy.world.Camera;
import com.surizigy.world.Node;
import com.surizigy.world.Vector2i;
import com.surizigy.world.World;

public class Entity {

	//initializing variables 
	public static BufferedImage[] PLAYER_IS_FLYING = {Game.spritesheet.getSprite(0, 0, 37, 32), Game.spritesheet.getSprite(38, 0, 37, 32), Game.spritesheet.getSprite(76, 0, 37, 32), Game.spritesheet.getSprite(114, 0, 37, 32), Game.spritesheet.getSprite(0, 32, 37, 32), Game.spritesheet.getSprite(38, 32, 37, 32), Game.spritesheet.getSprite(76, 32, 37, 32), Game.spritesheet.getSprite(114, 32, 37, 32)};
	public static BufferedImage PLAYER_HIT = Game.spritesheet.getSprite(0, 192, 39, 37);
	public static BufferedImage ZIGGY_96 = Game.spritesheet.getSprite(128, 64, 32, 128);
	public static BufferedImage KIKI_96 = Game.spritesheet.getSprite(0, 64, 32, 128);
		
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected double speed;
	
	public int depth;
	
	protected List<Node> path;
	
	public BufferedImage sprite;
	
	public static Random rand = new Random();
	
	public Entity(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		//initializing Entities
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	
	}
	
	public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {
		//comparing two objects
		
		@Override
		public int compare(Entity n0, Entity n1) {
			if(n1.depth < n0.depth)
				return +1;
			if(n1.depth > n0.depth)
				return -1;
			return 0;
		}	
	};
	
	public void updateCamera() {
		//updating camera to stay at the center of the Game
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, World.HEIGHT*16 - Game.HEIGHT);
	}
	
	//setting Entities coordinates
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	//getting Entities coordinates
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void tick() {
		//starting Looping
	}
	
	public double calculateDistance(int x1, int y1, int x2, int y2) {
		//calculating distances between objects
		return Math.sqrt(((x1 - x2)*(x1 - x2)) + ((y1 - y2)*(y1 - y2)));
	} 
	
	public void followPath(List<Node> path) {
		//tracking objects
		if(path != null) { 
			if(path.size() > 0) {  
				Vector2i target = path.get(path.size() - 1).tile; 
				if(x < target.x * 16) { 
					x++;
				}else if(x > target.x * 16) {
					x--;
				}
				
				if(y < target.y * 16) { 
					y++;
				}else if(y > target.y * 16) {
					y--;
				}

				if(x == target.x * 16 && y == target.y * 16) { 
					path.remove(path.size() - 1);
				}
			}
		}
	}
	
	public static boolean isColliding (Entity e1, Entity e2) {
		//checking collisions between Entities --> mask reduce -13
		Rectangle e1Mask = new Rectangle(e1.getX(), e1.getY(), e1.getWidth()-13, e1.getHeight()-13);
		Rectangle e2Mask = new Rectangle(e2.getX(), e2.getY(), e2.getWidth()-13, e2.getHeight()-13);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void render(Graphics g) {
		//rendering Entities
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	
}
