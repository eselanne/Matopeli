import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MainMenu {
	
	// main menu
	public Rectangle playNappi = new Rectangle(Game.WIDTH/4 + 70, 150, 100, 50);
	public Rectangle ohjeetNappi = new Rectangle(Game.WIDTH/4 + 70, 250, 100 , 50);
	public Rectangle exitNappi = new Rectangle(Game.WIDTH/4 + 70, 350, 100, 50);
	
	// game over
	public Rectangle uusiPeliNappi = new Rectangle(Game.WIDTH/4 + 70, 200, 130, 50);
	public Rectangle paavalikkoonNappi = new Rectangle(Game.WIDTH/4 + 45, 300, 180, 50);
	
	public void render(Graphics g) {
		
		// otsikko
		Font font0 = new Font("century gothic", Font.BOLD, 50);
		g.setFont(font0);
		g.setColor(Color.white);
		g.drawString("MATOPELI", Game.WIDTH/4, 100);
		
		// nappien tekstit
		Font font1 = new Font("arial", Font.BOLD, 25);
		g.setFont(font1);
		g.drawString("Pelaa", playNappi.x + 18, playNappi.y + 35);
		g.drawString("Ohjeet", ohjeetNappi.x + 10, ohjeetNappi.y + 35);
		g.drawString("Poistu", exitNappi.x + 14, exitNappi.y + 35);
		
		// nappien laatikot
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(playNappi);
		g2d.draw(ohjeetNappi);
		g2d.draw(exitNappi);
	}
	
	public void renderGameOver(Graphics g) {
		
		// otsikko + tulos
		Font font0 = new Font("century gothic", Font.BOLD, 50);
		g.setFont(font0);
		g.setColor(Color.white);
		g.drawString("PELI PÄÄTTYI", Game.WIDTH/5, 100);
		Font font1 = new Font("arial", Font.BOLD, 35);
		g.setFont(font1);
		g.drawString("Tuloksesi: " + (Game.getScore()), Game.WIDTH/5 + 40, 150);
		
		// nappien tekstit
		Font font2 = new Font("arial", Font.BOLD, 25);
		g.setFont(font2);
		g.drawString("Uusi peli", uusiPeliNappi.x + 15, uusiPeliNappi.y + 35);
		g.drawString("Päävalikkoon", paavalikkoonNappi.x + 10, paavalikkoonNappi.y + 35);
		
		// nappien laatikot
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(uusiPeliNappi);
		g2d.draw(paavalikkoonNappi);
		
	}
	
	public void renderOhjeet(Graphics g) {
		
		//ohjeet
		Font font = new Font("arial", Font.BOLD, 25);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Peliohjeet:", Game.WIDTH/3, 100);
		Font font5 = new Font("arial", Font.BOLD, 25);
		g.setFont(font5);
		g.setColor(Color.white);
		g.drawString("Liikuta matoa nuolinäppäimillä ja", Game.WIDTH/6, 200);
		g.drawString("yritä osua omenaan", Game.WIDTH/6, 250);
		
		
		//nappi
		g.drawString("Päävalikkoon", paavalikkoonNappi.x + 10, paavalikkoonNappi.y + 35);
		
		//napin laatikko
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(paavalikkoonNappi);
	}
}
