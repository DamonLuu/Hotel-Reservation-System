
public class AccountManager 
{
	ArrayList<Account> acc;
	public AccountManager()
	{
		acc = new ArrayList<Account>();
	}
	public Account findAccount(String loginID)
	{
		for(Account a : acc)
		{
			if(a.getLoginID().equals(loginID))
				return a;
		}
		return null;
	}
}
