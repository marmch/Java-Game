package main;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import entities.Enemy;

public class SpawnConditions {
	public ArrayList<String> conditions;
	public int time;
	public float x,y;
	public String spawntype;
	
	public SpawnConditions(){	
	}
	
	public boolean spawnConditionsMet(ArrayList<Enemy> e, Input input, int delta){
		for(String condition : conditions){
			if(condition.equals("empty")){
				if(empty(e))
					return true;
			}
			else if(condition.equals("timer")){
				if(timer(delta))
					return true;
			}
			else if(condition.equals("key")){
				if(keyPressed(input))
					return true;
			}
		}
		return false;
	}
	
	public boolean istimer(){
		for(String s : conditions)
			if(s.equals("timer"))
				return true;
		return false;
	}
	
	public boolean empty(ArrayList<Enemy> e){
		if(e.size()==0)
			return true;
		else
			return false;
	}
	
	public boolean timer(int delta){
		time -= delta;
		if(time <= 0)
			return true;
		else
			return false;
	}
	
	public boolean keyPressed(Input input){
		if(input.isKeyDown(Input.KEY_ENTER))
			return true;
		else
			return false;
	}
}
