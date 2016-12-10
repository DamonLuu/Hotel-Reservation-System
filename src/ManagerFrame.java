import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This class creates a frame that shows the manager their options
 * @author damonluu
 *
 */
public class ManagerFrame
{
	private ReservationManager model;
	
	/**
	 * Constructor for manager frame
	 * @param m the reservation model
	 */
	public ManagerFrame(ReservationManager m) 
	{
		this.model = m;
		
		JFrame managerFrame = new JFrame();
		managerFrame.setLayout(new BorderLayout());
		
		JLabel topLabel = new JLabel("Mananger User Interface", SwingConstants.CENTER);
		topLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		topLabel.setPreferredSize(new Dimension(350, 60));
				
		JPanel centerPanel = new JPanel();
		JButton openMonthViewButton = new JButton("View");
		
		openMonthViewButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
			model.attach(new MonthViewFrame(model));		
			model.attach(new RoomViewFrame(model));
			}
			
		});
		
		centerPanel.add(openMonthViewButton);
		
		JButton quit = new JButton("Save Data and Quit");
		
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.saveReservation();
				System.exit(0);	
			}
		});
		centerPanel.add(quit);
		
		JTextField rightTextField= new JTextField("list all available rooms with selected date here");
		rightTextField.setPreferredSize(new Dimension(300,150));

		JPanel bottomPanel = new JPanel();
		JButton loadButton = new JButton("Load Saved Data");
		
		loadButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				model.loadReservation();	
			}
		});
		
		
		JButton saveButton = new JButton("Save Data");
		
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				model.saveReservation();
			}
		});		
		
		bottomPanel.add(loadButton);
		bottomPanel.add(saveButton);
		
		managerFrame.add(topLabel, BorderLayout.NORTH);
		managerFrame.add(centerPanel, BorderLayout.CENTER);
		managerFrame.add(bottomPanel,BorderLayout.SOUTH);
		managerFrame.pack();
		
		managerFrame.setVisible(true);
		managerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
