//Concrete Strategy
public class ComprehensiveReceipt implements ReceiptFormatter{
	//id,name,valid res,total amt all res
	private int total;
	@Override
	public String formatHeader() {
		total = 0;
		return "Comprehensive Receipt \n\n";
	}
	@Override
	public String formatUserInfo(Account a) {
		return (String.format("LoginID: %s\nName: %s %s",a.getLoginID(),a.getFirstName(),a.getLastName()));
	}
	/*
	@Override
	public String formatRoom(Account a) {
		String t = "";
		for(Reservation r : a.getReservations()){
			total += r.getRoom().getCost();
			t += r.getRoom().getRoomNumber() + " ";
		}
		return (String.format("Reserved Room(s): %s",t));
	}
	*/
	public String formatRoom(Room r) {
		total += r.getCost();
		return (String.format("Reserved Room(s): %s",r.getRoomNumber()));
	}
	@Override
	public String formatTransaction() {
		return (String.format("Total: %d",total));
	}
}
