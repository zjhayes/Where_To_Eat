package program;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
	private ArrayList<String> pastDecisions;
	
	public DecisionMaker() throws FileNotFoundException, IOException, ParseException
	{
		allRestaurants = new RestaurantController("restaurant_dataset.json").getRestaurantQueue();
	}
	
	/**
	 * Create sorted linked list of best restaurant matches.
	 */
	public void decide(Preferences userPreferences)
	{
		this.userPreferences = userPreferences;
		Queue tempRestaurants = allRestaurants.copy();
		decisionList = new LinkedList();
		pastDecisions = new ArrayList<String>();

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
		return restaurant.getState().equals(userPreferences.getState()) && restaurant.isOpen()
				&& meetsDiningPreference(restaurant); // Checks if restaurant provides user's preferred dining option.
	}
	
	/**
	 * Checks if restaurant has user's dining preferences.
	 * All restaurants are assumed to have dine-in option.
	 * @param restaurant
	 * @return true if restaurant provides requested dining preference.
	 */
	private boolean meetsDiningPreference(Restaurant restaurant)
	{
		return (restaurant.hasTakeOut() == true && userPreferences.wantsTakeOut() == true) ||
				(restaurant.hasDelivery() == true && userPreferences.wantsDelivery() == true) ||
				userPreferences.wantsDineIn();
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
		
		// Add points for higher ratings.
		score += Math.round(restaurant.getStarRating());
		
		// Each category matched is worth 1 points.
		for(String category : userPreferences.getCategories())
		{
			if(Arrays.asList(restaurant.getCategories()).contains(category))
			{
				score += 5;
			}
		}
		
		return score;
	}
	
	/**
	 * Get next restaurant from decision list, skips duplicates.
	 * @return name of next restaurant.
	 */
	public String getNextDecision()
	{
		if(!decisionList.isEmpty())
		{	
			Link decision = decisionList.deleteFirst();
			Restaurant restaurant = (Restaurant) decision.getObject();
			String restaurantName = restaurant.getName();
			
			return skipDuplicates(restaurantName);
		}
		else
		{
			return "No Results Found";
		}
	}
	
	/**
	 * Checks if restaurant has been suggested, if so skip to next restaurant.
	 * @param restaurantName String name of restaurant.
	 * @return string of restaurant that isn't a duplicate.
	 */
	private String skipDuplicates(String restaurantName)
	{
		// Check if restaurant has already been suggested.
		if(!pastDecisions.contains(restaurantName))
		{
			pastDecisions.add(restaurantName);
			return restaurantName;
		}
		else
		{
			return getNextDecision(); 	// Skip and get next decision.
		}
	}
	
	/**
	 * For testing.
	 */
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
