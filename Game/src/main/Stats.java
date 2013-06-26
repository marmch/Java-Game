package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Stats {
	public static int mainhp = Constants.MAINHP;
	public static int level = 0;
	public static int mainfirerate = Constants.MAINBULLETDELAY;
	public static int points = 0;
	
	public static void readStats(String file){
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			level = Integer.parseInt(br.readLine());
			mainhp = Integer.parseInt(br.readLine());
			mainfirerate = Integer.parseInt(br.readLine());
			points = Integer.parseInt(br.readLine());
			br.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}