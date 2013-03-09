package entities;

import org.newdawn.slick.*;

public class Bullet {
	Image bullet; //Bullet sprite
	float speedx = 0;
	float speedy = 0;
	float angle; //Bullet angle
	float speed; //Bullet speed
	float x,y; //Coordinates
	
	public Bullet(String bullet, float x, float y, float speed, float angle) throws SlickException{
		this.bullet = new Image(bullet);
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.angle = angle;
	}
	
	public void move(int delta){
		//Calculate speed vector
		speedx = (float) Math.cos(Math.toRadians(angle)) * speed;
		speedy = (float) Math.sin(Math.toRadians(angle)) * speed;
		
		//Adjust coordinates
		x += speedx * delta;
		y += speedy * delta;
	}
	
	//Check if bullet is outside level
	public boolean outOfMap(int max_x, int max_y){
		if(x < 0 || x > max_x - bullet.getWidth())
			return true;
		else if(y < 0 || y > max_y - bullet.getHeight())
			return true;
		else
			return false;
	}
	
	public void draw(){
		bullet.draw(x,y);
	}
}
