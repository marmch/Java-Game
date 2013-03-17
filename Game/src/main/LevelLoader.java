package main;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.xml.*;
import enemies.*;
import entities.Enemy;

public class LevelLoader {
	XMLElement base;
	ArrayList<Enemy> enemies;
	
	public LevelLoader(String levelfile) throws SlickException{
		//Start parsing
		XMLParser parser = new XMLParser();
		base = parser.parse(levelfile);
	}
	
	public void load() throws SlickException{
		enemies = new ArrayList<Enemy>();
		XMLElementList elementlist = base.getChildren();
		for(int i = 0; i < elementlist.size(); i++){
			if(elementlist.get(i).getName().equals("enemy")){
				//Load enemy data from XML
				String type = elementlist.get(i).getAttribute("type");
				String color = elementlist.get(i).getAttribute("color");
				int group = Integer.parseInt(elementlist.get(i).getAttribute("group"));
				SpawnConditions spawn = new SpawnConditions();
				ArrayList<String> conditions = new ArrayList<String>();
				
				XMLElementList atts = elementlist.get(i).getChildren();
				for(int j = 0; j < atts.size(); j++){
					if(atts.get(j).getName().equals("spawncondition")){
						if(atts.get(j).getAttribute("type").equals("empty")){
							conditions.add("empty");
						}
						if(atts.get(j).getAttribute("type").equals("timer")){
							conditions.add("timer");
							spawn.time = atts.get(j).getIntAttribute("time");
						}
						if(atts.get(j).getAttribute("type").equals("key")){
							conditions.add("key");
						}
					}
					else if(atts.get(j).getName().equals("spawnlocation")){
						spawn.spawntype = atts.get(j).getAttribute("type");
						spawn.x = (float) atts.get(j).getDoubleAttribute("x");
						spawn.y = (float) atts.get(j).getDoubleAttribute("y");
					}
				}
				
				spawn.conditions = conditions;
				
				//Create enemy type
				Enemy enemytemp = null;
				if(type.equals("blob1"))
					enemytemp = new Blob1(type,color,spawn,group);
				else if(type.equals("blob2"))
					enemytemp = new Blob2(type,color,spawn,group);
				else if(type.equals("blob3"))
					enemytemp = new Blob3(type,color,spawn,group);
				else if(type.equals("blob4"))
					enemytemp = new Blob4(type,color,spawn,group);
				
				//Add enemy to list
				enemies.add(enemytemp);
			}
		}
	}
	
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
}