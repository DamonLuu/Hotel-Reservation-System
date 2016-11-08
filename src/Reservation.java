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
		room.getAvailability().put(startDate, this);
		
	}
	
	public void checkRoom()
	{
		//Room checker = new Room(roomType)
	}
}
