package entities;

import org.newdawn.slick.Image;

public class HomingBullet extends Bullet {
	
	final float HOMING = 0.01f;
	final float ACCELERATION = 0.002f;  //Acceleration rate

	public HomingBullet(Image bullet, float x, float y, float speed, float angle) {
		super(bullet, x, y, speed, angle);
		bullet.rotate(angle);
	}
	
	public void move(MainChar main, int delta){
		rotateTowards(main);
		float scaledAccel = ACCELERATION * delta;
		float angle = bullet.getRotation();
		if(Math.abs(speedx) <= speed)
			speedx += Math.cos(Math.toRadians(angle)) * scaledAccel / Math.sqrt(2);
		if(Math.abs(speedy) <= speed)
			speedy += Math.sin(Math.toRadians(angle)) * scaledAccel / Math.sqrt(2);
		
		x += speedx * delta;
		y += speedy * delta;
		
	}
	
	public void rotateTowards(MainChar obj){
		float dx = x + bullet.getWidth()/2 - obj.x - obj.charsprite.getWidth()/2;
		float dy = y + bullet.getHeight()/2 - obj.y - obj.charsprite.getHeight()/2;
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
		
		bullet.rotate(arctan - bullet.getRotation());
	}
}
