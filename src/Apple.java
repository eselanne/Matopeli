import java.awt.Color;
import java.awt.Graphics;

public class Apple {
	private int xCoord, yCoord, width, height;
	
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
		g.setColor(Color.red);
		g.fillRect(xCoord * width, yCoord*height, width, height);
	}

}
