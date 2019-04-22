import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

/**
 * Luokka edustaa pelin omenoita.
 *
 */
public class Apple {
	private int xCoord, yCoord, width, height;
	private ImageIcon omena;
	
	/**
	 * Luo uuden Apple-olion.
	 * @param xCoord Omenan x-koordinaatti
	 * @param yCoord Omenan y-koordinaatti
	 * @param tileSize Omenan sivun koko
	 */
	public Apple(int xCoord, int yCoord, int tileSize) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		width = tileSize;
		height = tileSize;
		
	}
	
	/**
	 * Palauttaa omenan x-koordinaatin
	 * @return omenan x-koordinaatti
	 */
	public int getxCoord() {
		return xCoord;
	}
	
    /**
     * Asettaa omenalle x-koordinaatin
     * @param xCoord Omenan x-koordinaatti
     */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * Palauttaa omenan y-koordinaatin
	 * @return omenan y-koordinaatti
	 */
	public int getyCoord() {
		return yCoord;
	}

	/**
     * Asettaa omenalle y-koordinaatin
     * @param xCoord Omenan y-koordinaatti
     */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	/**
	 * Piirtää omenan kuvan koordinaattien mukaiseen kohtaan.
	 * @param g Graphics-luokan esiintymä
	 */
	public void draw (Graphics g) {
		
		omena = new ImageIcon(getClass().getClassLoader().getResource("omena.png"));
		
		g.drawImage(omena.getImage(), xCoord * width, yCoord*height, width, height, null);
	}

}
