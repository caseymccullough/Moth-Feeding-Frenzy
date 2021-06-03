
public class Location {
	
	private int myX;
	private int myY;
	
	public Location(int x, int y) {
		// 
		myX = x;
		myY = y;
	}
	
	public int getX()
	{
		return myX;
	
	}
	
	public int getY()
	{
		return myY;
	}
	
	public void setX(int x)
	{
		myX = x;
		
	}
	
	public void setY (int y)
	{
		myY = y;
	}
	
	public void adjustX (int deltaX)
	{
		myX += deltaX;
	}
	
	public void adjustY (int deltaY)
	{
		myY += deltaY;
	}
	
	public String toString()
	{
		return "(" + myX + ", " + myY + " )";
	}
	
	public double distanceFrom (Location other)
	{
		return Math.sqrt((myX - other.getX()) * (myX - other.getX()) + (myY - other.getY()) * (myY - other.getY()));
		
	}
	
	public boolean equals (Location other)
	{
		return (myX == other.getX() && myY == other.getY());
	}

}
