package states;

import main.Constants;
import main.Game;
import main.MenuButton;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MenuState extends BasicGameState {

	int stateID;
	MenuButton levelButton;
	MenuButton loadButton;
	int buttondelay;
	final String level = "img\\LevelSelect.png";
	final String load = "img\\Load.png";
	//Initialize menu images, sound, etc.
	
	public MenuState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Game.lose.init(gc, sbg);
		Game.win.init(gc, sbg);
		Game.levelwin.init(gc, sbg);
		buttondelay = 500;
		levelButton = new MenuButton(level, 200, 200, Constants.SCALE);
		loadButton = new MenuButton(load, 200, 400, Constants.SCALE);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input input = gc.getInput();
		levelButton.draw(levelButton.x,levelButton.y, levelButton.mouseOver(input));
		loadButton.draw(loadButton.x,loadButton.y, loadButton.mouseOver(input));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(buttondelay >= 0)
			buttondelay -= delta;
		Input input = gc.getInput();
		if(buttondelay < 0 && levelButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			sbg.enterState(Game.LEVELSELECTSTATE);
		else if(buttondelay < 0 && loadButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			sbg.enterState(Game.LOADGAMESTATE);
	}

	@Override
	public int getID() {
		return stateID;
	}

}
