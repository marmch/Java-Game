package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class StateTemplate extends BasicGameState {

	int stateID;
	
	StateTemplate(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
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
