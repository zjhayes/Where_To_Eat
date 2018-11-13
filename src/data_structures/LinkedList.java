package data_structures;
/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class LinkedList
{
	private Link first;
	
	public LinkedList()
	{
		first = null;
	}
	
	public boolean isEmpty()
	{
		return (first == null);
	}
	
	public void insert(int key, Object object)
	{
		Link newLink = new Link(key, object);
		Link previous = null;
		Link current = first;
		
		while(current != null && key < current.getId())
		{
			previous = current;
			current = current.getNext();
		}
		
		if(previous == null)
		{
			first = newLink;
		}
		else
		{
			previous.setNext(newLink);
		}
		
		newLink.setNext(current);
	}
	
	public Link deleteFirst()
	{
		Link temp = first;
		first = first.getNext();
		return temp;
	}
	
	public Link delete(int key)
	{
		Link current = first;
		Link previous = first;
		
		while(current.getId() != key)
		{
			if(current.getNext() == null)
			{
				return null;
			}
			else
			{
				previous = current;
				current = current.getNext();
			}
		}
		
		if(current == first)
		{
			first = first.getNext();
		}
		else
		{
			previous.setNext(current.getNext());
		}
		
		return current;
	}
	
	public Link find(int key)
	{
		Link current = first;
		while(current.getId() != key)
		{
			if(current.getNext() == null)
			{
				return null;
			}
			else
			{
				current = current.getNext();
			}
		}
		
		return current;
	}
}
