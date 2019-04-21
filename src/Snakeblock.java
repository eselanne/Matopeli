import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Snakeblock {
	
	private int xCoord, yCoord, width, height;
	private Image kaarme;
	
	public Snakeblock(int xCoord, int yCoord, int tileSize) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		width = tileSize;
		height = tileSize;
		
	}
	
	public void tick () {
		
	}
	
	public void draw(Graphics g) {
		kaarme = Toolkit.getDefaultToolkit().getImage("snake.png");
		
		//g.setColor(Color.cyan);
		//g.fillRect(xCoord * width, yCoord * height, width, height);
		
		g.drawImage(kaarme, xCoord * width, yCoord*height, width, height, null);
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

}
