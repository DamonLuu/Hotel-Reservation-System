import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.event.ChangeListener;

/**
 * The main GUI window that contains an interactive month view with clickable days
 * and a day panel on the right side that displays the events on the selected date.
 * This window contains components that are both "View" and "Controller" portion of MVC.
 * @author stevenyen
 *
 */
public class MonthViewFrame extends JFrame implements ChangeListener
{
	private JPanel monthPanel; //the panel that displays the clickable days of the month.
	private ReservationManager model; //this reference is used by "View" to call accessor and "Controller" to call mutator on the data model.
	private JButton[] dayButtons; //the clickable days as buttons.
	private JTextArea dayViewArea; //the panel on the right that displays events on the selected date.
	private JLabel CalendarTopLabel; //the month/year the current day is in, painted on the top of month view.
	
	private static String[] Days = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"}; //label for printing days of week.
	private static String[] Months ={ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}; //label for printing month.

	/**
	 * Constructs a window with a clickable calendar and day view.
	 * @param ev is the EventsManager (Data Mode) associated with this frame.
	 */
	public MonthViewFrame(ReservationManager ev)
	{
		model= ev; //Pass the reference of the associated data model to currentEV instant variable for storage.
		
		//create the panel
		monthPanel = new JPanel();
		//monthPanel.setSize(400,400);
		monthPanel.setLayout(new GridLayout(7,7)); //create a grid that contains the days

		//Paint the heading "Sun, Mon, Tue,..." on top
		for(int i=0;i<7;i++)
		{
			monthPanel.add(new JLabel(Days[i], SwingConstants.CENTER));
		}
		
		//Create 42 empty buttons in a grid with labels for day in month
		dayButtons = new JButton[42];
		for(int i=0;i<42;i++) 
		{
			dayButtons[i] = new JButton(" ");
			monthPanel.add(dayButtons[i]);
		}		
	
		//Populate button with current month days
		GregorianCalendar timeNow = new GregorianCalendar();
		GregorianCalendar todayDate = new GregorianCalendar(timeNow.get(GregorianCalendar.YEAR),timeNow.get(GregorianCalendar.MONTH),timeNow.get(GregorianCalendar.DAY_OF_MONTH));
		ev.selectDate(todayDate);
		ArrayList<String> buttonLabels = getLablesForMonth(todayDate);
		
		//label the buttons with day labels matching the current month.
		for(int i=0;i<42;i++)
		{
			dayButtons[i].setText(buttonLabels.get(i));
			
			dayButtons[i].setFocusPainted(false);
			dayButtons[i].setContentAreaFilled(false);
			dayButtons[i].setBorderPainted(false);
			
			
			if((buttonLabels.get(i)).equals(Integer.toString(todayDate.get(GregorianCalendar.DAY_OF_MONTH))))
			{
				dayButtons[i].setBorderPainted(true);
				//dayButtons[i].setBorder(BorderFactory.createLineBorder(Color.red));
			}		
		}
		
		//Add ActionListeners to each button. These buttons and associated listeners
		//represent "Controllers" for the "View" portion representing on the day
		//panel on the right side that displays the selected day's event.
		for(int i=0;i<42;i++)
		{
			
			JButton temp = dayButtons[i];
			
			temp.addActionListener(new ActionListener()
			{
				
				public void actionPerformed(ActionEvent e)
				{
					if(temp.getText().equals(" "))
					{
						//System.out.println("invalid date selected");
					}
					else
					{
						resetButtonBorders();
						int buttonInput = Integer.parseInt(temp.getText());
						GregorianCalendar previousDate = model.getSelectedDate();
						int year = previousDate.get(GregorianCalendar.YEAR);
						int month = previousDate.get(GregorianCalendar.MONTH);
			
						GregorianCalendar newDate = new GregorianCalendar(year,month,buttonInput);
						model.selectDate(newDate); //invokes mutator method on "Model". "Model" then notify "Views".
						temp.setBorderPainted(true);
						//temp.setBorder(BorderFactory.createLineBorder(Color.red));
					}
				}
			});
		}
		
		//The ">" button and the associated listener represents a "Controller"
		//This "Controller" calls mutator to change selectedDate of "Model"
		JButton nextButton = new JButton("Month >");
		nextButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GregorianCalendar oldDate = (GregorianCalendar)(model.getSelectedDate().clone());
				oldDate.add(GregorianCalendar.MONTH,1);
				
				int year = oldDate.get(GregorianCalendar.YEAR);
				int month = oldDate.get(GregorianCalendar.MONTH);
				int day = oldDate.get(GregorianCalendar.DAY_OF_MONTH);
				
				model.selectDate(new GregorianCalendar(year,month,day));
				
				for(JButton bb : dayButtons)
				{
					bb.setFocusPainted(false);
					bb.setContentAreaFilled(false);
					bb.setBorderPainted(false);
					
					if(bb.getText().equals(Integer.toString(day)))
					{
						bb.setBorderPainted(true);
					}
					
				}
				
			}
		});
		
		//The "<" button and the associated listener represents a "Controller"
		//This "Controller" calls mutator to change selectedDate of "Model"
		JButton prevButton = new JButton("< Month");
		prevButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GregorianCalendar oldDate = (GregorianCalendar)(model.getSelectedDate().clone());
				oldDate.add(GregorianCalendar.MONTH,-1);
				
				int year = oldDate.get(GregorianCalendar.YEAR);
				int month = oldDate.get(GregorianCalendar.MONTH);
				int day = oldDate.get(GregorianCalendar.DAY_OF_MONTH);
				
				model.selectDate(new GregorianCalendar(year,month,day));
				
				for(JButton bb : dayButtons)
				{
					bb.setFocusPainted(false);
					bb.setContentAreaFilled(false);
					bb.setBorderPainted(false);
					
					if(bb.getText().equals(Integer.toString(day)))
					{
						bb.setBorderPainted(true);
					}
					
				}				
			}
		});		
		
		
		//The ">" button and the associated listener represents a "Controller"
		//This "Controller" calls mutator to change selectedDate of "Model"
		JButton nextButtonYear = new JButton("Year >");
		nextButtonYear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GregorianCalendar oldDate = (GregorianCalendar)(model.getSelectedDate().clone());
				oldDate.add(GregorianCalendar.YEAR,1);
				
				int year = oldDate.get(GregorianCalendar.YEAR);
				int month = oldDate.get(GregorianCalendar.MONTH);
				int day = oldDate.get(GregorianCalendar.DAY_OF_MONTH);
				
				model.selectDate(new GregorianCalendar(year,month,day));
				
				for(JButton bb : dayButtons)
				{
					bb.setFocusPainted(false);
					bb.setContentAreaFilled(false);
					bb.setBorderPainted(false);
					
					if(bb.getText().equals(Integer.toString(day)))
					{
						bb.setBorderPainted(true);
					}
					
				}
				
			}
		});
		
		//The "<" button and the associated listener represents a "Controller"
		//This "Controller" calls mutator to change selectedDate of "Model"
		JButton prevButtonYear = new JButton("< Year");
		prevButtonYear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GregorianCalendar oldDate = (GregorianCalendar)(model.getSelectedDate().clone());
				oldDate.add(GregorianCalendar.YEAR,-1);
				
				int year = oldDate.get(GregorianCalendar.YEAR);
				int month = oldDate.get(GregorianCalendar.MONTH);
				int day = oldDate.get(GregorianCalendar.DAY_OF_MONTH);
				
				model.selectDate(new GregorianCalendar(year,month,day));
				
				for(JButton bb : dayButtons)
				{
					bb.setFocusPainted(false);
					bb.setContentAreaFilled(false);
					bb.setBorderPainted(false);
					
					if(bb.getText().equals(Integer.toString(day)))
					{
						bb.setBorderPainted(true);
					}
					
				}				
			}
		});		
		
		//Adds the next and previous buttons to this navigation panel.
		//then add this navigation panel to the left panel.
		JPanel navigationPanel = new JPanel();
		navigationPanel.setLayout(new FlowLayout());
		navigationPanel.add(prevButton);
		navigationPanel.add(nextButton);
		navigationPanel.add(prevButtonYear);
		navigationPanel.add(nextButtonYear);
		
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////
		//left panel that will contain the clickable month view and the navigation panel for organization
		
		JPanel leftPanel = new JPanel();
		
		int m = model.getSelectedDate().get(GregorianCalendar.MONTH);
		
		CalendarTopLabel = new JLabel(""+Months[m]+" "+model.getSelectedDate().get(GregorianCalendar.YEAR), SwingConstants.CENTER);
		CalendarTopLabel.setFont(new Font("Calibri", Font.BOLD, 40));
		CalendarTopLabel.setPreferredSize(new Dimension(420, 100));
		
		leftPanel.setLayout(new BorderLayout());
		
		leftPanel.add(CalendarTopLabel, BorderLayout.NORTH);

		leftPanel.add(monthPanel, BorderLayout.CENTER);

		leftPanel.add(navigationPanel, BorderLayout.SOUTH);
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Day Panel that will dispalay the events on the selected day.
		//This represents a "View" of the MVC "Model". Every time the "Controller" changes the selected
		//Date of the "Model", "Notify" method is invoked on the "View" prompting it to reset the events displayed.
		dayViewArea = new JTextArea(15,50); //measured in character width not pixels.
		dayViewArea.setText(model.getSelectedDayRooms());

		
///////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Right Panel that will contain the Day Panel, create button, and quit button. It will be displayed
		//on the right side of the main window, next to the month view.
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		rightPanel.add(dayViewArea,BorderLayout.CENTER);
		

///////////////////////////////////////////////////////////////////////////////////////////////////////////		
		this.setTitle("Manager Month View");
		
		this.setSize(1000, 500);
		
		this.add(leftPanel);
		this.add(rightPanel);
		this.setLayout(new FlowLayout());
		//this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.pack();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * Helping method that resets the style of all date buttons to unselected (plain with no border)
	 * This called every time a new day is selected. 
	 */
	public void resetButtonBorders()
	{
		for(JButton b: dayButtons)
		{
			b.setFocusPainted(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
		}
	}

	/**
	 * This represents the "Notify" method of the "View", and required by ChangeListener interface.
	 * This "Notify" method is called by the "Model" (EventManager ev) every time there data (which includes
	 * the TreeMap of allEvents and the selectedDate) is updated by "Controllers"
	 * this happens when either the user navigates to a different date or creates new event.
	 * This method inovkes accessor method on "Model" to retrieve newly updated method, then it refreshes the "Views"
	 * @param e ChangeEvent is the event object thrown by the event sources like the buttons.
	 */
	public void stateChanged(ChangeEvent e)
	{
		ArrayList<String> buttonLabels = getLablesForMonth(model.getSelectedDate());
		
		//reset the labels of the buttons.
		for(int i=0;i<42;i++)
		{
			dayButtons[i].setText(buttonLabels.get(i));
		}
		
		int m = model.getSelectedDate().get(GregorianCalendar.MONTH);
		
		CalendarTopLabel.setText(Months[m]+" "+model.getSelectedDate().get(GregorianCalendar.YEAR));
		dayViewArea.setText(model.getSelectedDayRooms());
		//dayViewArea.setText(model.dailyEvents(model.getSelectedDate()));		
	}
	
    /**
     * Helping method returning an ArrayList containing the labels for the days of the current month
     * This method is used in the CalendarFrame when labeling the days of month.
     * @param c the current day from which to determine the current month
     * @return ArrayList of labels for the days of the current month.
     */
    public static ArrayList<String> getLablesForMonth(GregorianCalendar c)
    {
		
		int currentDayOfMonth = c.get(Calendar.DAY_OF_MONTH); // as DAY OF MONTh
        GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        int firstWeekDayOfMonth = temp.get(Calendar.DAY_OF_WEEK);  //as DAY OF WEEK
        
        temp.add(Calendar.MONTH, 1);
        temp.add(Calendar.DAY_OF_YEAR, -1);
        int lastDayOfMonth = temp.get(Calendar.DAY_OF_MONTH);
        
        ArrayList<String> dayLabels = new ArrayList<>();
        //String[] dayLabels = new String[42];
       
         
        for(int i=1; i<firstWeekDayOfMonth;i++)
        {
            //System.out.print("    ");
            dayLabels.add(" ");
        }
        
        for(int i=1; i<=lastDayOfMonth;i++)
        {
            
            if(i==currentDayOfMonth)
                {
            		//System.out.printf("[%2d]",i);
            		dayLabels.add(i+"");
                }
            else
                {
            		//System.out.printf("  %2d",i);
            		dayLabels.add(i+"");
                }
        }
        
        int space2fill = 42-(firstWeekDayOfMonth-1)-lastDayOfMonth;
        
        for(int i=0;i<space2fill;i++)
        {
        	dayLabels.add(" ");
        }
        
        return dayLabels;
    }
}


	
	

