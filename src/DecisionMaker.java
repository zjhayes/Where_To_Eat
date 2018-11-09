import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import data_structures.Queue;
import data_structures.States;

/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class DecisionMaker
{
	private String jsonURL = "data//" + "testDataSet.json";
	private int maxNumOfJsonObjects = 150000;
	private Queue allRestaurants;
	private String[] statesServiced;	// List of states this app services.
	private String[] categories;
	private JsonParser parser = new JsonParser();
	private States states = new States();
	
	public DecisionMaker() throws FileNotFoundException, IOException, ParseException
	{
		allRestaurants = parser.parse(jsonURL,  maxNumOfJsonObjects);		// Parse Json dataset.
		statesServiced = new String[states.getStateCodeToNameMap().size()];
		createListOfStatesServiced();
		//createListOfCategories();
	}
	
	/**
	 * Make list of states in restaurant dataset.
	 */
	private void createListOfStatesServiced()
	{
		statesServiced = createList("state");
	}
	
	private void createListOfCategories()
	{
		categories = createList("categories");
	}
	
	/**
	 * Create a list of properties from Json dataset.
	 * @param propertyName
	 * @return
	 */
	private String[] createList(String propertyName)
	{
		Queue tempAllRestaurants = allRestaurants;
		String[] arrayList = new String[maxNumOfJsonObjects]; 		// New list
		int itemsInList = -1;
		
		while(!tempAllRestaurants.isEmpty())
		{
			// Remove next item in queue.
			JSONObject currentObject = (JSONObject) tempAllRestaurants.remove();
			String currentProperty = (String) currentObject.get(propertyName);	// Get property.
			
			// Check if property already in list.
			if(!hasDuplicate(currentProperty, arrayList))
			{
				arrayList[++itemsInList] = currentProperty;		// Add to List.
			}
		}
		
		// Refine array so no null values.
		arrayList = (String[]) refineArray(arrayList, itemsInList);
		
		return arrayList;
	}
	
	/**
	 * Checks list for duplicate item.
	 * @param newItem Item to check against items in list.
	 * @param list List of items.
	 * @return True if duplicate found.
	 */
	private boolean hasDuplicate(String newItem, String[] list)
	{
		// Traverse items currently in list.
		for(String item : list)
		{
			// Check if newItem is duplicate of current item.
			if(item != null && item.equals(newItem))
			{
				return true;	// Returns true when duplicate found.
			}
		}
		return false;			// No duplicate found.
	}
	
	/**
	 * Refines an array to a given length.
	 * @param arrayToRefine Array that is too large.
	 * @param newLength Length of refined array.
	 * @return Refined array.
	 */
	private String[] refineArray(String[] arrayToRefine, int newLength)
	{
		String[] newArray = new String[newLength];
		int i = 0;
		
		// Move items into new array.
		for(String entity : arrayToRefine)
		{
			if(entity != null && i < newLength)
			{
				newArray[i++] = entity;
			}
		}
		
		return newArray;
	}
	
	/**
	 * Print list of states in dataset to console.
	 */
	public void printListOfStatesServiced()
	{
		for(String state : statesServiced)
		{
			System.out.print(state + " ");
			System.out.println(states.getStateCodeToNameMap().get(state));
		}	
	}
	
	public void printListOfCategories()
	{
		for(String category : categories)
		{
			System.out.print(category);
		}
	}
}
