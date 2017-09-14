package csc541projectOne;
import java.util.LinkedList;
import  java.util.Scanner;


public class projectOne {

   public static int minCylinder=0;
   public static int maxCylinder=200;
   public static int numRequest=10;
   public static int initialPoint=33;
	
	public static void main(String[] args) {

		defaultNum(); // request user to confirm and update variables
		
		//generate sequence
		Generator gen = new Generator(numRequest);
     	int[] ref = gen.getCylinders();
     	
     	// print out all sequence
     	System.out.println("The original disk scheduling sequence: ");
     	for(int i=0; i<numRequest; i++)
     	   {
     		  
     		    if((i+1)%10==0 && i>0)             // next line every ten elements
     		    	 System.out.println(ref[i]);
     		    else System.out.print(ref[i]+", ");
     			  
     	   }
     	System.out.println();
     	System.out.println();
     	
     	FCFS(ref); 
     	SSTF(ref);
     	SCAN(ref);
//     	CSCAN(ref);
//     	CLOOK(ref);
//     	LOOK(ref);

     	
	}

	public static void defaultNum()
	{
		
		System.out.println("The following numbers are defualt value: ");
		System.out.println("Minimum Cylinder: "+ minCylinder);
		System.out.println("Maximum Cylinder: "+ maxCylinder);
		System.out.println("Number of requests: "+ numRequest);
		System.out.println("Initial point: "+ initialPoint);
		System.out.println(); System.out.println();
		System.out.println("Would you reset all these variables? Y/N ");
		Scanner input= new Scanner(System.in);
		String  ans = input.nextLine();
		if(ans.equals("Y")||ans.equals("y"))
		 {
			System.out.println("Y ");
			
		 }
		
		else if(ans.equals("N")||ans.equals("n"))
		     {
			   System.out.println("N ");
			   
			 }
		else 
		{  
			System.out.println("<<<:::ERROR:::>>> Invalid Answer!!!");
			System.out.println("Please Enter 'Y' or 'N' to continue(NOT CASE SENSITIVE): ");
			System.out.println();
			System.out.println();
			defaultNum();
			
		}
		
		
	}
	
	
	
	public static void FCFS(int[] transit)
	{
		int sum=0;
		sum+=Math.abs(initialPoint-transit[0]);
		for (int i=0;i<transit.length-1;i++)
		   sum+= Math.abs(transit[i+1]-transit[i]);
		
		
	 System.out.println("********************************************" );
	 System.out.println("Head movement sequence for FCFS: " );
	 for(int i=0;i<transit.length;i++)
		 System.out.print(transit[i]+", " );
	 System.out.println();
	 System.out.println("Head movements for FCFS: " + sum);
	 System.out.println("********************************************" );
	 System.out.println();
	}
	
	public static void SSTF(int[] transit)
	{
		int[] updatedSequence = transit;	
		int[] arrayList = new int[transit.length];
		int[] sortedSeq = new int[transit.length];
		int temp, sum=0, minDistance=0;
		
		
		for(int i=0;i<arrayList.length;i++)
			arrayList[i]=updatedSequence[i];

		for(int i=0;i<arrayList.length;i++)
			{
				for(int j=i+1;j<arrayList.length;j++)
					if(Math.abs(arrayList[i]-initialPoint)>Math.abs(arrayList[j]-initialPoint))
						{
								minDistance=arrayList[j];
						}
		
			}
				
		
		  // calculate head movements
		  sum+=Math.abs(initialPoint-transit[0]);
		  for (int i=0;i<transit.length-1;i++)
		      sum+= Math.abs(transit[i+1]-transit[i]);
		
		  System.out.println("********************************************" );
			 System.out.println("Head movement sequence for SSTF: " );
			 for(int i=0;i<updatedSequence.length;i++)
				 System.out.print(updatedSequence[i]+", " );
			 System.out.println();
		  System.out.println("Head movements for SSTF: " + sum);
		  System.out.println("********************************************" );
		  System.out.println();
                	 
		
	}
	
	public static void SCAN(int[] transit)
	{
		int[] updatedSequence = transit;
		int[] tempSequence = transit;
		int min=0, max=0, sum=0;
		int index=0;
		int distance= Math.abs(updatedSequence[0]-initialPoint);
		for(int i=0;i<updatedSequence.length;i++)
		{
			if(i==0)
			{
				min=updatedSequence[0];
				max=updatedSequence[0];
			}
			else 
			{
				if(updatedSequence[i]<min)  // find the minimum number in sequence
					min=updatedSequence[i];
				
				if(updatedSequence[i]>max)   // find the maximum number in sequence
					max=updatedSequence[i];
				
		    }
			
			if(Math.abs(updatedSequence[i]-initialPoint)<distance)  // locate the nearest point on disk to start with
			{
				distance=Math.abs(updatedSequence[i]-initialPoint);
				index=i;
				

			}
				
		}
//		System.out.println(updatedSequence[index]+"<<< "+index +" >>>>>");
		
		for(int i=0;i<updatedSequence.length;i++)
			tempSequence[i]=updatedSequence[i];
		// re arrange from smallest to largest in sequence
		int temp, startLeftIndex=0,startRightIndex=0;
		for(int i=0;i<tempSequence.length-1;i++)
			for(int j=i+1;j<tempSequence.length;j++)
                 if(tempSequence[i]>tempSequence[j])
                	 {
                	    temp=tempSequence[i];
                	    tempSequence[i]=tempSequence[j];
                	    tempSequence[j]=temp;
                	 }
		

//		System.out.println(updatedSequence[index]+"<<<<<"+index +">>>>>");
		// confirm if the nearest point is on the left side or the right side
		if(updatedSequence[index]<=initialPoint)
			{
//			    
				int i=0;
				while(tempSequence[i]<=initialPoint)
				{
					startLeftIndex=i;
					
					i++;
				}
				
				
				 System.out.println("********************************************" );
				 System.out.println("Head movement sequence for SCAN: " );
				 for(int it=startLeftIndex;it>=0;it--)
					 System.out.print(tempSequence[it]+", ");
				 System.out.print(minCylinder+", ");
				 for(int it=startLeftIndex+1;it<tempSequence.length;it++)
					 System.out.print(tempSequence[it]+", ");
					 
//			
			    	  sum+=initialPoint-minCylinder;
			    	  sum+=max;
			    	
			    
			}
		else 
			{
			 
				int m=tempSequence.length-1;
				while(tempSequence[m]>initialPoint)
				{
					startRightIndex=m;
					m--;
				}
			
				 	sum+=maxCylinder-initialPoint;
				 	sum+=max-min;
					 System.out.println("********************************************" );
					 System.out.println("Head movement sequence for SCAN: " );
					 for(int it=startRightIndex;it<tempSequence.length;it++)
						 System.out.print(tempSequence[it]+", ");
					 System.out.print(maxCylinder+", ");
					 for(int it=startRightIndex-1;it>=0;it--)
						 System.out.print(tempSequence[it]+", ");
				 	
//		    
			 
			
			}
		
		

		  System.out.println();
		  System.out.println("Head movements for SCAN: " + sum);
		  System.out.println("********************************************" );
			 System.out.println();
	}
	
	public static void CSCAN(int[] transit)
	{
		int[] updatedSequence = transit;
		int temp, sum=0;
		int indexLeft=0, indexRight=0;

		for(int i=0;i<updatedSequence.length-1;i++)
			for(int j=i+1;j<updatedSequence.length;j++)
                 if(Math.abs(updatedSequence[i])>Math.abs(updatedSequence[j]))
                	 {
                	    temp=updatedSequence[i];
                	    updatedSequence[i]=updatedSequence[j];
                	    updatedSequence[j]=temp;
                	 }
		
		for(int i=0;i<updatedSequence.length;i++)  // get the index of nearest position on the right side
		{
			if(updatedSequence[i]>=initialPoint)
			{
				indexRight=i;
				break;
			}
		}
		
		for(int i=updatedSequence.length-1;i>=0;i--)
		{
			if(updatedSequence[i]<initialPoint)
			{
				indexLeft=i;
				break;
			}
		}
		
		  
        sum+= (maxCylinder-minCylinder)-(updatedSequence[indexRight]-updatedSequence[indexLeft]);
        System.out.println("Head movements for CSCAN: " + sum);
	    System.out.println();
		
		
	}
	
	public static void LOOK(int[] transit)
	{
		int[] updatedSequence = transit;
		int min=0, max=0, sum=0;
		int index=0;
		int distance= Math.abs(updatedSequence[0]-initialPoint);
		for(int i=0;i<updatedSequence.length;i++)
		{
			if(i==0)
			{
				min=updatedSequence[0];
				max=updatedSequence[0];
			}
			else 
			{
				if(updatedSequence[i]<min)  // find the minimum number in sequence
					min=updatedSequence[i];
				
				if(updatedSequence[i]>max)   // find the maximum number in sequence
					max=updatedSequence[i];
				
		    }
			
			if(Math.abs(updatedSequence[i]-initialPoint)<distance)  // locate the nearest point on disk to start with
			{
				distance=Math.abs(updatedSequence[i]-initialPoint);
				index=i;
				
			}
			
		}
			
		// confirm if the nearest point is on the left side or the right side
		if(updatedSequence[index]<=initialPoint)
			{
			    
			    	  sum+=initialPoint-minCylinder;
			    	  sum+=max;
			    	
			    
			}
		else 
			{
			 
		
				 	sum+=maxCylinder-initialPoint;
				 	sum+=max-min;
		    
			 
			
			}
		
		  System.out.println("Head movements for LOOK: " + sum);
		  System.out.println();
		
		
	}
	
	public static void CLOOK(int[] transit)
	{
		int[] updatedSequence = transit;
		int temp, sum=0;
		int indexLeft=0, indexRight=0;

		for(int i=0;i<updatedSequence.length-1;i++)
			for(int j=i+1;j<updatedSequence.length;j++)
                 if(Math.abs(updatedSequence[i])>Math.abs(updatedSequence[j]))
                	 {
                	    temp=updatedSequence[i];
                	    updatedSequence[i]=updatedSequence[j];
                	    updatedSequence[j]=temp;
                	 }
		
		for(int i=0;i<updatedSequence.length;i++)  // get the index of nearest position on the right side
		{
			if(updatedSequence[i]>=initialPoint)
			{
				indexRight=i;
				break;
			}
		}
		
		for(int i=updatedSequence.length-1;i>=0;i--)
		{
			if(updatedSequence[i]<initialPoint)
			{
				indexLeft=i;
				break;
			}
		}
		
		if((updatedSequence[indexRight]-initialPoint)>(initialPoint-updatedSequence[indexLeft]))
		{
			sum+=updatedSequence[updatedSequence.length-1]-updatedSequence[indexRight];
			sum+=initialPoint-updatedSequence[0];
		}
		else 
		{
			sum+=updatedSequence[updatedSequence.length-1]-initialPoint;
			sum+=updatedSequence[indexLeft]-updatedSequence[0];
		}

        System.out.println("Head movements for CLOOK: " + sum);
	    System.out.println();
		
		
		
	}
	
	
}
