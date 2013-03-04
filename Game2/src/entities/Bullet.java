package entities;

import org.newdawn.slick.*;

public class Bullet {
	Image bullet;
	float x,y;
	float speedx = 0;
	float speedy = 0;
	float angle;
	float speed = 1f;
	int dcounter = 0;
	int deltanum = 0;
	int averagedelta = 0;
	
	public Bullet(Image bullet, float x, float y, float speed, float angle){
		this.bullet = bullet;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.angle = angle;
	}
	
	public void move(int delta){
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
		speedx = (float) Math.cos(Math.toRadians(angle)) * speed * averagedelta;
		speedy = (float) Math.sin(Math.toRadians(angle)) * speed * averagedelta;
		System.out.println("speedx: " + averagedelta);
		System.out.println("speedy: " + speedy);
		x += speedx;
		y += speedy;
	}
	
	public void draw(){
		bullet.draw(x,y);
	}
}
