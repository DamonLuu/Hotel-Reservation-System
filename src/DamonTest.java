public class DamonTest {
	
	public static void main(String[] args) {
		System.out.println("hello World!");
		
		
		ReservationManager rm = new ReservationManager();
		rm.getAccountManager().addAccount("123", "John", "Smith");
		new WelcomeFrame(rm);
		
		//new GuestLoginFrame(rm).makeReservationFrame();
		//new ManagerFrame();
		//test githubbot
		
		//CalendarFrameBeta calframe= new CalendarFrameBeta(rm);
		
		//rm.attach(calframe);
		
	}

}
 