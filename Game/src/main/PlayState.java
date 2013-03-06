package main;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import enemies.*;
import entities.*;

public class PlayState extends BasicGameState {

	int stateID;
	MainChar main;
	ArrayList<Bullet> bulletList;
	ArrayList<Enemy> enemies;
	final int BULLETDELAY = 400;
	int bulletdelta = 0;
	Image maincharsprite;
	Image bulletsprite;
	Image enemysprite;
	int max_x, max_y;
	
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
		bulletsprite = new Image("img\\greenbullet.png");
		enemysprite = new Image("img\\blob3blue.png");
		main = new MainChar(maincharsprite,0,0); //Spawn main character
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
		
		//Move all bullets and despawn old ones
		for(int i = 0; i < bulletList.size(); i++){
			bulletList.get(i).move(delta);
			if(bulletList.get(i).outOfMap(max_x, max_y)){
				bulletList.remove(i);
				i--;
			}
		}
		
		//Fire bullet after delay
		if(bulletdelta > 0)
			bulletdelta-= delta;
		if(main.shoot(input) && bulletdelta <= 0){
			spawnBullet();
			bulletdelta = BULLETDELAY;
		}
		
		main.rotate(input); //Rotate main character towards mouse
		
		//Move enemies
		for(Enemy enemy : enemies){
			if(enemy.getType().equals("blob1"))
				((Blob1)enemy).move(main, delta);
		}
		
	}

	public void spawnBullet(){
		bulletList.add(new Bullet(bulletsprite,main.x + main.charsprite.getWidth()/2,main.y + main.charsprite.getHeight()/2,
				1f,main.charsprite.getRotation()));
	}
	
	@Override
	public int getID() {
		return stateID;
	}

}