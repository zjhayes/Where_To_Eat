package data_structures;
/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class Queue
{
	private int maxSize;
	private Object[] queArray;
	private int front;
	private int rear;
	private int nItems;
	
	public Queue(int size)
	{
		maxSize = size;
		queArray = new Object[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}
	
	/**
	 * Add object to end of queue.
	 * @param obj Object to be added.
	 */
	public void insert(Object obj)
	{
		if(rear == maxSize - 1)
		{
			rear = -1;
		}	
		queArray[++rear] = obj;
		nItems++;
	}
	
	/**
	 * Remove next object in queue.
	 * @return front object.
	 */
	public Object remove()
	{
		Object temp = queArray[front++];
		// Deal with wraparound.
		if(front == maxSize)
		{
			front = 0;
		}
		nItems--;
		return temp;
	}
	
	/**
	 * Peek at front object.
	 * @return object at front of queue.
	 */
	public Object peek()
	{
		return queArray[front];
	}
	
	/**
	 * Check if queue is empty.
	 * @return true if empty.
	 */
	public boolean isEmpty()
	{
		return (nItems == 0);
	}
	
	/**
	 * Check if queue is full.
	 * @return true if full.
	 */
	public boolean isFull()
	{
		return (nItems == maxSize);
	}
	
	/**
	 * Get number of items in queue.
	 * @return number of items in queue.
	 */
	public int size()
	{
		return nItems;
	}
}
