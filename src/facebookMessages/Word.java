package facebookMessages;

public class Word implements Comparable<Word>
{
	String name;
	int count;

	public Word(String name, int count)
	{

		this.name = name;
		this.count = count;
	}

	public boolean equals(Object obj)
	{
		if (name.equals((String) obj))
		{
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Word o)
	{
		
		//checks if they are equal
		if (  o.count - this.count == 0)
		{
			//if they are it returns the Alphabetical order
			return this.name.compareTo(o.name);
		}
		
		// returns numerical order if they aren't equal
		return  o.count - this.count;	
	}
}