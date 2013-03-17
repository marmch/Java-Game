package main;
//v0.20
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.io.File;

public class Game extends StateBasedGame{
	
	final static int MENUSTATE = 0;
	final static int LOADSTATE = 1;
	final static int PLAYSTATE = 2;
	final static int PAUSESTATE = 3;
	final static int LOSESTATE = 4;
	final static int LEVELWINSTATE = 5;
	final static int WINSTATE = 6;
	final static int RES_X = 1200; //Level width
	final static int RES_Y = 768; //Level height
	public static LoadState load;
	public static PlayState play;
	public static MenuState menu;
	public static LoseState lose;
	public static LevelWinState levelwin;
	public static WinState win;
	
	public Game() throws SlickException {
		super("Game");
		load = new LoadState(LOADSTATE);
		play = new PlayState(PLAYSTATE);
		menu = new MenuState(MENUSTATE);
		lose = new LoseState(LOSESTATE);
		levelwin = new LevelWinState(LEVELWINSTATE);
		win = new WinState(WINSTATE);
		
		//Initialize states
		addState(menu);
		addState(load);
		addState(play);
		addState(lose);
		addState(win);
		addState(levelwin);
		
		play.setMap(RES_X, RES_Y);
		
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
		app.setDisplayMode(RES_X, RES_Y, false);
		app.start();
	}
	
}