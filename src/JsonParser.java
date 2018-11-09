import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import data_structures.Queue;

/*
 * Zachary Hayes
 * CIS152 Data Structures and Algorithms
 * Final Project - Where To Eat
 */
public class JsonParser
{
	/**
	 * Parse Json dataset to Queue of Json Objects.
	 * @param jsonFile Json file location.
	 * @param maxNumOfJsonObjects Max number of Json objects to expect.
	 * @return Queue containing parsed Json objects.
	 */
	public Queue parse(String jsonFile, int maxNumOfJsonObjects) throws FileNotFoundException, IOException, ParseException
	{
		Queue jsonCollection = new Queue(maxNumOfJsonObjects);
		
		try(BufferedReader br = new BufferedReader(new FileReader(jsonFile)))
		{
			// Iterate through json object file.
			for(String line; (line = br.readLine()) != null; )
			{
				// Parse JSON file.
				Object tempObj = new JSONParser().parse(line);
				
				// Typecast to JSONObject.
				JSONObject currentJson = (JSONObject) tempObj;
				
				// Insert object into object queue.
				jsonCollection.insert(currentJson);
			}
			
			return jsonCollection;
		}
	}
}
