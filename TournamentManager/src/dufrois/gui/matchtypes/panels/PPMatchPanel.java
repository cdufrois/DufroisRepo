package dufrois.gui.matchtypes.panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dufrois.gui.home.WindowVars;
import dufrois.matches.ByeMatchException;
import dufrois.matches.PingPongMatch;
import dufrois.teams.PingPongTeam;

public class PPMatchPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    
    public static final Color PP_BACKGROUND = new Color(50, 50, 200);
    public static final Color PP_FOREGROUND = new Color(225, 225, 225);
    
    private JFrame frame;
    private JPanel mainMenuPanel, leftSide;
    private PPDisplayInfoPanel display;
    private PPInputInfoPanel input;
    private CardLayout cardLayout = new CardLayout();
    
    private int usableWidth, usableHeight;
    private int teamNamesHeight, teamWidth, oneX, twoX;
    private PingPongMatch match = null;
    private JLabel oneScore, twoScore;
    private JLabel serverLabel, winnerLabel;
    
    public PPMatchPanel(JFrame f, JPanel mainMenu, PingPongMatch m) throws ByeMatchException
    {
        /*
         * Initialize variables
         */
        frame = f;
        mainMenuPanel = mainMenu;
        match = m;
        setBackground(PP_BACKGROUND);
        setLayout(null);
        usableWidth = WindowVars.getUsableWidth();
        usableHeight = WindowVars.getUsableHeight();
        int splitPoint = usableWidth * 2 / 5;
        
        /*
         * Information panel/side
         */
        int leftWidth = splitPoint - WindowVars.X_BORDER_BUFFER;
        leftSide = new JPanel();
        input = new PPInputInfoPanel(this, match, leftWidth, usableHeight);
        display = new PPDisplayInfoPanel(this, match, leftWidth, usableHeight);
        
        leftSide.setBounds(WindowVars.X_BORDER_BUFFER, WindowVars.Y_BORDER_BUFFER, leftWidth, usableHeight);
        
        leftSide.setLayout(cardLayout);
        leftSide.add(input, "input");
        leftSide.add(display, "display");
        if (match.isEditableMatch())
            cardLayout.show(leftSide, "input");
        else
            cardLayout.show(leftSide, "display");
        add(leftSide);
        
        /*
         * Team Names and Score
         */
        // Team names
        teamNamesHeight = 50;
        JLabel oneName = new JLabel(match.getTeamOne().getName());
        oneName.setFont(new Font("Segoe UI", Font.BOLD, teamNamesHeight));
        oneName.setHorizontalAlignment(SwingConstants.CENTER);
        oneName.setForeground(PP_FOREGROUND);
        
        JLabel twoName = new JLabel(match.getTeamTwo().getName());
        twoName.setFont(new Font("Segoe UI", Font.BOLD, teamNamesHeight));
        twoName.setHorizontalAlignment(SwingConstants.CENTER);
        twoName.setForeground(PP_FOREGROUND);
        
        teamNamesHeight += 12;
        teamWidth = (WindowVars.getUsableWidth() - splitPoint) / 2;
        oneX = splitPoint + WindowVars.X_BORDER_BUFFER;
        twoX = oneX + teamWidth;
        oneName.setBounds(oneX, WindowVars.Y_BORDER_BUFFER, teamWidth, teamNamesHeight);
        twoName.setBounds(twoX, WindowVars.Y_BORDER_BUFFER, teamWidth, teamNamesHeight);
        add(oneName);
        add(twoName);
        
        // Score
        int scoreHeight = 80;
        oneScore = new JLabel();
        oneScore.setFont(new Font("Segoe UI", Font.BOLD, scoreHeight));
        oneScore.setHorizontalAlignment(SwingConstants.CENTER);
        oneScore.setForeground(PP_FOREGROUND);
        
        twoScore = new JLabel();
        twoScore.setFont(new Font("Segoe UI", Font.BOLD, scoreHeight));
        twoScore.setHorizontalAlignment(SwingConstants.CENTER);
        twoScore.setForeground(PP_FOREGROUND);
        
        int scoreY = (usableHeight / 3) + WindowVars.Y_BORDER_BUFFER;
        oneScore.setBounds(oneX, scoreY, teamWidth, scoreHeight);
        twoScore.setBounds(twoX, scoreY, teamWidth, scoreHeight);
        add(oneScore);
        add(twoScore);
        
        /*
         * Buttons
         */
        // Team one
        int customLength = WindowVars.BUTTON_HEIGHT * 4;
        
        JButton oneMinus1 = new JButton("-1");
        oneMinus1.setFont(new Font("Segoe UI", Font.BOLD, customLength * 2 / 5));
        oneMinus1.setBackground(WindowVars.BACKGROUND_COLOR);
        oneMinus1.setForeground(WindowVars.FOREGROUND_COLOR);
        oneMinus1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    match.addScoreTeamOne(-1);
                }
                catch (ByeMatchException e)
                {
                    System.out.println("Bye Match Exception");
                }
                updateLabels();
            }
        });
        
        JButton onePlus1 = new JButton("+1");
        onePlus1.setFont(new Font("Segoe UI", Font.BOLD, customLength * 2 / 5));
        onePlus1.setBackground(WindowVars.BACKGROUND_COLOR);
        onePlus1.setForeground(WindowVars.FOREGROUND_COLOR);
        onePlus1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    match.addScoreTeamOne(1);
                }
                catch (ByeMatchException e)
                {
                    System.out.println("Bye Match Exception");
                }
                updateLabels();
            }
        });
        
        // Team two
        JButton twoMinus1 = new JButton("-1");
        twoMinus1.setFont(new Font("Segoe UI", Font.BOLD, customLength * 2 / 5));
        twoMinus1.setBackground(WindowVars.BACKGROUND_COLOR);
        twoMinus1.setForeground(WindowVars.FOREGROUND_COLOR);
        twoMinus1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    match.addScoreTeamTwo(-1);
                }
                catch (ByeMatchException e)
                {
                    System.out.println("Bye Match Exception");
                }
                updateLabels();
            }
        });
        
        JButton twoPlus1 = new JButton("+1");
        twoPlus1.setFont(new Font("Segoe UI", Font.BOLD, customLength * 2 / 5));
        twoPlus1.setBackground(WindowVars.BACKGROUND_COLOR);
        twoPlus1.setForeground(WindowVars.FOREGROUND_COLOR);
        twoPlus1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    match.addScoreTeamTwo(1);
                }
                catch (ByeMatchException e)
                {
                    System.out.println("Bye Match Exception");
                }
                updateLabels();
            }
        });
        
        int buttY = scoreY + scoreHeight + 40;
        int oneMidX = oneX + teamWidth / 2;
        int twoMidX = twoX + teamWidth / 2;
        int oneMinX = oneMidX - customLength - WindowVars.X_BORDER_BUFFER / 2;
        int onePluX = oneMidX + WindowVars.X_BORDER_BUFFER / 2;
        int twoMinX = twoMidX - customLength - WindowVars.X_BORDER_BUFFER / 2;
        int twoPluX = twoMidX + WindowVars.X_BORDER_BUFFER / 2;
        
        oneMinus1.setBounds(oneMinX, buttY, customLength, customLength);
        onePlus1.setBounds(onePluX, buttY, customLength, customLength);
        twoMinus1.setBounds(twoMinX, buttY, customLength, customLength);
        twoPlus1.setBounds(twoPluX, buttY, customLength, customLength);
        
        add(oneMinus1);
        add(onePlus1);
        add(twoMinus1);
        add(twoPlus1);
        
        /*
         * Server and winning label
         */
        int serverHeight = (teamNamesHeight - 12) * 2 / 3;
        serverLabel = new JLabel("Server");
        serverLabel.setFont(new Font("Segoe UI", Font.BOLD, serverHeight));
        serverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        serverLabel.setForeground(Color.CYAN);
        add(serverLabel);
        
        winnerLabel = new JLabel();
        winnerLabel.setFont(new Font("Segoe UI", Font.BOLD, teamNamesHeight - 12));
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setForeground(Color.RED);
        int winY = buttY + customLength + 30;
        winnerLabel.setBounds(oneX, winY, WindowVars.getUsableWidth() - splitPoint, teamNamesHeight);
        add(winnerLabel);
        
        this.updateLabels();
        
        /*
         * Navigation bar
         */
        // Quit button
        JButton quit = new JButton("Quit Match");
        quit.setBackground(WindowVars.BACKGROUND_COLOR);
        quit.setForeground(WindowVars.FOREGROUND_COLOR);
        quit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                quitMatch(display);
            }
        });
        
        int quitX = splitPoint + WindowVars.X_BORDER_BUFFER;
        int navigationY = usableHeight - WindowVars.BUTTON_HEIGHT + WindowVars.Y_BORDER_BUFFER;
        quit.setBounds(quitX, navigationY, WindowVars.BUTTON_WIDTH, WindowVars.BUTTON_HEIGHT);
        add(quit);
        
        // Record button
        JButton record = new JButton("Record Match");
        record.setBackground(WindowVars.BACKGROUND_COLOR);
        record.setForeground(WindowVars.FOREGROUND_COLOR);
        record.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                quitMatch(display);
            }
        });
        
        int recordX = usableWidth - WindowVars.BUTTON_WIDTH + WindowVars.X_BORDER_BUFFER;
        record.setBounds(recordX, navigationY, WindowVars.BUTTON_WIDTH, WindowVars.BUTTON_HEIGHT);
        add(record);
    }
    
    private void updateLabels()
    {
        try
        {
            // Update scores
            oneScore.setText(String.valueOf(match.getScoreTeamOne()));
            twoScore.setText(String.valueOf(match.getScoreTeamTwo()));
            
            // Update server
            if (match.getCurrentServer())
                serverLabel.setBounds(oneX, WindowVars.Y_BORDER_BUFFER + teamNamesHeight, teamWidth, teamNamesHeight);
            else
                serverLabel.setBounds(twoX, WindowVars.Y_BORDER_BUFFER + teamNamesHeight, teamWidth, teamNamesHeight);
            
            // Update winner
            PingPongTeam winner = match.getWinner();
            if (winner != null)
                winnerLabel.setText(winner.getName() + " is the WINNER!");
            else
                winnerLabel.setText("");
            
            revalidate();
            repaint();
        }
        catch (ByeMatchException e)
        {
            e.printStackTrace();
        }
    }
    
    public boolean switchPanel(JPanel in)
    {
        if (in == display || in == input)
        {
            cardLayout.next(leftSide);
            return true;
        }
        return false;
    }
    
    public boolean setMatchInfo(JPanel in, boolean edit, boolean initial, int win, int serve, boolean wbt)
    {
        if (in != input)
            return false;
        if (match == null)
        {
            PingPongTeam one = new PingPongTeam("Team 1", null, null);
            PingPongTeam two = new PingPongTeam("Team 2", null, null);
            match = new PingPongMatch(one, two, edit, -1);
        }
        int tempWin = match.getWinningScore();
        if (!match.setWinningScore(win))
        {
            return false;
        }
        if (!match.setServingAmount(serve))
        {
            match.setWinningScore(tempWin);
            return false;
        }
        match.setInitialServer(initial);
        match.setWinByTwo(wbt);
        
        cardLayout.show(leftSide, "display");
        
        return true;
    }
    
    public boolean quitMatch(JPanel displayPanel)
    {
        if (displayPanel == display)
        {
            frame.setContentPane(mainMenuPanel);
            frame.setTitle("Tournament Manager");
            frame.setVisible(true);
            return true;
        }
        return false;
    }
}
