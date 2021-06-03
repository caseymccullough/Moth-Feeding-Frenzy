import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Trunk {

	private Color myColor;
	private int myX;
	private int myY;
	private int myWidth;
	private int myHeight;

	private BufferedImage image;
	
	public Trunk(int x, int y, int wid, int height, Color col)  {
		// TODO Auto-generated constructor stub
		myX = x;
		myY = y;
		myWidth = wid;
		myHeight = height;
		myColor = col;
		
		try {
			image = ImageIO.read(new File("DarkBark.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getTop ()
	{
		return myY;
	
	}
	
	public int getBottom()
	{
		return myY + myHeight;
	}
	
	public int getRight()
	{
		return myX + myWidth;
	}
	
	public int getLeft()
	{
		return myX;
	}
	
	public void draw (Graphics g)
	{
		g.setColor(myColor);
		g.fillRect (myX, myY, myWidth, myHeight);
		g.drawImage (image, myX, myY, null);
	}

}
