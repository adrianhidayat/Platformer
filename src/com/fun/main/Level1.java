package com.fun.main;

import java.util.Random;

public class Level1 {
	Handler handler;
	public static final int WIDTH= 720, HEIGHT = 480;
	Random r= new Random();
	private KeyInput levelInput = new KeyInput(handler);
	public Level1(Handler handler, KeyInput input)
	{
		this.handler=handler;

		handler.addObject(new Player(WIDTH/2, HEIGHT/2, ID.Player, handler));
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH/2), HEIGHT/2, ID.Enemy,handler));
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH/2), HEIGHT/2, ID.Enemy,handler));
		handler.addObject(new BasicGreenEnemy(r.nextInt(WIDTH/2), HEIGHT/2, ID.Enemy,handler));
		handler.addObject(new BasicGreenEnemy(r.nextInt(WIDTH/2), HEIGHT/2, ID.Enemy,handler));
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH/2)+WIDTH/2, HEIGHT/2, ID.Enemy,handler));
		//handler.addObject(new BasicEnemy(r.nextInt(WIDTH/4), HEIGHT/4, ID.Enemy));
		handler.addObject(new BasicWall(WIDTH-300, (int) ((int) HEIGHT/1.5), ID.Wall, 100,400));
		handler.addObject(new BasicWall(0, HEIGHT-100, ID.Wall, 720,400));
		handler.addObject(new BasicWall(WIDTH-100, (int) ((int) HEIGHT/2), ID.Wall, 100,100));
	}
	public KeyInput returnKeyInput(){
		return new KeyInput(handler);
	}
}
