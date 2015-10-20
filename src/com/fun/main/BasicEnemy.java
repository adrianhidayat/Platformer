package com.fun.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
	private Handler handler;
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		velX= 2;
		velY= 10;
		this.handler= handler;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x+= velX;
		y+= velY;
		
		if (y<= 0 || y >= Game.HEIGHT) 
		{
			velY*=-1;
		}
		if (x<= 0 || x >= Game.WIDTH) 
		{
			velX*=-1;
		}
		collision();
	}
	private void collision(){
		for (int i=0; i<handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID()== ID.Wall)
			{
				Rectangle anticipate= new Rectangle(x+velX,y+velY,32,32);
				if (anticipate.intersects(tempObject.getBounds()))
				{
					velX*=-1;
					velY*=-1;
				}
				
			}
		}
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillOval(x, y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,32,32);
	}

}
