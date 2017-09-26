package dufrois.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import dufrois.common.GameTypeEnum;
import dufrois.common.TournTypeEnum;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class HomePanel extends JPanel
{
    
    private JFrame frame;
    private int usableWidth;
    private int usableHeight;
    private int dropDownMenuWidth;
    private Color background = Color.LIGHT_GRAY;
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

        System.out.println("Usable Width: " + usableWidth + "\nWindow Width: " + WindowVars.WIDTH + "\nUsable Height: " + usableHeight + "\nWindow Height: " + WindowVars.HEIGHT);
        
        setBackground(Color.WHITE);
        setLayout(null);
        
        /*
         * Create the top section
         * The label, sport type selector, and tournament type selector
         */
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
        
        JComboBox<String> selectGameType = new JComboBox<String>();
        selectGameType.setFont(new Font("SansSerif", Font.PLAIN, 12));
        selectGameType.setBackground(background);
        selectGameType.setForeground(foreground);
        selectGameType.setModel(new DefaultComboBoxModel<String>(GameTypeEnum.getStringValues()));
        
        int xValSelect = usableWidth / 2 - dropDownMenuWidth / 2 + WindowVars.X_BORDER_BUFFER;
        int yValSelect = yValTitle + titleHeight;// + 10;//usableHeight / 2 - boxHeight / 2 + WindowVars.Y_BORDER_BUFFER;
        selectGameType.setBounds(xValSelect, yValSelect, dropDownMenuWidth, boxHeight);
        add(selectGameType);
        
        // Create tournament type selector
        JComboBox<String> selectTournType = new JComboBox<String>();
        selectTournType.setFont(new Font("SansSerif", Font.PLAIN, 12));
        selectTournType.setBackground(background);
        selectTournType.setForeground(foreground);
        selectTournType.setModel(new DefaultComboBoxModel<String>(TournTypeEnum.getStringValues()));
        
        selectGameType.setBounds(xValSelect, yValSelect + 2 * boxHeight, dropDownMenuWidth, boxHeight);
        add(selectTournType);
        
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
                GameTypeEnum gameType = (GameTypeEnum) selectGameType.getSelectedItem();
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
        
    }
}
