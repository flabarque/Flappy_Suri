package com.surizigy.world;

public class Node {

	//initializing Variables
	public Vector2i tile;
	public Node parent;
	public double fCost, gCost, hCost;
	
	//initializing Node
	public Node(Vector2i tile, Node parent, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = gCost + hCost;
		
	}
	
}
