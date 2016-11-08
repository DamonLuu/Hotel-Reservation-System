import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

public class Room 
{
	private int cost;
	private int roomNumber;
	private String roomType;
	private static Map<GregorianCalendar, Reservation> availability;
	
	/**
	 * Consturctor: creates a room object.
	 * @param roomType String value stating whether the room is Luxury or Economic
	 */
	public Room(String roomType) 
	{
		//test = new
		this.roomType = roomType.toLowerCase();
		if (roomType.startsWith("l"))
		{
			roomType = "Luxurious";
		}
		else if (roomType.startsWith("e"))
		{
			roomType = "Economic";
		}
		availability = new TreeMap<GregorianCalendar, Reservation>();
	}
	
	public boolean checkAvailbility(Date date)
	{
		return false;
	}
	
	public Map<GregorianCalendar, Reservation> getAvailability()
	{
		return availability;
	}
}
