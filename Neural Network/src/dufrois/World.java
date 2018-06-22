package dufrois;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class World implements Runnable
{
	public static final int NUMBER_OF_DOTS = 1000;
	public static final int GOAL_RADIUS = 6;
	
	private static final int PERIOD = 1; // In milliseconds
	private static final int FASTFORWARD = 5; // Fast forward by this factor
	private static final int SLOW_DOWN = 1; // Slow down by this amount
	
	private int width, height;
	private WorldPanel worldPanel;
	
	private Rectangle[] obsticles;
	private int numObs;
	
	public final int startX, startY;
	private int goalX, goalY;
	private Population pop;
	private int time;
	private int generation;
	
	public World(int w, int h, int sx, int sy)
	{
		width = w;
		height = h;
		
		obsticles = new Rectangle[8];
		numObs = 0;
		
		startX = sx;
		startY = sy;
		goalX = 0;
		goalY = 0;
		time = 0;
		generation = 0;
	}
	
	public int getGeneration()
	{
		return generation;
	}
	
	public void setGoal(int x, int y)
	{
		goalX = x;
		goalY = y;
	}
	
	public boolean addObsticle(int x, int y, int w, int h)
	{
		if (numObs < obsticles.length)
		{
			obsticles[numObs] = new Rectangle(x, y, w, h);
			numObs++;
			return true;
		}
		return false;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.BLUE);
		int gx = WorldPanel.round(goalX - GOAL_RADIUS);
		int gy = WorldPanel.round(goalY - GOAL_RADIUS);
		g.fillOval(gx, gy, 2 * GOAL_RADIUS, 2 * GOAL_RADIUS);
		
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < numObs; i++)
		{
			int x = WorldPanel.round(obsticles[i].getX());
			int y = WorldPanel.round(obsticles[i].getY());
			int w = WorldPanel.round(obsticles[i].getWidth());
			int h = WorldPanel.round(obsticles[i].getHeight());
			g.fillRect(x, y, w, h);
		}
		
		pop.draw(g);
	}
	
	/**
	 * Checks if the dot collided with anything in the world including the goal
	 * 
	 * @param dot
	 *            The dot that is being checked
	 * @return True if the dot has died or reached the goal, false otherwise
	 */
	public boolean checkColision(Dot dot)
	{
		if (dot.isDone())
			return true;
		
		int x = WorldPanel.round(dot.getX());
		int y = WorldPanel.round(dot.getY());
		
		// Check if in world
		if (x < 0 || x > width || y < 0 || y > height)
		{
			dot.kill();
			return true;
		}
		
		double dis = distance(goalX, goalY, dot.getX(), dot.getY());
		if (dis <= GOAL_RADIUS * 1D)
		{
			dot.reached();
			return true;
		}
		
		// Check all the obsticles
		for (int i = 0; i < numObs; i++)
		{
			// Get coords of the rectangle
			int l = WorldPanel.round(obsticles[i].getX());
			int r = l + WorldPanel.round(obsticles[i].getWidth());
			int t = WorldPanel.round(obsticles[i].getY());
			int b = t + WorldPanel.round(obsticles[i].getHeight());
			
			// Check bounds
			if (x <= r && x >= l && y <= b && y >= t)
			{
				dot.kill();
				// System.out.println(x + " " + y);
				return true;
			}
		}
		
		// Never collided with anything
		return false;
	}
	
	public double calculateFitness(Dot dot, int time)
	{
		if (dot.getReached())
		{
			double secondsish = time / 1024.0; // Give a better advantage to dots that make it
			System.out.print("Reached: " + (1000.0 / (secondsish * secondsish)));
			return 1000.0 / (secondsish * secondsish);
		}
		else
		{
			double dist = distance(goalX, goalY, dot.getX(), dot.getY());
			//System.out.println(1000.0 / (dist * dist));
			return 1000.0 / (dist * dist);
		}
	}
	
	private double distance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
	
	@Override
	public void run()
	{
		worldPanel = new WorldPanel(width, height, this);
		
		JFrame frame = new JFrame();
		frame.setTitle("Neural Network Experiment");
		frame.setBounds(0, 0, width + 6, height + 29);
		frame.setContentPane(worldPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		pop = new Population(NUMBER_OF_DOTS, startX, startY);
		
		startGeneration();
	}
	
	private void startGeneration()
	{
		generation++;
		
		// Create timer to update gui
		double frequency = 1000.0 / World.PERIOD;
		
		World me = this;
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask()
		{
			public void run()
			{
				time += PERIOD * FASTFORWARD / SLOW_DOWN;
				//if (time % 1000 == 0)
				//	System.out.println(time);
				pop.update(frequency * SLOW_DOWN / FASTFORWARD, time, me);
				worldPanel.repaint();
				
				if (pop.allDotsDone())
				{
					pop.evolve(time, me);
					time = 0;
					t.cancel();
					me.startGeneration();
				}
			}
		}, PERIOD, PERIOD);
	}
	
}
