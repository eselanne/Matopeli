import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500;
	private Thread thread;
	private boolean running;
	private boolean right = true, left = false, up = false, down = false;
	private Snakeblock s;
	private ArrayList<Snakeblock> snake;
	private int xCoord = 10, yCoord = 10, size = 5;
	private int ticks = 0;
	
	// kuvaa ohjelman tilaa
	private enum STATE{
		GAME,
		MENU
	};
	
	// ohjelman tila
	// private STATE State = STATE.MENU;
	
	public Game() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		snake = new ArrayList<Snakeblock> ();
		start();
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
		if(snake.size() ==0 ) {
			s = new Snakeblock(xCoord,yCoord,10);
			snake.add(s);
		}
		ticks++;
		if(ticks > 250000) {
			if (right) {
				xCoord++;
			}
			if(left) {
				xCoord--;
			}
			if (up) {
				yCoord--;
			}
			if (down) {
				yCoord++;
			}
			
			ticks = 0;
			
			s = new Snakeblock(xCoord, yCoord, 10);
			snake.add(s);
			
			if (snake.size() > size) {
				snake.remove(0);
			}
		}
		
		
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
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		
		
		
	}
	
	@Override 
	public void run() {
		while(running) {
			tick();
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		if (key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
			
		}
		if (key == KeyEvent.VK_UP && !down) {
			up = true;
			left = false;
			right = false;
			
		}
		if (key == KeyEvent.VK_DOWN && !up) {
			down = true;
			left = false;
			right = false;
		
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
