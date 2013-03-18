package main;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MenuButton {
	
	public Image button;
	public Image bigbutton;
	public float scale;
	public int x,y;
	
	public MenuButton(String image, int x, int y, float scale) throws SlickException{
		button = new Image(image);
		this.x = x;
		this.y = y;
		this.scale = scale;
		bigbutton = button.getScaledCopy(scale);
	}
	
	public void resize(float scale){
		button = button.getScaledCopy(scale);
	}
	
	public boolean mouseOver(Input input){
		return input.getMouseX() > x && input.getMouseY() > y && input.getMouseX() < x+button.getWidth() && input.getMouseY() < y+button.getHeight();
	}
	
	public void draw(int x, int y, boolean big){
		if(big)
			bigbutton.draw(x-(bigbutton.getWidth()-button.getWidth())/2,y-(bigbutton.getHeight()-button.getHeight())/2);
		else
			button.draw(x,y);
	}
}
