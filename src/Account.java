import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
/**
 * Account class that holds user info and reservations
 * @author jonathansu,damonluu,stevenyen
 *
 */
public class Account implements Serializable
{
	private String loginID;
	private String firstName;
	private String lastName;
	ArrayList<Reservation> reservations;
	private ArrayList<Reservation> currentTransaction;
	/**
	 * Constructor of Account class
	 * @param loginID String
	 * @param firstName String
	 * @param lastName String
	 */
	public Account(String loginID, String firstName, String lastName) 
	{
		this.loginID = loginID;
		this.firstName = firstName;
		this.lastName = lastName;
		reservations = new ArrayList<Reservation>();
		currentTransaction = new ArrayList<Reservation>();
	}
	/**
	 * Accessor to get the login ID of this account
	 * @return String
	 */
	public String getLoginID()
	{
		return loginID;
	}
	/**
	 * Accessor to get the first name of this account
	 * @return String
	 */
	public String getFirstName()
	{
		return firstName;
	}
	/**
	 * Accessor to get the last name of this account
	 * @return String
	 */
	public String getLastName()
	{
		return lastName;
	}
	/**
	 * Accessor to get all reservations this account has
	 * @return ArrayList<Reservation>
	 */
	public ArrayList<Reservation> getReservations()
	{
		return reservations;
	}
	/**
	 * Accessor to get this account's current transactions
	 * Used for simple receipt
	 * @return ArrayList<Reservation>
	 */
	public ArrayList<Reservation> getCurrentTransaction()
	{
		return currentTransaction;
	}
	/**
	 * Mutator to add a reservation to this account's arraylist of reservations
	 * @param r Reservation
	 */
	public void addReservation(Reservation r)
	{
		reservations.add(r);
		currentTransaction.add(r);
	}
	/**
	 * Mutator to clear stored transactions
	 * Used for simple receipt
	 */
	public void clearTransaction()
	{
		currentTransaction.clear();
	}
	/**
	 * Returns string representation of all room numbers this account has reserved
	 * @return String
	 */
	public String reservationToString()
	{
		String temp = "";
		for(Reservation r : reservations)
		{
			temp = temp + r.getRoom().getRoomNumber() + " ";
		}
		return temp.trim();
	}
	/**
	 * Mutator that removes a reservation from this account's arraylist of reservations
	 * @param r Reservation
	 */
	public void removeReservation(Reservation r)
	{
		reservations.remove(r);
	}
	/**
	 * Returns string representation of Account class
	 * @return String
	 */
	public String toString()
	{
		return "Login id: " + loginID + " Name: " + getFirstName() + " " + getLastName();
	}

}
