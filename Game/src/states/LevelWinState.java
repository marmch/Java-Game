package states;

import main.Game;
import main.MenuButton;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class LevelWinState extends BasicGameState {

	int stateID;
	MenuButton menuButton;
	final float SCALE = 1.2f;
	final String cont = "img\\ContinueButton.png";
	//Initialize menu images, sound, etc.
	
	public LevelWinState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		menuButton = new MenuButton(cont, 100, 100, SCALE);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input input = gc.getInput();
		menuButton.draw(100,100, menuButton.mouseOver(input));
		g.drawString("YOU WIN LEVEL " + Game.load.levelID, 400, 400);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		if(menuButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
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
