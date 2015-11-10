package com.fun.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MovingWall extends GameObject{
	private int WIDTH;
	private int HEIGHT;
	private int POS1X;
	private int POS1Y;
	private int POS2X;
	private int POS2Y;
	private int SPEED;
	private Handler handler;
	private double SLOPE;
	public MovingWall(int x, int y, ID id, int WIDTH, int HEIGHT, int POS2X, int POS2Y, int SPEED, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		POS1X = x;
		POS1Y = y;
		this.POS2X = POS2X;
		this.POS2Y = POS2Y;
		this.SPEED = SPEED;
		this.handler = handler;
		SLOPE = (POS2Y-POS1Y)/(POS2X-POS1X);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x+=SPEED;
		y=getYValue(x);
		if(x>POS2X)
			SPEED*=-1;
		else if(x<POS1X)
			SPEED*=-1;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.yellow);
		g.fillRect(x,y,WIDTH,HEIGHT);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,WIDTH,HEIGHT);
	}
	
	private int getYValue(int x)
	{
		return (int)((SLOPE*(x-POS1X))-POS1Y);
	}

}
