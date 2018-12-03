package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import program.DecisionMaker;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResultsWindow
{

	private JFrame frmWhereToEat;
	private DecisionMaker decider;
	
	public ResultsWindow(DecisionMaker decider)
	{
		this.decider = decider;
		initialize();
		launch();
	}

	/**
	 * Create the application.
	 */
	public ResultsWindow()
	{
		initialize();
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void launch()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ResultsWindow window = new ResultsWindow();
					window.frmWhereToEat.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmWhereToEat = new JFrame();
		frmWhereToEat.setTitle("Where To Eat - Decision");
		frmWhereToEat.setBounds(100, 100, 450, 270);
		frmWhereToEat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWhereToEat.getContentPane().setLayout(null);
		
		JLabel lblRestaurantName = new JLabel("Restaurant Name");
		//lblRestaurantName.setText(decider.getNextDecision());
		lblRestaurantName.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestaurantName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRestaurantName.setBounds(101, 56, 200, 50);
		frmWhereToEat.getContentPane().add(lblRestaurantName);
		
		JLabel lblEatHere = new JLabel("EAT HERE...");
		lblEatHere.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEatHere.setBounds(91, 22, 200, 50);
		frmWhereToEat.getContentPane().add(lblEatHere);
		
		JButton btnPickAnother = new JButton("Pick Another");
		btnPickAnother.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				lblRestaurantName.setText(decider.getNextDecision());
			}
		});
		btnPickAnother.setBounds(142, 158, 124, 50);
		frmWhereToEat.getContentPane().add(btnPickAnother);
	}

}
