package main;

import entities.*;

public class CollisionDetecter {
	
	public boolean enemyMain(Enemy enemy, MainChar main){
		if(enemy.x < main.x + main.charsprite.getWidth() && enemy.y < main.y + main.charsprite.getHeight() &&
				enemy.x + enemy.enemy.getWidth() > main.x && enemy.y + enemy.enemy.getHeight() > main.y)
			return true;
		return false;
	}
	
	public boolean enemyWall(Enemy enemy, int max_x, int max_y){
		if(enemy.x <= 0 || enemy.y <= 0 || enemy.x + enemy.enemy.getWidth() >= max_x || enemy.y + enemy.enemy.getHeight() >= max_y)
			return true;
		else
			return false;
	}
	
	public boolean mainBullet(MainChar main, Bullet bullet){
		if(bullet.x < main.x + main.charsprite.getWidth() && bullet.y < main.y + main.charsprite.getHeight() &&
				bullet.x + bullet.bullet.getWidth() > main.x && bullet.y + bullet.bullet.getHeight() > main.y)
			return true;
		return false;
	}
	
	public boolean enemyBullet(Enemy enemy, Bullet bullet){
		if(enemy.x < bullet.x + bullet.bullet.getWidth() && enemy.y < bullet.y + bullet.bullet.getHeight() &&
				enemy.x + enemy.enemy.getWidth() > bullet.x && enemy.y + enemy.enemy.getHeight() > bullet.y)
			return true;
		return false;
	}
}