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
	private Map<String, Reservation> reservationsMade;
	
	/**
	 * Constructor: creates a room object.
	 * @param roomType String value stating whether the room is Luxury or Economic
	 */
	public Room(String roomType) 
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
		reservationsMade = new TreeMap<String, Reservation>();
	}
	
	/**
	 * Constructor (overloaded): creates a room object and initialize room number
	 * @param roomType "Luxurious" or "Economic" (case doens't matter)
	 * @param roomNum integer representing the room number.
	 */
	public Room(String roomType, int roomNum)
	{
		this(roomType);
		roomNumber = roomNum;
	}
	/**
	 * Accessor method to get the cost
	 * @return cost as an int.
	 */
	public int getCost()
	{
		return cost;
	}
	/**
	 * Accessor method to get the room number
	 * @return room number as an integer.
	 */
	public int getRoomNumber()
	{
		return roomNumber;
	}
	/**
	 * Accessor method to get the room type
	 * @return room type as a String.
	 */
	public String getRoomType()
	{
		return roomType;
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
			//System.out.println(key);
			reservationsMade.put(key, rObj);
			
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
		
		int dayInt = someDate.get(GregorianCalendar.DAY_OF_MONTH);
		
		if(dayInt<10)
		{
			dayStr ="0"+dayInt;
		}
		else
		{
			dayStr = ""+dayInt;
		}
			
		String result = yearStr+monthStr+dayStr;
		
		return result;
	}
	
	/**
	 * Takes the date string in the format entered by user in reservation frame and returns GregCal obj
	 * @param DateEntered in the format "MM/DD/YYYY"
	 * @return GregorianCalendar object corresponding to the date entered.
	 */
	public static GregorianCalendar mmddyyyToGregCal(String DateEntered){
		
		String[] temp = DateEntered.split("/");
		
		GregorianCalendar result = new GregorianCalendar(Integer.parseInt(temp[2]),Integer.parseInt(temp[0])-1,Integer.parseInt(temp[1]));
		
		return result;
	}
	
	
	/**
	 * main method for testing Room class only. Does not affect compilation of MainTester.
	 * @param args not used.
	 */
	public static void main(String[] args)
	{
		
		ReservationManager rm = new ReservationManager();
		
		CalendarFrameBeta calframe= new CalendarFrameBeta(rm);
		
		rm.attach(calframe);
		
		Account a1 = new Account("1234","John","Smith");
		
		GregorianCalendar checkInDate = new GregorianCalendar(2016,10,15); //month 10 means November
		GregorianCalendar checkOutDate = new GregorianCalendar(2016,10,20); 
		
		Reservation rObj1 = new Reservation(checkInDate,checkOutDate,rm.getRoom(3),a1);
		
		System.out.println("Make reservation with check-in 11/15, check-out 11/20");
		rm.getRoom(3).reserveRoom(rObj1);
		

		System.out.println("room available on 2016/11/14?");
		System.out.println(rm.getRoom(3).checkAvailability("20161114"));
		
		System.out.println("room available on 2016/11/15?");
		System.out.println(rm.getRoom(3).checkAvailability("20161115"));		
		
		System.out.println("room available on 2016/11/18?");
		System.out.println(rm.getRoom(3).checkAvailability("20161118"));
		
		System.out.println("room available on 2016/11/20?");
		System.out.println(rm.getRoom(3).checkAvailability("20161120"));
		
		System.out.println("room available on 2016/11/22?");
		System.out.println(rm.getRoom(3).checkAvailability("20161122"));
		
		//Room roomX = new Room("luxurious",101);
		//System.out.println("room number:"+ roomX.getRoomNumber());
		
		

		
		//ArrayList<Integer> availableRooms = rm.findRoom("11/27/2016", "12/02/2016", "Luxurious");
		
		//System.out.println("Available Luxurious rooms in date range 11/27/2016-12/02/2016");
		
		//for(Integer RoomNumber: availableRooms)
		//{
		//	System.out.println("Room #"+RoomNumber);
		//}
		
		//System.out.println(rm.getDayView("20161203"));

	}


}