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
	
	
}
