package states;

import main.Game;
import main.MenuButton;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class LevelSelectState extends BasicGameState {

	int stateID;
	MenuButton playButton;
	Image helix1, helix2;
	MenuButton[] levels;
	final float SCALE = 1.2f;
	int buttondelay;
	final String play = "img\\PlayButton.png";
	//Initialize menu images, sound, etc.
	
	public LevelSelectState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		buttondelay = 500;
		playButton = new MenuButton(play, 200, 200, SCALE);
		helix1 = new Image("img\\Level Select\\Double Helix.png");
		helix2 = new Image("img\\Level Select\\Double Helix Flip.png");
		levels = new MenuButton[4];
		for(int i = 0; i < levels.length; i++){
			levels[i] = new MenuButton("img\\Level Select\\Levels\\l" + (i+1) + ".png", 
					400 + 128*i, 200 + (helix1.getHeight()-(new Image("img\\Level Select\\Levels\\l1.png").getHeight()))*((i+1)%2), SCALE);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input input = gc.getInput();
		
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
