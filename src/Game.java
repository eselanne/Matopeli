import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Luokka sisältää itse pelin toiminnallisuuden.
 *
 */
public class Game extends JPanel implements Runnable, KeyListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500; // ikkunan leveys ja korkeus
	private Thread thread;
	private boolean running;
	private boolean right = true, left = false, up = false, down = false; // madon suunta
	private Snakeblock s;
	private ArrayList<Snakeblock> snake; // mato
	private Apple apple;
	private ArrayList<Apple> apples;
	private Random r;
	private int xCoord = 10, yCoord = 10, size = 5; // madon aloituskoordinaatit ja -pituus
	private int ticks = 0;
	public static int highScore; // ennätys
	public static int score = 0; // reaaliaikainen tulos
	private MainMenu menu; // pelin valikko
	File hsFile;
	ImageIcon taustaValikko; // valikoiden taustakuva 
	ImageIcon taustaPeli; // pelin taustakuva
	
	// kuvaa ohjelman tilaa
	private enum STATE{
		GAME, MENU, GAMEOVER, OHJEET
	};
	
	// ohjelman tila
	private STATE State = STATE.MENU;
	
	/**
	 * Määrittää peli-ikkunan ja tarvittavat muuttujat.
	 */
	public Game() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		addMouseListener(this);
		
		snake = new ArrayList<Snakeblock> ();
		apples = new ArrayList<Apple>();
		r = new Random();
		menu = new MainMenu();
		taustaValikko = new ImageIcon(getClass().getClassLoader().getResource("tausta_menu.png"));
		taustaPeli = new ImageIcon(getClass().getClassLoader().getResource("tausta_peli.png"));
		
		hsFile = new File(".//res//highscore.txt");
		highScore = lueHighScore();
		
		start();
	}
	
	/**
	 * Käynnistää pelin uudestaan.
	 */
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
	
	/**
	 * Käynnistää pelin.
	 */
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	/**
	 * Sammuttaa pelin.
	 */
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Madon toiminnallisuus.
	 */
	public void tick () {
		if(State == STATE.GAME) {
			if(snake.size() ==0 ) {
				s = new Snakeblock(xCoord,yCoord,10);
				snake.add(s);
			}
			ticks++;
			if(ticks > 750000) {
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
						if(score > highScore) {
							highScore = score;
							uusiEnnatys();
						}
						State = STATE.GAMEOVER;
						
					}
				}
			}
			//Törmäys seinään
			if(xCoord < 0 || xCoord > 49 || yCoord < 0 || yCoord > 49) {
				if(score > highScore) {
					highScore = score;
					uusiEnnatys();
				}
				State = STATE.GAMEOVER;
				
			}
			
		}		
	}
	
	/**
	 * Palauttaa pelin tuloksen.
	 * @return pelin tuloksen
	 */
	public static int getScore() {
		return score;
	}
	
	/**
	 * Piirtää ikkunaan tarvittavat komponentit.
	 */
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		
		if(State == STATE.GAME) {
			g.drawImage(taustaPeli.getImage(), 0, 0, WIDTH, HEIGHT, null); // taustakuva
			
		    /** Ruudukko
			for(int i = 0; i < WIDTH/10; i++) {
				g.drawLine(i*10, 0, i*10, HEIGHT);
			}
			for(int i = 0; i < HEIGHT/10; i++) {
				g.drawLine(0, i*10,HEIGHT , i*10);
			}
			*/
			for (int i = 0; i < snake.size(); i++) {
				snake.get(i).draw(g);
			}
			for(int i = 0; i < apples.size(); i++) {
				apples.get(i).draw(g);
			}
		}else if(State == STATE.MENU) {
			g.drawImage(taustaValikko.getImage(), 0, 0, WIDTH, HEIGHT, null); // taustakuva
			menu.render(g); // piiretään päävalikko
		}else if(State == STATE.GAMEOVER) {
			g.drawImage(taustaValikko.getImage(), 0, 0, WIDTH, HEIGHT, null); // taustakuva
			menu.renderGameOver(g); // piiretään Game over -valikko
		}else if (State == STATE.OHJEET) {
			g.drawImage(taustaValikko.getImage(), 0, 0, WIDTH, HEIGHT, null); // taustakuva
			menu.renderOhjeet(g); // piiretään ohjeet-valikko
		}
	}
	
	@Override 
	public void run() {
		while(running) {
			tick();
			repaint();
		}
	}
	
	/**
	 * Lukee tekstitiedostosta aiemman ennätyksen.
	 * @return ennätyksen tekstitiedostosta
	 */
	public int lueHighScore(){
		Scanner lukija;
		int hs = 0;
		try {
			lukija = new Scanner(hsFile);
			hs = Integer.parseInt(lukija.nextLine()); 
			lukija.close();
			System.out.println(hs);
		} catch (FileNotFoundException e0) {
			e0.printStackTrace();
		} catch (NoSuchElementException e1) {
		}
		return hs;
	}
	
	/**
	 * Kirjoittaa uuden ennätyksen tekstitiedostoon.
	 */
	public void uusiEnnatys() {
		try {
			FileWriter fw = new FileWriter(hsFile,false);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(score);
			pw.close();
			fw.close();
		} catch (IOException e) {		
			
		}
	}

	/**
	 * Kun peli on käynnissä, metodi seuraa nuolinäppäimien syötettä.
	 */
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
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}
	
	/**
	 * Kun pelitila ei ole pelissä, metodi seuraa hiiren syötettä.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		// päävalikko
		if(State == STATE.MENU) {
			
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
					// painettu ohjeet-nappi
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
		}
		
		// päättynyt peli
		if(State == STATE.GAMEOVER) {
			
			// uusipeli-nappi
			if(mx >= Game.WIDTH/4 + 70 && mx <= Game.WIDTH/4 + 70 + 130) {
				if(my >= 200 && my <= 250) {
					restart();
					State = STATE.GAME;
				}
			}
			
			// päävalikkoon-nappi
			if(mx >= Game.WIDTH/4 + 45 && mx <= Game.WIDTH/4 + 45 + 180) {
				if(my >= 300 && my <= 350) {
					restart();
					State = STATE.MENU;
				}
			}
		}
		
		// ohjeet-sivu
		if(State == STATE.OHJEET) {
			
			// päävalikkoon-nappi
			if(mx >= Game.WIDTH/4 + 45 && mx <= Game.WIDTH/4 + 45 + 180) {
				if(my >= 300 && my <= 350) {
					State = STATE.MENU;
				}
			}
		}	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
