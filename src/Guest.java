import java.util.ArrayList;
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
	//Context
	public String formatSimpleReceipt(ReceiptFormatter formatter,ArrayList<Reservation> res)
	{
		String receipt = formatter.formatHeader();
		for(Reservation r : res)
		{
			receipt += formatter.formatRoom(r.getRoom());
		}
		return receipt + formatter.formatTransaction();
	}
	//Context
	public String formatComprehensiveReceipt(ReceiptFormatter formatter)
	{
		String receipt = formatter.formatHeader();
		for(Reservation r : a.getReservations())
		{
			receipt += formatter.formatRoom(r.getRoom());
		}
		return receipt + formatter.formatTransaction();
	}
}
