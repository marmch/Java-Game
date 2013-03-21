package states;

import main.Constants;
import main.Game;
import main.MenuButton;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class LoseState extends BasicGameState {

	int stateID;
	MenuButton menuButton;
	MenuButton retryButton;
	final String menu = "img\\MenuButton.png";
	final String retry = "img\\Retry.png";
	//Initialize menu images, sound, etc.
	
	public LoseState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		menuButton = new MenuButton(menu, 100, 100, Constants.SCALE);
		retryButton = new MenuButton(retry, 100, 300, Constants.SCALE);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input input = gc.getInput();
		menuButton.draw(100,100, menuButton.mouseOver(input));
		retryButton.draw(100,300, retryButton.mouseOver(input));
		g.drawString("YOU LOSE", 400, 400);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		if(menuButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			Game.currentlevel = 1;
			Game.menu.init(gc, sbg);
			sbg.enterState(Game.MENUSTATE);
		}
		else if(retryButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			Game.load.init(gc, sbg);
			Game.play.init(gc, sbg);
			Game.menu.init(gc, sbg);
			Game.win.init(gc, sbg);
			sbg.enterState(Game.LOADSTATE);
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
