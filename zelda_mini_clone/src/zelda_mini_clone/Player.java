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
		if(right) {
			x+=speed;
		} else if(left) {
			x-=speed;
		}
		
		if(up) {
			y-=speed;
		} else if(down) {
			y+=speed;
		}
	}
	
	public void render(Graphics g) { // render player color in x and y position with 32px of size
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
}
