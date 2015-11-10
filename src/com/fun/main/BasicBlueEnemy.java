package com.fun.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicBlueEnemy extends GameObject{
	private Handler handler;
	private boolean growing = true;
	int xSize=1;
	public BasicBlueEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.handler=handler;
		velX = 5;
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x+= velX;
		if (xSize>=200)
			growing = false;
		else if(xSize<=1)
			growing = true;
		if(growing)
			xSize++;
		else
			xSize--;
		if (x<= 0 || x >= Game.WIDTH-xSize) 
			velX*=-1;
		collision();
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		g.fillOval(x, y, xSize, 16);
	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y, xSize,32);
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

}
