import java.awt.Color;
import java.awt.Graphics;

public class Snakeblock {
	
	private int xCoord, yCoord, width, height;
	
	public Snakeblock(int xCoord, int yCoord, int tileSize) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		width = tileSize;
		height = tileSize;
		
	}
	
	public void tick () {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(xCoord * width, yCoord * height, width, height);
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
