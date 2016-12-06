import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Account implements Serializable
{
	private String loginID;
	private String firstName;
	private String lastName;
	ArrayList<Reservation> reservations;
	private ArrayList<Reservation> currentTransaction;
	
	public Account(String loginID, String firstName, String lastName) 
	{
		this.loginID = loginID;
		this.firstName = firstName;
		this.lastName = lastName;
		reservations = new ArrayList<Reservation>();
		currentTransaction = new ArrayList<Reservation>();
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
	public ArrayList<Reservation> getCurrentTransaction()
	{
		return currentTransaction;
	}
	public void addReservation(Reservation r)
	{
		reservations.add(r);
		currentTransaction.add(r);
	}
	public void clearTransaction()
	{
		currentTransaction.clear();
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
	
	public void removeReservation(Reservation r)
	{
		reservations.remove(r);
	}
	
	public String toString()
	{
		return "Login id: " + loginID + " Name: " + getFirstName() + " " + getLastName();
	}

}
