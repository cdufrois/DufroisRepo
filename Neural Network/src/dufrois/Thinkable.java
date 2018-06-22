package dufrois;

public interface Thinkable
{
	
	/**
	 * Returns the location the dot should move to given a time in milliseconds
	 * 
	 * @param cyclesPerSec Delay in milliseconds between executions
	 * @param time The time in milliseconds
	 * @return The position on the screen in pixels
	 */
	public CartVector think(double cyclesPerSec, int time);
	
	public Thinkable clone();
	
	public Thinkable mutateClone();
}
