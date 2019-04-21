import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500;
	private Thread thread;
	private boolean running;
	private boolean right = true, left = false, up = false, down = false;
	private Snakeblock s;
	public static ArrayList<Snakeblock> snake;
	private Apple apple;
	private ArrayList<Apple> apples;
	private Random r;
	private int xCoord = 10, yCoord = 10, size = 5;
	private int ticks = 0;
	public static int score = 0;
	
	private MainMenu menu;
	
	// kuvaa ohjelman tilaa
	private enum STATE{
		GAME, MENU, GAMEOVER, OHJEET
	};
	
	// ohjelman tila
	private STATE State = STATE.MENU;
	
	public Game() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		addMouseListener(this);
		snake = new ArrayList<Snakeblock> ();
		apples = new ArrayList<Apple>();
		r = new Random();
		menu = new MainMenu();
		start();
	}
	
	public void restart() {
		xCoord = 10;
		yCoord = 10;
		size = 5;
		score = 0;
		
		this.right = true;
		this.left = false;
		this.up = false;
		this.down = false;
		
		
		snake = new ArrayList<Snakeblock>();
		apples = new ArrayList<Apple>();
		
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
		if(State == STATE.GAME) {
			if(snake.size() ==0 ) {
				s = new Snakeblock(xCoord,yCoord,10);
				snake.add(s);
			}
			ticks++;
			if(ticks > 500000) {
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
			if (apples.size() ==0) {
				int xCoord =r.nextInt(49);
				int yCoord =r.nextInt(49);
				
				apple = new Apple(xCoord, yCoord,10);
				apples.add(apple);
				
			}
			for (int i = 0; i < apples.size();i++) {
				if (xCoord == apples.get(i).getxCoord() && yCoord ==  apples.get(i).getyCoord() ) {
					size++;
					apples.remove(i);
					i++;
					score++;
				}
			}
			//Törmäys matoon
			for(int i = 0; i < snake.size(); i++) {
				if(xCoord == snake.get(i).getxCoord() && yCoord == snake.get(i).getyCoord()) {
					if (i != snake.size()-1) {
						State = STATE.GAMEOVER;
						//stop();
					}
				}
			}
			//Törmäys seinään
			if(xCoord < 0 || xCoord > 49 || yCoord < 0 || yCoord > 49) {
				State = STATE.GAMEOVER;
				//stop();
			}
			
		}		
	}
	
	public static int getScore() {
		return score;
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(State == STATE.GAME) {
		
			for(int i = 0; i < WIDTH/10; i++) {
				g.drawLine(i*10, 0, i*10, HEIGHT);
			}
			for(int i = 0; i < HEIGHT/10; i++) {
				g.drawLine(0, i*10,HEIGHT , i*10);
			}
			for (int i = 0; i < snake.size(); i++) {
				snake.get(i).draw(g);
			}
			for(int i = 0; i < apples.size(); i++) {
				apples.get(i).draw(g);
			}
		}else if(State == STATE.MENU) {
			menu.render(g);
		}else if(State == STATE.GAMEOVER) {
			menu.renderGameOver(g);
		}else if (State == STATE.OHJEET) {
			menu.renderOhjeet(g);
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
		
		if(State == STATE.GAME) {
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
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		// pelaa-nappi
		if(mx >= Game.WIDTH/4 + 70 && mx <= Game.WIDTH/4 + 70 + 100) {
			if(my >= 150 && my <= 200) {
				// painettu play-nappi
				State = STATE.GAME;
			}
		}
		
		// ohjeet-nappi
		if(mx >= Game.WIDTH/4 + 15 && mx <= Game.WIDTH/4 + 15 + 210) {
			if(my >= 250 && my <= 300) {
				// painettu highscore-nappi
				State = STATE.OHJEET;
			}
		}
		
		// poistu-nappi
		if(mx >= Game.WIDTH/4 + 70 && mx <= Game.WIDTH/4 + 70 + 100) {
			if(my >= 350 && my <= 400) {
				// painettu poistu-nappi
				System.exit(1);
			}
		}
		
		// uusipeli-nappi
		if(mx >= Game.WIDTH/4 + 70 && mx <= Game.WIDTH/4 + 70 + 130) {
			if(my >= 200 && my <= 250) {
				State = STATE.GAME;
				//xCoord = 10;
				//yCoord = 10;
				restart();
			}
		}
		
		// päävalikko-nappi
		if(mx >= Game.WIDTH/4 + 45 && mx <= Game.WIDTH/4 + 45 + 180) {
			if(my >= 300 && my <= 350) {
				restart();
				State = STATE.MENU;
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
