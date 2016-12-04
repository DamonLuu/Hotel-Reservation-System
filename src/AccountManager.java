import java.util.ArrayList;

public class AccountManager 
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
	
	public boolean checkValidAccount(Account a, String firstName, String lastName)
	{
		if (getAccount(a.getLoginID()) != null)
		{
			if (a.getFirstName().equals(firstName) && a.getLastName().equals(lastName))
			{
				return true;
			}
		}	
		return false;
	}
}
