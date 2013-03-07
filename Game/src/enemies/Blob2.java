package enemies;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Bullet;
import entities.Enemy;
import entities.MainChar;

public class Blob2 extends Enemy {
	
	float angle;
	final float MAXSPEED = 0.2f;  //Maximum speed
	public final int BULLETDELAY = 800;
	public int bulletdelta = 0;
	float speedx;
	float speedy;
	
	public Blob2(String type, String color, int x, int y) throws SlickException {
		super(type, color, x, y);
		angle = (float)Math.random()*360f;
	}
	
	public void move(int delta){
		enemy.rotate(1f);
		speedx = (float) (Math.cos(Math.toRadians(angle)) * MAXSPEED);
		speedy = (float) (Math.sin(Math.toRadians(angle)) * MAXSPEED);
		
		x += speedx * delta;
		y += speedy * delta;
	}
	
	public Bullet spawnBullet(MainChar main, Image bulletsprite){
		float dx = x + enemy.getWidth()/2 - main.x - main.charsprite.getWidth()/2;
		float dy = y + enemy.getHeight()/2 - main.y - main.charsprite.getHeight()/2;
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

		return new Bullet(bulletsprite,x + enemy.getWidth()/2,y + enemy.getHeight()/2,1f,arctan);
	}
}
