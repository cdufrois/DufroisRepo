package dufrois;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class WorldPanel extends JPanel
{
	public static final int DOT_RADIUS = 2;
	public static final int BEST_DOT_RADIUS = 4;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private World world;
	@SuppressWarnings("unused")
	private int width;
	private int height;
	
	/**
	 * Create the panel.
	 */
	public WorldPanel(int w, int h, World wld)
	{
		width = w;
		height = h;
		world = wld;
		setBackground(Color.WHITE);
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setFont(new Font("Consolas", Font.PLAIN, 50));
		String gen = Integer.toString(world.getGeneration());
		int border = 15;
		g.drawString(gen, 0 + border, height - border);
		
		world.draw(g);
	}
	
	public static int round(double d)
	{
		if (d % 1 >= 0.5)
		{
			return (int) d + 1;
		}
		else if (d % 1 >= -0.5)
		{
			return (int) d;
		}
		else
		{
			return (int) d - 1;
		}
	}
}
