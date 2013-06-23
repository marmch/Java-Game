package states;

import main.Constants;
import main.Game;
import main.MenuButton;
import main.Stats;

import java.io.File;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class LoadGameState extends BasicGameState {

	int stateID;
	int buttondelay;
	MenuButton s1Button;
	MenuButton s2Button;
	MenuButton s3Button;
	MenuButton mainmenuButton;
	final String s1 = "img\\s1.png";
	final String s2 = "img\\s2.png";
	final String s3 = "img\\s3.png";
	final String mainmenu = "img\\mainmenu.png";
	final String s1save = "save\\s1.txt";
	final String s2save = "save\\s2.txt";
	final String s3save = "save\\s3.txt";
	//Initialize menu images, sound, etc.
	
	public LoadGameState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		buttondelay = 500;
		s1Button = new MenuButton(s1, 200, 200, Constants.SCALE);
		s2Button = new MenuButton(s2, 200, 400, Constants.SCALE);
		s3Button = new MenuButton(s3, 200, 600, Constants.SCALE);
		mainmenuButton = new MenuButton(mainmenu, 400, 400, Constants.SCALE);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input input = gc.getInput();
		s1Button.draw(s1Button.x,s1Button.y, s1Button.mouseOver(input));
		s2Button.draw(s2Button.x,s2Button.y, s2Button.mouseOver(input));
		s3Button.draw(s3Button.x,s3Button.y, s3Button.mouseOver(input));
		mainmenuButton.draw(mainmenuButton.x,mainmenuButton.y, mainmenuButton.mouseOver(input));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(buttondelay >= 0)
			buttondelay -= delta;
		Input input = gc.getInput();
		if(buttondelay < 0 && s1Button.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			Stats.readStats(s1save);
		else if(buttondelay < 0 && s2Button.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			Stats.readStats(s2save);
		else if(buttondelay < 0 && s3Button.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			Stats.readStats(s3save);
		else if(buttondelay < 0 && mainmenuButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			sbg.enterState(Game.MENUSTATE);
	}

	@Override
	public int getID() {
		return stateID;
	}

}
