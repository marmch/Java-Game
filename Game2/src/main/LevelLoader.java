package main;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.xml.*;

public class LevelLoader {
	public LevelLoader() throws SlickException{
		XMLParser derp = new XMLParser();
		XMLElement testelement = derp.parse("testxml.xml");
		System.out.println(testelement.getName());
	}
}