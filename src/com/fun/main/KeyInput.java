package com.fun.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		System.out.println(key);
		int movementAmount=3;
		for (int i=0; i<handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID()==ID.Player)
			{
				if (key == KeyEvent.VK_UP)
				{
					System.out.println("Up pressed");
					//tempObject.setVelY(tempObject.getVelY()-movementAmount);
					Player tempPlayerObject = (Player) handler.object.get(i);
					tempPlayerObject.tryJump();
				}
				if (key == KeyEvent.VK_DOWN)
				{
					System.out.println("Down pressed");
					tempObject.setVelY(tempObject.getVelY()+movementAmount);
				}
				if (key == KeyEvent.VK_LEFT)
				{
					System.out.println("LEFT pressed");
					tempObject.setVelX(tempObject.getVelX()-movementAmount);
				}
				if (key == KeyEvent.VK_RIGHT)
				{
					System.out.println("RIGHT pressed");
					tempObject.setVelX(tempObject.getVelX()+movementAmount);
				}
				if (key == KeyEvent.VK_SPACE)
				{
					//tempObject.setX(1)
				}
			}
		}
		if (key == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
	}
}
