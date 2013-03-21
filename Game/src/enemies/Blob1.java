package enemies;

import main.Collision;
import main.Constants;
import main.SpawnConditions;

import org.newdawn.slick.SlickException;

import entities.Enemy;
import entities.MainChar;


public class Blob1 extends Enemy {
	
	final int DASHTIME = Constants.BLOB1DASHTIME; //Time spent dashing
	final int DASHCOOLDOWN = Constants.BLOB1DASHCOOLDOWN; //Cooldown on dash
	final float MAXSPEED = Constants.BLOB1MAXSPEED; //Maximum speed
	final float ACCELERATION = Constants.BLOB1ACCELERATION; //Acceleration rate
	float scaledAccel; //Scaled acceleration
	float angle; //Dash angle
	public int dashtimer = 0; //Dashing timer
	int dashcooltimer = 0; //Dashing cooldown timer
	float speedx = 0;
	float speedy = 0;
	
	public Blob1(String type, String color, SpawnConditions spawn, int group) throws SlickException {
		super(type, color, spawn, group);
		angle = enemy.getRotation();
	}
	
	public void move(MainChar main, int delta){
		scaledAccel = 1 / ACCELERATION; //Scale acceleration
		rotateTowards(main);
		
		if(dashcooltimer > 0){
			//Dash is on cooldown; decrement cooldown timer and calculate new angle
			slowDown();
			dashcooltimer-= delta;
			angle = enemy.getRotation();
		}
		else{
			if(dashtimer > 0){
				//Dashing
				dash(delta);
				dashtimer-= delta;
			}
			else{
				//Reset timers
				dashcooltimer = DASHCOOLDOWN;
				dashtimer = DASHTIME;
			}
		}
		

		
	}
	
	void dash(int delta){
		float cosa = (float) Math.cos(Math.toRadians(angle));
		float sina = (float) Math.sin(Math.toRadians(angle));
		float r1 = MAXSPEED;
		
		//Calculate speed vector
		speedx += cosa * scaledAccel;
		speedy += sina * scaledAccel;
		
		//Calculate scaling coefficient
		float r2 = (float) Math.sqrt(speedx*speedx + speedy*speedy);
		float k = r1/r2;
		
		//Scale vector
		speedx *= k*k;
		speedy *= k*k;
		
		//Adjust coordinates
		x += speedx * delta;
		y += speedy * delta;
		
		if(Collision.enemyWall(this)){
			x -= speedx * delta;
			y -= speedy * delta;
		}
	}
	
	//Deceleration method
	public void slowDown(){
		if(speedx > 0){
			if(speedx - scaledAccel/2 <= 0)
				speedx = 0;
			else
				speedx -= scaledAccel/2;
		}
		if(speedx < 0){
			if(speedx + scaledAccel/2 >= 0)
				speedx = 0;
			else
				speedx += scaledAccel/2;
		}
		if(speedy > 0){
			if(speedy - scaledAccel/2 <= 0)
				speedy = 0;
			else
				speedy -= scaledAccel/2;
		}
		if(speedy < 0){
			if(speedy + scaledAccel/2 >= 0)
				speedy = 0;
			else
				speedy += scaledAccel/2;
		}
	}
}
