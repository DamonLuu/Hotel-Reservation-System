import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;


import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is the model portion of the MVC
 * It will store a calendar, hashmap of events, and a arraylist of listeners
 * @author Damon Luu
 * copyright 2016
 * version 1
 */
public class TemporaryModel 
{
	private GregorianCalendar gCal = new GregorianCalendar();
	private ArrayList<ChangeListener> listeners = new ArrayList<>();

	/**
	 * Constructor for the model, will load events stored in savedEvents.ser
	 */
	public TemporaryModel() 
	{
		
	}

	/**
	 * method that attaches change listeners to the model 
	 * @param l the change listener to attach
	 */
	public void attach(ChangeListener l)
	{
		listeners.add(l);
	}

	/**
	 * method that updates every listener
	 */
	public void update()
	{
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}

	/**
	 * Creates a new event with given information
	 * @param eventName the event name
	 * @param date the event date
	 * @param startTime the start time of event
	 * @param endTime the end time of event
	 */
	
	/**
	 * increments the calendar by one month
	 */
	public void incrementMonth()
	{
		gCal.add(Calendar.MONTH, 1);
		update();
	}

	/**
	 * decrements the calendar by one month
	 */
	public void decrementMonth()
	{
		gCal.add(Calendar.MONTH, -1);
		update();
	}
	
	/**
	 * increments the calendar by one year
	 */
	public void incrementYear()
	{
		gCal.add(Calendar.MONTH, 1);
		update();
	}

	/**
	 * decrements the calendar by one year
	 */
	public void decrementYear()
	{
		gCal.add(Calendar.MONTH, -1);
		update();
	}


	/**
	 * gets the 2 digit month in string form 
	 * @return the month
	 */
	public String getMonth()
	{
		String month = "" + (gCal.get(Calendar.MONTH)+1);
		if (month.length() == 1)
		{
			month = "0" + month;
		}
		return month;
	}

	/**
	 * gets the 4 digit year in string form
	 * @return the year
	 */
	public String getYear()
	{
		return "" + gCal.get(Calendar.YEAR);
	}

	/**
	 * gets the 2 digit day in string form
	 * @return the day
	 */
	public String getDay()
	{
		String day = "" + gCal.get(Calendar.DAY_OF_MONTH);
		if(day.length() == 1)
		{
			day = "0" + day;
		}
		return day;
	}


	/**
	 * gets the model calendar
	 * @return the Gregorian calendar 
	 */
	public GregorianCalendar getCalendar()
	{
		return gCal;
	}

}
