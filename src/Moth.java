import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Moth {

	private final static int SIZE = 20;
	private final static int STEP_SIZE = 10;
	private final static int MOVE_DISTANCE = 200;
	private final static double INITIAL_ORIENTATION = Math.PI;
	
	private final static double MOVE_PROBABILITY = .05;
	private static Random gen = new Random();
	
	private boolean isMoving;
	private Location myLoc;
	private Location desiredLoc;
	private Color myColor;
	private double orientation;
	
	private ArrayList <Moth> mothList;
	private Trunk myTrunk;
	
	public Moth(int x, int y, Color col, ArrayList<Moth> mothListIn, Trunk trunkIn) {
		
		isMoving = false;
		myLoc = new Location (x, y);
		desiredLoc = myLoc;
		myColor = col;
		orientation = INITIAL_ORIENTATION;
		
		mothList = mothListIn;
		myTrunk = trunkIn;

	}
	
	public static int getSize()
	{
		return SIZE;
	}
	
	public void setDesiredLocation (Location loc)
	{
		desiredLoc = loc;
	}
	
	public boolean processClick (Location clickLoc)
	{
		double distance = myLoc.distanceFrom(clickLoc);
		System.out.println("Location: " + myLoc + "clicked: " + clickLoc +  " distance: " + distance);
		if (distance <= SIZE)
		{
			System.out.println("HIT");
			removeSelf();
			return true;
		}
		else
		{
			System.out.println("MISS");
			return false;
		}
				
	}
	
	public void act ()
	{
		//System.out.println("at: " + myLoc + " desired: " + desiredLoc);
		if (!myLoc.equals (desiredLoc))
		{
			move();
		}
		else
		{
			if (Math.random() <= MOVE_PROBABILITY)
			{
				// establish new destination
				this.setDesiredLocation(new Location (myLoc.getX() + gen.nextInt (MOVE_DISTANCE) - MOVE_DISTANCE / 2, myLoc.getY() + gen.nextInt (MOVE_DISTANCE) - MOVE_DISTANCE / 2));
				
			}
		}
		
	}
	
	public void move ()
	{
		if (myLoc.distanceFrom(desiredLoc) < STEP_SIZE)
		{
			myLoc = desiredLoc;
		}
		else
		{
			// take incremental step in right direction
			// with some randomness as well
			
			int horizDist =  myLoc.getX() - desiredLoc.getX();
			{
				if (Math.abs(horizDist) < STEP_SIZE)
				{
					myLoc.setX(desiredLoc.getX());
				}
				else if (horizDist < 0) // move right
				{
					myLoc.adjustX(STEP_SIZE);
				}
				else
				{
					myLoc.adjustX(-STEP_SIZE);
				}
			}
			
			int vertDist =  myLoc.getY() - desiredLoc.getY();
			{
				if (Math.abs(vertDist) < STEP_SIZE)
				{
					myLoc.setY(desiredLoc.getY());
				}
				else if (vertDist < 0) // move right
				{
					myLoc.adjustY(STEP_SIZE);
				}
				else
				{
					myLoc.adjustY(-STEP_SIZE);
				}
			}
		}
		// is moth out of bounds? 
		if (myLoc.getX() < myTrunk.getLeft() ||
			myLoc.getX() > myTrunk.getRight() ||
			myLoc.getY() < myTrunk.getTop() ||
			myLoc.getY() > myTrunk.getBottom())			
		{
			int midX = (myTrunk.getLeft() + myTrunk.getRight()) / 2;
			int midY = (myTrunk.getTop() + myTrunk.getBottom()) / 2;
			desiredLoc = new Location (midX, midY);
		}
		
	}
	
	public void removeSelf()
	{
		mothList.remove(this);
	}
	
	public void draw (Graphics g)
	{
		int x = myLoc.getX(); 
		int y = myLoc.getY();
		
	
		int[] xVals = {x, x + SIZE / 2, x + SIZE, x, x - SIZE, x - SIZE / 2, x};
		int[] yVals = {y - SIZE, y, y + SIZE / 2, y + SIZE / 4, y + SIZE / 2, y, y - SIZE};
		
		
		g.setColor(myColor);
		g.fillPolygon(xVals, yVals, xVals.length);
		//g.drawOval(myLoc.getX()- SIZE, myLoc.getY() - SIZE, SIZE * 2, SIZE * 2);
		
		g.setColor(Color.red);
		g.fillOval(desiredLoc.getX(), desiredLoc.getY(), 2, 2);
	}
	
	

}
