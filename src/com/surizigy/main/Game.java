package com.surizigy.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
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

	//initializing Variables
	//Java Frame, size
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true; 
	public static final int WIDTH = 160;
	public static final int HEIGHT = 240;
	public static final int SCALE = 3;
	
	//images, sound and other Classes 
	private BufferedImage image;
	public static List<Entity> entities;
	public static Spritesheet spritesheet;
	
	public static Player player;
	public static TubeGenerator tubegenerator;
	public static UI ui; 
	public static Parallax parallax;
	public static Sounds sounds;
	
	//game start
	public static String gameState = "MENU";
	private boolean restartGame = false;
	
	//game states
	public Menu menu;
	public GetReady getReady;
	public GameOver gameOver;
	
	//score
	public HighScore highScore;
	public static double score = 0;
	
	//initializing Game	
	public Game() {				
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		
		//initializing Classes
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
		gameOver = new GameOver();
		highScore = new HighScore();
		
	}
	
	//initializing Java Frame
	public void initFrame() {		
		frame = new JFrame("Flappy Suri");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	
	}
	
	//checking Start Game
	public synchronized void start() {		 
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	
	}
	
	//checking Stop Game 
	public synchronized void stop() {		
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	
	}
	
	//starting Game 
	public static void main(String[] args) {		
		Game game = new Game();
		game.start();		
	}
	
	//starting Loop	
	public void tick() {			
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
			parallax.tick();
	
		}else if(gameState == "GAME_OVER") {
			gameOver.tick();
			highScore.tick();			
			
			if(restartGame) {
				restartGame = false;
				gameState = "MENU";
				World.restartGame();
			}
		}
				
	}
	
	//rendering Game 
	public void render() {		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		//rendering Background
		Graphics g = image.getGraphics();		
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
		//rendering Score
		ui.render(g);
		
		//rendering States
		if(gameState == "MENU") {
			menu.render(g);
		
		}else if(gameState == "GET_READY") {
			getReady.render(g);
		
		}else if(gameState == "GAME_OVER") {
			gameOver.render(g);			
		}		
		
		bs.show();
		
	}
	
	//running Game FPS 
	public void run() {		
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
	
	//initializing Pressed Keys
	@Override
	public void keyPressed(KeyEvent e) {				
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

	//initializing Released Keys
	@Override
	public void keyReleased(KeyEvent e) {		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.isFlying = false;
		}
		
	}

	//initializing Typed Keys
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
