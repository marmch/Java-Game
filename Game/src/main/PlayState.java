package main;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import bullets.*;
import enemies.*;
import entities.*;

public class PlayState extends BasicGameState {

	int stateID;
	MainChar main; //Main character
	Image maincharsprite; //Main character sprite
	ArrayList<Bullet> bulletList; //Bullet array
	String bulletsprite; //Bullet sprite location
	String hbulletsprite; //Homing bullet sprite location
	ArrayList<Enemy> enemies; //Enemy array
	Image enemysprite; //Enemy sprite
	int max_x, max_y; //Level width and height
	
	PlayState(int stateID) throws SlickException{
		this.stateID = stateID;
	}
	
	public void loadEnemies(ArrayList<Enemy> enemies){
		this.enemies = enemies; //Load enemies
	}
	
	public void setMap(int max_x, int max_y){
		this.max_x = max_x;
		this.max_y = max_y;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		maincharsprite = new Image("img\\blob3blue.png");
		bulletsprite ="img\\greenbullet.png";
		hbulletsprite = "img\\greenbullet.png";
		enemysprite = new Image("img\\blob3blue.png");
		main = new MainChar(maincharsprite,800,600); //Spawn main character
		bulletList = new ArrayList<Bullet>();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		main.draw(); //Render main character
		
		//Render bullets
		for(Bullet bullet : bulletList)
			bullet.draw();
		
		//Render enemies
		for(Enemy enemy : enemies){
			enemy.draw();
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput(); //Get input
		
		main.move(input, delta, max_x, max_y); //Move main character
		
		//Move bullets
		for(int i = 0; i < bulletList.size(); i++){
			if(bulletList.get(i).type.equals("normal"))
				((NormalBullet)bulletList.get(i)).move(delta);
			else if(bulletList.get(i).type.endsWith("homing"))
				((HomingBullet)bulletList.get(i)).move(main, delta);
			if(bulletList.get(i).outOfMap(max_x, max_y)){
				bulletList.remove(i);
				i--;
			}
		}
		
		//Main character bullet fire
		if(main.bulletdelta > 0)
			main.bulletdelta-= delta;
		if(main.shoot(input) && main.bulletdelta <= 0){
			bulletList.add(main.spawnBullet(bulletsprite));
			main.bulletdelta = main.BULLETDELAY;
		}
		
		main.rotate(input); //Rotate main character towards mouse
		
		//Move enemies
		for(Enemy enemy : enemies){
			
			if(enemy.type.equals("blob1"))
				((Blob1)enemy).move(main, delta);
			
			else if(enemy.type.equals("blob2")){
				((Blob2)enemy).move(delta);
				if(((Blob2)enemy).bulletdelta > 0)
					((Blob2)enemy).bulletdelta-= delta;
				if(((Blob2)enemy).bulletdelta <= 0){
					bulletList.add(((Blob2)enemy).spawnBullet(main, bulletsprite));
					((Blob2)enemy).bulletdelta = ((Blob2)enemy).BULLETDELAY;
				}
			}
			
			else if(enemy.type.equals("blob3")){
				((Blob3)enemy).move(main, delta);
				if(((Blob3)enemy).bulletdelta > 0)
					((Blob3)enemy).bulletdelta-= delta;
				if(((Blob3)enemy).bulletdelta <= 0){
					bulletList.add(((Blob3)enemy).spawnBullet(main, hbulletsprite));
					((Blob3)enemy).bulletdelta = ((Blob3)enemy).BULLETDELAY;
				}
			}
			
			else if(enemy.type.equals("blob4")){
				((Blob4)enemy).move(main, delta);
				if(((Blob4)enemy).bulletdelta > 0)
					((Blob4)enemy).bulletdelta-= delta;
				if(((Blob4)enemy).bulletdelta <= 0){
					bulletList.add(((Blob4)enemy).spawnBullet(bulletsprite));
					((Blob4)enemy).bulletdelta = ((Blob4)enemy).BULLETDELAY;
				}
			}
		}
		
	}
	
	@Override
	public int getID() {
		return stateID;
	}

}
