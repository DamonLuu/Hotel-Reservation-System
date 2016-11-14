import java.util.ArrayList;

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
	
	public String toString()
	{
		return "Login id: " + loginID + " Name: " + getFirstName() + " " + getLastName();
	}
}
