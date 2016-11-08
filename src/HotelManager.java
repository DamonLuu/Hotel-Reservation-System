
public class HotelManager
{

	private ReservationManager rm;
	//private AccountManager am;
	private Room[] luxRoom = new Room[10];
	private Room[] econRoom = new Room[10];
	
	public HotelManager() 
	{
		//initialize 10 Room objects in luxRoom
		for(int i=0;i<luxRoom.length;i++)
		{luxRoom[i] = new Room("lux");}
		
		//initialize 10 Room objects in econRoom
		for(int i=0;i<econRoom.length;i++)
		{econRoom[i] = new Room("econ");}
		
	}
	
	public Room findRoom()
	{
		return null;
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
}
