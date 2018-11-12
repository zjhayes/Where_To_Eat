import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
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
	
	public DecisionMaker(Preferences userPreferences) throws FileNotFoundException, IOException, ParseException
	{
		this.userPreferences = userPreferences;
		allRestaurants = new RestaurantController("restaurant_dataset.json").getRestaurantQueue();

	}
	
	public void decide()
	{
		Queue tempRestaurants = allRestaurants.copy();
		
		while(!allRestaurants.isEmpty())
		{
			Restaurant currentRestaurant = (Restaurant) tempRestaurants.remove();
			
			if(meetsManditoryPreferences(currentRestaurant))
			{
				int score = scoreRestaurant(currentRestaurant);
			}
		}
	}
	
	private boolean meetsManditoryPreferences(Restaurant restaurant)
	{
		return restaurant.getState() == userPreferences.getState() && restaurant.isOpen()
				&& restaurant.isTakeOut() == userPreferences.wantsTakeOut();
	}
	
	private int scoreRestaurant(Restaurant restaurant)
	{
		int score = 0;
		
		if(restaurant.getCity() == userPreferences.getCity())
		{
			score += 5;
		}
		
		for(String category : userPreferences.getCategories())
		{
			if(restaurant.getCategories().contains(category))
			{
				score++;
			}
		}
		
		return score;
	}
}
