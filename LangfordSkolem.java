/**@author: Adnan Karim
*/
/**Importing the neccessary libraries
*/
import java.util.Vector;
import java.util.Random;
import java.io.*;
import java.util.*;
import java.lang.*;

public class DrGuyRevised
{
	private static int n=15;
	private static int counter=0;
	private static Vector <Integer[]> storage=new Vector<Integer[]>();
	private static Vector <Vector <Integer[]> > hierachy=new Vector<Vector <Integer[]>>();

	public static void eliminate(Integer [] array, Vector <Integer[]> delete)
	{
		Vector <Integer []> toSend=new Vector<Integer[]>();
		toSend.add(array);
		for(int i=0;i<delete.size();i++)
		{
			if((array[0]!= delete.elementAt(i)[0] && array[0]!= delete.elementAt(i)[1] && array[0]!= delete.elementAt(i)[2]) &&
				(array[1]!= delete.elementAt(i)[0] && array[1]!= delete.elementAt(i)[1] && array[1]!= delete.elementAt(i)[2]) &&
				(array[2]!= delete.elementAt(i)[0] && array[2]!= delete.elementAt(i)[1] && array[2]!= delete.elementAt(i)[2]))
			{
				toSend.add(delete.elementAt(i));
			}
			//System.out.println(i);
		}

		toCheck(toSend);

	}

	public static void toCheck(Vector <Integer[]> delete)
	{
		Vector <Integer []> temp=new Vector<Integer[]>();

		for(int i=1;i<delete.size();i++)
		{
			temp.add(delete.elementAt(0));
			Integer [] key=new Integer[3];
			key[0]=delete.elementAt(i)[0];
			key[1]=delete.elementAt(i)[1];
			key[2]=delete.elementAt(i)[2];
			temp.add(key);
			
			int x=1;
			while(x<delete.size())
			{
				int j=1;
			while(j<delete.size())
			{
				if(key[0]!=delete.elementAt(j)[0] && key[0]!= delete.elementAt(j)[1] && key[0]!= delete.elementAt(j)[2] &&
					key[1]!=delete.elementAt(j)[0] && key[1]!= delete.elementAt(j)[1] && key[1]!= delete.elementAt(j)[2]&&
					key[2]!=delete.elementAt(j)[0] && key[2]!= delete.elementAt(j)[1] && key[2]!= delete.elementAt(j)[2])
				{
					if(checkForDuplicates(temp,delete.elementAt(j))==true)
					{
						temp.add(delete.elementAt(j));
						key=new Integer[3];
						key[0]=delete.elementAt(j)[0];
						key[1]=delete.elementAt(j)[1];
						key[2]=delete.elementAt(j)[2];
						j++;
					}
					else
					{
						j++;
					}
				}
				else
				{
					j++;
				}
			}
			if(temp.size()==n)
			{
				if(checkIfSolution(temp)==true)
				{
					System.out.println("Solution found!");
					counter++;
					printSolution(temp);
				
				}
				else{
					temp.subList(2,temp.size()-1).clear();
				}
			}
			x++;
		}//end of while loop 1 (x variable one)
		temp.clear();
		}
	}


	public static void printSolution(Vector <Integer[]> delete)
	{
		for(int i=0;i<delete.size();i++)
		{
			System.out.println(delete.elementAt(i)[0] + " " + delete.elementAt(i)[1] + " " +delete.elementAt(i)[2] + " ");
		}
	}
	public static boolean checkIfSolution(Vector <Integer[]> delete)
	{
		Integer [] actual=new Integer[3*n];
		for(int i=0;i<actual.length;i++)
		{
			actual[i]=i+1;
		}
		Vector <Integer> temp=new Vector<Integer>();
		for(int i=0;i<delete.size();i++)
		{
			temp.add(delete.elementAt(i)[0]);
			temp.add(delete.elementAt(i)[1]);
			temp.add(delete.elementAt(i)[2]);
		}

		Integer[] convert = temp.toArray(new Integer[temp.size()]);
		bubbleSort(convert);
		for(int i=0;i<actual.length;i++)
		{
			if(convert[i]!=actual[i])
			{
				return false;
			}
		}

		return true;


	}

	public static void bubbleSort(Integer []arr){ //Code for the Bubble Sort Algorithm
		for(int i=0; i<arr.length-1;i++){
			for(int j=arr.length-1;j>i;j--){
				if(arr[j-1]>arr[j]){
					int temp=arr[j-1];
					arr[j-1]=arr[j];
					arr[j]=temp;
				}
			}
		}

	}

	public static boolean checkForDuplicates(Vector <Integer[]> delete, Integer[] array)
	{
		for(int i=0;i<delete.size();i++)
		{
			if((array[0]== delete.elementAt(i)[0] || array[0]== delete.elementAt(i)[1] || array[0]== delete.elementAt(i)[2]) ||
				(array[1]== delete.elementAt(i)[0] || array[1]== delete.elementAt(i)[1] || array[1]== delete.elementAt(i)[2]) ||
				(array[2]== delete.elementAt(i)[0] || array[2]== delete.elementAt(i)[1] || array[2]== delete.elementAt(i)[2]))
			{
				return false;
			}
		}
		return true;
	}

	public static void main(String [] args)
	{
		//find all triples satisfying 3x+4y=5z and use counter to see number of total triples
		
		for(int z=1;z<=(3*n);z++){
			for(int y=1;y<=(3*n);y++){
				for(int x=1;x<=(3*n);x++){
					if((x!=z && y!=z && x!=y) && (3*x)+(4*y)==(5*z)){
						Integer [] temp=new Integer[3];
						temp[0]=x;
						temp[1]=y;
						temp[2]=z;
						System.out.println(x + " "+y+ " "+z);
						storage.add(temp);
						//counter++;
					}//end of if statement

				}//end of for loop
			}//end of for loop
		}//end of for loop
		System.out.println(storage.size());
		long start=System.nanoTime();
		for(int i=0;i<storage.size();i++)
		{
			eliminate(storage.elementAt(i),storage);
		}
		System.out.println(counter);

		long  end=System.nanoTime();
		long  time=end-start;
		System.out.println("Time taken: "+(long)time*Math.pow(10,-9)+"s");

	}
}//end of class
