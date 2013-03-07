package enemies;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.*;

public class Blob3 extends Enemy {
	
	final float MAXSPEED = 0.2f;  //Maximum speed
	public final int BULLETDELAY = 800;
	public int bulletdelta = 0;
	float speedx = 0;
	float speedy = 0;
	
	public Blob3(String type, String color, int x, int y) throws SlickException {
		super(type, color, x, y);
	}
	
	public void move(MainChar main, int delta){
		
		
		super.rotateTowards(main);
		float angle = super.enemy.getRotation();
		speedx = (float) (Math.cos(Math.toRadians(angle)) * MAXSPEED);
		speedy = (float) (Math.sin(Math.toRadians(angle)) * MAXSPEED);
		
		x += speedx * delta;
		y += speedy * delta;
	}
	
	public Bullet spawnBullet(Image bulletsprite){
		return new Bullet(bulletsprite,x + enemy.getWidth()/2,y + enemy.getHeight()/2,1f,enemy.getRotation());
	}
}
