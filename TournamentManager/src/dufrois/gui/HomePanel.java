package dufrois.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import dufrois.common.*;
import dufrois.pingpong.PingPongTeam;
import dufrois.tournaments.RoundRobinTourn;
import dufrois.tournaments.TournamentTypeEnum;
import dufrois.tournaments.TournamentStartedException;

/**
 * 
 * 
 * @author Christian Dufrois
 * @version 25.09.2017
 */
public class HomePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private JComboBox<String> selectSport, selectTourn;
	private int usableWidth, usableHeight;
	
	/**
	 * Create the panel.
	 */
	public HomePanel(JFrame f)
	{
		System.out.println("Home Panel");
		frame = f;
		usableWidth = WindowVars.getUsableWidth();
		usableHeight = WindowVars.getUsableHeight();
		
		setBackground(WindowVars.BACKGROUND_COLOR);
		setLayout(null);
		
		
		JButton butt = new JButton("TEST");
		butt.setBounds(800, WindowVars.HEIGHT - WindowVars.NONUSABLE_HEIGHT, WindowVars.BUTTON_WIDTH,
				WindowVars.BUTTON_HEIGHT);
		add(butt);
		
		/*
		 * The title label
		 */
		int titleHeight = 60; // Make size dependent
		
		JLabel titleLabel = new JLabel("New Tournament");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(WindowVars.FOREGROUND_COLOR);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, titleHeight));
		
		titleLabel.setBounds(WindowVars.X_BORDER_BUFFER, 0, usableWidth, titleHeight);
		add(titleLabel);
		
		/*
		 * Create labels and drop down boxes for type of sport and tournament
		 */
		// Create sport type selector
		JLabel sportLabel = new JLabel("Select Sport:");
		sportLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		sportLabel.setForeground(WindowVars.FOREGROUND_COLOR);
		
		selectSport = new JComboBox<String>();
		selectSport.setFont(new Font("SansSerif", Font.PLAIN, 14));
		selectSport.setBackground(WindowVars.BACKGROUND_COLOR);
		selectSport.setForeground(WindowVars.FOREGROUND_COLOR);
		selectSport.setModel(new DefaultComboBoxModel<String>(SportTypeEnum.getStringValues()));
		
		// Create tournament type selector
		JLabel tournLabel = new JLabel("Select Tournament:");
		tournLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tournLabel.setForeground(WindowVars.FOREGROUND_COLOR);
		
		selectTourn = new JComboBox<String>();
		selectTourn.setFont(new Font("SansSerif", Font.PLAIN, 14));
		selectTourn.setBackground(WindowVars.BACKGROUND_COLOR);
		selectTourn.setForeground(WindowVars.FOREGROUND_COLOR);
		selectTourn.setModel(new DefaultComboBoxModel<String>(TournamentTypeEnum.getStringValues()));
		
		int labelWidth = WindowVars.COMBOBOX_WIDTH;
		int labelX = (usableWidth / 2) - labelWidth + WindowVars.X_BORDER_BUFFER; // Left of center
		int comboX = (usableWidth / 2) + WindowVars.X_BORDER_BUFFER; // Right of center
		int sportY = titleHeight + 2 * WindowVars.COMBOBOX_HEIGHT;
		int tournY = sportY + (2 * WindowVars.COMBOBOX_HEIGHT);
		
		sportLabel.setBounds(labelX, sportY, labelWidth, WindowVars.COMBOBOX_HEIGHT);
		selectSport.setBounds(comboX, sportY, WindowVars.COMBOBOX_WIDTH, WindowVars.COMBOBOX_HEIGHT);
		tournLabel.setBounds(labelX, tournY, labelWidth, WindowVars.COMBOBOX_HEIGHT);
		selectTourn.setBounds(comboX, tournY, WindowVars.COMBOBOX_WIDTH, WindowVars.COMBOBOX_HEIGHT);
		add(sportLabel);
		add(selectSport);
		add(tournLabel);
		add(selectTourn);
		
		/*
		 * Navigation bar at bottom
		 */
		// Create the next button
		JButton createButton = new JButton("Create");
		createButton.setBackground(WindowVars.BACKGROUND_COLOR);
		createButton.setForeground(WindowVars.FOREGROUND_COLOR);
		createButton.addActionListener(new CreateNewTournament());
		
		// Center button horizontally
		int createX = usableWidth / 2 - WindowVars.BUTTON_WIDTH / 2 + WindowVars.X_BORDER_BUFFER;
		// Align button to bottom of screen
		int createY = usableHeight - WindowVars.BUTTON_HEIGHT + WindowVars.Y_BORDER_BUFFER;
		createButton.setBounds(createX, createY, WindowVars.BUTTON_WIDTH, WindowVars.BUTTON_HEIGHT);
		add(createButton);
		
		// SavedTournaments list = new SavedTournaments(this);
		// list.setBounds(50, 10, WindowVars.getUsableWidth() - 50, 100);
		// add(list);
		
	}
	
	public class CreateNewTournament implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			TournamentTypeEnum tournType = TournamentTypeEnum.getEnum((String) selectTourn.getSelectedItem());
			SportTypeEnum sport = SportTypeEnum.getEnum((String) selectSport.getSelectedItem());
			switch (tournType) {// Should be doing stuff through the tourn type and passing in game type
			case MATCH:
				JPanel prev = (JPanel) frame.getContentPane();
				frame.setContentPane(new SingleMatchPanel(frame, prev, sport));
				frame.setTitle("A Single " + sport.toString() + "  Match");
				frame.setVisible(true);
				break;
			case ROUNDROBIN:
				frame.setTitle(tournType.toString());
				frame.setVisible(true);
				break;
			default:
				frame.setTitle(TournamentTypeEnum.MATCH.toString());
				frame.setVisible(true);
				break;
			}
			frame.setVisible(true);
		}
	}
	
	public static void main(String[] args)
	{
		// helper();
		/// *
		JFrame frame = new JFrame("Tournament Manager");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(WindowVars.FRAME_LEFT, WindowVars.FRAME_TOP);
		frame.setSize(WindowVars.WIDTH, WindowVars.HEIGHT);
		frame.setBackground(WindowVars.BACKGROUND_COLOR);
		frame.setContentPane(new HomePanel(frame));
		frame.setFocusable(true);
		frame.setVisible(true);
		// */
	}
	
	@SuppressWarnings("unused")
	private static void helper()
	{
		// Make teams
		Team team1 = new PingPongTeam("1");
		Team team2 = new PingPongTeam("2");
		Team team3 = new PingPongTeam("3");
		Team team4 = new PingPongTeam("4");
		Team team5 = new PingPongTeam("5");
		Team team6 = new PingPongTeam("6");
		Team team7 = new PingPongTeam("7");
		
		// Add teams to tournament
		RoundRobinTourn tourn = new RoundRobinTourn(SportTypeEnum.PINGPONG, "Test", 2);
		try
		{
			tourn.addTeam(team1);
			tourn.addTeam(team2);
			tourn.addTeam(team3);
			tourn.addTeam(team4);
			tourn.addTeam(team5);
			tourn.addTeam(team6);
			tourn.addTeam(team7);
		}
		catch (TournamentStartedException e)
		{
			// Tournament all ready started
		}
		
		// Start tournament
		try
		{
			tourn.startTournament();
		}
		catch (TournamentStartedException e)
		{
		}
		
		System.out.println(tourn.toString());
		
	}
}
