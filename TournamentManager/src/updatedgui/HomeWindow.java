package updatedgui;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import dufrois.tournaments.TournamentTypeEnum;
import dufrois.common.SportTypeEnum;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeWindow extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JComboBox<TournamentTypeEnum> tournTypeDropdown;
	private JComboBox<SportTypeEnum> sportTypeDropdown;
	
	/**
	 * Create the panel.
	 */
	public HomeWindow()
	{
		setBackground(Color.LIGHT_GRAY);
		
		JLabel label = new JLabel("Tournament Manager");
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("SansSerif", Font.BOLD, 25));
		
		JButton createButton = new JButton("Create");
		createButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouse) {
				
				TournamentTypeEnum tourn = (TournamentTypeEnum) tournTypeDropdown.getSelectedItem();
				System.out.print(tourn.toString() + " of ");
				
				SportTypeEnum sport = (SportTypeEnum) sportTypeDropdown.getSelectedItem();
				System.out.println(sport.toString());
				
			}
		});
		createButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		tournTypeDropdown = new JComboBox<TournamentTypeEnum>();
		tournTypeDropdown.setModel(new DefaultComboBoxModel<TournamentTypeEnum>(TournamentTypeEnum.values()));
		
		sportTypeDropdown = new JComboBox<SportTypeEnum>();
		sportTypeDropdown.setModel(new DefaultComboBoxModel<SportTypeEnum>(SportTypeEnum.values()));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
					.addGap(15))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(808, Short.MAX_VALUE)
					.addComponent(createButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(785, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(sportTypeDropdown, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tournTypeDropdown, Alignment.TRAILING, 0, 200, Short.MAX_VALUE))
					.addGap(15))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(label)
					.addGap(100)
					.addComponent(tournTypeDropdown, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(sportTypeDropdown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 358, Short.MAX_VALUE)
					.addComponent(createButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		setLayout(groupLayout);
		
	}
}
