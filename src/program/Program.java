package program;

import java.util.List;
import data_structures.States;
import gui.ResultsWindow;
import interfaces.IProgram;

/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class Program implements IProgram
{
	DecisionMaker decider;
	private Preferences userPreferences;
	
	public Program(DecisionMaker decider)
	{
		this.decider = decider;
	}

	/*
	 * Run program and display results.
	 */
	@Override
	public void runProgram()
	{
		decider.decide(userPreferences);	// Run Decision Maker.
		new ResultsWindow(decider);			// New window displays results.
	}
	
	/*
	 * Set user preferences based on UI control settings.
	 */
	@Override
	public void setPreferences(String state, List<String> categories, boolean wantsDineIn, boolean wantsTakeOut, boolean wantsDelivery)
	{
		userPreferences = new Preferences();
		States states = new States();
		String stateCode = states.getStateNameToCodeMap().get(state);	// Convert state name to state abbreviation.
		userPreferences.setState(stateCode);
		userPreferences.setCategories(categories.toArray(new String[categories.size()]));
		userPreferences.setWantsDineIn(wantsDineIn);
		userPreferences.setWantsTakeOut(wantsTakeOut);
		userPreferences.setWantsDelivery(wantsDelivery);
	}

}
