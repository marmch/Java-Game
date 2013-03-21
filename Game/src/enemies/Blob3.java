package enemies;

import main.Collision;
import main.Constants;
import main.SpawnConditions;

import org.newdawn.slick.SlickException;

import bullets.HomingBullet;

import entities.Bullet;
import entities.Enemy;
import entities.MainChar;

public class Blob3 extends Enemy {
	
	
	final float MAXSPEED = Constants.BLOB3MAXSPEED;  //Maximum speed
	public final int BULLETDELAY = Constants.BLOB3BULLETDELAY; //Bullet spawn delay
	final float BULLETSPEED = Constants.BLOB3BULLETSPEED; //Bullet speed
	public int bulletdelta = 0; //Bullet spawn timer
	float angle; //Movement angle
	float speedx;
	float speedy;
	
	public Blob3(String type, String color, SpawnConditions spawn, int group) throws SlickException {
		super(type, color, spawn, group);
		angle = (float)Math.random()*360f; //Set random angle
		//Calculate speed vector
		speedx = (float) (Math.cos(Math.toRadians(angle)) * MAXSPEED);
		speedy = (float) (Math.sin(Math.toRadians(angle)) * MAXSPEED);
	}
	
	public Blob3(String type, String color, SpawnConditions spawn, int group, float angle) throws SlickException {
		super(type, color, spawn, group);
		this.angle = angle; //Set random angle
		//Calculate speed vector
		speedx = (float) Math.cos(Math.toRadians(angle)) * MAXSPEED;
		speedy = (float) Math.sin(Math.toRadians(angle)) * MAXSPEED;
	}
	
	public void move(MainChar main, int delta){
		rotateTowards(main);
		
		if(Collision.enemyWall(this) && (x<0 || x+enemy.getWidth() > Collision.max_x))
			speedx = -speedx;
		if(Collision.enemyWall(this) && (y<0 || y+enemy.getHeight() > Collision.max_y))
			speedy = -speedy;
		
		//Adjust coordinates
		x += speedx * delta;
		y += speedy * delta;
	}
	
	public Bullet spawnBullet(MainChar main) throws SlickException{
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
		
		//Spawn homing bullet
		Bullet b = new HomingBullet("homing", color,x + enemy.getWidth()/2,y + enemy.getHeight()/2,BULLETSPEED,arctan, false);
		return b;
	}
}
