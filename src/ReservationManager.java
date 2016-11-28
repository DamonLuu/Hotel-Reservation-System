import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReservationManager 
{
	private Room[] luxRooms = new Room[10];
	private Room[] econRooms = new Room[10];
	
	
	
	
	/**
	 * Constructor initializes the Room objects in the arrays
	 */
	public ReservationManager() 
	{
		for(int i=0;i<luxRooms.length;i++)
		{luxRooms[i] = new Room("Economic");}
		
		for(int i=0;i<econRooms.length;i++)
		{econRooms[i] = new Room("Luxurious");}
		
	}
	
	/**
	 * Given startDate, endDate, and roomType, loops through the rooms in array and check for availability
	 * @param checkIn String format MM/DD/YYYY
	 * @param checkOut String in format MM/DD/YYYY
	 * @param roomType luxurious or economic
	 * @return
	 */
	public ArrayList<Integer> findRoom(String checkIn, String checkOut, String roomType)
	{
		Room[] rooms2search;
		
		String[] startInput = checkIn.split("/");
		String[] endInput = checkOut.split("/");
		
		GregorianCalendar startDate = new GregorianCalendar(Integer.parseInt(startInput[2]),Integer.parseInt(startInput[0]),Integer.parseInt(startInput[1]));
		GregorianCalendar endDate = new GregorianCalendar(Integer.parseInt(endInput[2]),Integer.parseInt(endInput[0]),Integer.parseInt(endInput[1]));
		
		roomType = roomType.toLowerCase();
		
		if(roomType.startsWith("l"))
		{
			rooms2search = luxRooms;
		}
		else
		{
			rooms2search = econRooms;
		}
			
		ArrayList<Integer> openRooms = new ArrayList<>();
		
		outsideLoop:
		for(int i=0;i<rooms2search.length;i++)
		{
			GregorianCalendar tempDate = (GregorianCalendar)startDate.clone();
			GregorianCalendar stopDate = (GregorianCalendar)endDate.clone();
			stopDate.add(Calendar.DAY_OF_MONTH, 1);
			while(!tempDate.before(stopDate))
			{
				if(rooms2search[i].getAvailability().containsKey(Room.GregCalToKey(tempDate)))
				{
					continue outsideLoop;
				}
				tempDate.add(Calendar.DAY_OF_MONTH,1);
			}
			openRooms.add(i);
			
			
		}
		
		return openRooms;
	
	}
	
	public void cancelReservation()
	{
		
	}
	
	public void makeReservation()
	{
		
	}
	
	public void showAvailability()
	{
		
	}
	
	
	
}
