package dufrois.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import dufrois.common.SportTypeEnum;
import dufrois.common.TournTypeEnum;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

/**
 * 
 * 
 * @author Christian Dufrois
 * @version 25.09.2017
 */
public class HomePanel extends JPanel
{
    
    private JFrame frame;
    private int usableWidth;
    private int usableHeight;
    private int dropDownMenuWidth;
    private Color background = Color.WHITE;
    private Color foreground = Color.BLACK;
    
    /**
     * Create the panel.
     */
    public HomePanel(JFrame f)
    {
        frame = f;
        usableWidth = WindowVars.getUsableWidth();
        usableHeight = WindowVars.getUsableHeight();
        dropDownMenuWidth = 250;
        
        System.out.println("Usable Width: " + usableWidth + "\nWindow Width: " + WindowVars.WIDTH + "\nUsable Height: "
                + usableHeight + "\nWindow Height: " + WindowVars.HEIGHT);
        
        setBackground(background);
        setLayout(null);
        
        /*
         * Create the top section The label, sport type selector, and tournament type selector
         */
        int spacer = 20; // Space between lines
        int titleWidth = 400; // Make size dependent
        int titleHeight = 48; // Make size dependent
        
        JLabel titleLabel = new JLabel("New Tournament");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBackground(background);
        titleLabel.setForeground(foreground);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, titleHeight));
        
        int xValTitle = usableWidth / 2 - titleWidth / 2 + WindowVars.X_BORDER_BUFFER;
        int yValTitle = WindowVars.Y_BORDER_BUFFER;// usableHeight * 3 / 10 - titleHeight / 2 + WindowVars.Y_BORDER_BUFFER;
        titleLabel.setBounds(xValTitle, yValTitle, titleWidth, titleHeight);
        add(titleLabel);
        
        // Create the game type selector
        int boxHeight = WindowVars.BUTTON_HEIGHT;
        
        JComboBox<String> selectSport = new JComboBox<String>();
        selectSport.setFont(new Font("SansSerif", Font.PLAIN, 12));
        selectSport.setBackground(background);
        selectSport.setForeground(foreground);
        selectSport.setModel(new DefaultComboBoxModel<String>(SportTypeEnum.getStringValues()));
        
        int xValSelect = usableWidth / 2 - dropDownMenuWidth / 2 + WindowVars.X_BORDER_BUFFER;
        int yValSelect = yValTitle + titleHeight + spacer;// + 10;//usableHeight / 2 - boxHeight / 2 + WindowVars.Y_BORDER_BUFFER;
        selectSport.setBounds(xValSelect, yValSelect, dropDownMenuWidth, boxHeight);
        add(selectSport);
        
        // Create tournament type selector
        JComboBox<String> selectTourn = new JComboBox<String>();
        selectTourn.setFont(new Font("SansSerif", Font.PLAIN, 12));
        selectTourn.setBackground(background);
        selectTourn.setForeground(foreground);
        selectTourn.setModel(new DefaultComboBoxModel<String>(TournTypeEnum.getStringValues()));
        
        yValSelect = yValSelect + boxHeight + spacer;
        selectTourn.setBounds(xValSelect, yValSelect, dropDownMenuWidth, boxHeight);
        add(selectTourn);
        
        /*
         * Navigation bar at bottom
         */
        // Create the next button
        int nextWidth = 100;
        
        JButton nextButton = new JButton("Next");
        nextButton.setBackground(background);
        nextButton.setForeground(foreground);
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0)
            {
                frame.setContentPane(new JPanel());
                frame.setVisible(true);
                SportTypeEnum gameType = (SportTypeEnum) selectSport.getSelectedItem();
                switch (gameType) {
                case PINGPONG:
                    frame.setTitle("Ping Pong");
                    break;
                case BASIC:
                    frame.setTitle("Basic");
                    break;
                default:
                    frame.setTitle("Basic");
                    break;
                }
            }
        });
        
        int xValNext = usableWidth / 2 - nextWidth / 2 + WindowVars.X_BORDER_BUFFER;
        int yValNext = usableHeight - WindowVars.BUTTON_HEIGHT + WindowVars.Y_BORDER_BUFFER;
        nextButton.setBounds(xValNext, yValNext, nextWidth, WindowVars.BUTTON_HEIGHT);
        add(nextButton);
        
        
        SavedTournaments list = new SavedTournaments(this);
        list.setBounds(300, 300, 800, 100);
        add(list);
        
    }
}
