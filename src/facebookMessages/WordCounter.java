package facebookMessages;

import java.util.ArrayList;
import java.util.Collections;


public class WordCounter
{
	ArrayList<Word> words;

	WordCounter()
	{
		words = new ArrayList<Word>();
	}

	public void addWords(String[] more)
	{
		
		for (String name : more)
		{
			int temp;
			if ((temp = words.indexOf(name)) != -1)
			{
				words.get(temp).count++;
			} else
			{
				words.add(new Word(name, 0));
			}
		}
		Collections.sort(words);
		
	}
	
	
	
	

	public static void main(String[] args)
	{
		
	}

}
