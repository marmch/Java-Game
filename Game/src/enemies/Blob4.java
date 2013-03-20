package enemies;

import main.Constants;
import main.SpawnConditions;

import org.newdawn.slick.SlickException;

import bullets.NormalBullet;

import entities.Bullet;
import entities.Enemy;
import entities.MainChar;

public class Blob4 extends Enemy {
	
	final float MAXSPEED = Constants.BLOB4MAXSPEED; //Maximum speed
	public final int BULLETDELAY = Constants.BLOB4BULLETDELAY; //Bullet delay
	final float BULLETSPEED = Constants.BLOB4BULLETSPEED; //Bullet speed
	public int bulletdelta = 0; //Bullet spawn timer
	float speedx = 0;
	float speedy = 0;
	
	public Blob4(String type, String color, SpawnConditions spawn, int group) throws SlickException {
		super(type, color, spawn, group);
	}
	
	public void move(MainChar main, int delta){
		super.rotateTowards(main);
		
		//Calculate speed vector
		float angle = enemy.getRotation();
		speedx = (float) (Math.cos(Math.toRadians(angle)) * MAXSPEED);
		speedy = (float) (Math.sin(Math.toRadians(angle)) * MAXSPEED);
		
		//Adjust coordinates
		x += speedx * delta;
		y += speedy * delta;
	}
	
	public Bullet spawnBullet() throws SlickException{
		//Spawn bullet
		Bullet b = new NormalBullet("normal", color,x + enemy.getWidth()/2,y + enemy.getHeight()/2,BULLETSPEED,enemy.getRotation(),false);
		return b;
	}
}
