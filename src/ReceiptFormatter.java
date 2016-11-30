//Strategy
public interface ReceiptFormatter {
	String formatHeader();
	String formatUserInfo(Account a);
	String formatRoom(Room r);
	String formatTransaction();
}
