package main;

import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import entities.Enemy;

public class LoadState extends BasicGameState {

	int stateID;
	public int levelID = 3;
	LevelLoader level;
	ArrayList<Enemy> enemies;
	
	LoadState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//Load file into parser
		switch(levelID){
		case 1: level = new LevelLoader("levels\\level1.xml"); 
			break;
		case 2: level = new LevelLoader("levels\\level2.xml");
			break;
		case 3: level = new LevelLoader("levels\\level3.xml");
			break;
		default: level = new LevelLoader("levels\\testlevel.xml"); 
			break;
		}
		
		level.load(); //Load level contents
		enemies = level.getEnemies(); //Get enemies
		Game.play.loadEnemies(enemies); //Load enemies into play state
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("LOADING", 100, 100); //Temporary loading string
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		sbg.enterState(Game.PLAYSTATE); //Switch to play state
	}

	@Override
	public int getID() {
		return stateID;
	}

}
