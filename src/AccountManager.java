import java.util.ArrayList;
import java.io.Serializable;
public class AccountManager implements Serializable 
{
	private ArrayList<Account> acc;
	
	public AccountManager() 
	{
		acc = new ArrayList<Account>();
	}
	public Account getAccount(String loginID)
	{
		for(Account a : acc)
		{
			if(a.getLoginID().equals(loginID))
				return a;
		}
		return null;
	}
	public void addAccount(String loginID,String firstName,String lastName)
	{
		Account e = new Account(loginID,firstName,lastName);
		acc.add(e);
	}
	
	public boolean checkValidAccount(String loginID, String firstName, String lastName)
	{
		Account found = findAccount(loginID);
		if(found != null && found.getFirstName().equals(firstName) && found.getLastName().equals(lastName))
		{
			return true;
		}
		return false;
	}
	
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
