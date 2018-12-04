package gui;

/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import interfaces.IProgram;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppGui
{

	private JFrame mainWindow;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox<String> cbState;
	private JRadioButton rdbtnDinein;
	private JRadioButton rdbtnTakeout;
	private JRadioButton rdbtnDelivery;
	private JList<String> categoriesList;
	
	private IProgram program;

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public AppGui(IProgram program) throws FileNotFoundException, IOException
	{
		this.program = program;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private void initialize() throws FileNotFoundException, IOException
	{
		// Window
		mainWindow = new JFrame();
		mainWindow.setTitle("Where To Eat");
		mainWindow.setBounds(100, 100, 475, 385);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.getContentPane().setLayout(null);
		
		// State label
		JLabel lblState = new JLabel("State");
		lblState.setBounds(32, 35, 41, 50);
		mainWindow.getContentPane().add(lblState);
		
		// State combobox
		cbState = new JComboBox<String>();
		addStates(cbState, "data/states.txt");
		cbState.setBounds(32, 81, 127, 33);
		mainWindow.getContentPane().add(cbState);
		
		// Mood label
		JLabel lblWhatAreYou = new JLabel("What are you in the mood for?");
		lblWhatAreYou.setBounds(187, 35, 200, 50);
		mainWindow.getContentPane().add(lblWhatAreYou);
		
		// Dining preferences label
		JLabel lblDiningPreferences = new JLabel("Dining Preferences");
		lblDiningPreferences.setBounds(32, 125, 200, 50);
		mainWindow.getContentPane().add(lblDiningPreferences);
		
		// Dine-in radio button
		rdbtnDinein = new JRadioButton("Dine-in");
		buttonGroup.add(rdbtnDinein);
		rdbtnDinein.setSelected(true);
		rdbtnDinein.setBounds(61, 171, 109, 23);
		mainWindow.getContentPane().add(rdbtnDinein);
		
		// Take-out radio button
		rdbtnTakeout = new JRadioButton("Take-out");
		buttonGroup.add(rdbtnTakeout);
		rdbtnTakeout.setBounds(61, 197, 109, 23);
		mainWindow.getContentPane().add(rdbtnTakeout);
		
		// Delivery radio button
		rdbtnDelivery = new JRadioButton("Delivery");
		buttonGroup.add(rdbtnDelivery);
		rdbtnDelivery.setBounds(61, 223, 109, 23);
		mainWindow.getContentPane().add(rdbtnDelivery);
		
		// Scroll
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(187, 81, 216, 156);
		mainWindow.getContentPane().add(scrollPane);
		
		// Categories Listbox
		categoriesList = new JList<String>(createListModel("data/categories.txt"));
		scrollPane.setViewportView(categoriesList);
		
		// Tip label
		JLabel lblTipCtrlclickTo = new JLabel("Tip: Ctrl+Click to select multiple categories.");
		lblTipCtrlclickTo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblTipCtrlclickTo.setBounds(187, 219, 224, 50);
		mainWindow.getContentPane().add(lblTipCtrlclickTo);
		
		// Submit button
		JButton btnDecide = new JButton("DECIDE");
		btnDecide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				decideButtonPress();
			}
		});
		btnDecide.setBounds(168, 291, 106, 33);
		mainWindow.getContentPane().add(btnDecide);
		
		mainWindow.setVisible(true);
	}
	
	/**
	 * Runs when "Decide" button pressed.
	 */
	private void decideButtonPress()
	{
		String state = cbState.getSelectedItem().toString();
		List<String> categories = categoriesList.getSelectedValuesList();
		boolean wantsDineIn = rdbtnDinein.isSelected();
		boolean wantsTakeOut = rdbtnTakeout.isSelected();
		boolean wantsDelivery = rdbtnDelivery.isSelected();
		program.setPreferences(state, categories, wantsDineIn, wantsTakeOut, wantsDelivery);
		program.runProgram();
	}
	
	/**
	 * Add states to ComboBox.
	 * @param cbState States Combobox
	 */
	private void addStates(JComboBox<String> cBox, String url) throws FileNotFoundException, IOException
	{
		try(BufferedReader br = new BufferedReader(new FileReader(url)))
		{
			// Iterate through file and add to comboBox.
			for(String line; (line = br.readLine()) != null; )
			{
				cBox.addItem(line);
			}
		}
	}
	
	/**
	 * Create List Model.
	 * @param url File location of dataset.
	 */
	private DefaultListModel<String> createListModel(String url) throws IOException
	{
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(url)))
		{
			// Iterate through file and add to list model.
			for(String line; (line = br.readLine()) != null; )
			{
				listModel.addElement(line);
			}
		}
		
		return listModel;
	}
}
