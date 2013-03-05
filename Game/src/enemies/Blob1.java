package enemies;

import org.newdawn.slick.SlickException;

import entities.*;

public class Blob1 extends Enemy {
	
	final int MAXDASH = 100;
	int dash = 0;
	
	public Blob1(String type, String color, int x, int y) throws SlickException {
		super(type, color, x, y);
	}
	
	public void move(MainChar main, int delta){
		//This seems to help with lag jumps
		dcounter++;
		if(dcounter/1000000 > 0){
			deltanum = 0;
			dcounter = 0;
		}
		if(deltanum < 10000){
			averagedelta *= deltanum;
			averagedelta += delta;
			deltanum++;
			averagedelta /= deltanum;
		}
		
		/*
		if(dash > 0)
			dash--;
		else{
			dash = MAXDASH;
		}
		*/
		super.rotateTowards(main);
	}

	/*
	 *  So how blob 1 is gonna work is
	 * It will dash at you a certain distance
	 * And that dash will be relatively fast
	 * Then it will stop for like 1 or 2 seconds
	 * And then dash at you again
	 * 
	 */
}
