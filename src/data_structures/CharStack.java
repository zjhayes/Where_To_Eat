package data_structures;
/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class CharStack
{
	private int maxSize;
	private char[] stackArray;
	private int top;
	
	public CharStack(int size)
	{
		maxSize = size;
		stackArray = new char[maxSize];
		top = -1;
	}
	
	/**
	 * Put item on top of stack.
	 * @param ch - Character to put on stack.
	 */
	public void push(char ch)
	{
		stackArray[++top] = ch;
	}
	
	/**
	 * Take item from top of stack.
	 * @return character on top of stack.
	 */
	public char pop()
	{
		return stackArray[top--];
	}
	
	/**
	 * Peek at character on top of stack.
	 * @return character on top of stack.
	 */
	public char peek()
	{
		return stackArray[top];
	}
	
	/**
	 * Check if stack is empty.
	 * @return true if empty.
	 */
	public boolean isEmpty()
	{
		return (top == -1);
	}
}
