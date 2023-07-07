package zelda_mini_clone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle {
	public Block(int x, int y) {
		super(x, y, 32, 32);
	}

	public void render(Graphics g) {
		g.drawImage(Spritesheet.tile_wall, x, y, 32, 32, null);
	}
}
