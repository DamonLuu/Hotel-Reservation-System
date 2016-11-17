	import java.awt.Dimension;
	import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
	import javax.swing.ButtonGroup;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
import javax.swing.JPanel;



public class WelcomeFrame extends JFrame
{



	//private Model model;

	public WelcomeFrame() //PUT MODEL IN PARAMETERS 
	{
		this.setTitle("Pen Pineapple Apple Pen Hotel Reservation System");
		//this.setSize(500, 400);
		

		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(350, 350));
		//container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JLabel welcomeLabel = new JLabel("  Welcome to the Pineapple Hotel");
		welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
		welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		welcomeLabel.setPreferredSize(new Dimension(325, 100));
		
		JButton guestButton = new JButton("Hotel Guest");
		guestButton.setPreferredSize(new Dimension(300,100));
		guestButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				GuestFrame frame = new GuestFrame();
			}
		});
		
		JButton managerButton = new JButton("Hotel Manager");
		managerButton.setPreferredSize(new Dimension(300,100));

		container.add(welcomeLabel);
		container.add(guestButton);
		container.add(managerButton);
		
		add(container);
		
		
		pack();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

