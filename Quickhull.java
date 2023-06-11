package PravasiHW4;

import java.awt.Point;
import java.util.ArrayList;

import edu.du.dudraw.DUDraw;

public class Quickhull 
{
	public static int valueBasedOnLineDistance(Point a, Point b, Point p)
	{
		Point v1 = new Point(0,0);
		Point v2 = new Point(0,0);
		v1.x = b.x - a.x;
		v1.y = b.y - a.y;
		v2.x = p.x - a.x;
		v2.y = p.y - a.y;
		return v1.x * v2.y - v1.y * v2.x;
	}
	public static void quickHullStart(ArrayList<Point> S)
	{
		int minX = 501;
		int maxX = -1;
		Point a = null;
		Point b = null;
		ArrayList<Point> SUpper = new ArrayList();
		ArrayList<Point> SLower = new ArrayList(); 
		for(int i = 0; i < S.size(); i++)
		{
			if(S.get(i).x < minX)
			{
				a = S.get(i);
				minX = S.get(i).x;
			}
			if(S.get(i).x > maxX)
			{
				b = S.get(i);	
				maxX = S.get(i).x;
			}
		}
		for(int i = 0; i < S.size(); i++)
		{
			if(valueBasedOnLineDistance(a, b, S.get(i)) > 0)
			{
				SUpper.add(S.get(i));
			}
			else
			{				
				SLower.add(S.get(i));
			}
		}
		ArrayList<Point> Uresult = new ArrayList();
		ArrayList<Point> Lresult = new ArrayList();

		ArrayList<Point> upResult = quickHull(SUpper, a, b, Uresult);
		ArrayList<Point> lowResult = quickHull(SLower, b, a, Lresult);
		upResult.add(a);
		upResult.add(b);
		
		DUDraw.setPenColor(DUDraw.RED);
		for(Point p : upResult)
		{
			DUDraw.point(p.x, p.y);
		}
		for(Point p : lowResult)
		{
			DUDraw.point(p.x, p.y);
		}
	}
	public static ArrayList<Point> quickHull(ArrayList<Point> S, Point a, Point b, ArrayList<Point> result)
	{
		if(S.isEmpty())
		{
			return result;
		}
		Point furthest = null;
		int maxDistance = -1;
		ArrayList<Point> left = new ArrayList();
		ArrayList<Point> right = new ArrayList();
		for(Point p : S)
		{
			if(Math.abs(valueBasedOnLineDistance(a, b, p)) > maxDistance)
			{
				maxDistance = Math.abs(valueBasedOnLineDistance(a, b, p));
				furthest = p;
			}
		}
		for(Point p : S)
		{
			if(valueBasedOnLineDistance(a, furthest, p) > 0)
			{
				left.add(p);
			}
			else if(valueBasedOnLineDistance(furthest, b, p) > 0)
			{
				right.add(p);
			}
		}
		result.add(furthest);
		quickHull(left, a, furthest, result); 
		quickHull(right, furthest, b, result);
		return result;
	}
	public static void main(String[] args) 
	{
		ArrayList<Point> CH = new ArrayList();
		DUDraw.setCanvasSize(500,500);
		DUDraw.setXscale(0,200);
		DUDraw.setYscale(0,200);
		DUDraw.setPenRadius(5);
		for(int i = 0; i < 20; i++)
		{
			Point temp = new Point((int) (Math.random()*200), (int) (Math.random()*200));
			CH.add(temp);
			DUDraw.point(temp.x, temp.y);
		}
		quickHullStart(CH);
		
		/*int numberOfTrials = 1000;
		double runtime = 0;
		int n = 0;

		
		System.out.println("QuickHull: ");
		System.out.println("InputSize: \t RunTime: ");
		for(int inputSize = 0; inputSize < 10000; inputSize += 100)
		{
			for(int i = 0; i < inputSize; i++)
			{
				Point temp = new Point((int) (Math.random()*inputSize), (int) (Math.random()*inputSize));
				CH.add(temp);
			}
			n = CH.size();
			long startTime = System.currentTimeMillis();
			for(int i=0; i < numberOfTrials; i++)
			{
				quickHullStart(CH);
			}
			long endTime = System.currentTimeMillis();
			runtime = (endTime - startTime) / (double) numberOfTrials;
			System.out.println(inputSize + " \t\t " + runtime);
		}*/
	}
}
