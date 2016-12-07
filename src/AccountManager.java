import java.util.ArrayList;
import java.io.Serializable;

/**
 * AccountManager class that holds data structure for all accounts
 * @author jonathansu,damonluu
 */
public class AccountManager implements Serializable 
{
	private ArrayList<Account> acc;
	
	/**
	 * Constructor for AccountManager
	 * Initializes the arraylist of accounts
	 */
	public AccountManager() 
	{
		acc = new ArrayList<Account>();
	}
	/**
	 * Accessor to get an account by checking login ID
	 * @param loginID String
	 * @return Account if found
	 * @return null if not found
	 */
	public Account getAccount(String loginID)
	{
		for(Account a : acc)
		{
			if(a.getLoginID().equals(loginID))
				return a;
		}
		return null;
	}
	/**
	 * Mutator that takes in user info and creates and adds a new account 
	 * @param loginID String
	 * @param firstName String
	 * @param lastName String
	 */
	public void addAccount(String loginID,String firstName,String lastName)
	{
		Account e = new Account(loginID,firstName,lastName);
		acc.add(e);
	}
	/**
	 * Checks if an account is valid by checking loginID first then first and last name
	 * @param loginID String
	 * @param firstName String
	 * @param lastName String
	 * @return Boolean
	 */
	public boolean checkValidAccount(String loginID, String firstName, String lastName)
	{
		Account found = findAccount(loginID);
		if(found != null && found.getFirstName().equals(firstName) && found.getLastName().equals(lastName))
		{
			return true;
		}
		return false;
	}
	/**
	 * Accessor to find an account my taking login ID
	 * @param loginID String
	 * @return Account if found
	 * @return null if not found
	 */
	public Account findAccount(String loginID)
	{
		for (Account account : acc)
		{
			if (account.getLoginID().equals(loginID))
			{
				return account;
			}
		}
		return null;
	}
}
