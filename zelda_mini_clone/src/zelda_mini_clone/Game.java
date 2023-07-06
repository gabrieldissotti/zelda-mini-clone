package zelda_mini_clone;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	public static int WIDTH = 480, HEIGHT = 480;

	public Game() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
		}
	}

}
