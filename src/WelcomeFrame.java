import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class WelcomeFrame extends JFrame
{
	
	//private Model model;
	
	public WelcomeFrame() //PUT MODEL IN PARAMETERS 
	{
		this.setTitle("Pen Pineapple Apple Pen Hotel Reservation System");
		this.setSize(500, 200);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel welcomeLabel = new JLabel("Welcome to the Pineapple Hotel");
		welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
		welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		
		JRadioButton hotelGuestRadio = new JRadioButton("Hotel Guest");
		JRadioButton hotelManagerRadio = new JRadioButton("Hotel Manager");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(hotelGuestRadio);
		buttonGroup.add(hotelManagerRadio);
		
		JPanel radioPanel = new JPanel();
		radioPanel.add(hotelManagerRadio);
		radioPanel.add(hotelGuestRadio);
		
		JPanel loginPanel = new JPanel();
		JLabel loginLabel = new JLabel("Login ID:");
		JTextField loginTextfield = new JTextField("loginID");
		JLabel passwordLabel = new JLabel("Password:");
		JPasswordField passwordField = new JPasswordField("password");
		loginPanel.add(loginLabel);
		loginPanel.add(loginTextfield);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordField);
		
		JPanel buttonPanel = new JPanel();
		JButton loginButton = new JButton("Login");
		JButton signUpButton = new JButton("Sign Up");
		buttonPanel.add(loginButton);
		buttonPanel.add(signUpButton);

		
		this.add(welcomeLabel);
		this.add(radioPanel);
		this.add(loginPanel);
		this.add(buttonPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
