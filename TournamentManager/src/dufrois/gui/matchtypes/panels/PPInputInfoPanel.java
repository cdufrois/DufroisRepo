package dufrois.gui.matchtypes.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import dufrois.gui.home.WindowVars;
import dufrois.matches.PingPongMatch;

public class PPInputInfoPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    
    private PPMatchPanel parentPanel;
    private PingPongMatch match;
    private int width, height;
    
    public PPInputInfoPanel(PPMatchPanel par, PingPongMatch ppm, int w, int h)
    {
        /*
         * Initialize variables
         */
        parentPanel = par;
        match = ppm;
        width = w;
        height = h;
        setBackground(PPMatchPanel.PP_BACKGROUND);
        setLayout(null);
        
        /*
         * Input match information
         */
        
        
        
        /*
         * Navigation buttons
         */
        JButton cancel = new JButton("Cancel");
        cancel.setBackground(WindowVars.BACKGROUND_COLOR);
        cancel.setForeground(WindowVars.FOREGROUND_COLOR);
        cancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                JPanel input = (JPanel) cancel.getParent();
                parentPanel.switchPanel(input);
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
                JPanel input = (JPanel) set.getParent();
                parentPanel.setMatchInfo(input, true, true, 21, 5, true);
            }
        });
        
        int setX = width - WindowVars.BUTTON_WIDTH;
        set.setBounds(setX, navigationY, WindowVars.BUTTON_WIDTH, WindowVars.BUTTON_HEIGHT);
        add(set);
    }
}
