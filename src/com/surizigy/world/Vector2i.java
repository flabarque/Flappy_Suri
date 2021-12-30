package com.surizigy.world;

public class Vector2i {

	//initializing Variables
	public int x, y;
	
	//initializing Vector2i
	public Vector2i(int x, int y) {		
		this.x = x;
		this.y = y;
		}
	
	//comparing two objects
	public boolean equals(Object object) {		
		Vector2i vac = (Vector2i) object;
		if(vac.x == this.x && vac.y == this.y) {
			return true;
			}
		
		return false;
		}
	
}
