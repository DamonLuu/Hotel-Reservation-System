//Concrete Strategy
public class SimpleReceipt implements ReceiptFormatter{
	//id,name,res rooms,total amt of particular transaction
	private int total;
	@Override
	public String formatHeader() {
		total = 0;
		return "Simple Receipt\n\n";
	}
	@Override
	public String formatUserInfo(Account a) {
		return (String.format("LoginID: %s\nName: %s %s\n",a.getLoginID(),a.getFirstName(),a.getLastName()));
	}
	
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

	@Override
	public String formatTransaction() {
		return (String.format("TOTAL: $%d\n",total));
	}
	
}
