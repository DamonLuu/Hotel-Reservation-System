import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GuestLoginFrame extends JFrame
{
	
	//private Model model;
	
	public GuestLoginFrame() //PUT MODEL IN PARAMETERS 
	{
		this.setTitle("Pen Pineapple Apple Pen Hotel Reservation System");
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel welcomeLabel = new JLabel("Guest Login");
		welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
		welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		welcomeLabel.setPreferredSize(new Dimension(500, 100));
			
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(2, 2));
		JLabel loginLabel = new JLabel("      Login ID:");
		JTextField loginTextfield = new JTextField("123", 8);
		JLabel guestNameLabel = new JLabel("      Guest Name:");
		JTextField nameField = new JTextField("John Smith", 15);
		loginPanel.add(loginLabel);
		loginPanel.add(loginTextfield);
		loginPanel.add(guestNameLabel);
		loginPanel.add(nameField);

		JPanel buttonPanel = new JPanel();
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener()  //if login is successful
		{		
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				GuestReservationOptionFrame();
			}
		});
		
		buttonPanel.add(loginButton);	
		this.add(welcomeLabel);
		this.add(loginPanel);
		this.add(buttonPanel);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void GuestReservationOptionFrame()
	{
		JFrame guestReservationOptionFrame = new JFrame();
		//guestReservationOptionFrame.setSize(350, 350);
		//guestReservationOptionFrame.setLayout(new BoxLayout(guestReservationOptionFrame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(350, 350));
		
		JLabel optionLabel = new JLabel("Choose an Option", SwingConstants.CENTER);
		optionLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		optionLabel.setPreferredSize(new Dimension(325, 100));
		
		JButton makeReservationButton = new JButton("Make a Reservation");
		makeReservationButton.setPreferredSize(new Dimension(300,100));
		
		JButton viewOrCancelReservationButton = new JButton("View/Cancel a Reservation");
		viewOrCancelReservationButton.setPreferredSize(new Dimension(300,100));
		
		container.add(optionLabel);
		container.add(makeReservationButton);
		container.add(viewOrCancelReservationButton);
		
		guestReservationOptionFrame.setResizable(false);
		guestReservationOptionFrame.add(container);
		guestReservationOptionFrame.pack();
		guestReservationOptionFrame.setVisible(true);
	}
}
