package com.example.rul0070.ass2_v2;

public enum Direction {
	UP(-1, 0),
	RIGHT(0,1),
	DOWN(1,0),
	LEFT(0,-1),
	Delay(0,0);
	
	public int xAdjust;
	public int yAdjust;

	private Direction(int x, int y){
		xAdjust = x;
		yAdjust = y;
	}

}
