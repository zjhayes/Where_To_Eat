/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class EntryPoint
{

	public static void main(String[] args) throws Exception
	{
		Preferences userPreferences = new Preferences();
		userPreferences.setState("AZ");
		String[] categories = {"Pizza", "Middle Eastern", "Fast Food", "Mexican"};
		userPreferences.setCategories(categories);
		DecisionMaker decider = new DecisionMaker(userPreferences);
		decider.decide();
		decider.printTopTen();
	}

}