package assists;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import data_structures.States;

/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class DatasetCleaner
{
	private States states = new States();
	
	private int numOfRuns = 1;	// Keeps track of how many files are created.
	private String newFile = "data//cleanedUp" + numOfRuns + ".json";	// Name of new file.
	
	/**
	 * This is not part of the program, just used to clean up the dataset.
	 * It removes Json objects from a dataset which reference states not
	 * listed in the States HashMap.
	 * @param jsonFile File name of Json dataset.
	 */
	public void cleanUpDataset(String jsonFile) throws FileNotFoundException, IOException, ParseException
	{		
		try(BufferedReader br = new BufferedReader(new FileReader(jsonFile));
				BufferedWriter bw = new BufferedWriter(new FileWriter(newFile)))
		{
			System.out.print("Running cleanup... ");
			
			// Iterate through json object file.
			for(String line; (line = br.readLine()) != null; )
			{
				// Parse JSON file.
				Object tempObj = new JSONParser().parse(line);
				
				// Typecast to JSONObject.
				JSONObject currentJson = (JSONObject) tempObj;
				
				// Get state.
				String currentProperty = (String) currentJson.get("categories");

				/* FOR ARRAY */
				if(currentProperty != null)
				{
					String[] values = currentProperty.split("\\s*,\\s*");
					if(Arrays.asList(values).contains("Restaurants"))
					{
						System.out.println("Adding: " + Arrays.toString(values));

						bw.write(line);	// Write line to new file.
						bw.newLine();
					}

				}
				
				/* FOR STRING
				// If States HashMap matches Json Object state, add to new dataset.
				if(states.getStateCodeToNameMap().containsKey(currentProperty))
				{
					bw.write(line);	// Write line to new file.
					bw.newLine();
				}*/
			}
			
			numOfRuns++;
			System.out.println("Finished.");
		}
	}
}
