import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import data_structures.Queue;
import interfaces.IJsonHandler;

/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class RestaurantController
{
	private String jsonURL;							// Location of Json dataset.
	private int maxNumOfJsonObjects;				// Total number of restaurants in dataset.
	private JsonParser parser = new JsonParser();
	RestaurantJsonHandler restaurantHandler;
	
	/**
	 * The RestaurantController handles creating and modifying Restaurant objects.
	 * @param jsonFile
	 */
	public RestaurantController(String jsonFile) throws FileNotFoundException, IOException, ParseException
	{
		jsonURL = "data//" + jsonFile;
		maxNumOfJsonObjects = countDataObjects();		// Set to number of Json objects in file.
		restaurantHandler = new RestaurantJsonHandler(maxNumOfJsonObjects);
		parser.parse(restaurantHandler, jsonURL,  maxNumOfJsonObjects);		// Parse Json dataset.
	}
	
	/**
	 * Get queue of Restaurant objects.
	 * @return restaurantQueue;
	 */
	public Queue getRestaurantQueue()
	{
		return restaurantHandler.getRestaurantQueue();
	}
	
	/**
	 * Counts how many json objects are in dataset, when one per line.
	 * @return number of Json objects.
	 */
	private int countDataObjects() throws FileNotFoundException, IOException
	{
		int numOfDataObjects = 0;
		
		// Traverse dataset and count lines, each line is assumed to be a Json object.
		try(BufferedReader br = new BufferedReader(new FileReader(jsonURL)))
		{
			for(String line; (line = br.readLine()) != null; )
			{
				numOfDataObjects++;	// Iterate count.
			}
		}
		
		return numOfDataObjects;
	}
}
