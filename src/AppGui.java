
/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
import java.awt.EventQueue;
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
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import org.json.simple.parser.ParseException;

import data_structures.States;
import gui.ResultsWindow;
import program.DecisionMaker;
import program.Preferences;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppGui
{

	private JFrame frmWhatToEat;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static JComboBox cbState;
	private static JRadioButton rdbtnDinein;
	private static JRadioButton rdbtnTakeout;
	private static JRadioButton rdbtnDelivery;
	private static JList categoriesList;
	
	static Preferences userPreferences;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		launch();
	}
	
	public static void launch()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					AppGui window = new AppGui();
					window.frmWhatToEat.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public AppGui() throws FileNotFoundException, IOException
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private void initialize() throws FileNotFoundException, IOException
	{
		frmWhatToEat = new JFrame();
		frmWhatToEat.setTitle("Where To Eat");
		frmWhatToEat.setBounds(100, 100, 475, 385);
		frmWhatToEat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWhatToEat.getContentPane().setLayout(null);
		

		JLabel lblState = new JLabel("State");
		lblState.setBounds(32, 35, 41, 50);
		frmWhatToEat.getContentPane().add(lblState);
		
		cbState = new JComboBox();
		addStates(cbState);
		cbState.setBounds(32, 81, 127, 33);
		frmWhatToEat.getContentPane().add(cbState);
		
		JLabel lblWhatAreYou = new JLabel("What are you in the mood for?");
		lblWhatAreYou.setBounds(187, 35, 200, 50);
		frmWhatToEat.getContentPane().add(lblWhatAreYou);
		
		rdbtnDinein = new JRadioButton("Dine-in");
		buttonGroup.add(rdbtnDinein);
		rdbtnDinein.setSelected(true);
		rdbtnDinein.setBounds(61, 171, 109, 23);
		frmWhatToEat.getContentPane().add(rdbtnDinein);
		
		rdbtnTakeout = new JRadioButton("Take-out");
		buttonGroup.add(rdbtnTakeout);
		rdbtnTakeout.setBounds(61, 197, 109, 23);
		frmWhatToEat.getContentPane().add(rdbtnTakeout);
		
		rdbtnDelivery = new JRadioButton("Delivery");
		buttonGroup.add(rdbtnDelivery);
		rdbtnDelivery.setBounds(61, 223, 109, 23);
		frmWhatToEat.getContentPane().add(rdbtnDelivery);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(187, 81, 216, 156);
		frmWhatToEat.getContentPane().add(scrollPane);
		
		categoriesList = new JList(createListModel("data/categories.txt"));
		scrollPane.setViewportView(categoriesList);
		
		JLabel lblTipCtrlclickTo = new JLabel("Tip: Ctrl+Click to select multiple categories.");
		lblTipCtrlclickTo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblTipCtrlclickTo.setBounds(187, 219, 224, 50);
		frmWhatToEat.getContentPane().add(lblTipCtrlclickTo);
		
		JLabel lblDiningPreferences = new JLabel("Dining Preferences");
		lblDiningPreferences.setBounds(32, 125, 200, 50);
		frmWhatToEat.getContentPane().add(lblDiningPreferences);
		
		JButton btnDecide = new JButton("DECIDE");
		btnDecide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				runProgram();
			}
		});
		btnDecide.setBounds(168, 291, 106, 33);
		frmWhatToEat.getContentPane().add(btnDecide);
		
	}
	
	private static void runProgram()
	{
		setPreferences();
		DecisionMaker decider;
		try
		{
			decider = new DecisionMaker();
			decider.decide(userPreferences);
			ResultsWindow results = new ResultsWindow(decider);
		} catch (IOException | ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void setPreferences()
	{
		userPreferences = new Preferences();
		States states = new States();
		String state = states.getStateNameToCodeMap().get(cbState.getSelectedItem().toString());
		userPreferences.setState(state);
		List<String> userCategories = categoriesList.getSelectedValuesList();
		userPreferences.setCategories(userCategories.toArray(new String[userCategories.size()]));
		userPreferences.setWantsDineIn(rdbtnDinein.isSelected());
		userPreferences.setWantsTakeOut(rdbtnTakeout.isSelected());
		userPreferences.setWantsDelivery(rdbtnDelivery.isSelected());
	}
	
	private static void addStates(JComboBox cbState) throws FileNotFoundException, IOException
	{
		try(BufferedReader br = new BufferedReader(new FileReader("data/states.txt")))
		{
			// Iterate through file and add to comboBox.
			for(String line; (line = br.readLine()) != null; )
			{
				cbState.addItem(line);
			}
		}
	}
	
	private static DefaultListModel createListModel(String url) throws IOException
	{
		DefaultListModel listModel = new DefaultListModel();
		try(BufferedReader br = new BufferedReader(new FileReader(url)))
		{
			// Iterate through file and add to comboBox.
			for(String line; (line = br.readLine()) != null; )
			{
				listModel.addElement(line);
			}
		}
		
		return listModel;
	}
}
