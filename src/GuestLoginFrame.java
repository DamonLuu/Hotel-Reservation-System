import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

	private ReservationManager model;

	public GuestLoginFrame(ReservationManager m)
	{
		this();
		model = m;
	}

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
		loginPanel.setLayout(new GridLayout(3, 2));
		JLabel loginLabel = new JLabel("      Login ID:");
		JTextField loginTextfield = new JTextField("123", 8);

		JLabel firstNameLabel = new JLabel("      First Name:");
		JTextField firstNameField = new JTextField("John", 10);

		JLabel lastNameLabel = new JLabel("      Last Name:");
		JTextField lastNameField = new JTextField("Smith", 15);

		loginPanel.add(loginLabel);
		loginPanel.add(loginTextfield);
		loginPanel.add(firstNameLabel);
		loginPanel.add(firstNameField);
		loginPanel.add(lastNameLabel);
		loginPanel.add(lastNameField);

		JPanel buttonPanel = new JPanel();
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener()  //if login is successful
				{		
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String loginID = loginTextfield.getText();
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();

				Account temp = new Account(loginID, firstName, lastName);
				if (model.getAccountManager().checkValidAccount(temp, firstName, lastName))
				{
					dispose();
					GuestReservationOptionFrame();
				}
				else
				{
					incorrectLoginFrame();
				}
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
		
		GregorianCalendar today = new GregorianCalendar();
		String todayMonth = "" + (today.get(Calendar.MONTH)+1);
		String todayDay = "" + today.get(Calendar.DAY_OF_MONTH);
		String todayYear = "" + today.get(Calendar.YEAR);
		String niceTodayDate = todayMonth + "/" + todayDay + "/" + todayYear;
		
		GregorianCalendar nextWeek = new GregorianCalendar();
		
		nextWeek.add(Calendar.DAY_OF_MONTH, 7);
		
		String nextWeekMonth = "" + (nextWeek.get(Calendar.MONTH)+1);
		String nextWeekDay = "" + nextWeek.get(Calendar.DAY_OF_MONTH);
		String nextWeekYear = "" + nextWeek.get(Calendar.YEAR);
		String niceNextWeekDate = nextWeekMonth + "/" + nextWeekDay + "/" + nextWeekYear;
		
		JTextField checkInText = new JTextField(niceTodayDate);		
		JLabel checkOutLabel = new JLabel("Check Out Date:");
		JTextField checkOutText = new JTextField(niceNextWeekDate);

		datePanel.add(checkInLabel);
		datePanel.add(checkInText);
		datePanel.add(checkOutLabel);
		datePanel.add(checkOutText);
		topPanel.add(datePanel);

		String button = "";
		JRadioButton economyButton = new JRadioButton("Economy $80");
		JRadioButton luxuryButton = new JRadioButton("Luxury $200");
		ButtonGroup buttonGroup = new ButtonGroup();

		if (economyButton.isSelected())
		{
			button = "Economy";
		}
		else if (luxuryButton.isSelected())
		{
			button = "Luxury";
		}

		buttonGroup.add(economyButton);


		buttonGroup.add(luxuryButton);
		buttonGroup.setSelected(luxuryButton.getModel(), true);

		JPanel buttonPanel = new JPanel();
		JLabel roomType = new JLabel("Room Type: ");
		buttonPanel.add(roomType);
		buttonPanel.add(economyButton);
		buttonPanel.add(luxuryButton);
		topPanel.add(buttonPanel);

		//JTextArea changeLater = new JTextArea("Change this later to show all available rooms");
		JTextArea changeLater = new JTextArea(model.findRoom(checkInText.getText(), checkOutText.getText(), button));
		changeLater.setPreferredSize(new Dimension(275,250));

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());

		JPanel roomNumberPanel = new JPanel();
		JLabel enterRoomLabel = new JLabel("Enter the room number to reserve.");
		JTextField roomNumberTextArea = new JTextField("Room #");
		roomNumberPanel.add(enterRoomLabel);
		roomNumberPanel.add(roomNumberTextArea);


		JPanel bottomButtonPanel = new JPanel();
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{

			}
		});

		JButton moreButton = new JButton("More Reservations?");
		moreButton.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{

			}
		});

		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() 
		{			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				makeReservationFrame.dispose();
			}
		});

		bottomButtonPanel.add(confirmButton);
		bottomButtonPanel.add(moreButton);
		bottomButtonPanel.add(doneButton);

		southPanel.add(roomNumberPanel, BorderLayout.NORTH);
		southPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

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
		cancelButton.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{

			}
		});

		cancelReservationFrame.add(topLabel, BorderLayout.NORTH);
		cancelReservationFrame.add(changeLater, BorderLayout.CENTER);
		cancelReservationFrame.add(cancelButton, BorderLayout.SOUTH);

		cancelReservationFrame.pack();
		cancelReservationFrame.setVisible(true);
	}
	
	public void incorrectLoginFrame()
	{
		JFrame incorrectLoginFrame = new JFrame("Incorrect Login Info");
		incorrectLoginFrame.setLayout(new BorderLayout());
		JLabel errorText = new JLabel("Login information is incorrect, please try again", SwingConstants.CENTER);
		errorText.setPreferredSize(new Dimension(300,100));
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				incorrectLoginFrame.dispose();
			}
		});

		incorrectLoginFrame.add(errorText, BorderLayout.NORTH);
		incorrectLoginFrame.add(closeButton, BorderLayout.SOUTH);
		incorrectLoginFrame.pack();
		incorrectLoginFrame.setVisible(true);
		
	}
}
