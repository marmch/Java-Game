package entities;

import main.SpawnConditions;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy {
	public String color; //Enemy color
	public String type; //Enemy type
	public Image enemy; //Enemy sprite
	public float rotation = 0; //Enemy rotation
	public float x,y; //Coordinates
	public SpawnConditions spawn;
	public int group;
	public int spawntime;
	public int hp;
	
	public Enemy(String type, String color, SpawnConditions spawn, int group) throws SlickException{
		hp = 12;
		this.type = type;
		this.color = color;
		x = spawn.x;
		y = spawn.y;
		this.spawn = spawn;
		this.group = group;
		spawntime = 1000;
		
		//Set enemy sprite
		if(type.equals("blob1")){
			if(color.equals("red"))
				enemy = new Image("img\\blob1red.png");
			else if(color.equals("blue"))
				enemy = new Image("img\\blob1blue.png");
			else if(color.equals("green"))
				enemy = new Image("img\\blob1green.png");
			else
				enemy = new Image("img\\blob1.png");
		}
		else if(type.equals("blob2")){
			if(color.equals("red"))
				enemy = new Image("img\\blob2red.png");
			else if(color.equals("blue"))
				enemy = new Image("img\\blob2blue.png");
			else if(color.equals("green"))
				enemy = new Image("img\\blob2green.png");
			else
				enemy = new Image("img\\blob2.png");
		}
		else if(type.equals("blob3")){
			if(color.equals("red"))
				enemy = new Image("img\\blob3red.png");
			else if(color.equals("blue"))
				enemy = new Image("img\\blob3blue.png");
			else if(color.equals("green"))
				enemy = new Image("img\\blob3green.png");
			else
				enemy = new Image("img\\blob3.png");
		}
		else if(type.equals("blob4")){
			if(color.equals("red"))
				enemy = new Image("img\\blob4red.png");
			else if(color.equals("blue"))
				enemy = new Image("img\\blob4blue.png");
			else if(color.equals("green"))
				enemy = new Image("img\\blob4green.png");
			else
				enemy = new Image("img\\blob4.png");
		}
		
	}
	
	public void draw(){
		enemy.draw(x,y);
	}
	
	public void rotateTowards(MainChar main){
		//Calculate angle between enemy and main character
		float dx = x + enemy.getWidth()/2 - main.x - main.charsprite.getWidth()/2;
		float dy = y + enemy.getHeight()/2 - main.y - main.charsprite.getHeight()/2;
		float arctan;
		if(dy > 0 && dx >= 0 || dy < 0 && dx >= 0)
			arctan = (float)Math.toDegrees(Math.atan(dy/dx)) + 180;
		else if(dy < 0 && dx < 0 || dy > 0 && dx < 0)
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
