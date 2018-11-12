import java.lang.reflect.Array;

import org.joda.time.DateTime;
/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class Restaurant
{
	private String name;
	private int score;		// How well this restaurant matches user preferences.
	private String city;
	private String state;
	private long postalCode;
	private float starRating;
	private boolean isTakeOut;
	private Array categories;
	private String hours;
	
	public boolean isOpen()
	{
		return false;
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

	public long getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(long postalCode)
	{
		this.postalCode = postalCode;
	}

	public float getStarRating()
	{
		return starRating;
	}

	public void setStarRating(float starRating)
	{
		this.starRating = starRating;
	}

	public boolean isTakeOut()
	{
		return isTakeOut;
	}

	public void setTakeOut(boolean isTakeOut)
	{
		this.isTakeOut = isTakeOut;
	}

	public Array getCategories()
	{
		return categories;
	}

	public void setCategories(Array categories)
	{
		this.categories = categories;
	}

	public String getHours()
	{
		return hours;
	}

	public void setHours(String hours)
	{
		this.hours = hours;
	}
}
