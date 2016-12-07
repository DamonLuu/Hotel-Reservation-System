import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * This is the Data Model
 * @author jonathansu,damonluu,stevenyen
 *
 */
public class ReservationManager implements Serializable
{
	//Data Model (Subject) for MVC
	private Room[] allRooms = new Room[20]; //luxury rooms #0-9; econ rooms #19-20
	private AccountManager am = new AccountManager();
	private GregorianCalendar selectedDate;
	private Account currentAccount;
	
	private ArrayList<Reservation> reservations = new ArrayList<>();
	private ArrayList<Object> testSave = new ArrayList<>();

	//Arraylist of ChangeLister for MVC
	private ArrayList<ChangeListener> cListeners;
	
	/**
	 * Accessor method
	 * @return AccountManager
	 */
	public AccountManager getAccountManager()
	{
		return am;
	}

	/**
	 * Accessor method
	 * @return room corresponding to the room number
	 */
	public Room getRoom(int RoomNumber){

		return allRooms[RoomNumber];
	}

	/**
	 * Get a printout of the room availabilities of the selected day for manager month view
	 * @return String listing the room availabilities for a day
	 */
	public String getSelectedDayRooms(){

		String dateString = Room.GregCalToKey(selectedDate);

		String printout ="";

		printout = printout+"Room availabilities for ";

		printout = printout + (selectedDate.get(Calendar.MONTH)+1)+"/"+selectedDate.get(Calendar.DAY_OF_MONTH)+"/"+selectedDate.get(Calendar.YEAR)+":\n\n";

		printout += "Luxury Rooms: \n";

		for(int i=0;i<10;i++)
		{
			printout = printout +"     Room #" + allRooms[i].getRoomNumber()+": ";

			if(allRooms[i].getAvailability().containsKey(dateString)){
				printout += "Reserved\n";
			}
			else{
				printout += "Available\n";
			}
		}

		printout += "\nEconomy Rooms: \n";

		for(int i=10;i<20;i++)
		{
			printout = printout +"     Room #" + allRooms[i].getRoomNumber()+": ";

			if(allRooms[i].getAvailability().containsKey(dateString)){
				printout += "Reserved\n";
			}
			else{
				printout += "Available\n";
			}
		}


		return printout;
	}

	/**
	 * Constructor initializes the Room objects in the arrays
	 */
	public ReservationManager() 
	{
		for(int i=0;i<10;i++)
		{allRooms[i] = new Room("Luxury",i);}

		for(int i=10;i<20;i++)
		{allRooms[i] = new Room("Economy",i);}

		selectedDate = new GregorianCalendar();

		cListeners = new ArrayList<ChangeListener>();
	}

	/**
	 * Given startDate, endDate, and roomType, loops through the rooms in array and check for availability
	 * @param checkIn String format MM/DD/YYYY
	 * @param checkOut String in format MM/DD/YYYY
	 * @param roomType luxurious or economic
	 * @return String including all available rooms.
	 */
	public String findRoom(String checkIn, String checkOut, String roomType)
	{

		String[] startInput = checkIn.split("/");
		String[] endInput = checkOut.split("/");
		//System.out.println(Integer.parseInt(startInput[0]) -1);

		GregorianCalendar startDate = mmddyyyToGregCal(checkIn);
		GregorianCalendar endDate = mmddyyyToGregCal(checkOut);

		roomType = roomType.toLowerCase();

		int roomIdxS;
		int roomIdxE;

		if(roomType.startsWith("l"))
		{
			//rooms2search = luxRooms;
			roomIdxS=0;
			roomIdxE=9;
		}
		else
		{
			//rooms2search = econRooms;
			roomIdxS=10;
			roomIdxE=19;
		}

		//ArrayList<Integer> openRooms = new ArrayList<>();

		String stringOfAvailRooms="";

		outsideLoop:
			for(int i=roomIdxS;i<=roomIdxE;i++)
			{
				GregorianCalendar tempDate = (GregorianCalendar)startDate.clone();
				GregorianCalendar stopDate = (GregorianCalendar)endDate.clone();
				stopDate.add(Calendar.DAY_OF_MONTH, 1);
				//System.out.println(tempDate.get((Calendar.MONTH))+1 + "/" + tempDate.get(Calendar.DAY_OF_MONTH) + "/" + tempDate.get(Calendar.YEAR));
				//System.out.println(stopDate.get((Calendar.MONTH))+1 + "/" + stopDate.get(Calendar.DAY_OF_MONTH) + "/" + stopDate.get(Calendar.YEAR));
				while(tempDate.before(stopDate))
				{
					//System.out.println("2");
					if(allRooms[i].getAvailability().containsKey(Room.GregCalToKey(tempDate)))
					{
						System.out.println("Room: " + i + " is Not Open");
						continue outsideLoop;
					}
					tempDate.add(Calendar.DAY_OF_MONTH,1);
				}
				stringOfAvailRooms=stringOfAvailRooms+"Room #"+i+"\n";
			}
		return stringOfAvailRooms;
	}
	/**
	 * Mutator method that cancels reservation from account and rooms array
	 * @param room Room
	 * @param reserv Reservation
	 */
	public void cancelReservation(Room room, Reservation reserv)
	{

		TreeMap<String, Reservation> treeMap = room.getAvailability();
		Iterator<String> iter = treeMap.keySet().iterator();
		{
			while(iter.hasNext())
			{
				String key = iter.next();
				if(treeMap.get(key) == reserv)
				{
					iter.remove();
				}
			}
		}
		currentAccount.removeReservation(reserv);
		update();
	}
	/**
	 * Mutator that makes a reservation and adds it to the account, reservations arraylist, and room array
	 * @param checkIn String
	 * @param checkOut String
	 * @param roomNumber String
	 */
	public void makeReservation(String checkIn, String checkOut, String roomNumber)
	{
		GregorianCalendar checkInDate = mmddyyyToGregCal(checkIn);
		GregorianCalendar checkOutDate = mmddyyyToGregCal(checkOut);
		Room selectedRoom = getRoom(Integer.parseInt(roomNumber));
		
		Reservation rObj1 = new Reservation(checkInDate,checkOutDate,selectedRoom,currentAccount);
		reservations.add(rObj1);
		
		System.out.println("Make reservation with check-in " + gregorianToString(checkInDate) + 
				", check-out " + gregorianToString(checkOutDate));
		selectedRoom.reserveRoom(rObj1);
		System.out.println(selectedRoom.checkAvailability(GregCalToKeyTree(checkInDate)));
		currentAccount.addReservation(rObj1);
		update();
	}

	/**
	 * Select a different date. This is one of the update method of Data Model.
	 * It is a mutator that changes the data, and then notifies all views in cListeners.
	 * @param d is the date selected.
	 */
	public void selectDate(GregorianCalendar d)
	{
		selectedDate = d;
		//System.out.println("selectDate");

		update();

	}
	/**
	 * Mutator that updates the change listeners and view
	 */
	public void update()
	{
		for(ChangeListener l: cListeners)
		{
			l.stateChanged(new ChangeEvent(this));
			System.out.println("listeners notified");
		}
	}

	/**
	 * Accessor method to check value of selectedDate
	 * The selectedDate helps the View determine which day's event to show in day view on the right panel.
	 * This is one of the getData() methods of this data model.
	 * @return return the instance variable selectedDate of EventsManager object.
	 */
	public GregorianCalendar getSelectedDate()
	{
		return selectedDate;
	}

	/**
	 * Attach a listener to the DataModel. All listeners are notified when data is updated.
	 * This is the "Attach" method of the MVC model. 
	 * @param c the listener representing the "view" portion of MVC.
	 */
	public void attach(ChangeListener c)
	{
		cListeners.add(c);
	}
	/**
	 * Checks if the user tries to reserve a room starting before today's date
	 * @param checkIn String
	 * @param checkOut String
	 * @return boolean true if check-in/check-out date is before today
	 * @throws ParseException 
	 */
	public boolean dateBeforeCheck(String checkIn, String checkOut) throws ParseException
	{
		GregorianCalendar today = new GregorianCalendar();
		today.setTimeInMillis(0);
		GregorianCalendar checkInDate = mmddyyyToGregCal(checkIn);
		GregorianCalendar checkOutDate = mmddyyyToGregCal(checkOut);

		if (checkInDate.before(today) || checkOutDate.before(today))
		{
			return true;
		}
		return false;
	}
	/**
	 * Checks if the user reserves a room longer than 60 days
	 * @param checkIn String
	 * @param checkOut String
	 * @return boolean true if longer than 60
	 * @throws ParseException
	 */
	public boolean dateLongerThan60(String checkIn, String checkOut) throws ParseException
	{
		GregorianCalendar checkInDate = mmddyyyToGregCal(checkIn);
		GregorianCalendar checkOutDate = mmddyyyToGregCal(checkOut);

		checkInDate.add(Calendar.DAY_OF_MONTH, 60);

		if(checkInDate.before(checkOutDate))
		{
			return true;
		}
		return false;
	}
	/**
	 * Checks if user input for Check-In/Check-out is correct eg MM/DD/YYYY
	 * @param checkIn String
	 * @param checkOut String
	 * @return boolean true if valid format
	 */
	public boolean validDateFormat(String checkIn, String checkOut)
	{
		return checkIn.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})") && checkOut.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})");
	}
	/**
	 * Mutator that sets current account
	 * Used when a new guest logs in
	 * @param a
	 */
	public void setAccount(Account a)
	{
		currentAccount = a;
	}
	/**
	 * Accessor that return current account
	 * @return Account
	 */
	public Account getAccount()
	{
		return currentAccount;
	}
	/**
	 * Converts a MM/DD/YYYY string to corresponding day in GregorianCalendar format
	 * @param DateEntered String
	 * @return GregorianCalendar
	 */
	public GregorianCalendar mmddyyyToGregCal(String DateEntered){

		String[] temp = DateEntered.split("/");

		GregorianCalendar result = new GregorianCalendar(Integer.parseInt(temp[2]),Integer.parseInt(temp[0])-1,Integer.parseInt(temp[1]));

		return result;
	}
	/**
	 * Converts GregorianCalendar to MM/DD/YYYY String format
	 * @param cal GregorianCalendar
	 * @return String
	 */
	public String gregorianToString(GregorianCalendar cal) //mm/dd/yyyy
	{
		String month = "" + (cal.get(Calendar.MONTH) +1);
		if (month.length() == 1) month = "0" + month;
		String day = "" + cal.get(Calendar.DAY_OF_MONTH);
		if (day.length() == 1) day = "0" + day;
		String year = "" + cal.get(Calendar.YEAR);
		return month + "/" + day + "/" + year;
	}
	/**
	 * Converts GregorianCalendar to YYYYMMDD Sting format
	 * Used for accessing TreeMap<String,Reservation>
	 * @param someDate GregorianCalendar
	 * @return String
	 */
	public static String GregCalToKeyTree(GregorianCalendar someDate) //yyyyMMdd
	{
		String year = "" + someDate.get(GregorianCalendar.YEAR);
		String month = "" +someDate.get(GregorianCalendar.MONTH)+1; //return 11 for november
		if (month.length()==1) month = "0" + month;
		String day = "" + someDate.get(GregorianCalendar.DAY_OF_MONTH);
		if (day.length()==1) day = "0" + day;
		return year + month + day;

	}
	/**
	 * Context of Strategy Pattern for receipt use
	 * Returns string of receipt: either simple and comprehensive
	 * @param formatter ReceiptFormatter
	 * @return String
	 */
	public String format(ReceiptFormatter formatter)
	{
		String receipt = formatter.formatHeader();
		receipt += formatter.formatUserInfo(currentAccount);
		receipt += formatter.formatRoom(currentAccount);
		return receipt + formatter.formatTransaction();
	}
	/**
	 * Mutator that clears arraylist of current account's transaction
	 * Used for simple receipt
	 */
	public void clearTransaction()
	{
		currentAccount.clearTransaction();
	}
	/**
	 * Finds all available rooms given a start date, end date, and room type
	 * @param startDate GregorianCalendar
	 * @param endDate GregorianCalendar
	 * @param type String
	 * @return ArrayList<Room>
	 */
	public ArrayList<Room> findRoom(GregorianCalendar startDate, GregorianCalendar endDate, String type)
	{
		ArrayList<Room> openRooms = new ArrayList<>();
		
		outsideLoop:
		for (Room r : allRooms)
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
	/**
	 * Returns a string of room information like type, cost, and reservations
	 * Used by manager to view individual room information
	 * @param number int
	 * @return String
	 */
	public String viewRoomInformation(int number) //Manager room click
	{
		Room a = allRooms[number];
		TreeMap<String,Reservation> b = a.getAvailability();
		String res = "Room Information:\n";
		res += "Room #" + number;
		res += "\nRoom Type: " + a.getRoomType();
		res += "\nRoom Cost: " + a.getCost();
		res += "\nReservations: \n";
		Iterator<String> iter = b.keySet().iterator();
		{		
			String key = "";
			Boolean preventDuplicate = false;
			Reservation r = null;
			while(iter.hasNext())
			{
				if(r != null && b.get(key) == r){
					preventDuplicate = true;
				} else
					preventDuplicate = false;
					
				if(!preventDuplicate){
					key = iter.next();
					r = b.get(key);
					Account user = r.getAccount();
					res += "LoginID: " + user.getLoginID();
					res += " User: " + user.getFirstName() + " " + user.getLastName() + "\n";
					res += "Check-In Date: " + r.gregorianToString(r.getStartDate());
					res += " Check-Out Date: " + r.gregorianToString(r.getEndDate());
				} else
					key = iter.next();
			}
		}
		return res;
	}
	/**
	 * Loads from events.ser account info, room info, and reservations
	 * Uses deserialization
	 * Used by manager
	 */
	public void loadReservation()
	{
		File file = new File("events.ser");
		if(!file.exists())
			return;
		try {
	         FileInputStream fileIn = new FileInputStream("events.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         testSave = (ArrayList<Object>) in.readObject();
	         am = (AccountManager) testSave.get(0);
	         allRooms = (Room[]) testSave.get(1);
	         reservations = (ArrayList<Reservation>) testSave.get(2);
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
	}
	/**
	 * Saves all account info, room info, and reservations to events.ser
	 * Uses serialization
	 * Used by manager
	 */
	public void saveReservation()
	{
		try{
	        FileOutputStream fileOut = new FileOutputStream("events.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        //System.out.println(this.getClass());
	        //out.writeObject(this);
	        testSave.add(am);
	        testSave.add(allRooms);
	        testSave.add(reservations);
	        out.writeObject(testSave);
	        out.close();
	        fileOut.close();
	    }catch(IOException i) {
	    	i.printStackTrace();
	    }
	}
}
