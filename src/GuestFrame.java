
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class GuestFrame extends JFrame
{
	//private Model model;

	public GuestFrame() //PUT MODEL IN PARAMETERS 
	{
		this.setTitle("Guest Option Frame");
		//this.setSize(500, 400);


		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(350, 350));
		//container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		JLabel welcomeLabel = new JLabel("Guest Options", SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		welcomeLabel.setPreferredSize(new Dimension(325, 100));

		JButton returningGuestButton = new JButton("Returning Guest");
		returningGuestButton.setPreferredSize(new Dimension(300,100));
		returningGuestButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				GuestLoginFrame frame = new GuestLoginFrame();
			}
		});

		JButton newGuestButton = new JButton("New Guest");
		newGuestButton.setPreferredSize(new Dimension(300,100));
		newGuestButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				GuestSignUpFrame frame = new GuestSignUpFrame();
			}
		});

		container.add(welcomeLabel);
		container.add(returningGuestButton);
		container.add(newGuestButton);

		add(container);


		pack();

		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}



