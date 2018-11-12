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
	private String jsonURL;				// Location of Json dataset.
	private int maxNumOfJsonObjects;	// Total number of restaurants in dataset.
	private Queue allRestaurants;		// All restaurants in dataset.
	private JsonParser parser = new JsonParser();
	private Queue restaurantQueue;	// Queue of Restaurant objects.
	
	/**
	 * The RestaurantController handles creating and modifying Restaurant objects.
	 * @param jsonFile
	 */
	public RestaurantController(String jsonFile) throws FileNotFoundException, IOException, ParseException
	{
		jsonURL = "data//" + jsonFile;
		maxNumOfJsonObjects = countDataObjects();		// Set to number of Json objects in file.
		RestaurantJsonHandler restaurantHandler = new RestaurantJsonHandler();
		parser.parse(restaurantHandler, jsonURL,  maxNumOfJsonObjects);		// Parse Json dataset.
		allRestaurants = restaurantHandler.getRestaurantQueue();
		//createRestaurantQueue();
	}
	
	/**
	 * Create Queue of restaurants objects in user's state.
	 * @param userPreferences
	 */
	/*private void createRestaurantQueue()
	{
		Queue tempAllRestaurants = allRestaurants.copy();
		restaurantQueue = new Queue(tempAllRestaurants.size());
		
		// Create Restaurant objects from Json objects in dataset.
		while(!tempAllRestaurants.isEmpty())
		{
			JSONObject currentRestaurant = (JSONObject) tempAllRestaurants.remove();
			
			Restaurant newRestaurantObject = new Restaurant();
			newRestaurantObject.setName((String) currentRestaurant.get("name"));
			newRestaurantObject.setCity((String) currentRestaurant.get("city"));
			newRestaurantObject.setState((String) currentRestaurant.get("state"));
			newRestaurantObject.setPostalCode((long) currentRestaurant.get("postal code"));
			newRestaurantObject.setStarRating((float) currentRestaurant.get("stars"));
			newRestaurantObject.setCategories((Array) currentRestaurant.get("categories"));
				
			restaurantQueue.insert(newRestaurantObject);	// Insert into Restaurant queue.
		}
	}*/
	
	/**
	 * Get queue of Restaurant objects.
	 * @return restaurantQueue;
	 */
	public Queue getRestaurantQueue()
	{
		return restaurantQueue;
	}
	
	/**
	 * Counts how many json objects are in dataset, when one per line.
	 * @return number of Json objects.
	 */
	private int countDataObjects() throws FileNotFoundException, IOException
	{
		int numOfDataObjects = 0;
		
		try(BufferedReader br = new BufferedReader(new FileReader(jsonURL)))
		{
			for(String line; (line = br.readLine()) != null; )
			{
				numOfDataObjects++;
			}
		}
		
		return numOfDataObjects;
	}
}
