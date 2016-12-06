import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Reservation implements Serializable
{
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private Room room;
	private Account account;
	
	public Reservation(GregorianCalendar startDate, GregorianCalendar endDate, Room room, Account account) 
	{
		this.startDate = startDate;
		this.endDate = endDate;
		this.room = room;
		this.account = account;
		//check if room is open
		//room.getAvailability().put(startDate, this);
		
	}
		
	/**
	 * Accessor method to get the start (check-in) date for the reservation
	 * @return GregorianCalendar object that is clone of startDate instance variable
	 */
	public GregorianCalendar getStartDate()
	{
		return (GregorianCalendar) startDate.clone();
	}
	
	/**
	 * Accessor method to get the end (check-out) date for the reservation
	 * @return GregorianCalendar object that is clone of endDate instance variable
	 */
	public GregorianCalendar getEndDate()
	{
		return (GregorianCalendar) endDate.clone();
	}
	
	
	/**
	 * Accessor method to get the room
	 * @return room of reservation
	 */
	public Room getRoom()
	{
		return room;
	}
	
	
	public void checkRoom()
	{
		//Room checker = new Room(roomType)
	}
	
	public Account getAccount()
	{
		return account;
	}
	
	public String gregorianToString(GregorianCalendar cal) //mm/dd/yyyy
	{
		String month = "" + (cal.get(Calendar.MONTH) +1);
		if (month.length() == 1) month = "0" + month;
		String day = "" + cal.get(Calendar.DAY_OF_MONTH);
		if (day.length() == 1) day = "0" + day;
		String year = "" + cal.get(Calendar.YEAR);
		return month + "/" + day + "/" + year;
	}
	
	public String toString()
	{
		String roomNumber = "Room #" + room.getRoomNumber()+ ", ";
		String roomType = "Room Type: " + room.getRoomType() + ", ";
		String checkInDate = "Check-In Date: " + gregorianToString(startDate) + ", ";
		String checkOutDate = "Check-Out Date: " + gregorianToString(endDate);
		return roomNumber + roomType + checkInDate + checkOutDate;
	}
}
