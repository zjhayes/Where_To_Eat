package assists;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import data_structures.Queue;

/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class DataHandler
{
	Queue dataQueue;
	int queueSize;
	
	public DataHandler(Queue dataSource)
	{
		dataQueue = dataSource;
		queueSize = dataQueue.size();
	}

	/**
	 * Create a list of properties from data Queue.
	 * @param propertyName
	 * @return string array of property values.
	 */
	public String[] createList(String propertyName)
	{
		Queue tempDataQueue = dataQueue.copy();
		String[] arrayList = new String[queueSize]; 		// New list
		int itemsInList = -1;
		
		while(!tempDataQueue.isEmpty())
		{
			// Remove next item in queue.
			JSONObject currentObject = (JSONObject) tempDataQueue.remove();
			
			if(currentObject != null) 
			{
				String currentProperty = (String) currentObject.get(propertyName);	// Get property.
				
				// Check if property already in list.
				if(!hasDuplicate(currentProperty, arrayList))
				{
					arrayList[++itemsInList] = currentProperty;		// Add to List.
				}
			}
		}
		
		// Refine array so no null values.
		arrayList = (String[]) refineArray(arrayList, itemsInList);
		
		return arrayList;
	}
	
	public String[] createListFromArray(String arrayName)
	{
		Queue tempAllRestaurants = dataQueue.copy();
		String[] arrayList = new String[queueSize];	// New list.
		int itemsInList = -1;
				
		while(!tempAllRestaurants.isEmpty())
		{
			// Remove next item in queue.
			JSONObject currentObject = (JSONObject) tempAllRestaurants.remove();
			
			if(currentObject != null) 
			{
				// Get json array from property as string, split into java array.
				String stringList = (String) currentObject.get(arrayName);
				String[] values = stringList.split("\\s*,\\s*");

				for(String value : values)	// Traverse array.
				{
					// Check if value already in list.
					if(!hasDuplicate(value, arrayList))
					{
						arrayList[++itemsInList] = value;	// Add to list.
					}
				}
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
	 * Create an array from the lines in a text file.
	 * @param txtFile to create array from.
	 * @return Array of strings.
	 */
	public String[] createListFromTextFile(String txtFile) throws FileNotFoundException, IOException, ParseException
	{
		String[] arrayList = new String[queueSize];	// New list.
		int itemsInList = -1;
		
		try(BufferedReader br = new BufferedReader(new FileReader(txtFile)))
		{
			for(String line; (line = br.readLine()) != null; )
			{
				arrayList[++itemsInList] = line;	// Add to list.
			}
		}
		
		// Refine array so no null values.
		arrayList = (String[]) refineArray(arrayList, itemsInList);
		
		return arrayList;
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
	
}
