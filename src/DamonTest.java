public class DamonTest {
	
	public static void main(String[] args) {
		System.out.println("hello World!");
		
		
		ReservationManager rm = new ReservationManager();
		//new WelcomeFrame(rm);
		//new GuestLoginFrame().makeReservationFrame();
		//new ManagerFrame();
		//test githubbot
		
		CalendarFrameBeta calframe= new CalendarFrameBeta(rm);
		
		rm.attach(calframe);
		
	}

}
 