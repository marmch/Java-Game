package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import entities.*;

public class TestState extends BasicGameState {

	int stateID;
	MainChar main;
	Bullet b;
	boolean bulletexists = false;
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
		b = new Bullet(bullet,main.x,main.y,0f,main.charsprite.getRotation());
		//LevelLoader test = new LevelLoader();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(bulletexists)
			b.draw();
		main.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		if(bulletexists)
			b.move(delta);
		main.move(input, delta, 800, 600);
		if(main.shoot(input)){
			bulletexists = true;
			spawnBullet();
		}
		main.rotate(input);
	}

	public void spawnBullet(){
		b = new Bullet(bullet,main.x + main.charsprite.getWidth()/2,main.y + main.charsprite.getHeight()/2,1f,main.charsprite.getRotation());
	}
	@Override
	public int getID() {
		return stateID;
	}

}
