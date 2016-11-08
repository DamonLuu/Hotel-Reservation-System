import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HotelManager
{

	private ReservationManager rm;
	//private AccountManager am;
	private Room[] rooms = new Room[20];
	//private Room[] luxRoom = new Room[10];
	//private Room[] econRoom = new Room[10];

	public HotelManager() 
	{
		//initialize 20 rooms, 10 which are economic and 10 are luxurious
		for(int i=0;i<rooms.length;i++)
		{
			if (i < 10)
			{
				rooms[i] = new Room("Economic");
			}
			else
			{
				rooms[i] = new Room("Luxurious");
			}
		}
	}

	public ArrayList<Room> findRoom(GregorianCalendar startDate, GregorianCalendar endDate, String type)
	{
		ArrayList<Room> openRooms = new ArrayList<>();
		
		outsideLoop:
		for (Room r : rooms)
		{
			GregorianCalendar temp2 = startDate;
			while(!temp2.equals(endDate))		
			{
				if (r.getAvailability().containsKey(temp2))
				{
					continue outsideLoop;
				}
				temp2.add(Calendar.DAY_OF_MONTH, 1);
			}
			openRooms.add(r);
			
		}
		return openRooms;
		
	}

	public Room getRoom(int roomNumber)
	{
		return rooms[roomNumber];
	}

	public void signIn()
	{
		
	}

	public void quit()
	{

	}

	public void saveReservation()
	{

	}

	public void viewInformation()
	{

	}

	public void loadReservation()
	{

	}
	
	public void printEmptyRooms()
	{
		
	}
}
