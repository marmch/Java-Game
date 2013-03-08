package entities;

import org.newdawn.slick.Image;

public class HomingBullet extends Bullet {
	
	final float HOMING = 0.01f;

	public HomingBullet(Image bullet, float x, float y, float speed, float angle) {
		super(bullet, x, y, speed, angle);
	}
	
	public void move(MainChar main, int delta){
		
		float dx = x + bullet.getWidth()/2 - main.x - main.charsprite.getWidth()/2;
		float dy = y + bullet.getHeight()/2 - main.y - main.charsprite.getHeight()/2;
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
		
		float rotation = arctan - angle;
		
		if(rotation >= 0)
			angle += Math.min(rotation, HOMING);
		else
			angle -= Math.max(rotation, -HOMING);
		
		System.out.println(arctan);
		bullet.rotate(angle);
		
		x += Math.cos(Math.toRadians(bullet.getRotation())) * speed * delta;
		y +=  Math.sin(Math.toRadians(bullet.getRotation())) * speed * delta;
	}
}
