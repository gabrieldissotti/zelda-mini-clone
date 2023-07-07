package zelda_mini_clone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {
	public int speed = 4;
	public boolean right, up, down, left;
	
	public int currentAnimation = 0;
	
	public int currentFrames = 0, targetFrames = 15;
	
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	
	public boolean shoot = false;
	
	public int currentDirection = 1; 
	
	public Player(int x, int y) {
		super(x, y, 32, 32); // player size and position
	}

	public void tick() { // player movement rules
		boolean moved = false;
		
		if(right && World.isFree(x+speed, y)) {
			x+=speed;
			moved = true;
			currentDirection = 1;
		} else if(left && World.isFree(x-speed, y)) {
			x-=speed;
			moved = true;
			currentDirection = -1;
		}
		
		if(up && World.isFree(x, y-speed)) {
			y-=speed;
			moved = true;
		} else if(down && World.isFree(x, y+speed)) {
			y+=speed;
			moved = true;
		}
		
		if(moved) {
			currentFrames++;
			if(currentFrames == targetFrames) {
				currentFrames = 0;
				currentAnimation++;
				if(currentAnimation == Spritesheet.player_front.length) {
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
		g.drawImage(Spritesheet.player_front[currentAnimation], x, y, 32, 32, null);
		

		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
}
