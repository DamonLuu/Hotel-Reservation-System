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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GuestSignUpFrame extends JFrame
{	
	private ReservationManager model;

	/**
	 * Creates a frame that lets a guest sign up
	 * @param m model
	 */
	public GuestSignUpFrame(ReservationManager m) //PUT MODEL IN PARAMETERS 
	{
		model = m;
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
		
		JLabel firstNameLabel = new JLabel("      First Name:");
		JTextField firstNameField = new JTextField("John", 10);
		
		JLabel lastNameLabel = new JLabel("      Last Name:");
		JTextField lastNameField = new JTextField("Smith", 15);
		
		signUpPanel.add(loginLabel);
		signUpPanel.add(loginTextfield);
		signUpPanel.add(firstNameLabel);		
		signUpPanel.add(firstNameField);
		signUpPanel.add(lastNameLabel);
		signUpPanel.add(lastNameField);
		
		JPanel buttonPanel = new JPanel();
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	String loginID = loginTextfield.getText();
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				
				if(!loginID.isEmpty() && !lastName.isEmpty() && !lastName.isEmpty())
				{
					if(model.getAccountManager().getAccount(loginID) == null)
					{
					model.getAccountManager().addAccount(loginID, firstName, lastName);
					dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Login ID already chose\n Please try a different Login ID");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Every box must be filled out\n"+ "Please try again");
				}
			}
		});
		
		buttonPanel.add(signUpButton);
		
		this.add(welcomeLabel);
		this.add(signUpPanel);
		this.add(buttonPanel);
		this.setVisible(true);
	}
}
