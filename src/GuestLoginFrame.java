import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
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
		makeReservationButton.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				makeReservationFrame();
			}
		});
		
		JButton viewOrCancelReservationButton = new JButton("View/Cancel a Reservation");
		viewOrCancelReservationButton.setPreferredSize(new Dimension(300,100));
		viewOrCancelReservationButton.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				viewOrCancelReservationFrame();
			}
		});
		
		container.add(optionLabel);
		container.add(makeReservationButton);
		container.add(viewOrCancelReservationButton);
		
		guestReservationOptionFrame.setResizable(false);
		guestReservationOptionFrame.add(container);
		guestReservationOptionFrame.pack();
		guestReservationOptionFrame.setVisible(true);
	}
	
	public void makeReservationFrame()
	{
		JFrame makeReservationFrame = new JFrame("Make a Reservation");
		makeReservationFrame.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));		
		
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new GridLayout(2, 2));
		
		JLabel checkInLabel = new JLabel("Check In Date:");
		JTextField checkInText = new JTextField("11/25/2016");		
		JLabel checkOutLabel = new JLabel("Check Out Date:");
		JTextField checkOutText = new JTextField("11/27/2016");
		
		datePanel.add(checkInLabel);
		datePanel.add(checkInText);
		datePanel.add(checkOutLabel);
		datePanel.add(checkOutText);
		topPanel.add(datePanel);
		
		JRadioButton economyButton = new JRadioButton("Economy $80");
		JRadioButton luxuryButton = new JRadioButton("Luxury $200");
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(economyButton);
		buttonGroup.add(luxuryButton);
		buttonGroup.setSelected(luxuryButton.getModel(), true);
		
		JPanel buttonPanel = new JPanel();
		JLabel roomType = new JLabel("Room Type: ");
		buttonPanel.add(roomType);
		buttonPanel.add(economyButton);
		buttonPanel.add(luxuryButton);
		topPanel.add(buttonPanel);
		
		JTextArea changeLater = new JTextArea("Change this later to show all available rooms");
		changeLater.setPreferredSize(new Dimension(275,250));
		
		JPanel southPanel = new JPanel();
		JButton confirmButton = new JButton("Confirm Transaction");
		JButton completeButton = new JButton("Return");
		southPanel.add(confirmButton);
		southPanel.add(completeButton);
		
		
		makeReservationFrame.add(topPanel, BorderLayout.NORTH);
		makeReservationFrame.add(changeLater, BorderLayout.CENTER);
		makeReservationFrame.add(southPanel, BorderLayout.SOUTH);
		
		makeReservationFrame.pack();
		//makeReservationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		makeReservationFrame.setVisible(true);		
	}
	
	public void viewOrCancelReservationFrame()
	{
		JFrame cancelReservationFrame = new JFrame("View/Cancel Reservation");
		cancelReservationFrame.setLayout(new BorderLayout());
		
		JLabel topLabel = new JLabel("Listed below is all your reservations");
		
		JTextArea changeLater = new JTextArea("Change this later to show all reservations");
		changeLater.setPreferredSize(new Dimension(275,250));
		
		JButton cancelButton = new JButton("Cancel Selected Reservation");
		
		cancelReservationFrame.add(topLabel, BorderLayout.NORTH);
		cancelReservationFrame.add(changeLater, BorderLayout.CENTER);
		cancelReservationFrame.add(cancelButton, BorderLayout.SOUTH);
		
		cancelReservationFrame.pack();
		cancelReservationFrame.setVisible(true);
	}
}
