import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

public class Room 
{
	private int cost;
	private String roomType;
	private static Map<GregorianCalendar, Reservation> availability;
	
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
}
