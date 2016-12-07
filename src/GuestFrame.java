import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Creates a frame that lets guest choose returning guest or sign up 
 * @author damonluu
 *
 */
public class GuestFrame extends JFrame
{
	private ReservationManager model;

	/**
	 * Constructor for the guest frame
	 * @param m the model
	 */
	public GuestFrame(ReservationManager m)
	{
		model = m;
		this.setTitle("Guest Option Frame");

		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(300, 300));

		JLabel welcomeLabel = new JLabel("Guest Options", SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		welcomeLabel.setPreferredSize(new Dimension(325, 75));

		JButton returningGuestButton = new JButton("Returning Guest / Login");
		returningGuestButton.setPreferredSize(new Dimension(300,100));
		returningGuestButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				new GuestLoginFrame(model);
			}
		});

		JButton newGuestButton = new JButton("New Guest");
		newGuestButton.setPreferredSize(new Dimension(300,100));
		newGuestButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				new GuestSignUpFrame(model);
			}
		});

		container.add(welcomeLabel);
		container.add(returningGuestButton);
		container.add(newGuestButton);

		add(container);
		pack();
		this.setResizable(false);
		this.setVisible(true);
	}
}