package enemies;

import org.newdawn.slick.SlickException;

import entities.*;

public class Blob1 extends Enemy {
	
	final int DASHTIME = 500;
	final int DASHCOOLDOWN = 500;
	final float MAXSPEED = 0.7f;  //Maximum speed
	final float ACCELERATION = 0.002f;  //Acceleration rate
	float scaledAccel;
	float speedx = 0;
	float speedy = 0;
	int dashtimer = 0;
	int dashcooltimer = 0;
	
	public Blob1(String type, String color, int x, int y) throws SlickException {
		super(type, color, x, y);
	}
	
	public void move(MainChar main, int delta){
		scaledAccel = ACCELERATION * delta;
		if(dashcooltimer > 0){
			slowDown();
			dashcooltimer-= delta;
		}
		else{
			if(dashtimer > 0){
				dash(delta);
				dashtimer-= delta;
			}
			else{
				dashcooltimer = DASHCOOLDOWN;
				dashtimer = DASHTIME;
			}
		}
		
		super.rotateTowards(main);
	}
	
	void dash(int delta){
		float angle = super.enemy.getRotation();
		if(Math.abs(speedx) <= MAXSPEED)
			speedx += Math.cos(Math.toRadians(angle)) * scaledAccel / Math.sqrt(2);
		if(Math.abs(speedy) <= MAXSPEED)
			speedy += Math.sin(Math.toRadians(angle)) * scaledAccel / Math.sqrt(2);
		
		x += speedx * delta;
		y += speedy * delta;
	}
	
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
