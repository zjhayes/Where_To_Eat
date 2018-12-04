package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

public class ErrorBox
{
	private JFrame errorWindow;
	
	Exception ex;

	/**
	 * Create the application.
	 */
	public ErrorBox(Exception ex)
	{
		this.ex = ex;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		errorWindow = new JFrame();
		errorWindow.setTitle("Error");
		errorWindow.setBounds(100, 100, 450, 300);
		errorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		errorWindow.getContentPane().setLayout(null);
		
		JLabel lblTheApplicationEncountered = new JLabel("The application encountered an error.");
		lblTheApplicationEncountered.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTheApplicationEncountered.setBounds(79, 23, 275, 50);
		errorWindow.getContentPane().add(lblTheApplicationEncountered);
		
		JTextPane errorTextBox = new JTextPane();
		errorTextBox.setText(ex.getMessage());
		errorTextBox.setBounds(37, 73, 369, 166);
		errorWindow.getContentPane().add(errorTextBox);
		
		errorWindow.setVisible(true);
	}
}
