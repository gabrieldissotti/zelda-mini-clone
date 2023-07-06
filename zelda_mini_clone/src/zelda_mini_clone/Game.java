package zelda_mini_clone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	public static int WIDTH = 480, HEIGHT = 480;

	public Game() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public void tick() { // the tick responsibility is the game rules like movement, collisions and others 
		
	}
	
	public void render() { // where we render graphics
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) { // create a buffer strategy if it does not exists
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();

		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT); // create the black background (if not, the screen will blink forever)
		
		
		g.setColor(Color.red);
		g.fillRect(10, 10, 100, 100); // create a rectangle in screen
		
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		frame.add(game); // add object Game to JFrame object
		frame.setTitle("Mini Zelda"); // define a window title
		
		frame.pack(); // apply the window sizes defined in setPreferredSize
		
		frame.setLocationRelativeTo(null); // show window in window centered
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // use default JFrame method on close (Maybe it's not necessary? a default that is not default?)
		
		frame.setVisible(true); // show the window
		
		new Thread(game).start(); // Start a new thread, .start() look for a run method in "game" and invoke  
	}
	
	@Override
	public void run() {
		while(true) {
			System.out.println("Chamando game looping!"); // infinite run until close window
			tick(); // run game rules
			render(); // render game
			
			try {
				Thread.sleep(1000/60); // render in 60 FPS
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
