import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MainMenu {
	
	public Rectangle playNappi = new Rectangle(Game.WIDTH/4 + 70, 150, 100, 50);
	public Rectangle highScoreNappi = new Rectangle(Game.WIDTH/4 + 15, 250, 210, 50);
	public Rectangle exitNappi = new Rectangle(Game.WIDTH/4 + 70, 350, 100, 50);
	
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
		g.drawString("Parhaat tulokset", highScoreNappi.x + 10, highScoreNappi.y + 35);
		g.drawString("Poistu", exitNappi.x + 14, exitNappi.y + 35);
		
		// nappien laatikot
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(playNappi);
		g2d.draw(highScoreNappi);
		g2d.draw(exitNappi);
	}
}
