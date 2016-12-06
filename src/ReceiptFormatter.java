//Strategy
public interface ReceiptFormatter {
	String formatHeader();
	String formatUserInfo(Account a);
	String formatRoom(Account a);
	String formatTransaction();
}
