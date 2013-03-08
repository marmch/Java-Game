package entities;

import org.newdawn.slick.Image;

public class HomingBullet extends Bullet {
	
	final float HOMING = 0.0001f;
	final float ACCELERATION = 5f;  //Acceleration rate

	public HomingBullet(Image bullet, float x, float y, float speed, float angle) {
		super(bullet, x, y, speed, angle);
		bullet.rotate(angle);
	}
	
	public void move(MainChar main, int delta){
		if(delta != 1)
			return;
		rotateTowards(main);
		float scaledAccel = ACCELERATION * delta;
		float angle = bullet.getRotation();
		float cosa = (float) Math.cos(Math.toRadians(angle));
		float sina = (float) Math.sin(Math.toRadians(angle));
		
		float r1 = speed;
		speedx += cosa * scaledAccel;
		speedy += sina * scaledAccel;
		float r2 = (float) Math.sqrt(speedx*speedx + speedy*speedy);
		float k = r1/r2;
		
		speedx *= k*k;
		speedy *= k*k;
		
		//System.out.println("SPEED " + speedx + "," + speedy);
		System.out.println("R " + r1 + "," + r2);
		//System.out.println(delta + "," + ACCELERATION);
		//System.out.println(r1);
		//System.out.println(x + "," + y);
		
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
		
		float rotation = arctan - bullet.getRotation();
		float newrotation;
		if(rotation >= 0)
			newrotation = Math.min(HOMING, rotation);
		else
			newrotation = Math.min(-HOMING, rotation);
		
		
		bullet.rotate(newrotation);
	}
}
