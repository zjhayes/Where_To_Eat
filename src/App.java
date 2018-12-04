import gui.AppGui;
import gui.ErrorBox;
import program.DecisionMaker;
import program.Program;

/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class App
{

	public static void main(String[] args)
	{
		try
		{
			DecisionMaker decider = new DecisionMaker();	// Setup Decision Maker.
			Program program = new Program(decider);			// Launch program.
			new AppGui(program);							// Launch UI.

		}
		catch(Exception ex)
		{
			new ErrorBox(ex);     // Display error box when error is encountered.
		}
	}

}
