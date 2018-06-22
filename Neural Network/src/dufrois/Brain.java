package dufrois;

import java.util.Random;

public class Brain implements Thinkable
{
	public static final int STANDARD_BRAIN_SIZE = 50;
	
	private static final int MAX_DIR_SIZE = 50;
	
	private static double MUTATION_RATE = 0.05;
	
	private CartVector vel; // Unit: Pixels per second
	private CartVector acc; // Unit: Pixels per second per second
	private CartVector[] dirs;
	private int timeAlive;
	
	public Brain(int size)
	{
		dirs = new CartVector[size];
		Random rand = new Random();
		for (int i = 0; i < dirs.length; i++)
		{
			dirs[i] = randomizeVector(rand);
		}
		vel = new CartVector(0, 0, 300, 300);
		//acc = new CartVector(0, 0, 50, 50);
		acc = dirs[0];
		timeAlive = 0;
	}
	
	private Brain(CartVector[] js)
	{
		dirs = js;
		vel = new CartVector(0, 0, 300, 300);
		acc = dirs[0];
		timeAlive = 0;
	}
	
	public CartVector think(double freq, int time)
	{
		// Update the jerk based on time alive
		if (time > dirs.length * 1000)
			return null;
		timeAlive = time;
		if (timeAlive % 1000 == 0)
		{
			acc = dirs[ (timeAlive / 1000) % dirs.length];
		}
		
		// Update acceleration and velocity
		//acc.addFraction(jerk, freq);
		vel.addFraction(acc, freq);
		
		// Return how much to move on this execution
		CartVector pos = new CartVector(0, 0);
		pos.addFraction(vel, freq);
		return pos;
	}
	
	public Brain clone()
	{
		//Brain.MUTATION_RATE = 2000.0 / timeAlive;
		System.out.println(Brain.MUTATION_RATE);
		System.out.println(timeAlive);
		return new Brain(this.copyDirectionArray());
	}
	
	public Brain mutateClone()
	{
		Random rand = new Random();
		CartVector[] newJerks = this.copyDirectionArray();
		
		// Loop through all the values in the array
		for (int i = 0; i < newJerks.length; i++)
		{
			// This value is being mutated by random chance
			if (rand.nextDouble() < Brain.MUTATION_RATE)
			{
				newJerks[i] = randomizeVector(rand);
			}
		}
		
		return new Brain(newJerks);
	}
	
	private CartVector randomizeVector(Random rand)
	{
		int max = Brain.MAX_DIR_SIZE;
		double x = rand.nextDouble() * (2 * max) - max;
		double y = rand.nextDouble() * (2 * max) - max;
		return new CartVector(x, y);
	}
	
	private CartVector[] copyDirectionArray()
	{
		CartVector[] newDirs = new CartVector[dirs.length];
		for (int i = 0; i < newDirs.length; i++)
		{
			newDirs[i] = dirs[i].clone();
		}
		return newDirs;
	}
	
}
