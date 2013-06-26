package states;

import main.Constants;
import main.Game;
import main.MenuButton;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class LevelSelectState extends BasicGameState {

	int stateID;
	Image helix1, helix2;
	Image grid;
	MenuButton[] levels;
	int buttondelay;
	//Initialize menu images, sound, etc.
	
	public LevelSelectState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		buttondelay = 500;
		helix1 = new Image("img\\Level Select\\Double Helix.png");
		helix2 = new Image("img\\Level Select\\Double Helix Flip.png");
		grid = new Image("img\\Level Select\\Character Grid.png");
		levels = new MenuButton[4];
		int helixwidth = helix1.getWidth();
		int helixheight = helix1.getHeight();
		int lvlwidth = (new Image("img\\Level Select\\Levels\\l0.png")).getWidth();
		int lvlheight = (new Image("img\\Level Select\\Levels\\l0.png")).getHeight();
		for(int i = 0; i < levels.length; i++){
			levels[i] = new MenuButton("img\\Level Select\\Levels\\l" + (i+1) + ".png", 
					Constants.LEVELPOSX + (lvlwidth+helixwidth)*i, 
					Constants.LEVELPOSY + (helixheight-lvlheight)*((i+1)%2), 
					Constants.SCALE);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input input = gc.getInput();
		
		grid.draw(Constants.CHARGRIDX, Constants.CHARGRIDY);
		
		//playButton.draw(playButton.x,playButton.y, playButton.mouseOver(input));
		for(int i = 0; i < levels.length-1; i++){
			if(i%2==1)
				helix1.draw(levels[i].x+levels[i].button.getWidth(),levels[i].y);
			else
				helix2.draw(levels[i].x+levels[i].button.getWidth(),levels[i].y-helix2.getHeight()/2);
			levels[i].draw(levels[i].x, levels[i].y, levels[i].mouseOver(input));
		}
		levels[levels.length-1].draw(levels[levels.length-1].x, levels[levels.length-1].y, levels[levels.length-1].mouseOver(input));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(buttondelay >= 0)
			buttondelay -= delta;
		Input input = gc.getInput();
		//if(buttondelay < 0 && playButton.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
		//	sbg.enterState(Game.LOADSTATE);
		for(int i = 0; i < levels.length; i++)
			if(buttondelay < 0 && levels[i].mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				Game.currentlevel = i+1;
				Game.load.levelID = i+1;
				Game.load.init(gc, sbg);
				Game.play.init(gc, sbg);
				sbg.enterState(Game.LOADSTATE);
			}
	}

	@Override
	public int getID() {
		return stateID;
	}

}