package com.fun.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	Random r = new Random();
	private int tryJump= 0;
	public boolean activeJump=false;
	private boolean jolted= false;
	private Handler handler;
	private int numJumps = 0;
	private int numHeadbangs = 0;
	public boolean onGround;
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.handler= handler;
		velX=0;
		velY=8;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		collision();
		x+= velX;
		y+= velY;
		x= Game.clamp(x, 0, Game.WIDTH-48);
		y= Game.clamp(y,0, Game.HEIGHT-32);
		velX= Game.clamp(velX, -32, 32);
		if (velY==0)
		{
		//	velY++;
		}
		if (y<= 0 || y >= Game.HEIGHT) 
		{
			velY*=0;
		}
		if (x<= 0 || x >= Game.WIDTH) 
		{
			velX*=0;
		}
		
		if (tryJump>-16) //jump velocity
		{
			velY=-tryJump;
			tryJump--;
		}
		boolean intersectsSomething=false;
		for (int i=0; i<handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
				if (getBounds().intersects(tempObject.getBounds())&&tempObject.getID()!=ID.Player)
				{
					intersectsSomething=true;
					//onGround=true;
					if (tryJump<=-16&&onGround==false)
					{
						velY++;
					}
				}

		}
		if (intersectsSomething==true)
		{
			System.out.println("intersectsSomething is true");
			if (tryJump<=-16&&onGround==false)
			{
				velY++;
			}
		}
		//System.out.println("Hi" + velX);
	}

	private void collision(){
		boolean intersectsWall=false;
		int initialVelY= velY;
		for (int i=0; i<handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID()== ID.Enemy)
			{
				if (getBounds().intersects(tempObject.getBounds()))
				{
					activeJump=true;
					jolted=true;
					tryJump=16;
					if(HUD.SCORE>0)
						HUD.SCORE-=10;
				}
			}

			if (tempObject.getID()== ID.Wall)
			{
				int adjustedVelY=velY;
				Rectangle anticipate;
				if (velY==0)
				{
					adjustedVelY = -1;
				}
				anticipate= new Rectangle(x+velX,y+adjustedVelY,32,32);
				if (anticipate.intersects(tempObject.getBounds()))
				{
					while (anticipate.intersects(tempObject.getBounds()))
					{
						anticipate= new Rectangle(x+velX,y+velY,32,32);
						jolted=false;
						velX=velX/2;
						velY=velY/2;
						intersectsWall=true;
						activeJump=false;
						onGround = true;
						if (tempObject.getY()<y)
						{
							numJumps = 0;
							numHeadbangs = 0;
						}
						
						//else if (tempObject.getY()>y && tempObject.getX()<=x && tempObject.getX()+tempObject.getBounds().getWidth()>=x)
						//System.out.println(tempObject.getY() + " " + y);
						//System.out.println(tempObject.getX() + " " + x);
						//onGround=true;
						//else {
							
						//}
						if (onGround == true)
						{
							numJumps = 0;
						}
					}
					//onGround=false;
				}
			}
			
			if (tempObject.getID()== ID.MovingWall)
			{
				Rectangle anticipate;
				if (velY==0)
				{
					//velY=1;
				}
				anticipate= new Rectangle(x+velX,y+velY,32,32);
				if(anticipate.intersects(tempObject.getBounds()))
				{
					while (anticipate.intersects(tempObject.getBounds()))
					{
						anticipate= new Rectangle(x+velX,y+velY,32,32);
						jolted=false;
						velX=velX/2;
						velY=velY/2;
						x+=tempObject.getVelX();
						y+=tempObject.getVelY();			
						intersectsWall=true;
						activeJump=false;
						if (tempObject.getY()>y && (tempObject.getX()<x && (tempObject.getX()+x)>x))
						{
							velY = tempObject.getVelY();
							//velX = tempObject.getVelX();
							numJumps = 0;
						}
						else if (tempObject.getY()<y && (tempObject.getX()>x || (tempObject.getX()+x)<x))
						{
							velX = tempObject.getVelX();
						}
						else if (tempObject.getY()<y && (tempObject.getX()<x && (tempObject.getX()+x)>x))
						{
							velY = tempObject.getVelY();
							velX = tempObject.getVelX();
							numJumps = 0;
						}
						//System.out.println(tempObject.getY() + " " + y);
						//System.out.println(tempObject.getX() + " " + x);
					}
				tryJump = 0;
				}
			}
		}
		if (intersectsWall==false&&velY==0)
		{
			boolean intersectCheck=false;
			for (int i=0; i<handler.object.size(); i++)
			{
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getID()== ID.Wall)
				{
					onGround = false;
					Rectangle anticipate= new Rectangle(x,y+8,32,32);
					if (anticipate.intersects(tempObject.getBounds()))
					{
						/*
						velY=tempObject.getVelY();
						velX= tempObject.getVelX();
						x=x+velX;
						y=y+velY;
						activeJump=false;
						*/
						intersectCheck=true;
					}
				}
				
			}
			if (intersectCheck==false){
				velY=4;
			}
			if (intersectCheck==true)
			{
				
			}

		}
		

	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		if (activeJump==true)
		{
			g.drawString("Active Jump is true", 150, 15);
		}
		if (onGround==true)
		{
			g.drawString("onGround is true", 400, 15);
			
		}
		else {
			g.drawString("onGround is false", 400, 15);
		}
		g.drawString("velX: " + velX, 300, 15);
		g.drawString("velY: " + velY, 300, 30);
		
		g.setColor(Color.gray);
		int tail = 5;
		if (activeJump==false)
		{
			g.setColor(Color.cyan);
		}
		else if (activeJump==true){
			g.setColor(Color.red);
			if (tryJump<0)
			{
				g.setColor(Color.blue);
			}
			if (jolted==true)
			{
				g.setColor(Color.yellow);
				if (tryJump<0)
				{
					g.setColor(Color.MAGENTA);
				}
			}

		}
		g.fillRect(x-velX*tail,y-velY*tail,32,32);
		g.setColor(Color.gray);
		for (int tailTracker=tail-1; tailTracker>1; tailTracker--)
		{
			if (tailTracker%2==0) //only renders even tails
			{
				g.fillRect(x-velX*tailTracker,y-velY*tailTracker,32,32);
			}
		}
		if (activeJump==false)
		{
			g.setColor(Color.blue);
		}
		else if (activeJump==true){
			g.setColor(Color.red);
			if (tryJump<0)
			{
				g.setColor(Color.blue);
			}
			if (jolted==true)
			{
				g.setColor(Color.yellow);
				if (tryJump<0)
				{
					g.setColor(Color.MAGENTA);
				}
			}

		}
		
		g.fillRect(x,y,32,32);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,32,32);
	}	
	
	public void tryJump()
	{
		if (numJumps<2)
		{
		tryJump=16;
		activeJump=true;
		numJumps++;
		}
	}


}
