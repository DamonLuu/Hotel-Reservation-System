/**
 * Class that runs the hotel reservation system
 * @author Damon
 *
 */
public class HotelReservationSystem
{
	/**
	 * creates a new model and welcome frame
	 * @param args not used
	 */
	public static void main(String[] args) 
	{
		ReservationManager rm = new ReservationManager();
		new WelcomeFrame(rm);
	}
}
 