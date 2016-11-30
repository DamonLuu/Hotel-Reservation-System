import java.util.GregorianCalendar;

public class Guest{
	private Account a;
	//private GregorianCalendar gc;
	public Guest(Account a)
	{
		this.a = a;
		//gc = new GregorianCalendar();
	}
	public void addReservation(String startDate,String endDate,String roomType)
	{
		//can't exceed 60 days
		//can't be before current date
		
		//presents roomAvailability after date input
	}
	/*
	public void viewReservation()
	{
		//opens room availability window
		
	}
	*/
	public void cancelReservaiton()
	{
		
	}
}
