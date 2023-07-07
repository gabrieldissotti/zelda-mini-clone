package zelda_mini_clone;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	public static BufferedImage spritesheet;

	public static BufferedImage[] player_front; 
	public static BufferedImage tile_wall; 
	
	public Spritesheet() {
		try {
			spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png")); // read sprite sheet file
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player_front = new BufferedImage[2];
		player_front[0] = Spritesheet.getSprite(0, 11, 16, 16);
		player_front[1] = Spritesheet.getSprite(16, 11, 16, 16);
		
		tile_wall = Spritesheet.getSprite(280, 221, 16, 16);  // define position of sprite in sprite sheet
	}
	
	public static BufferedImage getSprite(int x, int y, int width, int heigth) {
		return spritesheet.getSubimage(x,y, width, heigth); // get a part of image as a new image
	}
}
