package states;

import main.Constants;
import main.Game;
import main.MenuButton;
import main.Stats;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class LevelSelectState extends BasicGameState {

	int stateID;
	Image helix1, helix2;
	Image grid;
	Image title;
	Image mainchar;
	MenuButton mainmenu;
	MenuButton options;
	MenuButton credits;
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
		grid = new Image("img\\Level Select\\Character Grid.png").getScaledCopy(1.2f);
		mainchar = new Image("img\\mainchar.png").getScaledCopy(2f);
		title = new Image("img\\Level Select Title.png").getScaledCopy(1.175f);
		mainmenu = new MenuButton("img\\Level Select\\Buttons\\Main Menu Button.png", Constants.CHARGRIDX+55, 600, Constants.SCALE);
		options = new MenuButton("img\\Level Select\\Buttons\\Options Button.png", Constants.CHARGRIDX+55, 650, Constants.SCALE);
		credits = new MenuButton("img\\Level Select\\Buttons\\Credits Button.png", Constants.CHARGRIDX+55, 700, Constants.SCALE);
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
		title.draw(0,0);
		grid.draw(Constants.CHARGRIDX, Constants.CHARGRIDY);
		mainmenu.draw(mainmenu.x, mainmenu.y, mainmenu.mouseOver(input));
		options.draw(options.x, options.y, options.mouseOver(input));
		credits.draw(credits.x, credits.y, credits.mouseOver(input));
		
		//playButton.draw(playButton.x,playButton.y, playButton.mouseOver(input));
		for(int i = 0; i < levels.length-1; i++){
			if(i%2==1)
				helix1.draw(levels[i].x+levels[i].button.getWidth(),levels[i].y);
			else
				helix2.draw(levels[i].x+levels[i].button.getWidth(),levels[i].y-helix2.getHeight()/2);
			levels[i].draw(levels[i].x, levels[i].y, levels[i].mouseOver(input));
		}
		levels[levels.length-1].draw(levels[levels.length-1].x, levels[levels.length-1].y, levels[levels.length-1].mouseOver(input));
		
		mainchar.draw(Constants.CHARGRIDX+50,Constants.CHARGRIDY+80);
		
		g.setColor(Color.black);
		
		g.drawString("Name", Constants.CHARGRIDX+180, Constants.CHARGRIDY+43);
		
		g.drawString("HP:" + Stats.mainhp, Constants.CHARGRIDX+180, Constants.CHARGRIDY+90);
		
		g.drawString("Fire", Constants.CHARGRIDX+180, Constants.CHARGRIDY+115);
		g.drawString("Rate:" + 1000/Stats.mainfirerate, Constants.CHARGRIDX+180, Constants.CHARGRIDY+130);
		
		g.drawString("Damage:_", Constants.CHARGRIDX+180, Constants.CHARGRIDY+155);
		
		g.drawString("Speed:_", Constants.CHARGRIDX+180, Constants.CHARGRIDY+180);
		
		g.drawString("Points:_____", Constants.CHARGRIDX+70, Constants.CHARGRIDY+258);
		
		g.drawString("A", Constants.CHARGRIDX+67, Constants.CHARGRIDY+318);
		
		g.drawString("B", Constants.CHARGRIDX+148, Constants.CHARGRIDY+318);
		
		g.drawString("C", Constants.CHARGRIDX+230, Constants.CHARGRIDY+318);
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
				Game.play.main.bulletdelta=500;
				sbg.enterState(Game.LOADSTATE);
			}
		
		if(mainmenu.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			sbg.enterState(Game.MENUSTATE);
		else if(credits.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			;
		else if(options.mouseOver(input) && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			;
	}

	@Override
	public int getID() {
		return stateID;
	}

}