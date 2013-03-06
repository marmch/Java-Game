package enemies;

import org.newdawn.slick.SlickException;

import entities.*;

public class Blob1 extends Enemy {
	
	final int DASHTIME = 500;
	final int DASHCOOLDOWN = 1000;
	final float MAXSPEED = 0.7f;  //Maximum speed
	final float ACCELERATION = 0.003f;  //Acceleration rate
	float speedx = 0;
	float speedy = 0;
	int dashtimer = 0;
	int dashcooltimer = 0;
	
	public Blob1(String type, String color, int x, int y) throws SlickException {
		super(type, color, x, y);
	}
	
	public void move(MainChar main, int delta){
		//This seems to help with lag jumps
		dcounter++;
		if(dcounter/1000000 > 0){
			deltanum = 0;
			dcounter = 0;
		}
		if(deltanum < 10000){
			averagedelta *= deltanum;
			averagedelta += delta;
			deltanum++;
			averagedelta /= deltanum;
		}
		
		
		if(dashcooltimer > 0){
			slowDown();
			dashcooltimer-= averagedelta;
		}
		else{
			if(dashtimer > 0){
				dash(averagedelta);
				dashtimer-= averagedelta;
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
			speedx += Math.cos(Math.toRadians(angle)) * ACCELERATION / Math.sqrt(2);
		if(Math.abs(speedy) <= MAXSPEED)
			speedy += Math.sin(Math.toRadians(angle)) * ACCELERATION / Math.sqrt(2);
		
		x += speedx * averagedelta;
		y += speedy * averagedelta;
	}
	
	public void slowDown(){
		if(speedx > 0){
			if(speedx - ACCELERATION/2 <= 0)
				speedx = 0;
			else
				speedx -= ACCELERATION/2;
		}
		if(speedx < 0){
			if(speedx + ACCELERATION/2 >= 0)
				speedx = 0;
			else
				speedx += ACCELERATION/2;
		}
		if(speedy > 0){
			if(speedy - ACCELERATION/2 <= 0)
				speedy = 0;
			else
				speedy -= ACCELERATION/2;
		}
		if(speedy < 0){
			if(speedy + ACCELERATION/2 >= 0)
				speedy = 0;
			else
				speedy += ACCELERATION/2;
		}
	}
}