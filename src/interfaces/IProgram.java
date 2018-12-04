package interfaces;

import java.util.List;

/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public interface IProgram
{
	public void runProgram();
	public void setPreferences(String state, List<String> categories, boolean wantsDineIn, boolean wantsTakeOut, boolean wantsDelivery);
}
