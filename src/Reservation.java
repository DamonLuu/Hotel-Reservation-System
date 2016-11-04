
public class Reservation
{
	private String startDate;
	private String endDate;
	private Room room;
	private Account account;
	
	public Reservation(String startDate, String endDate, Room room, Account account) 
	{
		this.startDate = startDate;
		this.endDate = endDate;
		this.room = room;
		this.account = account;
	}
	
	public void checkRoom()
	{
		
	}
}
