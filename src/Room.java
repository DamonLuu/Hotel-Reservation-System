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
	private static Map<GregorianCalendar, Reservation> reservationsMade;
	
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
		reservationsMade = new TreeMap<GregorianCalendar, Reservation>();
	}
	
	/**
	 * Checks if the room is available on the given date
	 * @param date the date for which we want to check the room's availability
	 * @return true if room is available, false if room is not available.
	 */
	public boolean checkAvailbility(GregorianCalendar date)
	{
		if(reservationsMade.containsKey(date))
		{
			return false;
		}
		else
		{
		return true;
		}
		
	}
	
	
	/**
	 * Complete the reservation, saving it to the room's treeMap.
	 * @param rObj reservation object 
	 */
	public void reserveRoom(Reservation rObj)
	{
		//reservationsMade.put(rObj.getDate(), rObj);
	}
	
	
	/**
	 * Accessor method
	 * @return get the treeMap containing reservations made for the room.
	 */
	public Map<GregorianCalendar, Reservation> getAvailability()
	{
		return reservationsMade;
	}
}
