package dufrois;

import java.awt.Color;
import java.awt.Graphics;

public class Dot
{
	private Thinkable brain;
	private CartVector pos; // Unit: Pixel
	private boolean dead, best, reachedGoal;
	private double fitness;
	
	public Dot(int x, int y)
	{
		pos = new CartVector(x, y);
		brain = new Brain(Brain.STANDARD_BRAIN_SIZE);
		
		dead = false;
		best = false;
		reachedGoal = false;
	}
	
	private Dot(int x, int y, Thinkable b)
	{
		pos = new CartVector(x, y);
		brain = b;
		
		dead = false;
		best = false;
		reachedGoal = false;
	}
	
	public int getX()
	{
		return WorldPanel.round(pos.getX());
	}
	
	public int getY()
	{
		return WorldPanel.round(pos.getY());
	}
	
	public boolean isDone()
	{
		return dead || reachedGoal;
	}
	
	public boolean getReached()
	{
		return reachedGoal;
	}
	
	public double getFitness()
	{
		return fitness;
	}
	
	public void setFitness(double fit)
	{
		fitness = fit;
	}
	
	public void kill()
	{
		dead = true;
	}
	
	public void reached()
	{
		reachedGoal = true;
	}
	
	public void makeBest()
	{
		best = true;
	}
	
	public void draw(Graphics g)
	{
		int radius = WorldPanel.DOT_RADIUS;
		if (best)
		{
			g.setColor(Color.GREEN);
			radius = WorldPanel.BEST_DOT_RADIUS;
		}
		else if (dead)
		{
			g.setColor(Color.RED);
		}
		else
		{
			g.setColor(Color.BLACK);
		}
		int x = WorldPanel.round(pos.getX() - radius);
		int y = WorldPanel.round(pos.getY() - radius);
		g.fillOval(x, y, 2 * radius, 2 * radius);
	}
	
	public void move(double freq, int time)
	{
		if (!dead || !reachedGoal)
		{
			CartVector addToPos = brain.think(freq, time);
			
			if (addToPos != null)
				pos.add(addToPos);
			else
				dead = true;
		}
	}
	
	/**
	 * Make a child of this Dot and whether or not it is an exact clone or mutant
	 * 
	 * @param world
	 *            The world the dot lives in to get the starting point
	 * @param mutate
	 *            False if the baby should be an exact clone, false if it should
	 *            mutate
	 * @return The new child
	 */
	public Dot makeChild(World world, boolean mutate)
	{
		Thinkable newBrain;
		if (mutate)
		{
			newBrain = brain.mutateClone();
		}
		else
		{
			newBrain = brain.clone();
		}
		
		return new Dot(world.startX, world.startY, newBrain);
	}
}
