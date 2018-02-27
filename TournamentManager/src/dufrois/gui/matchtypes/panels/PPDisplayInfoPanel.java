package dufrois.gui.matchtypes.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import dufrois.gui.home.WindowVars;
import dufrois.matches.PingPongMatch;

public class PPDisplayInfoPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    
    private PPMatchPanel parentPanel;
    private PingPongMatch match;
    private int width, height;
    
    public PPDisplayInfoPanel(PPMatchPanel par, PingPongMatch ppm, int w, int h)
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
         * Display match information
         */
        
        /*
         * Navigation buttons
         */
        // Set button
        if (match.isEditableMatch())
        {
            JButton edit = new JButton("Edit");
            edit.setBackground(WindowVars.BACKGROUND_COLOR);
            edit.setForeground(WindowVars.FOREGROUND_COLOR);
            edit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) // Set the initial variables and match panel
                {
                    JPanel input = (JPanel) edit.getParent();
                    parentPanel.switchPanel(input);
                }
            });
            
            int editX = width - WindowVars.BUTTON_WIDTH;
            int navigationY = height - WindowVars.BUTTON_HEIGHT;
            edit.setBounds(editX, navigationY, WindowVars.BUTTON_WIDTH, WindowVars.BUTTON_HEIGHT);
            add(edit);
        }
    }
}
