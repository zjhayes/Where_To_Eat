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
		DecisionMaker decider = new DecisionMaker(userPreferences);
	}

}