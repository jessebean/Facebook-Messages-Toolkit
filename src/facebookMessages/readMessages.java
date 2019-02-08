package facebookMessages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class readMessages
{

	public static void readJson(File toImport) throws FileNotFoundException, IOException, ParseException
	{
		// parsing file "JSONExample.json"
		Object obj = new JSONParser().parse(new FileReader(toImport));

		// typecasting obj to JSONObject
		JSONObject contents = (JSONObject) obj;

		JSONArray messages = (JSONArray) contents.get("messages");

		parseMessages(messages);

	}

	@SuppressWarnings("unchecked")
	public static void parseMessages(JSONArray messages)
	{

		int numOfPictures = 0;
		int numOfStickers = 0;
		int numOfVideos = 0;
		int numOfLinks = 0;
		int numOfEmojis = 0;
		for (Object item : messages)
		{
			//Casts and Object to the JSONObject
			JSONObject message = (JSONObject) item;
			
			//Checks to see what type of message it is.
			//Different types implemented to date are:
			//photos
			//videos
			//sticker
			//share
			
			if (message.get("photos") != null)
			{
				JSONArray photos = (JSONArray) message.get("photos");
				numOfPictures += photos.size();
			} else if (message.get("videos") != null)
			{
				JSONArray videos = (JSONArray) message.get("videos");
				numOfVideos += videos.size();
			} else if (message.get("sticker") != null)
			{
				numOfStickers++;
			} else if (message.get("share") != null)
			{
				numOfLinks++;
			} 
			
			//If it doesn't fall under those types 
			//It is parsed under string
			else
			{
				//Read in the message and convert to lower case
				String content = (String) message.get("content");
				content = content.toLowerCase();
				
				
				//Some links were getting through. This should clean up the rest
				if (content.indexOf("http://") != -1 || content.indexOf("https://") != -1)
				{
					numOfLinks++;
				} 
				
				//This handles emojis.				
				else if (content.indexOf("\\u") != -1)
				{
					// \\u used to define when an emoji is used. Not 100% accurate. But too lazy to
					// improve rn
					String findStr = "\\u";
					int lastIndex = 0;

					while (lastIndex != -1)
					{
						lastIndex = content.indexOf(findStr, lastIndex);
						if (lastIndex != -1)
						{
							numOfEmojis++;
							lastIndex += findStr.length();
						}
					}
				} 
				
				
				else
				{
					// Was runnig into a run time error with the two replaceAlls
					String[] words = content.replaceAll("[?,.\"(){};:<>/\\+=_-]'", "").replaceAll("[%$#@!`~]", "")
							.split("\\s+");

				}

			}

		}
	}

	// driver
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException
	{
		readJson(new File(
				"\\\\ccanet\\home\\jhmcdona\\My Documents\\facebook-msot99\\messages\\inbox\\jonathantrost_svbtaho_qa\\message.json"));

	}

}
