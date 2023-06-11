package PravasiHW4;

import java.awt.Point;
import java.util.ArrayList;
import edu.du.dudraw.DUDraw;

public class BruteForceConvexHull 
{
	public static void convexHull(ArrayList<Point> S)
	{
		int leftTurnCount = 0;
		ArrayList<Point> CH = new ArrayList();
		for(int i = 0; i < S.size(); i++)
		{
			for(int j = i+1; j < S.size(); j++)
			{
				leftTurnCount = 0;
				for(int k = 0; k < S.size(); k++)
				{
					if(k != i && k != j)
					{
						if(leftTurn(S.get(i), S.get(j), S.get(k)))
						{
							leftTurnCount += 1;
						}	
					}
				}
				if(leftTurnCount == 0 || leftTurnCount == S.size() - 2)
				{
					/*DUDraw.setPenColor(DUDraw.RED);
					DUDraw.point(S.get(i).x, S.get(i).y);
					DUDraw.point(S.get(j).x, S.get(j).y);*/
					CH.add(S.get(i));
					CH.add(S.get(j));
				}
			}
		}
	}
	public static boolean leftTurn(Point a, Point b, Point i)
	{
		Point vab = new Point(0,0);
		Point vbi = new Point(0,0);
		vab.x = b.x - a.x;
		vab.y = b.y - a.y;
		vbi.x = i.x - b.x;
		vbi.y = i.y - b.y;
		int crossProduct = (vab.x * vbi.y) - (vab.y * vbi.x);
		return crossProduct>0;
	}
	public static void main(String[] args) 
	{
		/*DUDraw.setCanvasSize(1000,1000);
		DUDraw.setXscale(0,1000);
		DUDraw.setYscale(0,1000);
		DUDraw.setPenRadius(10);*/
		ArrayList<Point> CH = new ArrayList();
		/*for(int i = 0; i < 20; i++)
		{
			Point temp = new Point((int) (Math.random()*200), (int) (Math.random()*200));
			CH.add(temp);
			DUDraw.point(temp.x, temp.y);
		}*/
		
		int numberOfTrials = 1000;
		double runtime = 0;
		int n = 0;

		
		System.out.println("Brute Force Convex Hull: ");
		System.out.println("InputSize: \t RunTime: ");
		for(int inputSize = 0; inputSize < 1000; inputSize += 10)
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
				convexHull(CH);
			}
			long endTime = System.currentTimeMillis();
			runtime = (endTime - startTime) / (double) numberOfTrials;
			System.out.println(inputSize + " \t\t " + runtime);
		}
	}
}
