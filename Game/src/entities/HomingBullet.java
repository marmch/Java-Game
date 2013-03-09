package entities;

import org.newdawn.slick.SlickException;

public class HomingBullet extends Bullet {
	
	final float HOMING = 0.05f; //Homing rate
	final float ACCELERATION = 0.2f;  //Acceleration rate

	public HomingBullet(String bullet, float x, float y, float speed, float angle) throws SlickException {
		super(bullet, x, y, speed, angle);
		super.bullet.rotate(angle);
	}
	
	public void move(MainChar main, int delta){
		rotateTowards(main);
		float scaledAccel = delta / ACCELERATION; //Scale acceleration
		float angle = bullet.getRotation(); //Find absolute bullet angle
		
		//Calculate speed vector
		float r1 = speed;
		float cosa = (float) Math.cos(Math.toRadians(angle));
		float sina = (float) Math.sin(Math.toRadians(angle));
		speedx += cosa * scaledAccel;
		speedy += sina * scaledAccel;
		
		//Calculate scaling coefficient
		float r2 = (float) Math.sqrt(speedx*speedx + speedy*speedy);
		float k = r1/r2;
		
		//Scale vector
		speedx *= k*k*delta;
		speedy *= k*k*delta;
		
		//Adjust coordinates
		x += speedx * delta;
		y += speedy * delta;
		
	}
	
	public void rotateTowards(MainChar main){
		//Calculate angle between bullet and main character
		float dx = x + bullet.getWidth()/2 - main.x - main.charsprite.getWidth()/2;
		float dy = y + bullet.getHeight()/2 - main.y - main.charsprite.getHeight()/2;
		float arctan;
		if(dy > 0 && dx > 0 || dy < 0 && dx > 0)
			arctan = (float)Math.toDegrees(Math.atan(dy/dx)) + 180;
		else if(dy < 0 && dx < 0 || dy > 0 && dx < 0)
			arctan = (float)Math.toDegrees(Math.atan(dy/dx));
		else if (dx > 0)
			arctan = 90;
		else if (dx < 0)
			arctan = -90;
		else if (dy > 0)
			arctan = 0;
		else
			arctan = 180;
		
		float rotation = (arctan - bullet.getRotation() + 720)%360; //Calculate positive maximum angle for bullet rotation
		
		//Calculate actual angle for bullet rotation
		float newrotation;
		if(rotation <= 180)
			newrotation = Math.min(HOMING, rotation);
		else
			newrotation = Math.max((360-HOMING)%360, rotation);
		
		bullet.rotate(newrotation); //Rotate bullet
	}
}
