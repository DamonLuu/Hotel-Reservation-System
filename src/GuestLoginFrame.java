import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
		
		//JRadioButton hotelGuestRadio = new JRadioButton("Hotel Guest");
		//JRadioButton hotelManagerRadio = new JRadioButton("Hotel Manager");
		
		//ButtonGroup buttonGroup = new ButtonGroup();
		//buttonGroup.add(hotelGuestRadio);
		//buttonGroup.add(hotelManagerRadio);
		
		//JPanel radioPanel = new JPanel();
		//radioPanel.add(hotelManagerRadio);
		//radioPanel.add(hotelGuestRadio);
		
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
		//loginPanel.add(passwordField);
		
		JPanel buttonPanel = new JPanel();
		JButton loginButton = new JButton("Login");
		//JButton signUpButton = new JButton("Sign Up");
		buttonPanel.add(loginButton);
		//buttonPanel.add(signUpButton);

		
		this.add(welcomeLabel);
		//this.add(radioPanel);
		this.add(loginPanel);
		this.add(buttonPanel);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}
