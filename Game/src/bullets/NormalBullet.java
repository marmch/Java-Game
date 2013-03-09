package bullets;

import org.newdawn.slick.*;

import entities.Bullet;

public class NormalBullet extends Bullet {
	
	public NormalBullet(String type, String bullet, float x, float y, float speed, float angle, boolean friendly) throws SlickException{
		super(type, bullet, x, y, speed, angle, friendly);
	}
	
	public void move(int delta){
		//Calculate speed vector
		speedx = (float) Math.cos(Math.toRadians(angle)) * speed;
		speedy = (float) Math.sin(Math.toRadians(angle)) * speed;
		
		//Adjust coordinates
		x += speedx * delta;
		y += speedy * delta;
	}
	
	public void draw(){
		bullet.draw(x,y);
	}
}
