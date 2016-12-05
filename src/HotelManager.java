import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

	public void signIn() //prob not needed
	{
		
	}

	public void quit() //prob not needed, just save and close frames?
	{
		saveReservation();
	}

	public void saveReservation()
	{
		try{
	        FileOutputStream fileOut = new FileOutputStream("events.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        //out.writeObject(test);
	        out.writeObject(rm);
	        out.close();
	        fileOut.close();
	    }catch(IOException i) {
	    	i.printStackTrace();
	    }
	}

	public void viewInformation()
	{

	}

	public void loadReservation()
	{
		File file = new File("events.ser");
		if(!file.exists())
			return;
		try {
	         FileInputStream fileIn = new FileInputStream("events.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         //test = (ArrayList<Object>) in.readObject();
	         rm = (ReservationManager) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c) {
		         System.out.println("Classes not found");
		         c.printStackTrace();
		         return;
		  }
		//am = (AccountManager) test.get(0);
		//rm = (ReservationManager) test.get(1);
	}
	
	public void printEmptyRooms()
	{
		
	}
}
