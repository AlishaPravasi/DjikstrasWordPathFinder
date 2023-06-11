package PravasiHW6;
import java.util.*;

public class WordPath 
{
	public Hashtable<String, ArrayList<String>> dict;
	public WordPath(Hashtable<String, ArrayList<String>> d)
	{
		this.dict = d;
	}
	public void hashWords()
	{
		for(String word : dict.keySet())
		{
			addEdges(word);
		}
	}
	public void addEdges(String wordToBeHashed)
	{
		String tempWord = wordToBeHashed;
		StringBuilder builder = new StringBuilder(tempWord);
		int ascii = 97;
		for(int i = 0; i < wordToBeHashed.length(); i++)
		{
			tempWord = wordToBeHashed;
			builder = new StringBuilder(tempWord);
			for(int j = 0; j < 26; j++)
			{
				ascii = 97 + j;
				builder.replace(i, i+1, new Character((char) ascii).toString());
				tempWord = builder.toString();
				if(dict.containsKey(tempWord) && !tempWord.equals(wordToBeHashed))
				{
					dict.get(wordToBeHashed).add(tempWord);
				}
			}
		}
		//System.out.println(wordToBeHashed + ": " + dict.get(wordToBeHashed));
	}
	public int weight(String s1, String s2) 
	{
        int weight = 0;
        for (int i = 0; i < s1.length(); i++)
        {
            if (s1.charAt(i) != s2.charAt(i)) 
            {
                weight = Math.abs(s1.charAt(i) - s2.charAt(i));
            }
        }
        return weight;
    }
	public ArrayList<String> shortestPath(String start, String end)
	{
	    PriorityQueue<DjikstraVertex> openPq = new PriorityQueue<>();
	    Hashtable<String, DjikstraVertex> openList = new Hashtable<>();
	    Hashtable<String, DjikstraVertex> closeList = new Hashtable<>();
	    DjikstraVertex startVertex = new DjikstraVertex(start, 0, "");
	    openPq.offer(startVertex);
	    openList.put(startVertex.vertex, startVertex);
	    
	    while(!openPq.isEmpty() && !closeList.containsKey(end))
	    {
	        DjikstraVertex x = openPq.poll();
	        if(closeList.containsKey(x.vertex)) 
	        {
	            continue; 
	        }
	        closeList.put(x.vertex, x);
	        ArrayList<String> tempNeighbors = dict.get(x.vertex);
	        if(tempNeighbors.size() > 0)
	        {
	            for(String neighbor : tempNeighbors)
	            {
	                DjikstraVertex temp = new DjikstraVertex(neighbor, (x.d+weight(x.vertex, neighbor)), x.vertex);
	                if(!closeList.containsKey(temp.vertex) && (!openList.containsKey(temp.vertex) || openList.get(temp.vertex).d > temp.d))
	                {
	                    openPq.offer(temp);
	                    openList.put(temp.vertex, temp);
	                }
	            }
	        }
	    }
	    
	    ArrayList<String> path = new ArrayList<>();
	    String temp = end;
	    try {
	    	while(!temp.equals(start))
		    {
		        path.add(0, temp);
		        temp = closeList.get(temp).predecessor;
		    }
		    path.add(0, start);
		    return path;
	    }catch(Exception e)
	    {
	    	path.clear();
	    	path.add("NO PATH HERE");
	    	return path;
	    }
	}
}
