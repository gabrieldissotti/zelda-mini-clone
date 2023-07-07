package zelda_mini_clone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {
	public static List<Block> blocks = new ArrayList<Block>();
	
	public World(int screenWidth, int screenHeight, int blockSize) {
		int screenColumns = screenWidth / blockSize;
		int screenRows = screenHeight / blockSize;
		
		
		for(int i = 0; i < screenColumns; i++) { // 15 blocks are the window size divided by object size (480/32)
			blocks.add(new Block(i*blockSize,0));
		}
		
		for(int i = 0; i < screenColumns; i++) {
			blocks.add(new Block(i*blockSize,screenHeight-blockSize)); // 480-32 to add blocks at bottom
		}
		
		for(int i = 0; i < screenRows; i++) { // add blocks on vertical at left
			blocks.add(new Block(0,i*blockSize));
		}
		
		for(int i = 0; i < screenRows; i++) { // add blocks on vertical at right
			blocks.add(new Block(screenWidth-blockSize,i*blockSize));
		}
	}
	
	public static boolean isFree(int x, int y) {
		for(int i = 0; i < blocks.size(); i++) {
			Block currentBlock = blocks.get(i);
			if(currentBlock.intersects(new Rectangle(x, y, 32, 32))) {
				return false;
			}
		}
		
		return true;
	}
	
	public void render(Graphics g) { // render all blocks in world
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).render(g);
		}
	}
}
