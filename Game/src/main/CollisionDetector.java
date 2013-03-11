package main;

import entities.*;

public class CollisionDetector {
	
	public int max_x = Game.RES_X;
	public int max_y = Game.RES_Y;
	
	public boolean enemyMain(Enemy enemy, MainChar main){
		if(enemy.x < main.x + main.charsprite.getWidth() && enemy.y < main.y + main.charsprite.getHeight() &&
				enemy.x + enemy.enemy.getWidth() > main.x && enemy.y + enemy.enemy.getHeight() > main.y)
			return true;
		return false;
	}
	
	public boolean enemyWall(Enemy enemy){
		
		if(enemy.x <= 0 || enemy.y <= 0 || enemy.x + enemy.enemy.getWidth() >= max_x || enemy.y + enemy.enemy.getHeight() >= max_y)
			return true;
		else
			return false;
	}
	
	public boolean bulletMain(MainChar main, Bullet bullet){
		if(!bullet.friendly && bullet.x < main.x + main.charsprite.getWidth() && bullet.y < main.y + main.charsprite.getHeight() &&
				bullet.x + bullet.bullet.getWidth() > main.x && bullet.y + bullet.bullet.getHeight() > main.y)
			return true;
		return false;
	}
	
	public boolean enemyBullet(Enemy enemy, Bullet bullet){
		if(bullet.friendly && enemy.x < bullet.x + bullet.bullet.getWidth() && enemy.y < bullet.y + bullet.bullet.getHeight() &&
				enemy.x + enemy.enemy.getWidth() > bullet.x && enemy.y + enemy.enemy.getHeight() > bullet.y)
			return true;
		return false;
	}
	
	public boolean bulletBullet(Bullet bullet1, Bullet bullet2){
		if(bullet2.x < bullet1.x + bullet1.bullet.getWidth() && bullet2.y < bullet1.y + bullet1.bullet.getHeight() &&
				bullet2.x + bullet2.bullet.getWidth() > bullet1.x && bullet2.y + bullet2.bullet.getHeight() > bullet1.y)
			return true;
		return false;
	}
}