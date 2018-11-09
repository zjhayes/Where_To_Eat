import org.joda.time.DateTime;
/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class Restaurant
{
	private String id;
	private String name;
	private String city;
	private String state;
	private long postalCode;
	private float starRating;
	private boolean isTakeOut;
	private String[] categories;
	private String hours;
	
	private boolean isOpen()
	{
		return false;
	}
}
