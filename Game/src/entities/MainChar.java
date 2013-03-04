package entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MainChar {
	
	Image charsprite;
	final float MAXSPEED = 0.25f;  //Maximum speed you can reach
	final float ACCELERATION = 0.001f;  //Acceleration rate
	float x, y;
	float speedx = 0;
	float speedy = 0;
	
	public MainChar(Image mainchar, int x, int y) throws SlickException{
		charsprite = mainchar;
		this.x = x;
		this.y = y;
	}
	
	public void draw(){
		charsprite.draw(x,y);
	}
	
	public void move(Input input, int delta, int max_x, int max_y){

		if(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_D)){
		}
		else if(input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_S)){
			slowDown();
		}
		else if(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_W)){
			if(speedx >= -MAXSPEED)
				speedx -= ACCELERATION*delta/Math.sqrt(2);
			if(speedy >= -MAXSPEED)
				speedy -= ACCELERATION*delta/Math.sqrt(2);
		}
		else if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_W)){
			if(speedx <= MAXSPEED)
				speedx += ACCELERATION*delta/Math.sqrt(2);
			if(speedy >= -MAXSPEED)
				speedy -= ACCELERATION*delta/Math.sqrt(2);
		}
		else if(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_S)){
			if(speedx >= -MAXSPEED)
				speedx -= ACCELERATION*delta/Math.sqrt(2);
			if(speedy <= MAXSPEED)
				speedy += ACCELERATION*delta/Math.sqrt(2);
		}
		else if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_S)){
			if(speedx <= MAXSPEED)
				speedx += ACCELERATION*delta/Math.sqrt(2);
			if(speedy <= MAXSPEED)
				speedy += ACCELERATION*delta/Math.sqrt(2);
		}
		else if(input.isKeyDown(Input.KEY_A)){
			if(speedx >= -MAXSPEED)
				speedx -= ACCELERATION*delta;
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
		else if(input.isKeyDown(Input.KEY_D)){
			if(speedx <= MAXSPEED)
				speedx += ACCELERATION*delta;
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
		else if(input.isKeyDown(Input.KEY_W)){
			if(speedy >= -MAXSPEED)
				speedy -= ACCELERATION*delta;
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
		}
		else if(input.isKeyDown(Input.KEY_S)){
			if(speedy <= MAXSPEED)
				speedy += ACCELERATION*delta;
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
		}
		else{
			slowDown();
		}
		
		if(x + speedx >= 0 && x+speedx <= max_x - charsprite.getWidth())
			x += speedx;
		if(y + speedy >= 0 && y+speedy <= max_y - charsprite.getHeight())
			y += speedy;
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
