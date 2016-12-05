import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Account 
{
	private String loginID;
	private String firstName;
	private String lastName;
	ArrayList<Reservation> reservations;
	
	public Account(String loginID, String firstName, String lastName) 
	{
		this.loginID = loginID;
		this.firstName = firstName;
		this.lastName = lastName;
		reservations = new ArrayList<Reservation>();
	}
	
	public String getLoginID()
	{
		return loginID;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public ArrayList<Reservation> getReservations()
	{
		return reservations;
	}
	
	public void addReservation(Reservation r)
	{
		reservations.add(r);
	}
	
	public String reservationToString()
	{
		String temp = "";
		for(Reservation r : reservations)
		{
			temp = temp + r.getRoom().getRoomNumber() + " ";
		}
		return temp.trim();
	}
	
	public String toString()
	{
		return "Login id: " + loginID + " Name: " + getFirstName() + " " + getLastName();
	}

}
