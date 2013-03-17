package main;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import bullets.*;
import enemies.*;
import entities.*;

public class PlayState extends BasicGameState {

	int stateID;
	public MainChar main; //Main character
	Image maincharsprite; //Main character sprite
	Image spawncircle; //Spawning circle
	ArrayList<Bullet> bulletList; //Bullet array
	String bulletsprite; //Bullet sprite location
	ArrayList<Enemy> enemies; //Active enemy array
	ArrayList<Enemy> enemyspawn; //Enemies to be spawned
	int groupcounter; //Group counter
	int max_x, max_y; //Level width and height
	CollisionDetector collision;
	
	PlayState(int stateID) throws SlickException{
		this.stateID = stateID;
	}
	
	public void loadEnemies(ArrayList<Enemy> enemies){
		this.enemyspawn = enemies; //Load enemies
		this.enemies = new ArrayList<Enemy>();
	}
	
	public void setMap(int max_x, int max_y){
		this.max_x = max_x;
		this.max_y = max_y;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		maincharsprite = new Image("img\\mainchar.png");
		spawncircle = new Image("img\\spawncircle.png");
		bulletsprite ="img\\bbullet.png";
		main = new MainChar(maincharsprite,600,400); //Spawn main character
		bulletList = new ArrayList<Bullet>();
		collision = new CollisionDetector();
		groupcounter = 1;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		main.draw(); //Render main character
		
		//Render bullets
		for(Bullet bullet : bulletList)
			bullet.draw();
		
		//Render enemies
		for(Enemy enemy : enemies){
			if(enemy.spawntime > 0){
				Image tempcircle = spawncircle.getScaledCopy(enemy.spawntime/1000f);
				tempcircle.draw(enemy.x + enemy.enemy.getWidth()/2 - tempcircle.getWidth()/2,
						enemy.y + enemy.enemy.getHeight()/2 - tempcircle.getHeight()/2);
			}
			else
				enemy.draw();
		}
		
		if(main.hp <= 0)
			sbg.enterState(Game.LOSESTATE);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput(); //Get input
		
		main.move(input, delta, max_x, max_y); //Move main character
		for(int i = 0; i < enemyspawn.size(); i++){
			if(enemyspawn.get(i).group == groupcounter){
				if(enemyspawn.get(i).spawn.spawnConditionsMet(enemies, input, delta)){
					for(int j = 0; j < enemyspawn.size(); j++){
						if(enemyspawn.get(j).group == groupcounter){
							if(enemyspawn.get(j).spawn.spawntype.equals("relative")){
								enemyspawn.get(j).x += main.x;
								enemyspawn.get(j).y += main.y;
								if(enemyspawn.get(j).x > max_x-enemyspawn.get(j).enemy.getWidth())
									enemyspawn.get(j).x = max_x-enemyspawn.get(j).enemy.getWidth();
								else if(enemyspawn.get(j).x < 0)
									enemyspawn.get(j).x = 0;
								if(enemyspawn.get(j).y > max_y-enemyspawn.get(j).enemy.getWidth())
									enemyspawn.get(j).y = max_y-enemyspawn.get(j).enemy.getWidth();
								else if(enemyspawn.get(j).y < 0)
									enemyspawn.get(j).y = 0;
							}
							enemies.add(enemyspawn.get(j));
							enemyspawn.remove(j--);
						}
					}
					groupcounter++;
					break;
				}
			}
		}
		
		if(enemyspawn.size()==0 && enemies.size()==0)
			sbg.enterState(Game.WINSTATE);
			
		
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
		
		for(int i = 0; i < bulletList.size(); i++){
			if(collision.bulletMain(main, bulletList.get(i))){
				main.hp--;
				bulletList.remove(i--);
				continue;
			}
			for(int j = i+1; j < bulletList.size(); j++){
				if(((bulletList.get(i).friendly && bulletList.get(j).type.equals("homing")) || (bulletList.get(j).friendly && bulletList.get(i).type.equals("homing"))) && 
						collision.bulletBullet(bulletList.get(i), bulletList.get(j))){
					bulletList.remove(i--);
					bulletList.remove(--j);
					break;
				}
			}
		}
		
		//Move enemies
		for(int i = 0; i < enemies.size(); i++){
			if(enemies.get(i).spawntime > 0)
				enemies.get(i).spawntime -= delta;
			else{
				if(collision.enemyMain(enemies.get(i), main)){
					main.hp--;
					enemies.remove(i--);
					continue;
				}
				boolean loop = true;
				for(int j = 0; j < bulletList.size(); j++){
					if(collision.enemyBullet(enemies.get(i), bulletList.get(j))){
						enemies.remove(i--);
						bulletList.remove(j--);
						loop = false;
						break;
					}
				}
				if(!loop)
					continue;
				if(enemies.get(i).type.equals("blob1"))
					((Blob1)enemies.get(i)).move(main, delta);
				
				else if(enemies.get(i).type.equals("blob2")){
					((Blob2)enemies.get(i)).move(delta);
					if(((Blob2)enemies.get(i)).bulletdelta > 0)
						((Blob2)enemies.get(i)).bulletdelta-= delta;
					if(((Blob2)enemies.get(i)).bulletdelta <= 0){
						bulletList.add(((Blob2)enemies.get(i)).spawnBullet(main));
						((Blob2)enemies.get(i)).bulletdelta = ((Blob2)enemies.get(i)).BULLETDELAY;
					}
				}
				
				else if(enemies.get(i).type.equals("blob3")){
					((Blob3)enemies.get(i)).move(main, delta);
					if(((Blob3)enemies.get(i)).bulletdelta > 0)
						((Blob3)enemies.get(i)).bulletdelta-= delta;
					if(((Blob3)enemies.get(i)).bulletdelta <= 0){
						bulletList.add(((Blob3)enemies.get(i)).spawnBullet(main));
						((Blob3)enemies.get(i)).bulletdelta = ((Blob3)enemies.get(i)).BULLETDELAY;
					}
				}
				
				else if(enemies.get(i).type.equals("blob4")){
					((Blob4)enemies.get(i)).move(main, delta);
					if(((Blob4)enemies.get(i)).bulletdelta > 0)
						((Blob4)enemies.get(i)).bulletdelta-= delta;
					if(((Blob4)enemies.get(i)).bulletdelta <= 0){
						bulletList.add(((Blob4)enemies.get(i)).spawnBullet());
						((Blob4)enemies.get(i)).bulletdelta = ((Blob4)enemies.get(i)).BULLETDELAY;
					}
				}
			}
		}
		
	}
	
	@Override
	public int getID() {
		return stateID;
	}

}
