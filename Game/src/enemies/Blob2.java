package enemies;

import org.newdawn.slick.SlickException;

import bullets.NormalBullet;

import entities.Bullet;
import entities.Enemy;
import entities.MainChar;

public class Blob2 extends Enemy {
	
	
	final float MAXSPEED = 0.2f;  //Maximum speed
	public final int BULLETDELAY = 800; //Bullet spawn delay
	final float ROTATESPEED = 0.2f; //Speed of rotation
	final float BULLETSPEED = 0.4f; //Speed of bullet
	public int bulletdelta = 0; //Bullet spawn timer
	float angle; //Movement angle
	float speedx;
	float speedy;
	
	public Blob2(String type, String color, int x, int y) throws SlickException {
		super(type, color, x, y);
		angle = (float)Math.random()*360f; //Set random angle
		//Calculate speed vector
		speedx = (float) Math.cos(Math.toRadians(angle)) * MAXSPEED;
		speedy = (float) Math.sin(Math.toRadians(angle)) * MAXSPEED;
	}
	
	public void move(int delta){
		enemy.rotate(ROTATESPEED*delta);
		
		if(collision.enemyWall(this) && (x<0 || x+enemy.getWidth() > collision.max_x))
			speedx = -speedx;
		if(collision.enemyWall(this) && (y<0 || y+enemy.getHeight() > collision.max_y))
			speedy = -speedy;
		
		//Adjust coordinates
		x += speedx * delta * delta;
		y += speedy * delta * delta;
	}
	
	public Bullet spawnBullet(MainChar main, String bulletsprite) throws SlickException{
		//Calculate angle between blob and main character
		float dx = x + enemy.getWidth()/2 - main.x - main.charsprite.getWidth()/2;
		float dy = y + enemy.getHeight()/2 - main.y - main.charsprite.getHeight()/2;
		float arctan;
		if(dy > 0 && dx > 0 || dy < 0 && dx > 0)
			arctan = (float)Math.toDegrees(Math.atan(dy/dx)) + 180;
		else if(dy < 0 && dx <= 0 || dy > 0 && dx <= 0)
			arctan = (float)Math.toDegrees(Math.atan(dy/dx));
		else if (x > 0)
			arctan = 90;
		else if (x < 0)
			arctan = -90;
		else
			arctan = 0;
		
		//Spawn bullet
		Bullet b = new NormalBullet("normal", bulletsprite,x + enemy.getWidth()/2,y + enemy.getHeight()/2,BULLETSPEED,arctan,false);
		return b;
	}
}
