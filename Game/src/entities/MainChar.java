package entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MainChar {
	
	public Image charsprite;
	final float MAXSPEED = 0.5f;  //Maximum speed
	final float ACCELERATION = 0.002f;  //Acceleration rate
	public float x;
	public float y;
	float speedx = 0;
	float speedy = 0;
	float averagedelta = 0;
	int  deltanum = 0;
	int dcounter = 0;
	
	public MainChar(Image mainchar, int x, int y) throws SlickException{  //(x,y) are starting coordinates
		charsprite = mainchar;
		this.x = x;
		this.y = y;
	}
	
	public void draw(){
		charsprite.draw(x,y);
	}
	
	public void move(Input input, int delta, int max_x, int max_y){
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
		
		//Key input adds acceleration value to speed
		if(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_D)){
			slowDown((int)averagedelta);
		}
		else if(input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_S)){
			slowDown((int)averagedelta);
		}
		else if(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_W)){
			if(speedx >= -MAXSPEED)
				speedx -= ACCELERATION*(int)averagedelta/Math.sqrt(2);
			if(speedy >= -MAXSPEED)
				speedy -= ACCELERATION*(int)averagedelta/Math.sqrt(2);
		}
		else if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_W)){
			if(speedx <= MAXSPEED)
				speedx += ACCELERATION*(int)averagedelta/Math.sqrt(2);
			if(speedy >= -MAXSPEED)
				speedy -= ACCELERATION*(int)averagedelta/Math.sqrt(2);
		}
		else if(input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_S)){
			if(speedx >= -MAXSPEED)
				speedx -= ACCELERATION*(int)averagedelta/Math.sqrt(2);
			if(speedy <= MAXSPEED)
				speedy += ACCELERATION*(int)averagedelta/Math.sqrt(2);
		}
		else if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_S)){
			if(speedx <= MAXSPEED)
				speedx += ACCELERATION*(int)averagedelta/Math.sqrt(2);
			if(speedy <= MAXSPEED)
				speedy += ACCELERATION*(int)averagedelta/Math.sqrt(2);
		}
		else if(input.isKeyDown(Input.KEY_A)){
			if(speedx >= -MAXSPEED)
				speedx -= ACCELERATION*(int)averagedelta;
			if(speedy > 0){
				if(speedy - ACCELERATION/2*(int)averagedelta <= 0)
					speedy = 0;
				else
					speedy -= ACCELERATION/2*(int)averagedelta;
			}
			if(speedy < 0){
				if(speedy + ACCELERATION/2*(int)averagedelta >= 0)
					speedy = 0;
				else
					speedy += ACCELERATION/2*(int)averagedelta;
			}
		}
		else if(input.isKeyDown(Input.KEY_D)){
			if(speedx <= MAXSPEED)
				speedx += ACCELERATION*(int)averagedelta;
			if(speedy > 0){
				if(speedy - ACCELERATION/2*(int)averagedelta <= 0)
					speedy = 0;
				else
					speedy -= ACCELERATION/2*(int)averagedelta;
			}
			if(speedy < 0){
				if(speedy + ACCELERATION/2*(int)averagedelta >= 0)
					speedy = 0;
				else
					speedy += ACCELERATION/2*(int)averagedelta;
			}
		}
		else if(input.isKeyDown(Input.KEY_W)){
			if(speedy >= -MAXSPEED)
				speedy -= ACCELERATION*(int)averagedelta;
			if(speedx > 0){
				if(speedx - ACCELERATION/2*(int)averagedelta <= 0)
					speedx = 0;
				else
					speedx -= ACCELERATION/2*(int)averagedelta;
			}
			if(speedx < 0){
				if(speedx + ACCELERATION/2*(int)averagedelta >= 0)
					speedx = 0;
				else
					speedx += ACCELERATION/2*(int)averagedelta;
			}
		}
		else if(input.isKeyDown(Input.KEY_S)){
			if(speedy <= MAXSPEED)
				speedy += ACCELERATION*(int)averagedelta;
			if(speedx > 0){
				if(speedx - ACCELERATION/2*(int)averagedelta <= 0)
					speedx = 0;
				else
					speedx -= ACCELERATION/2*(int)averagedelta;
			}
			if(speedx < 0){
				if(speedx + ACCELERATION/2*(int)averagedelta >= 0)
					speedx = 0;
				else
					speedx += ACCELERATION/2*(int)averagedelta;
			}
		}
		else{
			slowDown((int)averagedelta);
		}
		
		//Position is updated according to speed
		if(x + speedx >= 0 && x+speedx <= max_x - charsprite.getWidth())
			x += speedx;
		if(y + speedy >= 0 && y+speedy <= max_y - charsprite.getHeight())
			y += speedy;
	}
	
	//Method to slow down when no keys are pressed
	public void slowDown(int delta){
		if(speedx > 0){
			if(speedx - ACCELERATION/2*delta <= 0)
				speedx = 0;
			else
				speedx -= ACCELERATION/2*delta;
		}
		if(speedx < 0){
			if(speedx + ACCELERATION/2*delta >= 0)
				speedx = 0;
			else
				speedx += ACCELERATION/2*delta;
		}
		if(speedy > 0){
			if(speedy - ACCELERATION/2*delta <= 0)
				speedy = 0;
			else
				speedy -= ACCELERATION/2*delta;
		}
		if(speedy < 0){
			if(speedy + ACCELERATION/2*delta >= 0)
				speedy = 0;
			else
				speedy += ACCELERATION/2*delta;
		}
	}
	
	//Rotates the sprite to point towards the mouse
	public void rotate(Input input){
		float dx = x + charsprite.getWidth()/2 - input.getMouseX();
		float dy = y + charsprite.getHeight()/2 - input.getMouseY();
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
		charsprite.rotate(arctan - charsprite.getRotation());
	}
	
	public boolean shoot(Input input) throws SlickException{
		return input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
	}
}
