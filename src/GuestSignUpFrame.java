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

public class GuestSignUpFrame extends JFrame
{
	
	//private Model model;
	
	public GuestSignUpFrame() //PUT MODEL IN PARAMETERS 
	{
		this.setTitle("Pen Pineapple Apple Pen Hotel Reservation System");
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel welcomeLabel = new JLabel("Guest Sign Up");
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
		
		JPanel signUpPanel = new JPanel();
		signUpPanel.setLayout(new GridLayout(2, 2));
		JLabel loginLabel = new JLabel("      Login ID:");
		JTextField loginTextfield = new JTextField("123", 8);
		JLabel guestNameLabel = new JLabel("      Guest Name:");
		JTextField nameField = new JTextField("John Smith", 15);
		signUpPanel.add(loginLabel);
		signUpPanel.add(loginTextfield);
		signUpPanel.add(guestNameLabel);
		//loginPanel.add(passwordField);
		signUpPanel.add(nameField);
		
		JPanel buttonPanel = new JPanel();
		//JButton loginButton = new JButton("Login");
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				dispose();
			}
		});
		
		//buttonPanel.add(loginButton);
		buttonPanel.add(signUpButton);

		
		this.add(welcomeLabel);
		//this.add(radioPanel);
		this.add(signUpPanel);
		this.add(buttonPanel);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}
