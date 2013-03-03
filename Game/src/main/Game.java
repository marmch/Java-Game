package main;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.*;

public class Game extends BasicGame{
	
	public Game(){
	super("Game");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException{
		/*
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_A)){
			plane.rotate(-0.2f * delta);
		}
		if(input.isKeyDown(Input.KEY_D)){
			plane.rotate(0.2f * delta);
		}
		if(input.isKeyDown(Input.KEY_W)){
			float hip = 0.4f * delta;
			float rotation = plane.getRotation();
			x+= hip * Math.sin(Math.toRadians(rotation));
			y-= hip * Math.cos(Math.toRadians(rotation));
		}

		if(input.isKeyDown(Input.KEY_2)){
			scale += (scale >= 5.0f) ? 0 : 0.1f;
			plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
		}
		if(input.isKeyDown(Input.KEY_1)){
			scale -= (scale <= 1.0f) ? 0 : 0.1f;
			plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
		}
		*/
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException{
		
	}

	public static void main(String[] args) throws SlickException{
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		AppGameContainer app = new AppGameContainer(new Game());
		app.setDisplayMode(800, 600, false);
		app.start();
	}
}