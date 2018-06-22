package dufrois;

public class CartVector
{
	private double myX;
	private double myY;
	private double capX;
	private double capY;
	private boolean capped;
	
	public CartVector(double x, double y)
	{
		myX = x;
		myY = y;
		capX = 0;
		capY = 0;
		capped = false;
	}
	
	public CartVector(double x, double y, double cx, double cy)
	{
		myX = x;
		myY = y;
		capX = cx;
		capY = cy;
		capped = true;
	}
	
	public double getX()
	{
		return myX;
	}
	
	public double getY()
	{
		return myY;
	}
	
	public void setX(double x) {
		myX = x;
	}
	
	public void setY(double y) {
		myY = y;
	}
	
	public void add(CartVector other)
	{
		myX += other.myX;
		myY += other.myY;
		checkCaps();
	}
	
	public void addFraction(CartVector number, double divisor)
	{
		myX += (number.myX / divisor);
		myY += (number.myY / divisor);
		checkCaps();
	}
	
	private void checkCaps() {
		if (!capped) return;
		// Check the value of X
		if (myX > capX)
		{
			myX = capX;
		}
		else if (myX < (-1 * capX))
		{
			myX = -1 * capX;
		}
		
		// Check the value of Y
		if (myY > capY)
		{
			myY = capY;
		}
		else if (myY < (-1 * capY))
		{
			myY = -1 * capY;
		}
	}
	
	public CartVector clone()
	{
		return new CartVector(myX, myY, capX, capY);
	}
}
