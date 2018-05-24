package dufrois.gui;

import java.awt.Color;

/**
 * Constants used throughout the program in order to keep consistency
 * 
 * @author Christian Dufrois
 * @version 25.09.2017
 */
public class WindowVars
{
	/**
	 * Space used by the window frame
	 */
	public static final int NONUSABLE_WIDTH = 16;
	
	public static final int NONUSABLE_HEIGHT = 39;
	
	public static final int X_BORDER_BUFFER = 20;
	
	public static final int Y_BORDER_BUFFER = 20;
	
	public static final int FRAME_LEFT = 50;
	
	public static final int FRAME_TOP = 50;
	
	public static final int WIDTH = 1200;
	
	public static final int HEIGHT = 700;
	
	public static final int BUTTON_HEIGHT = 35;
	
	public static final int BUTTON_WIDTH = BUTTON_HEIGHT * 4;
	
	public static final int COMBOBOX_WIDTH = 200;
	
	public static final int COMBOBOX_HEIGHT = 25;
	
	public static final int TEXTFIELD_WIDTH = 160;
	
	public static final int TEXTFIELD_HEIGHT = 25;
	
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	
	public static final Color FOREGROUND_COLOR = Color.BLACK;
	
	public static int getUsableWidth()
	{
		return WIDTH - (2 * X_BORDER_BUFFER) - NONUSABLE_WIDTH;
	}
	
	public static int getUsableHeight()
	{
		return HEIGHT - (2 * Y_BORDER_BUFFER) - NONUSABLE_HEIGHT;
	}
	/*
	 * public static int getCenterXValue() { return (WindowVars.WIDTH -
	 * WindowVars.NONUSABLE_WIDTH) / 2; }
	 * 
	 * public static int getCenterYValue() { return (WindowVars.HEIGHT -
	 * WindowVars.NONUSABLE_HEIGHT) / 2; }
	 */
}
