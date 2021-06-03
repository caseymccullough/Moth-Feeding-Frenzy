import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Target {
	
	private static final int BIRD_GAP = 15;
	
	private int myX;
	private int myY;
	private int birdX;
	private int birdY;
	private int mySize; // length of lines that comprise target;
	private Color myColor; 
	
	private static BufferedImage image;
	
	public Target (int x, int y, int size, Color c)
	{
		myX = x;
		myY = y;
		birdX = x + BIRD_GAP;
		
		mySize = size;
		myColor = c;	
		
		try {
			image = ImageIO.read(new File("BirdHead.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setX (int xIn)
	{
		myX = xIn;
		birdX = myX + BIRD_GAP;
	}
	
	public void setY (int yIn)
	{
		myY = yIn;
		birdY = myY + BIRD_GAP;
	}
	
	public void moveLeft()
	{
		birdX -= BIRD_GAP;
		
	}
	
	public void moveRight()
	{
		birdX += BIRD_GAP;
	}
	
	public void draw(Graphics g)
	 {
	  g.setColor(myColor);
	  g.drawLine(myX - mySize, myY, myX + mySize, myY);
	  g.drawLine(myX,  myY - mySize, myX, myY + mySize);
	  g.drawOval(myX - mySize, myY - mySize, mySize * 2, mySize * 2);
	  
	  g.drawImage (image, birdX, birdY - 30, image.getWidth() / 8, image.getHeight() / 8, null);
	  // drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)
	 }
	
	

}
