package PravasiHW6;

public class DjikstraVertex implements Comparable
{
	public String vertex;
	public int d;
	public String predecessor;
	public DjikstraVertex(String v, int distance, String pre)
	{
		this.vertex = v;
		this.d = distance;
		this.predecessor = pre;
	}
	public int compareTo(Object o) 
	{
		if(o instanceof DjikstraVertex)
		{
			return this.d - ((DjikstraVertex)o).d;
		}
		return 0;
	}	
	public boolean equals(Object o)
	{
		if(o instanceof DjikstraVertex)
		{
			return this.vertex == ((DjikstraVertex)o).vertex;
		}
		return false;
	}
	public String toString()
	{
		return " vertex: " + this.vertex + " d: " + this.d + " predecessor: " + this.predecessor + " \n";
	}
}

