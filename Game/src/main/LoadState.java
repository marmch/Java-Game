package main;

import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import entities.Enemy;

public class LoadState extends BasicGameState {

	int stateID;
	int levelID = 0;
	LevelLoader level;
	ArrayList<Enemy> enemies;
	
	LoadState(int stateID){
		this.stateID = stateID;
	}
	
	public void setLevel(int ID){
		levelID = ID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		switch(levelID){
			default: level = new LevelLoader("levels\\testlevel.xml"); //Load file into parser
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
