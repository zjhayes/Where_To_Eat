package tests;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import program.DecisionMaker;
import program.Preferences;

/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class TestEntryPoint
{	
	public static void main(String[] args) throws Exception
	{
		System.out.println("Running test.");
		runDecisionMaker();
	}
	
	private static void runDecisionMaker() throws FileNotFoundException, IOException, ParseException
	{
		Preferences userPreferences = new Preferences();
		userPreferences.setState("AZ");
		String[] categories = {"Pizza", "Middle Eastern", "Fast Food", "Mexican", "Sandwiches", "Seafood", "Chicken Wings"};
		userPreferences.setCategories(categories);
		userPreferences.setWantsTakeOut(true);
		userPreferences.setWantsDelivery(true);
		userPreferences.setWantsDineIn(true);
		DecisionMaker decider = new DecisionMaker();
		decider.decide(userPreferences);
		decider.printTopTen();
	}
}