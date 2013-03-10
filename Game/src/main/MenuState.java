package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MenuState extends BasicGameState {

	int stateID;
	MenuButton playButton;
	final float SCALE = 1.2f;
	int buttondelay;
	final String play = "img\\menuitem.png";
	//Initialize menu images, sound, etc.
	
	MenuState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		System.out.println("MENU");
		Game.load.init(gc, sbg);
		Game.play.init(gc, sbg);
		Game.lose.init(gc, sbg);
		buttondelay = 500;
		playButton = new MenuButton(play, 200, 200, SCALE);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input input = gc.getInput();
		playButton.draw(playButton.x,playButton.y, playButton.mouseOver(input));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(buttondelay >= 0)
			buttondelay -= delta;
		Input input = gc.getInput();
		if(buttondelay < 0 && playButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			sbg.enterState(Game.LOADSTATE);
	}

	@Override
	public int getID() {
		return stateID;
	}

}
