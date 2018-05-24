package dufrois.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SavedTournaments extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private JPanel panel;
	
	public SavedTournaments(JPanel p)
	{
		
		panel = p;
		
		setLayout(null);
		
		
		JLabel title = new JLabel("Saved Tournaments");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBackground(Color.WHITE);
		title.setForeground(Color.BLACK);
		title.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		title.setBounds(400, 50, 400, 48);
		add(title);
	}
}
