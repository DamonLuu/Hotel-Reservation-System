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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GuestSignUpFrame extends JFrame
{
	
	private ReservationManager model;
	
	public GuestSignUpFrame(ReservationManager m)
	{
		this();
		model = m;
	}
	
	public GuestSignUpFrame() //PUT MODEL IN PARAMETERS 
	{
		this.setTitle("Pen Pineapple Apple Pen Hotel Reservation System");
		this.setSize(250, 200);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel welcomeLabel = new JLabel("Guest Sign Up");
		welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
		welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		welcomeLabel.setPreferredSize(new Dimension(300, 100));

		JPanel signUpPanel = new JPanel();
		signUpPanel.setLayout(new GridLayout(3, 2));
		JLabel loginLabel = new JLabel("      Login ID:");
		JTextField loginTextfield = new JTextField("123", 8);
		
		JLabel guestNameLabel = new JLabel("      First Name:");
		JTextField nameField = new JTextField("John", 10);
		
		JLabel guestLastLabel = new JLabel("      Last Name:");
		JTextField lastNameField = new JTextField("Smith", 15);
		
		signUpPanel.add(loginLabel);
		signUpPanel.add(loginTextfield);
		signUpPanel.add(guestNameLabel);		
		signUpPanel.add(nameField);
		signUpPanel.add(guestLastLabel);
		signUpPanel.add(lastNameField);
		
		JPanel buttonPanel = new JPanel();
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	String loginID = loginTextfield.getText();
				String guestName = nameField.getText();
				String lastName = lastNameField.getText();
				
				if(!loginID.isEmpty() && !guestName.isEmpty() && !lastName.isEmpty())
				{
					//Account guest = new Account(loginID, guestName, lastName);
					model.getAccountManager().addAccount(loginID, guestName, lastName);
					//System.out.println(guest.toString());
					dispose();
				}
				else
				{
					emptyFieldError();
				}
			}
		});
		
		buttonPanel.add(signUpButton);

		
		this.add(welcomeLabel);
		this.add(signUpPanel);
		this.add(buttonPanel);
		//this.setResizable(false);
		this.setVisible(true);
	}
	
	public void emptyFieldError()
	{
		JFrame emptyErrorFrame = new JFrame("Empty Error");
		emptyErrorFrame.setLayout(new BorderLayout());
		JLabel errorText = new JLabel("Fill Out Every Box!!", SwingConstants.CENTER);
		errorText.setPreferredSize(new Dimension(300,100));
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				emptyErrorFrame.dispose();
			}
		});

		emptyErrorFrame.add(errorText, BorderLayout.NORTH);
		emptyErrorFrame.add(closeButton, BorderLayout.SOUTH);
		emptyErrorFrame.pack();
		emptyErrorFrame.setVisible(true);
	}
	
}
