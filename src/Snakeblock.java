import java.awt.Graphics;

import javax.swing.ImageIcon;

/**
 * Luokka edustaa pelin madon osia.
 *
 */
public class Snakeblock {
	
	private int xCoord, yCoord, width, height;
	private ImageIcon kaarme;
	
	/**
	 * Luo uuden Snakeblock-olion.
	 * @param xCoord Madon osan x-koordinaatti
	 * @param yCoord Madon osan y-koordinaatti
	 * @param tileSize Madon osan sivun koko
	 */
	public Snakeblock(int xCoord, int yCoord, int tileSize) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		width = tileSize;
		height = tileSize;
		
	}
	
	/**
	 * Piirtää madon osan koordinaattien mukaiseen kohtaan.
	 * 
	 */
	public void draw(Graphics g) {
		kaarme = new ImageIcon(getClass().getClassLoader().getResource("snake.png"));
		
		g.drawImage(kaarme.getImage(), xCoord * width, yCoord*height, width, height, null);
	}

	/**
	 * Palauttaa madon osan x-koordinaatin
	 * @return madon osan x-koordinaatti
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * Asettaa madon osalle x-koordinaatin.
	 * @param xCoord Madon osan x-koordinaatti
	 */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * Palauttaa madon osan y-koordinaatin
	 * @return madon osan y-koordinaatti
	 */
	public int getyCoord() {
		return yCoord;
	}

	/**
	 * Asettaa madon osalle y-koordinaatin.
	 * @param yCoord Madon osan y-koordinaatti
	 */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

}
