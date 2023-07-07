package zelda_mini_clone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
	
	public static int WIDTH = 640, HEIGHT = 480;
	public static int SCALE = 3;
	public static Player player;
	public World world;
	public List<Enemy> enemies = new ArrayList<Enemy>();

	public Game() {
		this.addKeyListener(this); // define class that have key listener method implementations
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		new Spritesheet();
		
		int blockSize = 32;
		
		world = new World(WIDTH, HEIGHT, blockSize);
		
		player = new Player(32,32);
		enemies.add(new Enemy(32,32));		
		enemies.add(new Enemy(64,64));		
	}
	
	public void tick() { // the tick responsibility is the game rules like movement, collisions and others 
		player.tick();
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
		}
	}
	
	public void render() { // where we render graphics
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) { // create a buffer strategy if it does not exists
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();

		
		g.setColor(new Color(0, 135, 13));
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE); // create the black background (if not, the screen will blink forever)
		
		player.render(g);
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).render(g);
		}
		world.render(g);
		
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		} else if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			player.shoot = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		} else if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}
	}

}
