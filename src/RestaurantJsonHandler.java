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

	@Override
	public void handleJson(JSONObject currentRestaurant)
	{
		Restaurant newRestaurantObject = new Restaurant();
		newRestaurantObject.setName((String) currentRestaurant.get("name"));
		newRestaurantObject.setCity((String) currentRestaurant.get("city"));
		newRestaurantObject.setState((String) currentRestaurant.get("state"));
		newRestaurantObject.setPostalCode((long) currentRestaurant.get("postal code"));
		newRestaurantObject.setStarRating((float) currentRestaurant.get("stars"));
		newRestaurantObject.setCategories((Array) currentRestaurant.get("categories"));
			
		restaurantQueue.insert(newRestaurantObject);	// Insert into Restaurant queue.
	}
	
	public Queue getRestaurantQueue()
	{
		return restaurantQueue;
	}

}
