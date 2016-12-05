import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GuestLoginFrame extends JFrame
{

	private ReservationManager model;
	private String button;
	

	public GuestLoginFrame(ReservationManager m) //PUT MODEL IN PARAMETERS 
	{
		model = m;
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
				//System.out.println("login pressed");
				String loginID = loginTextfield.getText();
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();

				Account temp = new Account(loginID, firstName, lastName);
				if (model.getAccountManager().checkValidAccount(temp, firstName, lastName))
				{
					dispose();
					model.setAccount(temp);
					GuestReservationOptionFrame();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Login information is incorrect\n"
							+ "Please try again");
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
				System.out.println("make reservation pushed");
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
				System.out.println("cancel button pushed");
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
		JTextArea listAllRoomsTextArea = new JTextArea();


		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));		

		JPanel datePanel = new JPanel();
		datePanel.setLayout(new GridLayout(2, 2));

		JLabel checkInLabel = new JLabel("Check In Date:");

		GregorianCalendar today = new GregorianCalendar();
		today.add(Calendar.DATE, 1);
		String niceTodayDate = model.gregorianToString(today);

		GregorianCalendar nextWeek = new GregorianCalendar();
		nextWeek.add(Calendar.DAY_OF_MONTH, 8);

		String niceNextWeekDate = model.gregorianToString(nextWeek);

		JTextField checkInText = new JTextField(niceTodayDate);		
		JLabel checkOutLabel = new JLabel("Check Out Date:");
		JTextField checkOutText = new JTextField(niceNextWeekDate);

		datePanel.add(checkInLabel);
		datePanel.add(checkInText);
		datePanel.add(checkOutLabel);
		datePanel.add(checkOutText);
		topPanel.add(datePanel);

		JButton luxuryButton = new JButton("Luxury $200");
		luxuryButton.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (model.validDateFormat(checkInText.getText(), checkOutText.getText()))
				{
					try 
					{
						if(model.dateBeforeCheck(checkInText.getText(), checkOutText.getText()))
						{
							JOptionPane.showMessageDialog(null, "Check-in or Check-out date is before today");
						}
						else
						{
							if(model.dateLongerThan60(checkInText.getText(), checkOutText.getText()))
							{
								JOptionPane.showMessageDialog(null, "Reservation cannot be longer than 60 days");
							}
							else 
							{
								listAllRoomsTextArea.setText(model.findRoom(checkInText.getText(), checkOutText.getText(), "Luxury"));
								//System.out.println(checkInText.getText());
							}
						}
					}
					catch (ParseException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Date format is incorrect\n"
							+ "Must be MM/DD/YY\n"
							+ "Example: 01/01/2017");
				}
				button = "Luxury";
				
			}
		});

		JButton economyButton = new JButton("Economy $80");
		economyButton.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (model.validDateFormat(checkInText.getText(), checkOutText.getText()))
				{
					try 
					{
						if(model.dateBeforeCheck(checkInText.getText(), checkOutText.getText()))
						{
							JOptionPane.showMessageDialog(null, "Check-in or Check-out date is before today");
						}
						else
						{
							if(model.dateLongerThan60(checkInText.getText(), checkOutText.getText()))
							{
								JOptionPane.showMessageDialog(null, "Reservation cannot be longer than 60 days");
							}
							else 
							{
								listAllRoomsTextArea.setText(model.findRoom(checkInText.getText(), checkOutText.getText(), "Economy"));
							}
						}
					}
					catch (ParseException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Date format is incorrect\n"
							+ "Must be MM/DD/YY\n"
							+ "Example: 01/01/2017");
				}
				button = "Economy";
			}
		});

		JPanel buttonPanel = new JPanel();
		JLabel roomType = new JLabel("Room Type: ");
		buttonPanel.add(roomType);
		buttonPanel.add(luxuryButton);
		buttonPanel.add(economyButton);
		topPanel.add(buttonPanel);


		listAllRoomsTextArea.setPreferredSize(new Dimension(275,250));

		//testing change listener
		ChangeListener changeListener = new ChangeListener() 
		{	
			@Override
			public void stateChanged(ChangeEvent e) 
			{
			//	final String currentButton = new String(button);
				String allAvailable = model.findRoom(checkInText.getText(), checkOutText.getText(), button);
				listAllRoomsTextArea.setText(allAvailable);
			}
		};
		model.attach(changeListener);

		//

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());

		JPanel roomNumberPanel = new JPanel();
		JLabel enterRoomLabel = new JLabel("Enter the room number to reserve.");
		JTextField roomNumberTextArea = new JTextField("Room #", 5);
		roomNumberPanel.add(enterRoomLabel);
		roomNumberPanel.add(roomNumberTextArea);


		JPanel bottomButtonPanel = new JPanel();
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				model.makeReservation(checkInText.getText(), checkOutText.getText(), roomNumberTextArea.getText());
				JOptionPane.showMessageDialog(null, "Your Room Has Been Reserved");
				
			}
		});

		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() 
		{			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				makeReservationFrame.dispose();
				receiptFrame();
			}
		});

		bottomButtonPanel.add(confirmButton);
		bottomButtonPanel.add(doneButton);

		southPanel.add(roomNumberPanel, BorderLayout.NORTH);
		southPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

		makeReservationFrame.add(topPanel, BorderLayout.NORTH);
		makeReservationFrame.add(listAllRoomsTextArea, BorderLayout.CENTER);
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
		System.out.println("at cancel panel");
		JTextArea changeLater = new JTextArea(model.getAccount().reservationToString());
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


	public void receiptFrame()
	{
		JFrame receiptFrame = new JFrame("Receipt");
		receiptFrame.setLayout(new BorderLayout());
		JTextArea receiptInfo = new JTextArea("put receipt stuff here");
		JScrollPane speakerScroll = new JScrollPane(receiptInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		receiptInfo.setPreferredSize(new Dimension(300,600));
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				receiptFrame.dispose();
			}
		});
		receiptFrame.add(speakerScroll, BorderLayout.NORTH);
		receiptFrame.add(closeButton, BorderLayout.SOUTH);
		receiptFrame.pack();
		receiptFrame.setVisible(true);	
	}
}
