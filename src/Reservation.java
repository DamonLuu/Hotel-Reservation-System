import java.util.GregorianCalendar;

public class Reservation
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
	
	
	
	public void checkRoom()
	{
		//Room checker = new Room(roomType)
	}
}
