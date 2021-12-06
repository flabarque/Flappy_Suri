package com.surizigy.world;

public class Vector2i {

	//initializing variables
	public int x, y;
	
	public Vector2i(int x, int y) {
		//initializing Vector2i
		this.x = x;
		this.y = y;
		}
	
	public boolean equals(Object object) {
		//comparing two objects
		Vector2i vac = (Vector2i) object;
		if(vac.x == this.x && vac.y == this.y) {
			return true;
			}
		
		return false;
		}
	
}
