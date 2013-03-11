package entities;

import org.newdawn.slick.*;

public class Bullet {
	public String type;
	public Image bullet; //Bullet sprite
	protected float speedx = 0;
	protected float speedy = 0;
	protected float speed; //Bullet speed
	protected float angle; //Bullet angle
	public float x,y; //Coordinates
	public boolean friendly; //Damages enemy or player
	
	public Bullet(String type, String color, float x, float y, float speed, float angle, boolean friendly) throws SlickException{
		String bulletsprite;
		if(type.equals("homing")){
			if(color.equals("red"))
				bulletsprite = "img\\dbulletred.png";
			else if(color.equals("blue"))
				bulletsprite = "img\\dbulletblue.png";
			else if(color.equals("green"))
				bulletsprite = "img\\dbulletgreen.png";
			else
				bulletsprite = "img\\dbullet.png";
		}
		else{
			if(color.equals("red"))
				bulletsprite = "img\\bbulletred.png";
			else if(color.equals("blue"))
				bulletsprite = "img\\bbulletblue.png";
			else if(color.equals("green"))
				bulletsprite = "img\\bbulletgreen.png";
			else
				bulletsprite = "img\\bbullet.png";
		}
		bullet = new Image(bulletsprite);
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.angle = angle;
		this.friendly = friendly;
		this.type = type;
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
	
	public void homeOn(MainChar main, float homing){
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
			newrotation = Math.min(homing, rotation);
		else
			newrotation = Math.max((360-homing)%360, rotation);
		
		bullet.rotate(newrotation); //Rotate bullet
	}
	
	public void draw(){
		bullet.draw(x,y);
	}
}
