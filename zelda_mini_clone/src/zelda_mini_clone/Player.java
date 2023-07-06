package zelda_mini_clone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {
	public int speed = 4;
	public boolean right, up, down, left;
	
	public Player(int x, int y) {
		super(x, y, 32, 32); // player size and position
	}

	public void tick() { // player movement rules
		if(right && World.isFree(x+speed, y)) {
			x+=speed;
		} else if(left && World.isFree(x-speed, y)) {
			x-=speed;
		}
		
		if(up && World.isFree(x, y-speed)) {
			y-=speed;
		} else if(down && World.isFree(x, y+speed)) {
			y+=speed;
		}
	}
	
	public void render(Graphics g) { // render player color in x and y position with 32px of size
		g.drawImage(Spritesheet.player_front, x, y, 32, 32, null);
	}
}
