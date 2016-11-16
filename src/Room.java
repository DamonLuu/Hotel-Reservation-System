import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;
import java.util.Calendar;

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
	public Room(String roomType,String roomNumber) 
	{
		//test = new
		this.roomType = roomType.toLowerCase();
		if (roomType.startsWith("l"))
		{
			roomType = "Luxurious";
			cost = 200;
		}
		else if (roomType.startsWith("e"))
		{
			roomType = "Economic";
			cost = 80;
		}
		this.roomNumber = roomNumber;
		reservationsMade = new TreeMap<GregorianCalendar, Reservation>();
	}
	
	/**
	 * Checks if the room is available on the given date
	 * @param date the date for which we want to check the room's availability
	 * @return true if room is available, false if room is not available.
	 */
	public boolean checkAvailability(GregorianCalendar date)
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
		GregorianCalendar date = rObj.getStartDate();
		
		while(date.before(rObj.getEndDate()))
		{
			reservationsMade.put(date, rObj);
			
			//System.out.println(date.toString());
			date.add(GregorianCalendar.DAY_OF_MONTH, 1);
			date = new GregorianCalendar(date.get(GregorianCalendar.YEAR),date.get(GregorianCalendar.MONTH),date.get(GregorianCalendar.DAY_OF_MONTH));
			
		}
		
	}
	
	
	/**
	 * Accessor method
	 * @return get the treeMap containing reservations made for the room.
	 */
	public Map<GregorianCalendar, Reservation> getAvailability()
	{
		return reservationsMade;
	}


	public static void main(String[] args)
	{
		Room room1 = new Room("economy");
		
		Account a1 = new Account("1234","John","Smith");
		
		GregorianCalendar checkInDate = new GregorianCalendar(2016,10,15); //month 10 means october
		GregorianCalendar checkOutDate = new GregorianCalendar(2016,10,20); 
		//i think since someone can check in same date someone else checkout, it is avaialble. Will ask Dr. Kim.
		
		Reservation rObj1 = new Reservation(checkInDate,checkOutDate,room1,a1);
		room1.reserveRoom(rObj1);
		
		
		//room1.reservationsMade.put(new GregorianCalendar(2016,10,17),rObj1);
		//GregorianCalendar temp = new GregorianCalendar(2016,10,18);
		//System.out.println(temp.toString());
		
		
		System.out.println("room available on 2016/11/18?");
		System.out.println(room1.checkAvailability(new GregorianCalendar(2016,10,18)));
		
		System.out.println("room available on 2016/11/22?");
		System.out.println(room1.checkAvailability(new GregorianCalendar(2016,10,22)));
		
		//for(GregorianCalendar t : reservationsMade.keySet())
		//{
		//	System.out.print(t.get(Calendar.MONTH));
		//	System.out.print(t.get(Calendar.DAY_OF_MONTH));
		//	System.out.println(t.get(Calendar.YEAR));
		//}
		
	
	}


}
