package states;

import main.Game;
import main.MenuButton;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class LoseState extends BasicGameState {

	int stateID;
	MenuButton menuButton;
	final float SCALE = 1.2f;
	final String menu = "img\\MenuButton.png";
	//Initialize menu images, sound, etc.
	
	public LoseState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		menuButton = new MenuButton(menu, 100, 100, SCALE);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input input = gc.getInput();
		menuButton.draw(100,100, menuButton.mouseOver(input));
		g.drawString("YOU LOSE", 400, 400);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		if(menuButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			Game.menu.init(gc, sbg);
			sbg.enterState(Game.MENUSTATE);
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}