package zelda_mini_clone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle {
	public int speed = 2;
	public int right = 1, up = 0, down = 0, left = 0;
	
	public int currentAnimation = 0;
	
	public int currentFrames = 0, targetFrames = 15;
	
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	
	public boolean shoot = false;
	
	public int currentDirection = 1; 
	
	public Enemy(int x, int y) {
		super(x, y, 32, 32); // player size and position
	}
	
	public void followPlayer() {
		Player player = Game.player;
		
		if(x < player.x && World.isFree(x + speed, y)) {
			if(new Random().nextInt(100) < 50) 
				x+=speed;
		} else if(x > player.x && World.isFree(x - speed, y)) {
			if(new Random().nextInt(100) < 70)
				x-=speed;
		}
		
		if(y < player.y && World.isFree(x, y + speed)) {
			if(new Random().nextInt(100) < 50)
				y+=speed;
		} else if(y > player.y && World.isFree(x, y - speed)) {
			if(new Random().nextInt(100) < 50)
				y-=speed;
		}
	}

	public void tick() { // player movement rules
		boolean moved = true;
		
		followPlayer();
		
		if(right == 1 && World.isFree(x + 1, y)) {
			x++;
		}
		
		if(moved) {
			currentFrames++;
			if(currentFrames == targetFrames) {
				currentFrames = 0;
				currentAnimation++;
				if(currentAnimation == Spritesheet.enemy_front.length) {
					currentAnimation = 0;
				}
			}
		}
		
		if(shoot) {
			shoot = false;
			bullets.add(new Bullet(x,y,currentDirection));
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}
	
	public void render(Graphics g) { // render player color in x and y position with 32px of size
		g.drawImage(Spritesheet.enemy_front[currentAnimation], x, y, 32, 32, null);
		

		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
}
