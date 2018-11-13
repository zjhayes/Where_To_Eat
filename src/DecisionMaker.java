import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import org.json.simple.parser.ParseException;
import data_structures.Link;
import data_structures.LinkedList;
import data_structures.Queue;

/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class DecisionMaker
{
	private Preferences userPreferences;
	private Queue allRestaurants;
	private LinkedList decisionList;
	
	public DecisionMaker(Preferences userPreferences) throws FileNotFoundException, IOException, ParseException
	{
		this.userPreferences = userPreferences;
		allRestaurants = new RestaurantController("restaurant_dataset.json").getRestaurantQueue();
	}
	
	/**
	 * Create sorted linked list of best restaurant matches.
	 */
	public void decide()
	{
		Queue tempRestaurants = allRestaurants.copy();
		decisionList = new LinkedList();

		// Check all restaurants, add matches to linked list.
		while(!tempRestaurants.isEmpty())
		{
			Restaurant currentRestaurant = (Restaurant) tempRestaurants.remove();

			if(meetsManditoryPreferences(currentRestaurant))
			{
				int score = scoreRestaurant(currentRestaurant);
				decisionList.insert(score, currentRestaurant);
			}
		}
	}
	
	/**
	 * Checks if restaurant meets user's mandatory preferences.
	 * @param restaurant
	 * @return true if restaurant meets minimum requirements.
	 */
	private boolean meetsManditoryPreferences(Restaurant restaurant)
	{
		// Check if restaurant is in user's preferred state..
		// If user wants take out, must be take out..
		// If user doesn't want take out, doesn't matter.
		return restaurant.getState().equals(userPreferences.getState()) && restaurant.isOpen()
				&& (restaurant.isTakeOut() == userPreferences.wantsTakeOut() ||
				userPreferences.wantsTakeOut() == false);
	}
	
	/**
	 * Score how well restaurant matches user preferences.
	 * @param restaurant
	 * @return Score based on how well restaurant matches preferences.
	 */
	private int scoreRestaurant(Restaurant restaurant)
	{
		int score = 0;
		
		// Proximity is worth 5 points.
		if(restaurant.getCity() == userPreferences.getCity())
		{
			score += 5;
		}
		
		// Each category matched is worth 1 points.
		for(String category : userPreferences.getCategories())
		{
			if(Arrays.asList(restaurant.getCategories()).contains(category))
			{
				score++;
			}
		}
		
		return score;
	}
	
	public void printTopTen()
	{
		int countDown = 10;
		
		System.out.println("Top Ten:");
		while(!decisionList.isEmpty() && countDown != 0)
		{
			Link restaurantLink = decisionList.deleteFirst();
			int score = restaurantLink.getId();
			Restaurant restaurant = (Restaurant) restaurantLink.getObject();
			String restaurantName = restaurant.getName();
			
			System.out.println("Score: " + score + " - Restaurant: " + restaurantName);
			
			countDown--;
		}
	}
}
