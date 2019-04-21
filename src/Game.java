import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500;
	private Thread thread;
	private boolean running;
	private Snakeblock s;
	private ArrayList<Snakeblock> snake;
	private int xCoord = 10, yCoord = 10, size = 5;
	
	// kuvaa ohjelman tilaa
	private enum STATE{
		GAME,
		MENU
	};
	
	// ohjelman tila
	// private STATE State = STATE.MENU;
	
	public Game() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void tick () {
		
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for(int i = 0; i < WIDTH/10; i++) {
			g.drawLine(i*10, 0, i*10, HEIGHT);
		}
		for(int i = 0; i < HEIGHT/10; i++) {
			g.drawLine(0, i*10,HEIGHT , i*10);
		}
		
		
		
	}
	
	@Override 
	public void run() {
		while(running) {
			tick();
			repaint();
		}
	}
	
	
}
