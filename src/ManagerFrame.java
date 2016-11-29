import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ManagerFrame
{
	public ManagerFrame() 
	{
		JFrame managerFrame = new JFrame();
		managerFrame.setLayout(new BorderLayout());
		
		JLabel topLabel = new JLabel("Mananger User Interface", SwingConstants.CENTER);
		topLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		topLabel.setPreferredSize(new Dimension(350, 75));
				
		JPanel centerPanel = new JPanel();
		JTextField changeLater = new JTextField("Someone put a calendar here");
		changeLater.setPreferredSize(new Dimension(350,250));
		centerPanel.add(changeLater);
		
		JTextField rightTextField= new JTextField("list all available rooms with selected date here");
		rightTextField.setPreferredSize(new Dimension(300,150));

		JPanel bottomPanel = new JPanel();
		JButton loadButton = new JButton("Load Saved Data");
		JButton saveButton = new JButton("Save Data and Exit");
		bottomPanel.add(loadButton);
		bottomPanel.add(saveButton);
		
		managerFrame.add(topLabel, BorderLayout.NORTH);
		managerFrame.add(centerPanel, BorderLayout.CENTER);
		managerFrame.add(rightTextField, BorderLayout.EAST);
		managerFrame.add(bottomPanel,BorderLayout.SOUTH);
		managerFrame.pack();
		
		managerFrame.setVisible(true);
		//managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
