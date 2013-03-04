package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MenuState extends BasicGameState {

	int stateID;
	//Initialize menu images, sound, etc.
	
	MenuState(int stateID){
		stateID = this.stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//Load images
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//Draw images
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		//Implement update code
	}

	@Override
	public int getID() {
		return stateID;
	}

}
