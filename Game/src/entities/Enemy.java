package entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy {
	final float MAXSPEED = 0.5f;  //Maximum speed
	final float ACCELERATION = 0.002f;  //Acceleration rate
	String color; //Enemy color
	public String type; //Enemy type
	public Image enemy; //Enemy sprite
	public float rotation = 0; //Enemy rotation
	public float x,y; //Coordinates
	
	public Enemy(String type, String color, float x, float y) throws SlickException{
		this.type = type;
		this.color = color;
		this.x = x;
		this.y = y;
		
		//Set enemy sprite
		if(type.equals("blob1"))
			enemy = new Image("img\\blob3blue.png");
		else if(type.equals("blob2"))
			enemy = new Image("img\\blob3blue.png");
		else if(type.equals("blob3"))
			enemy = new Image("img\\blob3blue.png");
		else if(type.equals("blob4"))
			enemy = new Image("img\\blob3blue.png");
	}
	
	public void draw(){
		enemy.draw(x,y);
	}
	
	public void rotateTowards(MainChar main){
		//Calculate angle between enemy and main character
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
		
		//Rotate enemy towards main character
		enemy.rotate(arctan - enemy.getRotation());
	}
}
