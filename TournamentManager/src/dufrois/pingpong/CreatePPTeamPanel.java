package dufrois.pingpong;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dufrois.common.*;
import dufrois.gui.*;

public class CreatePPTeamPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private ReceiveTeamInterfacePanel teamReceiver;
	private PingPongTeam team;
	private boolean isTeamOne;
	private int width, height;
	
	/**
	 * Creates a panel to edit the properties of the ping pong team passed as the
	 * parameter
	 * 
	 * @param rec
	 *            The panel that recieves the updated team
	 * @param t
	 *            The team to be modified
	 * @param one
	 * @param w
	 * @param h
	 */
	public CreatePPTeamPanel(ReceiveTeamInterfacePanel rec, PingPongTeam t, boolean one, int w, int h)
	{
		System.out.println("Create PP Team Panel");
		/*
		 * Intitalize variables
		 */
		teamReceiver = rec;
		team = t;
		isTeamOne = one;
		width = w;
		height = h;
		setBackground(WindowVars.BACKGROUND_COLOR);
		setLayout(null);
		
		/*
		 * Input team information
		 */
		// Title
		int titleHeight = 50;
		String tStr;
		if (isTeamOne)
			tStr = "Team One";
		else
			tStr = "Team Two";
		JLabel title = new JLabel(tStr);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Segoe UI", Font.BOLD, titleHeight));
		title.setForeground(WindowVars.FOREGROUND_COLOR);
		
		int titleY = 10;
		title.setBounds(0, titleY, width, titleHeight);
		add(title);
		
		// Team name
		JLabel teamNameLabel = new JLabel("Team Name:");
		teamNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, WindowVars.TEXTFIELD_HEIGHT));
		teamNameLabel.setForeground(WindowVars.FOREGROUND_COLOR);
		
		String origName = "";
		if (team != null)
			origName = team.getName();
		JTextField teamName = new JTextField(origName);
		teamName.setFont(new Font("Segoe UI", Font.PLAIN, WindowVars.TEXTFIELD_HEIGHT - 10));
		teamName.setBackground(WindowVars.BACKGROUND_COLOR);
		teamName.setForeground(WindowVars.FOREGROUND_COLOR);
		
		// Player one
		JLabel pOneLabel = new JLabel("Player One Name:");
		pOneLabel.setFont(new Font("Segoe UI", Font.PLAIN, WindowVars.TEXTFIELD_HEIGHT - 5));
		pOneLabel.setForeground(WindowVars.FOREGROUND_COLOR);
		
		String origOneName = "";
		if (team != null && team.getPlayerOne() != null)
			origOneName = team.getPlayerOne().getName();
		JTextField pOneName = new JTextField(origOneName);
		pOneName.setFont(new Font("Segoe UI", Font.PLAIN, WindowVars.TEXTFIELD_HEIGHT - 10));
		pOneName.setBackground(WindowVars.BACKGROUND_COLOR);
		pOneName.setForeground(WindowVars.FOREGROUND_COLOR);
		
		// Player two
		JLabel pTwoLabel = new JLabel("Player Two Name:");
		pTwoLabel.setFont(new Font("Segoe UI", Font.PLAIN, WindowVars.TEXTFIELD_HEIGHT - 5));
		pTwoLabel.setForeground(WindowVars.FOREGROUND_COLOR);
		
		String origTwoName = "";
		if (team != null && team.getPlayerTwo() != null)
			origTwoName = team.getPlayerTwo().getName();
		JTextField pTwoName = new JTextField(origTwoName);
		pTwoName.setFont(new Font("Segoe UI", Font.PLAIN, WindowVars.TEXTFIELD_HEIGHT - 10));
		pTwoName.setBackground(WindowVars.BACKGROUND_COLOR);
		pTwoName.setForeground(WindowVars.FOREGROUND_COLOR);
		
		// Add components
		int fieldX = width - WindowVars.TEXTFIELD_WIDTH;
		int tNameY = titleY + titleHeight + 100;
		int pOneY = tNameY + WindowVars.TEXTFIELD_HEIGHT + 40;
		int pTwoY = pOneY + WindowVars.TEXTFIELD_HEIGHT + 40;
		teamNameLabel.setBounds(0, tNameY, fieldX, WindowVars.TEXTFIELD_HEIGHT);
		teamName.setBounds(fieldX, tNameY, WindowVars.TEXTFIELD_WIDTH, WindowVars.TEXTFIELD_HEIGHT);
		pOneLabel.setBounds(0, pOneY, fieldX, WindowVars.TEXTFIELD_HEIGHT);
		pOneName.setBounds(fieldX, pOneY, WindowVars.TEXTFIELD_WIDTH, WindowVars.TEXTFIELD_HEIGHT);
		pTwoLabel.setBounds(0, pTwoY, fieldX, WindowVars.TEXTFIELD_HEIGHT);
		pTwoName.setBounds(fieldX, pTwoY, WindowVars.TEXTFIELD_WIDTH, WindowVars.TEXTFIELD_HEIGHT);
		add(teamNameLabel);
		add(teamName);
		add(pOneLabel);
		add(pOneName);
		add(pTwoLabel);
		add(pTwoName);
		
		/*
		 * Navigation buttons
		 */
		JButton cancel = new JButton("Cancel");
		cancel.setBackground(WindowVars.BACKGROUND_COLOR);
		cancel.setBackground(WindowVars.BACKGROUND_COLOR);
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JPanel input = (JPanel) cancel.getParent();
				teamReceiver.deliverTeam(input, null);
			}
		});
		
		int navigationY = height - WindowVars.BUTTON_HEIGHT;
		cancel.setBounds(0, navigationY, WindowVars.BUTTON_WIDTH, WindowVars.BUTTON_HEIGHT);
		add(cancel);
		
		// Set button
		JButton set = new JButton("Set");
		set.setBackground(WindowVars.BACKGROUND_COLOR);
		set.setForeground(WindowVars.FOREGROUND_COLOR);
		set.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) // Set the initial variables and match panel
			{
				// Get team variables
				String teamNameStr = teamName.getText();
				String pOneStr = pOneName.getText();
				String pTwoStr = pTwoName.getText();
				
				team = createNewTeam(teamNameStr, pOneStr, pTwoStr);
				
				JPanel input = (JPanel) set.getParent();
				teamReceiver.deliverTeam(input, team);
			}
		});
		
		int setX = width - WindowVars.BUTTON_WIDTH;
		set.setBounds(setX, navigationY, WindowVars.BUTTON_WIDTH, WindowVars.BUTTON_HEIGHT);
		add(set);
	}
	
	private PingPongTeam createNewTeam(String tName, String name1, String name2)
	{
		if (tName.isEmpty())
			return null;
		
		Player one = null;
		if (!name1.isEmpty())
		{
			one = new Player(name1);
			// System.out.println(name1);
		}
		
		Player two = null;
		if (!name2.isEmpty())
		{
			two = new Player(name2);
			// System.out.println(name2);
		}
		
		return new PingPongTeam(tName, one, two);
	}
}
