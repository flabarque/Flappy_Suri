package com.surizigy.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import com.surizigy.entities.Entity;
import com.surizigy.entities.Player;
import com.surizigy.graphics.Spritesheet;
import com.surizigy.graphics.UI;
import com.surizigy.world.TubeGenerator;
import com.surizigy.world.World;

public class Game extends Canvas implements Runnable, KeyListener {

	//initializing variables 
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true; 
	public static final int WIDTH = 160;
	public static final int HEIGHT = 240;
	public static final int SCALE = 3;
	
	private BufferedImage image;
	public static List<Entity> entities;
	public static Spritesheet spritesheet;
	
	public static Player player;
	public static TubeGenerator tubegenerator;
	public static UI ui; 
	public static Parallax parallax;
	public static Sounds sounds;
	
	public static String gameState = "MENU"; //State: Menu, Get_Ready, Playing, Falling, Game_Over
	private boolean showMessageGameOver = true;
	private int framesGameOver = 0;
	private boolean restartGame = false;
	
	public Menu menu;
	public GetReady getReady;
	
	public static double score = 0;
	
	public Game() {
		//initializing Game			
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		entities = new ArrayList<Entity>();
		
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(WIDTH/4, HEIGHT/2, 37, 32, 2, Entity.PLAYER_IS_FLYING[0]);
		entities.add(player);
		tubegenerator = new TubeGenerator(); 
		parallax = new Parallax();
		ui = new UI();
		
		menu = new Menu();
		getReady = new GetReady();
		
	}
	
	public void initFrame() {
		//initializing Java Frame
		frame = new JFrame("Flappy Suri");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	
	}
	
	public synchronized void start() {
		//checking Start Game 
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	
	}
	
	public synchronized void stop() {
		//checking Stop Game 
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		//starting Game 
		Game game = new Game();
		game.start();		
	}
	
	public void tick() {
		//starting Loop		
		if (gameState == "MENU") {			 
			restartGame = false;			
			menu.tick();			
		
		}else if (gameState == "GET_READY") { 			
			restartGame = false;
			getReady.tick();						
					
		}else if(gameState == "PLAYING") {
			restartGame = false;
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
						
			tubegenerator.tick();
			//ui.tick();
			parallax.tick();
	
		}else if(gameState == "GAME_OVER") {
			Sounds.musicBackground.stop(0);
			this.framesGameOver++;
			if(this.framesGameOver == 30) {
				this.framesGameOver = 0;
				if(this.showMessageGameOver)
					this.showMessageGameOver = false;
				else
					this.showMessageGameOver = true;
				}
			
			if(restartGame) {
				restartGame = false;
				gameState = "MENU";
				World.restartGame();
			}
		}
				
	}
	
	public void render() {
		//rendering Game 
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		//rendering Background
		Graphics g = image.getGraphics();
		//g.setColor(new Color(122, 102, 255));
		//g.fillRect(0, 0, WIDTH, HEIGHT);
		parallax.render(g);
		
		//rendering Entities
		Collections.sort(entities, Entity.nodeSorter); 
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g); 
		}
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		ui.render(g);
		
		if(gameState == "MENU") {
			menu.render(g);
		
		}else if(gameState == "GET_READY") {
			getReady.render(g);
		
		}else if(gameState == "GAME_OVER") {
			Graphics2D g2 = (Graphics2D) g; 
			g2.setColor(new Color(0, 0, 0, 100)); //opacidade
			g2.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
			//g2.setFont(new Font("calibri", Font.BOLD, 50));
			//g2.setColor(Color.white);
			//g2.drawString("GAME OVER", ((WIDTH*SCALE)/2) - 115, ((HEIGHT*SCALE)/2) - 30);
			g2.drawImage(UI.GAME_OVER, ((Game.WIDTH*Game.SCALE) / 2) - 33*Game.SCALE, ((Game.HEIGHT*Game.SCALE) / 2) - 90, 66*Game.SCALE, 25*Game.SCALE, null);
			//g2.setFont(new Font("calibri", Font.BOLD, 30));
			//g2.setColor(Color.white);
			if(showMessageGameOver) {
				g2.setFont(new Font("calibri", Font.BOLD, 30));
				g2.setColor(Color.black);
				g2.drawString(">Press SPACE to continue<", ((WIDTH*SCALE)/2) - 165, ((HEIGHT*SCALE)/2) + 40);
				g2.setFont(new Font("calibri", Font.BOLD, 30));
				g2.setColor(Color.white);
				g2.drawString(">Press SPACE to continue<", ((WIDTH*SCALE)/2) - 166, ((HEIGHT*SCALE)/2) + 39);				
			}
		}		
		
		bs.show();
		
	}
	
	public void run() {
		//running Game FPS 
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer = System.currentTimeMillis();
			}
		}
		
		stop();
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {		
			restartGame = true;			
			if(gameState == "MENU") {
				menu.space = true;
			}else if(gameState == "GET_READY") {
				getReady.space = true;				
			}else if(gameState == "PLAYING") {
				if(Player.isHit == false) {
					player.isFlying = true;
				}else {
					player.isFlying = false;
				}				
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_W) {	
			if(gameState == "MENU") {
				menu.up = true;
			}
		
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN || 
				e.getKeyCode() == KeyEvent.VK_S) {
			if(gameState == "MENU") {
				menu.down = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(gameState == "MENU") {
				menu.esc = true;
			}else if(gameState == "PLAYING") {
				gameState = "MENU";
				Menu.pause = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.isFlying = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
