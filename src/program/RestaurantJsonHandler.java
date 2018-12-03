package program;
import java.lang.reflect.Array;
import java.util.Map;

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
		
		// Add attributes.
		Map<String, String> attributes = (Map<String, String>) currentRestaurant.get("attributes");
		newRestaurantObject.setTakeOut(Boolean.parseBoolean(getAttribute(attributes, "RestaurantsTakeOut")));
		newRestaurantObject.setDelivery(Boolean.parseBoolean(getAttribute(attributes, "RestaurantsDelivery")));
		
		// Add business hours.
		Map<String, String> hours = (Map<String, String>) currentRestaurant.get("hours");
		newRestaurantObject.setHours(hours);
		
		restaurantQueue.insert(newRestaurantObject);	// Insert into Restaurant queue.
	}
	
	/**
	 * Get attribute with given key from attribute map.
	 * @param attributes Map of attributes.
	 * @param attribute name of desired attribute.
	 * @return value of attribute.
	 */
	private String getAttribute(Map<String, String> attributes, String attribute)
	{
		if(attributes != null && attributes.containsKey(attribute))
		{
			return attributes.get(attribute);
		}
		
		return "";
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
