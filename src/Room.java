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
	private static Map<String, Reservation> reservationsMade;
	
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
		reservationsMade = new TreeMap<String, Reservation>();
	}
	
	/**
	 * Checks if the room is available on the given date
	 * @param date the date for which we want to check the room's availability
	 * @return true if room is available, false if room is not available.
	 */
	public boolean checkAvailability(String key)
	{
		//String key = GregCalToKey(date);
		
		
		if(reservationsMade.containsKey(key))
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
		GregorianCalendar stop = rObj.getEndDate();
		stop.add(GregorianCalendar.DAY_OF_MONTH, 1);
		
		while(date.before(stop))
		{
			
			String key = GregCalToKey(date);
			
			reservationsMade.put(key, rObj);
			
			//System.out.println(date.toString());
			
			date.add(GregorianCalendar.DAY_OF_MONTH, 1);
			//date = new GregorianCalendar(date.get(GregorianCalendar.YEAR),date.get(GregorianCalendar.MONTH),date.get(GregorianCalendar.DAY_OF_MONTH));
			
		}
		
	}
	
	
	/**
	 * Accessor method
	 * @return get the treeMap containing reservations made for the room.
	 */
	public Map<String, Reservation> getAvailability()
	{
		return reservationsMade;
	}
	
	/**
	 * Method to convert GregorianCalendar object to string date YYYYMMDD
	 * @param somedate GregorianCalendar object corresponding to a date
	 * @return a String key YYYYMMDD for corresponding to the date.
	 */
	public static String GregCalToKey(GregorianCalendar someDate)
	{
		String yearStr, monthStr, dayStr;
		
		yearStr = ""+someDate.get(GregorianCalendar.YEAR);
		
		int monthInt = someDate.get(GregorianCalendar.MONTH)+1; //return 11 for november
		
		if(monthInt<10)
		{
			monthStr = "0"+monthInt;
		}
		else
		{
			monthStr = ""+monthInt;
		}
		
		dayStr = ""+someDate.get(GregorianCalendar.DAY_OF_MONTH);
		
		String result = yearStr+monthStr+dayStr;
		
		return result;
	}
	
	public static void main(String[] args)
	{
		Room room1 = new Room("economy");
		
		Account a1 = new Account("1234","John","Smith");
		
		GregorianCalendar checkInDate = new GregorianCalendar(2016,10,15); //month 10 means November
		GregorianCalendar checkOutDate = new GregorianCalendar(2016,10,20); 
		//i think since someone can check in same date someone else checkout, it is avaialble. Will ask Dr. Kim.
		
		Reservation rObj1 = new Reservation(checkInDate,checkOutDate,room1,a1);
		
		System.out.println("Make reservation with check-in 11/15, check-out 11/20");
		room1.reserveRoom(rObj1);
		
		
		//room1.reservationsMade.put(new GregorianCalendar(2016,10,17),rObj1);
		//GregorianCalendar temp = new GregorianCalendar(2016,10,18);
		//System.out.println(temp.toString());

		System.out.println("room available on 2016/11/14?");
		System.out.println(room1.checkAvailability("20161114"));
		
		System.out.println("room available on 2016/11/15?");
		System.out.println(room1.checkAvailability("20161115"));		
		
		System.out.println("room available on 2016/11/18?");
		System.out.println(room1.checkAvailability("20161118"));
		
		System.out.println("room available on 2016/11/20?");
		System.out.println(room1.checkAvailability("20161120"));
		
		System.out.println("room available on 2016/11/22?");
		System.out.println(room1.checkAvailability("20161122"));		
		
		//System.out.println(rObj1.getStartDate());
		
		
		
		//for(GregorianCalendar t : reservationsMade.keySet())
		//{
		//	System.out.print(t.get(Calendar.MONTH));
		//	System.out.print(t.get(Calendar.DAY_OF_MONTH));
		//	System.out.println(t.get(Calendar.YEAR));
		//}
		
	
	}


}