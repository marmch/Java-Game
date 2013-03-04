package main;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.io.File;

public class Game extends StateBasedGame{
	
	//final int MENUSTATE = 0;
	final int LOADSTATE = 1;
	final int PLAYSTATE = 2;
	final int PAUSESTATE = 3;
	final int TESTSTATE = 0;
	
	public Game() throws SlickException {
		super("Game");
		
		//Initialize states
		/*
		this.addState(new MenuState(MENUSTATE));
		this.addState(new LoadState(LOADSTATE));
		this.addState(new PlayState(PLAYSTATE));
		this.addState(new PauseState(PAUSESTATE));
		*/
		this.addState(new TestState(TESTSTATE));
		
		this.enterState(TESTSTATE);
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		//Assign numbers to states
		/*
		this.getState(MENUSTATE).init(gc, this);
		this.getState(LOADSTATE).init(gc, this);
		this.getState(PLAYSTATE).init(gc, this);
		this.getState(PAUSESTATE).init(gc, this);
		*/
		
		this.getState(TESTSTATE).init(gc, this);
	}
	
	public static void main(String[] args) throws SlickException{
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		AppGameContainer app = new AppGameContainer(new Game());
		app.setDisplayMode(800, 600, false);
		app.start();
	}
	
}