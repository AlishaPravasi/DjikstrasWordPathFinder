package PravasiHW6;

import java.io.*;
import java.util.*;

public class WordPathDriver 
{
	public static void main(String[] args) 
	{
		Hashtable<String, ArrayList<String>> dict = new Hashtable<>();

		Scanner s = null;
		
		try {
			s = new Scanner(new File("Dict.txt"));
			while(s.hasNext())
			{
				dict.put(s.next(), new ArrayList<String>());
			}
		}catch(FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		WordPath obj = new WordPath(dict);
		obj.hashWords();
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the start word: ");
		String start = input.next();
		System.out.println("Please enter the end word: ");
		String end = input.next();
		while(start.length() != end.length())
		{
			System.out.println("The words must be the same length. \nPlease enter the start word: ");
			start = input.next();
			System.out.println("Please enter the end word: ");
			end = input.next();
		}
		System.out.println(obj.shortestPath(start, end));
	}

}
