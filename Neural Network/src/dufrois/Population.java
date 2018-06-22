package dufrois;

import java.awt.Graphics;
import java.util.Random;

public class Population
{
	private Dot[] dots;
	private boolean allDone;
	
	public Population(int size, int startX, int startY)
	{
		allDone = false;
		
		dots = new Dot[size];
		for (int i = 0; i < dots.length; i++)
		{
			dots[i] = new Dot(startX, startY);
		}
	}
	
	public boolean allDotsDone()
	{
		return allDone;
	}
	
	public void draw(Graphics g)
	{
		for (Dot dot : dots)
		{
			dot.draw(g);
		}
	}
	
	/**
	 * Updates all the dots in this population that have not died or reached the
	 * goal
	 * 
	 * @param freq
	 *            The frequency this function is being called
	 * @param time
	 *            The total time this generation has taken
	 * @param world
	 *            The world the dots live in
	 * @return True if at least one dot was updated, false if none were updated
	 */
	public boolean update(double freq, int time, World world)
	{
		
		allDone = true;
		for (Dot dot : dots)
		{
			if (!world.checkColision(dot))
			{
				dot.move(freq, time);
				allDone = false;
			}
		}
		return !allDone;
	}
	
	public void evolve(int time, World world)
	{
		if (!allDone)
		{
			return;
		}
		
		// Calculate fitnesses and the fittest dot
		Dot bestDot = dots[0];
		double bestFit = 0;
		double fitnessSum = 0;
		for (Dot dot : dots)
		{
			double fitness = world.calculateFitness(dot, time);
			println(fitness + "");
			dot.setFitness(fitness); // Set dots fitness
			
			fitnessSum += fitness; // Add to sum
			
			if (fitness > bestFit) // Check if its the fittest
			{
				bestFit = fitness;
				bestDot = dot;
			}
		}
		println("Fit sum = " + fitnessSum);
		bestDot = bestDot.makeChild(world, false);
		bestDot.makeBest();
		
		Dot[] nextGen = new Dot[dots.length];
		nextGen[0] = bestDot;
		
		for (int i = 1; i < nextGen.length; i++)
		{
			//print(i + " ");
			Dot dot = selectParent(fitnessSum);
			//println("  " + dot.toString());
			dot = dot.makeChild(world, true);
			nextGen[i] = dot;
		}
		dots = nextGen;
		allDone = false;
	}
	
	private Dot selectParent(double fitnessSum)
	{
		Random rand = new Random();
		double limit = rand.nextDouble() * fitnessSum;
		
		double runningSum = 0;
		
		for (Dot dot : dots)
		{
			runningSum += dot.getFitness();
			if (runningSum > limit)
			{
				return dot;
			}
		}
		
		// should never get to this point
		
		return null;
	}
	
	private void print(String str)
	{
		System.out.print(str);
	}
	
	private void println(String str)
	{
		System.out.println(str);
	}
}
