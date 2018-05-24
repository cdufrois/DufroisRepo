package dufrois.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import dufrois.common.*;
import dufrois.pingpong.*;
import dufrois.tournaments.MatchOrganizer;

/**
 * Create the teams for the match to be played
 * 
 * @author Christian Dufrois
 *
 */
public class SingleMatchPanel extends JPanel implements ReceiveTeamInterfacePanel
{
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private JPanel previousPanel, leftSide;
	
	private SportTypeEnum sport;
	private Team teamOne = null, teamTwo = null;
	private boolean settingTeamOne = true;
	
	private int splitPoint = WindowVars.getUsableWidth() * 2 / 5;
	private int leftPanelWidth = splitPoint - WindowVars.X_BORDER_BUFFER;
	private JLabel oneLabel, twoLabel;
	private JTextArea onePArea, twoPArea;
	
	public SingleMatchPanel(JFrame f, JPanel pre, SportTypeEnum s)
	{
		System.out.println("Single Match Panel");
		/*
		 * Intitalize variables
		 */
		frame = f;
		previousPanel = pre;
		sport = s;
		setBackground(WindowVars.BACKGROUND_COLOR);
		setLayout(null);
		
		/*
		 * Input team information
		 */
		leftSide = new ChooseTeamPanel(leftPanelWidth, WindowVars.getUsableHeight());
		leftSide.setBounds(WindowVars.X_BORDER_BUFFER, WindowVars.Y_BORDER_BUFFER, leftPanelWidth,
				WindowVars.getUsableHeight());
		add(leftSide);
		
		/*
		 * Show teams playing
		 */
		// teamOne = new PingPongTeam("Christian", null, null);
		// teamTwo = new PingPongTeam("Jeremie", null, null);
		// Team One
		int teamNamesHeight = 40;
		int playerSize = 20;
		
		oneLabel = new JLabel();
		oneLabel.setFont(new Font("Segoe UI", Font.BOLD, teamNamesHeight));
		oneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		oneLabel.setForeground(WindowVars.FOREGROUND_COLOR);
		
		onePArea = new JTextArea();
		onePArea.setFont(new Font("Segoe UI", Font.PLAIN, playerSize));
		onePArea.setForeground(WindowVars.FOREGROUND_COLOR);
		onePArea.setEditable(false);
		
		// Team Two
		twoLabel = new JLabel();
		twoLabel.setFont(new Font("Segoe UI", Font.BOLD, teamNamesHeight));
		twoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		twoLabel.setForeground(WindowVars.FOREGROUND_COLOR);
		
		twoPArea = new JTextArea();
		twoPArea.setFont(new Font("Segoe UI", Font.PLAIN, playerSize));
		twoPArea.setForeground(WindowVars.FOREGROUND_COLOR);
		twoPArea.setEditable(false);
		
		this.updateLabels();
		
		// Add team names and players
		teamNamesHeight += 12;
		int rightWidth = WindowVars.getUsableWidth() - splitPoint;
		int teamWidth = rightWidth / 2 - WindowVars.X_BORDER_BUFFER;
		int twoX = WindowVars.getUsableWidth() - teamWidth + WindowVars.X_BORDER_BUFFER;
		int oneX = twoX - teamWidth;
		int playY = teamNamesHeight + 30 + WindowVars.Y_BORDER_BUFFER;
		int playHeight = 300;
		oneLabel.setBounds(oneX, WindowVars.Y_BORDER_BUFFER, teamWidth, teamNamesHeight);
		onePArea.setBounds(oneX, playY, teamWidth, playHeight);
		twoLabel.setBounds(twoX, WindowVars.Y_BORDER_BUFFER, teamWidth, teamNamesHeight);
		twoPArea.setBounds(twoX, playY, teamWidth, playHeight);
		
		add(oneLabel);
		add(onePArea);
		add(twoLabel);
		add(twoPArea);
		
		/*
		 * Navigation buttons
		 */
		JButton quit = new JButton("Quit Match");
		quit.setBackground(WindowVars.BACKGROUND_COLOR);
		quit.setForeground(WindowVars.FOREGROUND_COLOR);
		quit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				frame.setContentPane(previousPanel);
				frame.setTitle("Tournament Manager");
				frame.setVisible(true);
			}
		});
		
		int navigationY = WindowVars.getUsableHeight() - WindowVars.BUTTON_HEIGHT + WindowVars.Y_BORDER_BUFFER;
		quit.setBounds(oneX, navigationY, WindowVars.BUTTON_WIDTH, WindowVars.BUTTON_HEIGHT);
		add(quit);
		
		// Begin button
		JButton begin = new JButton("Begin Match");
		begin.setBackground(WindowVars.BACKGROUND_COLOR);
		begin.setForeground(WindowVars.FOREGROUND_COLOR);
		begin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) // Set the initial variables and match panel
			{
				if (teamOne != null && teamTwo != null)
				{
					MatchOrganizer match = new MatchOrganizer(sport, teamOne, teamTwo, true, 0);
					switch (sport) {
					case BASIC:
						break;
					case PINGPONG:
						try
						{
							frame.setContentPane(
									new PPMatchPanel(frame, previousPanel, (PingPongMatch) match.getMatch()));
							frame.setTitle("Ping Pong: " + teamOne.getName() + " vs " + teamTwo.getName());
						}
						catch (ByeMatchException e)
						{
							e.printStackTrace();
						}
						break;
					default:
						break;
					}
					frame.setVisible(true);
				}
			}
		});
		
		int beginX = WindowVars.getUsableWidth() - WindowVars.BUTTON_WIDTH + WindowVars.X_BORDER_BUFFER;
		begin.setBounds(beginX, navigationY, WindowVars.BUTTON_WIDTH, WindowVars.BUTTON_HEIGHT);
		add(begin);
	}
	
	private void updateLabels()
	{
		String oneName = "";
		String oneStr = "";
		if (teamOne != null)
		{
			oneName = teamOne.getName();
			oneStr = formatPlayers(teamOne.getPlayers());
		}
		String twoName = "";
		String twoStr = "";
		if (teamTwo != null)
		{
			twoName = teamTwo.getName();
			twoStr = formatPlayers(teamTwo.getPlayers());
		}
		/*
		 * System.out.println(oneName); System.out.println(oneStr);
		 * System.out.println(twoName); System.out.println(twoStr);
		 * System.out.println();
		 */
		oneLabel.setText(oneName);
		onePArea.setText(oneStr);
		twoLabel.setText(twoName);
		twoPArea.setText(twoStr);
	}
	
	private String formatPlayers(Player[] players)
	{
		StringBuilder builder = new StringBuilder("Players:\n");
		for (int i = 0; i < players.length; i++)
		{
			builder.append("\nP");
			builder.append(i + 1);
			builder.append("-    ");
			if (players[i] != null)
				builder.append(players[i].getName());
		}
		return builder.toString();
	}
	
	@Override
	public boolean deliverTeam(JPanel input, Team team)
	{
		if (input != leftSide || (team != null && team.getName() == ByeMatchException.BYE_MATCH_NAME))
		{
			// System.out.println("Panel: " + leftSide.toString());
			// System.out.println("Panel: " + input.toString());
			
			// System.out.println("Same: " + (input == leftSide));
			// System.out.println("Team: " + team.toString());
			return false;
		}
		
		if (team != null)
		{
			if (settingTeamOne)
				teamOne = team;
			else
				teamTwo = team;
		}
		remove(leftSide);
		leftSide = new ChooseTeamPanel(leftPanelWidth, WindowVars.getUsableHeight());
		leftSide.setBounds(WindowVars.X_BORDER_BUFFER, WindowVars.Y_BORDER_BUFFER, leftPanelWidth,
				WindowVars.getUsableHeight());
		add(leftSide);
		this.updateLabels();
		revalidate();
		repaint();
		return true;
	}
	
	private class ChooseTeamPanel extends JPanel
	{
		private static final long serialVersionUID = 1L;
		
		private int width, height;
		
		private ChooseTeamPanel(int w, int h)
		{
			System.out.println("Choose Team Panel");
			/*
			 * Intitalize variables
			 */
			width = w;
			height = h;
			setBackground(WindowVars.BACKGROUND_COLOR);
			setLayout(null);
			
			/*
			 * Title
			 */
			int titleHeight = 50;
			JLabel title = new JLabel("Choose Team");
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setFont(new Font("Segoe UI", Font.BOLD, titleHeight));
			title.setForeground(WindowVars.FOREGROUND_COLOR);
			
			int titleY = 10;
			title.setBounds(0, titleY, width, titleHeight);
			add(title);
			
			/*
			 * Instruction labels and connector label
			 */
			int instructHeight = WindowVars.BUTTON_HEIGHT * 2 / 3;
			JLabel instructOne = new JLabel("Click this button to edit Team One");
			instructOne.setFont(new Font("Segoe UI", Font.PLAIN, instructHeight));
			instructOne.setForeground(WindowVars.FOREGROUND_COLOR);
			instructOne.setHorizontalAlignment(SwingConstants.CENTER);
			
			JLabel instructTwo = new JLabel("Click this button to edit Team Two");
			instructTwo.setFont(new Font("Segoe UI", Font.PLAIN, instructHeight));
			instructTwo.setForeground(WindowVars.FOREGROUND_COLOR);
			instructTwo.setHorizontalAlignment(SwingConstants.CENTER);
			
			int orHeight = (int) (WindowVars.BUTTON_HEIGHT * 1.5);
			JLabel orLabel = new JLabel("OR");
			orLabel.setFont(new Font("Segoe UI", Font.PLAIN, orHeight));
			orLabel.setForeground(WindowVars.FOREGROUND_COLOR);
			orLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			/*
			 * Buttons
			 */
			JButton oneButt = new JButton("Edit Team One");
			oneButt.setBackground(WindowVars.BACKGROUND_COLOR);
			oneButt.setBackground(WindowVars.BACKGROUND_COLOR);
			oneButt.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					settingTeamOne = true;
					showInputTeamPanel();
				}
			});
			
			// Button two
			JButton twoButt = new JButton("Edit Team Two");
			twoButt.setBackground(WindowVars.BACKGROUND_COLOR);
			twoButt.setBackground(WindowVars.BACKGROUND_COLOR);
			twoButt.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					settingTeamOne = false;
					showInputTeamPanel();
				}
			});
			
			/*
			 * Add labels and buttons
			 */
			int buttX = width / 2 - WindowVars.BUTTON_WIDTH / 2;
			int orY = height / 2;
			int orBuff = 60;
			int lbBuff = 10;
			int oneButY = orY - orBuff - WindowVars.BUTTON_HEIGHT;
			int twoButY = orY + orHeight + orBuff + instructHeight + lbBuff;
			
			instructOne.setBounds(0, oneButY - lbBuff - instructHeight, width, instructHeight);
			oneButt.setBounds(buttX, oneButY, WindowVars.BUTTON_WIDTH, WindowVars.BUTTON_HEIGHT);
			orLabel.setBounds(0, orY, width, orHeight);
			instructTwo.setBounds(0, twoButY - lbBuff - instructHeight, width, instructHeight);
			twoButt.setBounds(buttX, twoButY, WindowVars.BUTTON_WIDTH, WindowVars.BUTTON_HEIGHT);
			
			add(instructOne);
			add(oneButt);
			add(orLabel);
			add(instructTwo);
			add(twoButt);
			
		}
	}
	
	private void showInputTeamPanel()
	{
		Team team;
		if (settingTeamOne)
			team = teamOne;
		else
			team = teamTwo;
		
		remove(leftSide);
		switch (sport) {
		case BASIC:
			break;
		case PINGPONG:
			leftSide = new CreatePPTeamPanel(this, (PingPongTeam) team, settingTeamOne, leftPanelWidth,
					WindowVars.getUsableHeight());
			break;
		default:
			break;
		}
		leftSide.setBounds(WindowVars.X_BORDER_BUFFER, WindowVars.Y_BORDER_BUFFER, leftPanelWidth,
				WindowVars.getUsableHeight());
		add(leftSide);
		revalidate();
		repaint();
	}
}
