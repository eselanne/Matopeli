import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Apple {
	private int xCoord, yCoord, width, height;
	private Image omena;
	
	public Apple(int xCoord, int yCoord, int tileSize) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		width = tileSize;
		height = tileSize;
		
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

	public void tick() {
		
	}
	
	public void draw (Graphics g) {
		omena = Toolkit.getDefaultToolkit().getImage("omena.png");
		//g.setColor(Color.red);
		//g.fillRect(xCoord * width, yCoord*height, width, height);
		g.drawImage(omena, xCoord * width, yCoord*height, width, height, null);
	}

}
