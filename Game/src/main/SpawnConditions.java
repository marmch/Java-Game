package main;

import java.util.ArrayList;

import entities.Enemy;

public class SpawnConditions {
	public ArrayList<String> conditions;
	
	public SpawnConditions(){
		
	}
	
	public boolean spawnConditionsMet(ArrayList<Enemy> e){
		for(String condition : conditions){
			if(condition.equals("empty")){
				if(!empty(e))
					return false;
			}
		}
		return true;
	}
	
	public static boolean empty(ArrayList<Enemy> e){
		if(e.size()==0)
			return true;
		else
			return false;
	}
}
