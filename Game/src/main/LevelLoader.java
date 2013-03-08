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
		XMLParser parser = new XMLParser();
		base = parser.parse(levelfile);
	}
	
	public void load() throws SlickException{
		enemies = new ArrayList<Enemy>();
		//System.out.println(base.getName());
		XMLElementList elementlist = base.getChildren();
		for(int i = 0; i < elementlist.size(); i++){
			if(elementlist.get(i).getName().equals("enemy")){
				//Load enemy data from XML
				String type = elementlist.get(i).getAttribute("type");
				String color = elementlist.get(i).getAttribute("color");
				int x = Integer.parseInt(elementlist.get(i).getAttribute("x"));
				int y = Integer.parseInt(elementlist.get(i).getAttribute("y"));
				
				//Create enemy type
				Enemy enemytemp = null;
				if(type.equals("blob1"))
					enemytemp = new Blob1(type,color,x,y);
				else if(type.equals("blob2"))
					enemytemp = new Blob2(type,color,x,y);
				else if(type.equals("blob3"))
					enemytemp = new Blob3(type,color,x,y);
				else if(type.equals("blob4"))
					enemytemp = new Blob4(type,color,x,y);
				
				//Add enemy to list
				enemies.add(enemytemp);
			}
		}
	}
	
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
}