
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Creates frame that shows the room view
 * @author stevenyen
 */
public class RoomViewFrame extends JFrame implements ChangeListener{

	private ReservationManager model;
	JTextArea roomInfo;
	String roomClicked = "0";

	/**
	 * connstructor for the room view frame
	 * @param m the reservation model
	 */
	public RoomViewFrame(ReservationManager m){

		model = m;
		this.setLocation(0, 470);
		roomInfo = new JTextArea(10,10);

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		leftPanel.add(new JLabel("Select Room Number Below"),BorderLayout.NORTH);

		JPanel roomPanel = new JPanel();
		roomPanel.setSize(200,200);
		roomPanel.setLayout(new GridLayout(4,5));

		for(int i=0;i<20;i++){

			JButton roomButton = new JButton(""+i);

			roomButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("selected room: "+roomButton.getText());
					roomInfo.setText(model.viewRoomInformation(Integer.parseInt(roomButton.getText())));
					roomClicked = roomButton.getText();
				}	
			});

			roomPanel.add(roomButton);
		}

		leftPanel.add(roomPanel,BorderLayout.CENTER);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(new JLabel("Reservation for Selected Room"),BorderLayout.NORTH);
		rightPanel.add(roomInfo,BorderLayout.CENTER);

		setTitle("Manager Room View");
		setSize(800,400);
		setLayout(new BorderLayout());
		add(leftPanel,BorderLayout.WEST);
		add(rightPanel,BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * updates model with the clicked room
	 */
	@Override
	public void stateChanged(ChangeEvent e) 
	{
		System.out.println(roomClicked);
		roomInfo.setText(model.viewRoomInformation(Integer.parseInt(roomClicked)));
	}
}
