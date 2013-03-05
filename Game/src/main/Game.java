package main;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.io.File;

public class Game extends StateBasedGame{
	
	final static int MENUSTATE = 0;
	final static int LOADSTATE = 1;
	final static int PLAYSTATE = 2;
	final static int PAUSESTATE = 3;
	public static LoadState load;
	public static PlayState play;
	
	public Game() throws SlickException {
		super("Game");
		load = new LoadState(LOADSTATE);
		play = new PlayState(PLAYSTATE);
		//Initialize states
		//this.addState(new MenuState(MENUSTATE));
		addState(load);
		addState(play);
		//this.addState(new PauseState(PAUSESTATE));
		
		//Enter Load State
		this.enterState(LOADSTATE);
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		
	}
	
	public static void main(String[] args) throws SlickException{
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		AppGameContainer app = new AppGameContainer(new Game());
		app.setDisplayMode(800, 600, false);
		app.start();
	}
	
}