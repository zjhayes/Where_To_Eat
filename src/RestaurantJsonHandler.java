import java.lang.reflect.Array;
import org.json.simple.JSONObject;
import data_structures.Queue;
import interfaces.IJsonHandler;
/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class RestaurantJsonHandler implements IJsonHandler
{
	private Queue restaurantQueue;	// Queue of Restaurant objects.
	
	public RestaurantJsonHandler(int sizeOfQueue)
	{
		restaurantQueue = new Queue(sizeOfQueue);
	}

	@Override
	public void handleJson(JSONObject currentRestaurant)
	{
		// Create Restaurant objects.
		Restaurant newRestaurantObject = new Restaurant();
		newRestaurantObject.setName((String) currentRestaurant.get("name"));
		newRestaurantObject.setCity((String) currentRestaurant.get("city"));
		newRestaurantObject.setState((String) currentRestaurant.get("state"));
		newRestaurantObject.setStarRating((double) currentRestaurant.get("stars"));
		String categoryStringList = (String) currentRestaurant.get("categories");
		newRestaurantObject.setCategories(parseStringToArray(categoryStringList));
		
		restaurantQueue.insert(newRestaurantObject);	// Insert into Restaurant queue.
	}
	
	/**
	 * Split string of listed objects, separated by comma, into array.
	 * @param stringList
	 * @return array
	 */
	private String[] parseStringToArray(String stringList)
	{
		String[] array = stringList.split("\\s*,\\s*");
		
		return array;
	}
	
	/**
	 * Queue of restaurant objects in Json file.
	 * @return restaurantQueue
	 */
	public Queue getRestaurantQueue()
	{
		return restaurantQueue;
	}

}
