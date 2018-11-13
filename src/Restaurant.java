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
	private String city;
	private String state;
	private double starRating;
	private boolean isTakeOut;
	private String[] categories;
	private String hours;
	
	public boolean isOpen()
	{
		return true;
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

	public boolean isTakeOut()
	{
		return isTakeOut;
	}

	public void setTakeOut(boolean isTakeOut)
	{
		this.isTakeOut = isTakeOut;
	}

	public String[] getCategories()
	{
		return categories;
	}

	public void setCategories(String[] categories)
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
