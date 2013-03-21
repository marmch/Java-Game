package states;

import main.Constants;
import main.Game;
import main.MenuButton;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class LevelWinState extends BasicGameState {

	int stateID;
	MenuButton menubutton;
	MenuButton continuebutton;
	final String cont = "img\\ContinueButton.png";
	final String menu = "img\\MenuButton.png";
	//Initialize menu images, sound, etc.
	
	public LevelWinState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		menubutton = new MenuButton(menu, 100, 100, Constants.SCALE);
		continuebutton = new MenuButton(cont, 100, 300, Constants.SCALE);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input input = gc.getInput();
		menubutton.draw(100,100, menubutton.mouseOver(input));
		continuebutton.draw(100,300, continuebutton.mouseOver(input));
		g.drawString("YOU WIN LEVEL " + Game.load.levelID, 400, 400);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		if(menubutton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			Game.load.levelID = 1;
			Game.currentlevel = 1;
			Game.load.init(gc, sbg);
			Game.play.init(gc, sbg);
			Game.lose.init(gc, sbg);
			sbg.enterState(Game.MENUSTATE);
		}
		else if(continuebutton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			Game.load.levelID++;
			Game.currentlevel = Game.load.levelID;
			Game.load.init(gc, sbg);
			Game.play.init(gc, sbg);
			Game.lose.init(gc, sbg);
			sbg.enterState(Game.LOADSTATE);
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
