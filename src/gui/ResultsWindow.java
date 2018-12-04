package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import program.DecisionMaker;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class ResultsWindow
{

	private JFrame resultWindow;
	private DecisionMaker decider;
	
	public ResultsWindow(DecisionMaker decider)
	{
		this.decider = decider;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		resultWindow = new JFrame();
		resultWindow.setTitle("Where To Eat - Decision");
		resultWindow.setBounds(100, 100, 430, 270);
		resultWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultWindow.getContentPane().setLayout(null);
		
		
		// Eat Here label
		JLabel lblEatHere = new JLabel("EAT HERE...");
		lblEatHere.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEatHere.setBounds(91, 22, 200, 50);
		resultWindow.getContentPane().add(lblEatHere);
		
		// Result label
		JLabel lblRestaurantName = new JLabel("");
		lblRestaurantName.setText(decider.getNextDecision());
		lblRestaurantName.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestaurantName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRestaurantName.setBounds(50, 56, 300, 50);
		resultWindow.getContentPane().add(lblRestaurantName);
		
		// Next button
		JButton btnPickAnother = new JButton("Pick Another");
		btnPickAnother.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				lblRestaurantName.setText(decider.getNextDecision());
			}
		});
		btnPickAnother.setBounds(142, 158, 124, 50);
		resultWindow.getContentPane().add(btnPickAnother);
		
		resultWindow.setVisible(true);
	}

}
