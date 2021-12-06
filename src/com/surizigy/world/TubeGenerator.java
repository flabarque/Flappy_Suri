package com.surizigy.world;

import com.surizigy.entities.Entity;
import com.surizigy.entities.Tube;
import com.surizigy.main.Game;

public class TubeGenerator {

	//initializing variables 
	public int time = 0;
	public int targetTime = 0;
			
	public void tick() {
		//initializing Loop 
		time++;
		if(time == 105) {				
			int heightup = Entity.rand.nextInt(30 - 5) + 5;
			Tube tubeup = new Tube(Game.WIDTH, -heightup, 32, 96, 0, Entity.ZIGGY_96);
			Game.entities.add(tubeup);
			
			int heightdown = heightup + Entity.rand.nextInt(20 - 5) + 5;
			Tube tubedown = new Tube(Game.WIDTH, Game.HEIGHT - heightdown - 24, 32, 96, 0, Entity.KIKI_96);
			Game.entities.add(tubedown);
					
			time = 0;
		
		}else if (Game.score > 75) {
			if(time == 35) {				
				int heightup = Entity.rand.nextInt(45 - 5) + 5;
				Tube tubeup = new Tube(Game.WIDTH, -heightup, 32, 96, 0, Entity.ZIGGY_96);
				Game.entities.add(tubeup);
				
				int heightdown = heightup + Entity.rand.nextInt(35 - 5) + 5;
				Tube tubedown = new Tube(Game.WIDTH, Game.HEIGHT - heightdown - 24, 32, 96, 0, Entity.KIKI_96);
				Game.entities.add(tubedown);
						
				time = 0;
			}
		}else if (Game.score > 50) {
			if(time == 35) {				
				int heightup = Entity.rand.nextInt(40 - 5) + 5;
				Tube tubeup = new Tube(Game.WIDTH, -heightup, 32, 96, 0, Entity.ZIGGY_96);
				Game.entities.add(tubeup);
				
				int heightdown = heightup + Entity.rand.nextInt(30 - 5) + 5;
				Tube tubedown = new Tube(Game.WIDTH, Game.HEIGHT - heightdown - 24, 32, 96, 0, Entity.KIKI_96);
				Game.entities.add(tubedown);
						
				time = 0;
			}
		}else if (Game.score > 25) {
			if(time == 70) {				
				int heightup = Entity.rand.nextInt(35 - 5) + 5;
				Tube tubeup = new Tube(Game.WIDTH, -heightup, 32, 96, 0, Entity.ZIGGY_96);
				Game.entities.add(tubeup);
				
				int heightdown = heightup + Entity.rand.nextInt(25 - 5) + 5;
				Tube tubedown = new Tube(Game.WIDTH, Game.HEIGHT - heightdown - 24, 32, 96, 0, Entity.KIKI_96);
				Game.entities.add(tubedown);
						
				time = 0;
			}
		}
			
	}
	
}
