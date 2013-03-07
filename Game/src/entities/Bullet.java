package entities;

import org.newdawn.slick.*;

public class Bullet {
	Image bullet;
	float x,y;
	float speedx = 0;
	float speedy = 0;
	float angle;
	float speed = 1f;
	
	public Bullet(Image bullet, float x, float y, float speed, float angle){
		this.bullet = bullet;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.angle = angle;
	}
	
	public void move(int delta){
		speedx = (float) Math.cos(Math.toRadians(angle)) * speed;
		speedy = (float) Math.sin(Math.toRadians(angle)) * speed;
		x += speedx * delta;
		y += speedy * delta;
	}
	
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
