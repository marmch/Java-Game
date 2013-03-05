package main;

import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import entities.Enemy;

public class LoadState extends BasicGameState {

	int stateID;
	LevelLoader level;
	ArrayList<Enemy> enemies;
	
	LoadState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		level = new LevelLoader("levels\\testlevel.xml"); //Load file into parser
		level.load(); //Load level contents
		enemies = level.getEnemies(); //Get enemies
		Game.play.loadEnemies(enemies); //Load enemies into play state
		sbg.enterState(Game.PLAYSTATE); //Switch to play state
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
