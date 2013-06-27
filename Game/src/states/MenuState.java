package states;

import main.Constants;
import main.Game;
import main.MenuButton;
import main.Stats;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MenuState extends BasicGameState {

	int stateID;
	MenuButton playButton;
	MenuButton optionsButton;
	MenuButton controlsButton;
	Image[] controlsScreen;
	Image blob;
	Image title;
	boolean controlsOn;
	//MenuButton loadButton;
	int controlsdelay;
	int controlsslide;
	int buttondelay;
	double blobx;
	final String level = "img\\Play.png";
	final String options = "img\\Options.png";
	final String controls = "img\\Controls.png";
	final String s1save = "save\\s1.txt";
	//final String load = "img\\Load.png";
	//Initialize menu images, sound, etc.
	
	public MenuState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Game.lose.init(gc, sbg);
		Game.win.init(gc, sbg);
		Game.levelwin.init(gc, sbg);
		controlsOn = false;
		Stats.readStats(s1save);
		buttondelay = 500;
		controlsdelay = 500;
		controlsslide = 0;
		title = new Image("img\\Title.png");
		blob = new Image("img\\mainchar.png");
		blob.rotate(90);
		blobx = 0;
		playButton = new MenuButton(level, 230, 340, Constants.SCALE);
		playButton.button = playButton.button.getScaledCopy(1.5f);
		playButton.bigbutton = playButton.bigbutton.getScaledCopy(1.5f);
		optionsButton = new MenuButton(options,320,520,Constants.SCALE);
		controlsButton = new MenuButton(controls,320,660,Constants.SCALE);
		controlsScreen = new Image[16];
		for(int i = 0; i < 16; i++)
			controlsScreen[i] = new Image("img\\Controls\\Controls " + (i+1) + ".png");
		//loadButton = new MenuButton(load, 200, 400, Constants.SCALE);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input input = gc.getInput();
		title.draw(70,10);
		if(controlsOn){
			playButton.draw(playButton.x,playButton.y, false);
			optionsButton.draw(optionsButton.x,optionsButton.y, false);
			controlsButton.draw(controlsButton.x,controlsButton.y, false);
			controlsScreen[controlsslide].draw(140,100);
		}
		else{
			playButton.draw(playButton.x,playButton.y, playButton.mouseOver(input));
			optionsButton.draw(optionsButton.x,optionsButton.y, optionsButton.mouseOver(input));
			controlsButton.draw(controlsButton.x,controlsButton.y, controlsButton.mouseOver(input));
		}
		blob.draw((int)blobx,300);
		//loadButton.draw(loadButton.x,loadButton.y, loadButton.mouseOver(input));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(buttondelay >= 0)
			buttondelay -= delta;
		
		if(controlsOn){
			if(input.isKeyPressed(Input.KEY_ENTER))
				controlsOn = false;
			if(controlsdelay >= 0)
				controlsdelay -= delta;
			else{
				controlsdelay = 500;
				controlsslide++;
				if(controlsslide==16)
					controlsslide = 0;
			}
		}
		else{
			blobx += (double)delta * 0.3;
			if(blobx >= Constants.RES_X)
				blobx = 0;
			if(buttondelay < 0 && playButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				controlsOn = false;
				sbg.enterState(Game.LEVELSELECTSTATE);
			}
			else if(buttondelay < 0 && optionsButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				controlsOn = false;
			else if(buttondelay < 0 && controlsButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				controlsOn = true;
			//else if(buttondelay < 0 && loadButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			//	sbg.enterState(Game.LOADGAMESTATE);
		}
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
