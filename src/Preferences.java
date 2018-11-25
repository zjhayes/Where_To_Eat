/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class Preferences
{
	private String city;
	private String state;
	private String[] categories;
	private boolean wantsDineIn;
	private boolean wantsTakeOut;
	private boolean wantsDelivery;
	
	
	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
	}
	public String[] getCategories()
	{
		return categories;
	}
	public void setCategories(String[] categories)
	{
		this.categories = categories;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public boolean wantsDineIn()
	{
		return wantsDineIn;
	}
	public void setWantsDineIn(boolean wantsDineIn)
	{
		this.wantsDineIn = wantsDineIn;
	}
	public boolean wantsTakeOut()
	{
		return wantsTakeOut;
	}
	public void setWantsTakeOut(boolean wantsTakeOut)
	{
		this.wantsTakeOut = wantsTakeOut;
	}
	public boolean wantsDelivery()
	{
		return wantsDelivery;
	}
	public void setWantsDelivery(boolean wantsDelivery)
	{
		this.wantsDelivery = wantsDelivery;
	}
	
}

