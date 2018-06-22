package dufrois.updatedgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow
{
	
	private JFrame mainFrame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainWindow window = new MainWindow();
					window.mainFrame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public MainWindow()
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		mainFrame = new JFrame();
		mainFrame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouse) {
				if (mouse.getButton() != MouseEvent.BUTTON2)
					return;
				
				if (mainFrame.getTitle().equals("Tournament Manager"))
				{
					mainFrame.setTitle("Tournament Manager LOL");
				}
				else
				{
					mainFrame.setTitle("Tournament Manager");
				}
			}
		});
		mainFrame.setFont(new Font("Consolas", Font.PLAIN, 12));
		mainFrame.setTitle("Tournament Manager");
		mainFrame.setBackground(SystemColor.desktop);
		mainFrame.setBounds(100, 100, 1000, 600);
		mainFrame.setContentPane(new HomeWindow());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
