package com.fun.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicWall extends GameObject{
	private int WIDTH;
	private int HEIGHT;
Random r= new Random();
	public BasicWall(int x, int y, ID id,int WIDTH,int HEIGHT) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.HEIGHT=HEIGHT;
		this.WIDTH=WIDTH;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	//	y=y+ r.nextInt(50)-25;
		//x=x+ r.nextInt(50)-25;

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

		g.setColor(Color.gray);
		g.fillRect(x,y,WIDTH,HEIGHT);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,WIDTH,HEIGHT);
	}

}
