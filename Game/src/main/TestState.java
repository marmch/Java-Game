package main;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import entities.*;

public class TestState extends BasicGameState {

	int stateID;
	MainChar main;
	ArrayList<Bullet> bulletList;
	final int BULLETDELAY = 400;
	int bulletdelta = 0;
	Image maincharsprite;
	Image bullet;
	
	TestState(int stateID) throws SlickException{
		stateID = this.stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		maincharsprite = new Image("img\\blob3blue.png");
		bullet = new Image("img\\greenbullet.png");
		main = new MainChar(maincharsprite,0,0);
		bulletList = new ArrayList<Bullet>();
		//LevelLoader test = new LevelLoader();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		main.draw(); //Render main character
		//Render all bullets
		for(Bullet b : bulletList)
			b.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput(); //Get input
		main.move(input, delta, 800, 600); //Move main character
		//Move all bullets and despawn old ones
		for(int i = 0; i < bulletList.size(); i++){
			bulletList.get(i).move(delta);
			if(bulletList.get(i).outOfMap(800, 600)){
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
	}

	public void spawnBullet(){
		bulletList.add(new Bullet(bullet,main.x + main.charsprite.getWidth()/2,main.y + main.charsprite.getHeight()/2,1f,main.charsprite.getRotation()));
	}
	
	@Override
	public int getID() {
		return stateID;
	}

}
