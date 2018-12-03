package program;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class Restaurant
{
	private String name;
	private String city;
	private String state;
	private double starRating;
	private boolean hasTakeOut;
	private boolean hasDelivery;
	private String[] categories;
	private Map<String, String> hours;
	private boolean AlwaysOpen = false;	// Open 24/7
	
	/**
	 * Check if restaurant is open for business.
	 * @return true if business is open.
	 */
	public boolean isOpen()
	{
		if(AlwaysOpen)
		{
			return true;	// Open 24/7.
		}
		
		DateTime now = new DateTime();
		String businessHours = getTodaysHours(now.dayOfWeek().getAsText());	// Get hours from current day of week.
		
		if(businessHours == null)
		{
			return false;	// Closed all day.
		}
		
		return compareBusinessHours(now, businessHours);
	}
	
	/**
	 * Get today's business hours.
	 * @param currentDayOfWeek The name of the day of the week.
	 * @return today's business hours.
	 */
	private String getTodaysHours(String currentDayOfWeek)
	{
		return hours.get(currentDayOfWeek);			// Get hours from current day of week.

	}
	
	/**
	 * Compare the chronological order of opening and closing time,
	 * and add 24 hours to closing time if after midnight.
	 * @param now Current day/time
	 * @param businessHoursString Business hours as a String.
	 * @return true if business is open.
	 */
	private boolean compareBusinessHours(DateTime now, String businessHoursString)
	{
		String[] businessHours = businessHoursString.split("-|:");			// Split hours into array.
		
		LocalTime openingTime = new LocalTime(Integer.parseInt(businessHours[0]), Integer.parseInt(businessHours[1]));
		LocalTime closingTime = new LocalTime(Integer.parseInt(businessHours[2]), Integer.parseInt(businessHours[3]));
		
		int hoursChronology = openingTime.compareTo(closingTime);
		
		if(hoursChronology == 0)
		{
			return true;				// Restaurant is open all day.
		}
		else if(hoursChronology > 0)	// Closes before midnight.
		{
			return nowIsWithinRange(now, openingTime, closingTime);
		}
		else							// Closes after midnight.
		{
			return nowIsWithinRange(now, openingTime, closingTime.plusMinutes(1440));
		}
	}
	
	/**
	 * Check if current time is within range of business hours.
	 * @param now Current DateTime
	 * @param openingTime Time business opens.
	 * @param closingTime Time business closes.
	 * @return True if current time is within business hours.
	 */
	private boolean nowIsWithinRange(DateTime now, LocalTime openingTime, LocalTime closingTime)
	{
		if(now.getMillisOfDay() > openingTime.getMillisOfDay() &&
				now.getMillisOfDay() < closingTime.getMillisOfDay())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public double getStarRating()
	{
		return starRating;
	}

	public void setStarRating(double d)
	{
		this.starRating = d;
	}

	public boolean hasTakeOut()
	{
		return hasTakeOut;
	}

	public void setTakeOut(boolean hasTakeOut)
	{
		this.hasTakeOut = hasTakeOut;
	}
	
	public boolean hasDelivery()
	{
		return hasDelivery;
	}
	
	public void setDelivery(boolean hasDelivery)
	{
		this.hasDelivery = hasDelivery;
	}

	public String[] getCategories()
	{
		return categories;
	}

	public void setCategories(String[] categories)
	{
		this.categories = categories;
	}

	public Map<String, String> getHours()
	{
		return hours;
	}

	public void setHours(Map<String, String> hours)
	{
		if(hours == null)	// Assume open 24/7 when no hours provided.
		{
			this.hours = null;
			this.AlwaysOpen = true;
		}
		this.hours = hours;
	}
}
