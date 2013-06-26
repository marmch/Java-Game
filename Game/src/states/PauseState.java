package states;

import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import entities.Bullet;
import entities.Enemy;
import entities.MainChar;
import main.Game;

public class PauseState extends BasicGameState {

	int stateID;
	public int pausetimer = 500;
	public MainChar main;
	ArrayList<Enemy> enemies;
	ArrayList<Bullet> bulletList;
	Image spawncircle; //Spawning circle
	
	public PauseState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		spawncircle = new Image("img\\spawncircle.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		main.draw();
		
		for(Bullet bullet : bulletList)
			bullet.draw();
		
		for(Enemy enemy : enemies){
			if(enemy.spawntime > 0){
				Image tempcircle = spawncircle.getScaledCopy(enemy.spawntime/1000f);
				tempcircle.draw(enemy.x + enemy.enemy.getWidth()/2 - tempcircle.getWidth()/2,
						enemy.y + enemy.enemy.getHeight()/2 - tempcircle.getHeight()/2);
			}
			else
				enemy.draw();
		}
		
		g.drawString("HP: " + main.hp, 50, 50);
		g.drawString("GAME PAUSED", 200, 200);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_P) && pausetimer <= 0){
			Game.play.pausetimer=500;
			sbg.enterState(Game.PLAYSTATE);
		}
		else if(pausetimer > 0)
			pausetimer -= delta;
	}

	@Override
	public int getID() {
		return stateID;
	}

}