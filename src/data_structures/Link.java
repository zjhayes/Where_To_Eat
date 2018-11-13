package data_structures;
/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class Link
{
	private int id;
	private Object object;
	private Link next;
	
	public Link(int id, Object object)
	{
		this.id = id;
		this.object = object;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Object getObject()
	{
		return object;
	}

	public void setObject(Object object)
	{
		this.object = object;
	}

	public Link getNext()
	{
		return next;
	}

	public void setNext(Link next)
	{
		this.next = next;
	}
}
