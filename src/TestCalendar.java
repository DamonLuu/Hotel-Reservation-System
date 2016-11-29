import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 *	enum for each of the months
 */
enum MONTHS
{
	January, February, March, April, May, June, July, August, September, October, November, December;
}

/**
 * enum for each of the week days
 *
 */
enum DAYS
{
	Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday;
}

/**
 * This class is the view portion of the MVC
 * It will show a frame that has multiple panels
 * @author Damon Luu
 * copyright 2016
 * version 1
 */
public class TestCalendar extends JPanel implements ChangeListener
{
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
	private TemporaryModel model;
	private static DAYS[] arrayOfDays = DAYS.values();
	private static MONTHS[] arrayOfMonths = MONTHS.values();
	private ArrayList<JButton> dayButtons = new ArrayList<>();
	private int lastHighlight = -1;
	private int currentMonth;
	private JTextField monthTextField;
	private JPanel days = new JPanel();
	private JPanel leftPanel = new JPanel();
	//private JPanel buttonPanel = new JPanel();
	private JTextField weekDays = new JTextField("    Sun        Mon        Tue        Wed        Thu          Fri          Sat");

	public TestCalendar() 
	{
		this.model = new TemporaryModel();
		this.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		currentMonth = model.getCalendar().get(Calendar.MONTH);
		setLayout(new FlowLayout());



		dayButtonGenerator();
		days.setLayout(new GridLayout(0,7));
		days.setPreferredSize(new Dimension(420, 200));
		for (JButton b : dayButtons)
		{
			days.add(b);
		}

		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setPreferredSize(new Dimension(420, 260));
		showMonth();
		showWeekDays();


		//leftPanel.add(buttonPanel);
		//leftPanel.add(monthTextField);		
		
		JPanel buttonContainer = new JPanel();
		
		JPanel monthButtonPanel = new JPanel();
		monthButtonPanel.setLayout(new BorderLayout());
		JLabel monthStringLabel = new JLabel("Month", SwingConstants.CENTER);
		JButton leftMonth = new JButton("<");
		leftMonth.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				model.decrementMonth();
				System.out.println(model.getMonth());
			}
		});
		JButton rightMonth = new JButton(">");
		rightMonth.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				model.incrementMonth();
			}
		});
		monthButtonPanel.add(monthStringLabel, BorderLayout.NORTH);
		monthButtonPanel.add(leftMonth, BorderLayout.WEST);
		monthButtonPanel.add(rightMonth, BorderLayout.EAST);
		
		JPanel yearButtonPanel = new JPanel();
		yearButtonPanel.setLayout(new BorderLayout());
		JLabel yearStringLabel = new JLabel("Year", SwingConstants.CENTER);
		JButton leftYear= new JButton("<");
		leftYear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				model.decrementYear();
			}
		});
		JButton rightYear= new JButton(">");
		rightYear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				model.incrementYear();
			}
		});
		yearButtonPanel.add(yearStringLabel, BorderLayout.NORTH);
		yearButtonPanel.add(leftYear, BorderLayout.WEST);
		yearButtonPanel.add(rightYear, BorderLayout.EAST);
		
		buttonContainer.add(monthButtonPanel);
		buttonContainer.add(monthTextField);
		buttonContainer.add(yearButtonPanel);
		
		leftPanel.add(buttonContainer);
		leftPanel.add(weekDays);
		leftPanel.add(days);		

		String weekDay = "" + arrayOfDays[model.getCalendar().get((Calendar.DAY_OF_WEEK))-1];


		dayButtonClicked(model.getCalendar().get(Calendar.DAY_OF_MONTH));
		add(leftPanel);

		setVisible(true);
	}

	/**
	 * method that will change will show all events on the day clicked
	 * @param n the day to show events for
	 */
	public void dayButtonClicked(int n)
	{
		model.getCalendar().set(Calendar.DAY_OF_MONTH, n);
		String dateKey = model.getYear() + model.getMonth() + model.getDay();
		String date =  model.getMonth() + "/" + model.getDay() + "/" + model.getYear();
		String allEvents = "";
		//if (model.getEvents().get(dateKey) != null)
		{
			//allEvents = model.allEvents(date);
		}
		String weekDay = "" + arrayOfDays[model.getCalendar().get((Calendar.DAY_OF_WEEK))-1];

		highlightDay(model.getCalendar().get(Calendar.DAY_OF_MONTH)-1);

	}

	/**
	 * Method that is called whenever a user makes a change, required by the model
	 */
	public void stateChanged(ChangeEvent e) 
	{
		if (model.getCalendar().get(Calendar.MONTH) != currentMonth)
		{
			currentMonth = model.getCalendar().get(Calendar.MONTH);
			monthTextField.setText(arrayOfMonths[model.getCalendar().get(Calendar.MONTH)] + " " + model.getCalendar().get(Calendar.YEAR));
			leftPanel.remove(days);
			dayButtons.clear();
			days.removeAll();
			dayButtonGenerator();
			lastHighlight = -1;
			highlightDay(model.getCalendar().get(Calendar.DAY_OF_MONTH)-1);

			for(JButton b : dayButtons)
			{
				days.add(b);
			}

			leftPanel.add(days);

			dayButtonClicked(model.getCalendar().get(Calendar.DAY_OF_MONTH));
			repaint();
		}
		else
		{
			int currentDay = model.getCalendar().get(Calendar.DAY_OF_MONTH);
			dayButtonClicked(currentDay);
			highlightDay(currentDay-1);
		}
	}

	/**
	 * Helper method to create invisible unclickable buttons for the days before the
	 * start day on calendar
	 */
	public void dummyDays()
	{
		GregorianCalendar temp = (GregorianCalendar)model.getCalendar().clone();
		temp.set(Calendar.DAY_OF_MONTH, 1);
		int blank = temp.get(Calendar.DAY_OF_WEEK);
		for (int i = 1; i < blank; i++)
		{
			JButton blankButton = new JButton();
			blankButton.setBorder(BorderFactory.createEmptyBorder());
			blankButton.setEnabled(false);
			days.add(blankButton);
		}
	}

	/**
	 * Helper method to generate a button for each day of the month
	 * and add it into the button ArrayList
	 */
	public void dayButtonGenerator()
	{
		dummyDays();
		for (int i = 1; i <= model.getCalendar().getActualMaximum(Calendar.DAY_OF_MONTH); i++)
		{
			JButton dayI = new JButton("" + (i));
			dayI.setBackground(Color.WHITE);
			final int j = i;
			dayI.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					dayButtonClicked(j);
					highlightDay(j-1);
				}
			});
			dayButtons.add(dayI);
		}
	}

	/**
	 * Method that highlights the current day that is showing
	 * @param n the number of the day
	 */
	public void highlightDay(int n)
	{
		dayButtons.get(n).setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		if (lastHighlight != n && lastHighlight != -1)
		{
			JButton temp = new JButton();
			Border defaultBorder = temp.getBorder();
			dayButtons.get(lastHighlight).setBorder(defaultBorder);
		}
		lastHighlight = n;
	}

	/**
	 * Helper method to make a a JTextfield that displays the month and year
	 */
	public void showMonth()
	{
		monthTextField = new JTextField("     " + arrayOfMonths[model.getCalendar().get(Calendar.MONTH)] + " " + model.getCalendar().get(Calendar.YEAR) + "     ");
		monthTextField.setFont(new Font("Serif", Font.BOLD, 25));
		monthTextField.setOpaque(false);
		monthTextField.setBorder(null);
		monthTextField.setEditable(false);
		monthTextField.setHorizontalAlignment(JTextField.CENTER);
	}

	/**
	 * Helper method that shows the weekdays Sunday - Saturday
	 */
	public void showWeekDays()
	{
		weekDays.setPreferredSize(new Dimension(350, 40));
		weekDays.setSize(weekDays.getPreferredSize());
		weekDays.setFont(new Font("Serif", Font.BOLD, 15));
		weekDays.setOpaque(false);
		weekDays.setBorder(null);
		weekDays.setEditable(false);

	}
	
	  public void addListener(ChangeListener c)
	   {
	      listeners.add(c);
	   }

}