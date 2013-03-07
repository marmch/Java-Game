package enemies;

import org.newdawn.slick.SlickException;

import entities.Enemy;

public class Blob3 extends Enemy{
	
	float angle;
	float speedx;
	float speedy;
	final float MAXSPEED = 0.2f;  //Maximum speed
	
	public Blob3(String type, String color, int x, int y) throws SlickException {
		super(type, color, x, y);
		angle = (float)Math.random()*360f;
	}
	
	public void move(int delta){
		enemy.rotate(1f);
		speedx = (float) (Math.cos(Math.toRadians(angle)) * MAXSPEED);
		speedy = (float) (Math.sin(Math.toRadians(angle)) * MAXSPEED);
		
		x += speedx * delta;
		y += speedy * delta;
	}

}
