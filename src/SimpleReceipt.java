//Concrete Strategy
/**
 * Concrete Strategy of receipt to format a simple receipt
 * Used after a user is done reserving rooms
 * Shows a receipt of user info, all reservations made by user in one transaction, and the total cost
 * @author jonathansu
 *
 */
public class SimpleReceipt implements ReceiptFormatter{
	
	private int total;
	
	/**
	 * Initializes total and returns header of receipt
	 * @return String
	 */
	@Override
	public String formatHeader() {
		total = 0;
		return "Simple Receipt\n\n";
	}
	/**
	 * Takes an account and returns the account's loginID, first name, and last name
	 * @return String
	 */
	@Override
	public String formatUserInfo(Account a) {
		return (String.format("LoginID: %s\nName: %s %s\n",a.getLoginID(),a.getFirstName(),a.getLastName()));
	}
	/**
	 * Takes an account and adds cost of each room to total
	 * Returns a list of all reservations made by the user in one transaction
	 * @return String
	 */
	@Override
	public String formatRoom(Account a) 
	{
		String res = "Reserved Room(s):";
		for(Reservation r : a.getCurrentTransaction()){
			total += r.getRoom().getCost();
			String t = r.getRoom().getRoomNumber() + "";
			res += (String.format("Room#%s ",t));
		}
		return res + "\n";
	}
	/**
	 * Takes the total of all reservations a user has in one transaction and returns the total
	 * @return String
	 */
	@Override
	public String formatTransaction() {
		return (String.format("TOTAL: $%d\n",total));
	}
	
}
