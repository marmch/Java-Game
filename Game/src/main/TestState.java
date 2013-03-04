package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import entities.MainChar;

public class TestState extends BasicGameState {

	int stateID;
	MainChar main;
	Image maincharsprite;
	
	TestState(int stateID) throws SlickException{
		stateID = this.stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		maincharsprite = new Image("img\\mainchar.png");
		main = new MainChar(maincharsprite,0,0);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		main.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		main.move(input, delta, 800, 600);
	}

	@Override
	public int getID() {
		return stateID;
	}

}
