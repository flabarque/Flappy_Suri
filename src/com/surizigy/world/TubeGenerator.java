package com.surizigy.world;

import com.surizigy.entities.Entity;
import com.surizigy.entities.Tube;
import com.surizigy.main.Game;

public class TubeGenerator {

	//initializing Variables 
	public int time = 0;
	public int targetTime = 0;
			
	//initializing Loop
	public void tick() { 
		//less time, more tubes, more difficulty
		time++;
		
		if(time == 105) {				
			int heightup = Entity.rand.nextInt(90 - 5) + 5;
			Tube tubeup = new Tube(Game.WIDTH, -heightup, 32, 128, 0, Entity.ZIGGY_96);
			Game.entities.add(tubeup);
			
			int heightdown = heightup + Entity.rand.nextInt(10 - 5) + 5;
			Tube tubedown = new Tube(Game.WIDTH, Game.HEIGHT - heightdown - 24, 32, 128, 0, Entity.KIKI_96);
			Game.entities.add(tubedown);
					
			time = 0;
		
		}else if(Game.score >= 96) {
				time--;			
			
		}else if (Game.score > 75) {
			if(time == 35) {				
				int heightup = Entity.rand.nextInt(65 - 5) + 5;
				Tube tubeup = new Tube(Game.WIDTH, -heightup, 32, 128, 0, Entity.ZIGGY_96);
				Game.entities.add(tubeup);
				
				int heightdown = heightup + Entity.rand.nextInt(35 - 5) + 5;
				Tube tubedown = new Tube(Game.WIDTH, Game.HEIGHT - heightdown - 24, 32, 128, 0, Entity.KIKI_96);
				Game.entities.add(tubedown);
						
				time = 0;
			}
		}else if (Game.score > 49) {
			if(time == 35) {				
				int heightup = Entity.rand.nextInt(65 - 5) + 5;
				Tube tubeup = new Tube(Game.WIDTH, -heightup, 32, 128, 0, Entity.ZIGGY_96);
				Game.entities.add(tubeup);
				
				int heightdown = heightup + Entity.rand.nextInt(35 - 5) + 5;
				Tube tubedown = new Tube(Game.WIDTH, Game.HEIGHT - heightdown - 24, 32, 128, 0, Entity.KIKI_96);
				Game.entities.add(tubedown);
						
				time = 0;
			}
		}else if (Game.score > 25) {
			if(time == 70) {				
				int heightup = Entity.rand.nextInt(90 - 5) + 5;
				Tube tubeup = new Tube(Game.WIDTH, -heightup, 32, 128, 0, Entity.ZIGGY_96);
				Game.entities.add(tubeup);
				
				int heightdown = heightup + Entity.rand.nextInt(10 - 5) + 5;
				Tube tubedown = new Tube(Game.WIDTH, Game.HEIGHT - heightdown - 24, 32, 128, 0, Entity.KIKI_96);
				Game.entities.add(tubedown);
						
				time = 0;
			}
		}			
	}
	
}
