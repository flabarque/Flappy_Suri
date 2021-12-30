package com.surizigy.world;

import com.surizigy.entities.Entity;
import com.surizigy.entities.Player;
import com.surizigy.main.Game;
import com.surizigy.main.Parallax;

public class World {	
	
	//restarting Game
	public static void restartGame() {		
		Game.score = 0;
		Game.entities.clear();
		Game.player = new Player(Game.WIDTH/4, Game.HEIGHT/2, 37, 32, 2, Entity.PLAYER_IS_FLYING[0]);
		Game.parallax = new Parallax();
		Game.tubegenerator = new TubeGenerator(); 
		Game.entities.add(Game.player);		
		Player.isHit = false;
		return;
	}	
	
}
