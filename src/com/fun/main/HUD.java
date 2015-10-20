package com.fun.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	public static int SCORE=0;
	private int FPS=0;
	public void tick(){
		
	}
	public void render(Graphics g){
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, 60, 25);
		g.setColor(Color.RED);
		g.drawString("FPS: "+ FPS, 5, 15);
		g.drawString("Score :" + SCORE, 620, 15);

	}
	public void FPS(int frames)
	{
		FPS= frames;
	}
}
