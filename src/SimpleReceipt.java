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
		return (String.format("LoginID: %s\nName: %s %s",a.getLoginID(),a.getFirstName(),a.getLastName()));
	}
	/*
	@Override
	public String formatRoom(Account a) {
		total += r.getCost();
		return (String.format("Reserved Room: %s", r.getRoomNumber()));
	}
	*/
	@Override
	public String formatRoom(Room r) {
		total += r.getCost();
		return (String.format("Reserved Room: %s", r.getRoomNumber()));
	}
	@Override
	public String formatTransaction() {
		return (String.format("Total: %d",total));
	}
	
}