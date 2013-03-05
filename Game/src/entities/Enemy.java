package entities;

public class Enemy {
	String type;
	String color;
	public float x;
	public float y;
	protected int dcounter = 0;
	protected int deltanum = 0;
	protected int averagedelta = 0;
	
	public Enemy(String color, float x, float y){
		this.color = color;
		this.x = x;
		this.y = y;
	}

}
