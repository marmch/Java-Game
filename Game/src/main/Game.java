package main;
//v0.35
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import states.*;

import java.io.File;

public class Game extends StateBasedGame{
	
	public final static int MENUSTATE = 0;
	public final static int LOADSTATE = 1;
	public final static int PLAYSTATE = 2;
	public final static int PAUSESTATE = 3;
	public final static int LOSESTATE = 4;
	public final static int LEVELWINSTATE = 5;
	public final static int WINSTATE = 6;
	public final static int LEVELSELECTSTATE = 7;
	public final static int LOADGAMESTATE = 8;
	public static int currentlevel = 3;
	public static LoadState load;
	public static LoadGameState loadgame;
	public static PlayState play;
	public static MenuState menu;
	public static LoseState lose;
	public static LevelWinState levelwin;
	public static WinState win;
	public static LevelSelectState level;
	
	public Game() throws SlickException {
		super("Game");
		load = new LoadState(LOADSTATE);
		play = new PlayState(PLAYSTATE);
		menu = new MenuState(MENUSTATE);
		lose = new LoseState(LOSESTATE);
		levelwin = new LevelWinState(LEVELWINSTATE);
		win = new WinState(WINSTATE);
		level = new LevelSelectState(LEVELSELECTSTATE);
		loadgame = new LoadGameState(LOADGAMESTATE);
		
		//Initialize states
		addState(menu);
		addState(load);
		addState(play);
		addState(lose);
		addState(win);
		addState(levelwin);
		addState(level);
		addState(loadgame);
		
		play.setMap(Constants.RES_X, Constants.RES_Y);
		
		//this.addState(new PauseState(PAUSESTATE));
		
		//Enter Menu State
		enterState(MENUSTATE);
		
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		
	}
	
	public static void main(String[] args) throws SlickException{
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		AppGameContainer app = new AppGameContainer(new Game());
		app.setDisplayMode(Constants.RES_X, Constants.RES_Y, false);
		app.start();
	}
	
}