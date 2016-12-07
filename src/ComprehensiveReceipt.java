//Concrete Strategy
/**
 * Concrete Strategy of receipt to format a comprehensive receipt
 * Used after a user is done reserving rooms
 * Shows a receipt of user info, all reservations made by user, and the total cost
 * @author jonathansu
 *
 */
public class ComprehensiveReceipt implements ReceiptFormatter{
	
	private int total;
	
	/**
	 * Initializes total and returns the header of receipt
	 * @return String
	 */
	@Override
	public String formatHeader() {
		total = 0;
		return "Comprehensive Receipt \n\n";
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
	 * Returns a list of all reservations made by the user
	 * @return String
	 */
	@Override
	public String formatRoom(Account a) 
	{
		String res = "Reserved Room(s):\n";
		for(Reservation r : a.getReservations()){
			total += r.getRoom().getCost();
			res += r.toString() + "\n";
		}
		return res;
	}
	/**
	 * Takes the total of all transactions the user has and returns the total
	 * @return String
	 */
	@Override
	public String formatTransaction() {
		return (String.format("TOTAL: $%d\n",total));
	}
}
