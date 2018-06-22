package dufrois;

public class Evolution
{
	
	private static final int width = 1000;
	private static final int height = 1000;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		
		World world = new World(width, height, width / 2, height - 100);
		world.setGoal(width / 2, height / 10);
		int dx = width / 2 + 50;
		world.addObsticle(0, height / 2 - 200, dx, 100);
		world.addObsticle(width - dx, height / 2 + 100, dx, 100);
		
		world.run();
	}
}
