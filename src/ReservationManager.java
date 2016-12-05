import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This is the Data Model
 */
public class ReservationManager 
{
	//Data Model (Subject) for MVC
	//private Room[] luxRooms = new Room[10];
	//private Room[] econRooms = new Room[10];
	private Room[] allRooms = new Room[20]; //econ rooms #0-9; lux rooms #19-20
	private AccountManager am = new AccountManager();
	private GregorianCalendar selectedDate;
	private Account currentAccount;
	
	private ArrayList<Reservation> reservations = new ArrayList<>();
	
	
	//Arraylist of ChangeLister for MVC
	private ArrayList<ChangeListener> cListeners;
	
	/**
	 * Accessor method
	 * @return
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
		
		printout += "Economy Rooms: \n";
		
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
		
		printout += "\nLuxury Rooms: \n";
		
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
//		for(int i=0;i<luxRooms.length;i++)
//		{luxRooms[i] = new Room("Economic",i);}
//		
//		for(int i=0;i<econRooms.length;i++)
//		{econRooms[i] = new Room("Luxurious",i);}
		
		for(int i=0;i<10;i++)
		{allRooms[i] = new Room("Economic",i);}
		
		for(int i=10;i<20;i++)
		{allRooms[i] = new Room("Luxurious",i);}
		
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
		//Room[] rooms2search;
		
		String[] startInput = checkIn.split("/");
		String[] endInput = checkOut.split("/");
		System.out.println(Integer.parseInt(startInput[0]) -1);
			
		GregorianCalendar startDate = new GregorianCalendar(Integer.parseInt(startInput[2]),Integer.parseInt(startInput[0]) -1,Integer.parseInt(startInput[1]));
		GregorianCalendar endDate = new GregorianCalendar(Integer.parseInt(endInput[2]),Integer.parseInt(endInput[0]) -1,Integer.parseInt(endInput[1]));
		
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
			System.out.println(tempDate.get((Calendar.MONTH))+1 + "/" + tempDate.get(Calendar.DAY_OF_MONTH) + "/" + tempDate.get(Calendar.YEAR));
			System.out.println(stopDate.get((Calendar.MONTH))+1 + "/" + stopDate.get(Calendar.DAY_OF_MONTH) + "/" + stopDate.get(Calendar.YEAR));
			while(!tempDate.before(stopDate))
			{
				System.out.println("2");
				if(allRooms[i].getAvailability().containsKey(Room.GregCalToKey(tempDate)))
				{
					System.out.println("Room: " + i + " is Open");
					continue outsideLoop;
				}
				tempDate.add(Calendar.DAY_OF_MONTH,1);
			}
			//openRooms.add(i);
			stringOfAvailRooms=stringOfAvailRooms+"Room #"+i+"\n";
			
		}
		
		return stringOfAvailRooms;
	
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
	
	 /**
     * Select a different date. This is one of the update method of Data Model.
     * It is a mutator that changes the data, and then notifies all views in cListeners.
     * @param d is the date selected.
     */
    public void selectDate(GregorianCalendar d)
    {
    	selectedDate = d;
    	//System.out.println("selectDate");
    	
    	for(ChangeListener l: cListeners)
    	{
    		l.stateChanged(new ChangeEvent(this));
    		//System.out.println("listeners notified");
    	}
    		
    }
    
    public void addReservation(Reservation r)
    {
    	//selectedDate = d;
    	//System.out.println("selectDate");
    	reservations.add(r);
    	Room selected = allRooms[r.getRoom().getRoomNumber()];
    	//selected.reserveRoom(rObj);)
    	
    	for(ChangeListener l: cListeners)
    	{
    		l.stateChanged(new ChangeEvent(this));
    		//System.out.println("listeners notified");
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
	
	public boolean dateBeforeCheck(String checkIn, String checkOut) throws ParseException
	{
		DateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String today = simpleDateFormat.format(new Date());
		Date todayDate = simpleDateFormat.parse(today);
		Date checkInDate = simpleDateFormat.parse(checkIn);
		Date checkOutDate = simpleDateFormat.parse(checkOut);
		if (checkInDate.before(todayDate) || checkOutDate.before(todayDate))
		{
			return true;
		}
		return false;
	}
	
	public boolean dateLongerThan60(String checkIn, String checkOut) throws ParseException
	{
		DateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date checkInDate = simpleDateFormat.parse(checkIn);
		Date checkOutDate = simpleDateFormat.parse(checkOut);
		
		long dayInMilliSec = 1000 * 60 * 60 * 24;
		if(((checkOutDate.getTime() - checkInDate.getTime()) / dayInMilliSec) > 60)
		{
			return true;
		}
		return false;
	}
	
	public boolean validDateFormat(String checkIn, String checkOut)
	{
		return checkIn.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})") && checkOut.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})");
	}

	public void setAccount(Account a)
	{
		currentAccount = a;
	}
	
	public Account getAccount()
	{
		return currentAccount;
	}

}
