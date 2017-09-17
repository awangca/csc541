package csc541projectOne;
import java.util.LinkedList;
import  java.util.Scanner;


public class projectOne {

   public static int minCylinder=0;
   public static int maxCylinder=200;
   public static int numRequest=10;
   public static int initialPoint=33;
   public static int sumFromScan;
	
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
     	System.out.println("The initial point of sequence is: "+initialPoint );
     	System.out.println();
     	
     	FCFS(ref); 
     	SSTF(ref);
     	SCAN(ref);
     	CSCAN(ref);
        LOOK(ref);
     	CLOOK(ref);
  

     	
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
			System.out.println();
			System.out.println("SUGGESTION: Cylinder starts at least 0; Maximum is 500: ");
			System.out.println("");
			System.out.println("Please Input Parameters As Intructed(ALL NUMBERS SHALL BE 'INT' TYPE): ");
			
			System.out.println();
			System.out.println("Input value for Minimum Cylinder:");
			Scanner minCylinerInput= new Scanner(System.in);
			int mcInput=minCylinerInput.nextInt();
			minCylinder=mcInput;
			
			System.out.println();
			System.out.println("Input value for Maximum Cylinder:");
			Scanner maxCylinerInput= new Scanner(System.in);
			int mxcInput=maxCylinerInput.nextInt();
			maxCylinder=mcInput;
			
			System.out.println();
			System.out.println("Input value for No. of Request:");
			Scanner nreqInput= new Scanner(System.in);
			int nrInput=nreqInput.nextInt();
			numRequest=nrInput;
			
			System.out.println();
			System.out.println("Input value for Initial Point:");
			Scanner iniPointInput= new Scanner(System.in);
			int ipInput=iniPointInput.nextInt();
			initialPoint=ipInput;
			
			
			
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
		int[] updatedSequence = new int[transit.length];	
		int[] arrayList = new int[transit.length];
		int[] sortedSeq = new int[transit.length];
		int temp=0, sum=0, minDistance=0, k=0;
		int nsPoint=initialPoint;
		
		for(int i=0;i<transit.length;i++)
			{
				updatedSequence[i]=transit[i];
				arrayList[i]=transit[i];;
			}
		
		for(int p=0;p<arrayList.length;p++)
		{			
			
			minDistance=Math.abs(arrayList[0]-nsPoint);
			for(int i=0;i<arrayList.length;i++)
			{
			
				if(minDistance>=Math.abs(arrayList[i]-nsPoint))
				{
					minDistance=Math.abs(arrayList[i]-nsPoint);
					temp=i;
				}
			}
		    	nsPoint=arrayList[temp];
		    	sortedSeq[p]=nsPoint;
				arrayList[temp]=-1000;

		}
			
		for(int i=1;i<sortedSeq.length-1;i++)
			sum+=Math.abs(sortedSeq[i]-sortedSeq[i-1]);
		sum+=Math.abs(sortedSeq[0]-initialPoint);

		
		  System.out.println("********************************************" );
			 System.out.println("Head movement sequence for SSTF: " );
			 for(int i=0;i<sortedSeq.length;i++)
				 System.out.print(sortedSeq[i]+", " );
			 System.out.println();
		  System.out.println("Head movements for SSTF: " + sum);
		  System.out.println("********************************************" );
		  System.out.println();
                	 
		
	}
	
	public static void SCAN(int[] transit)
	{
		int[] updatedSequence = new int[transit.length];
		int[] tempSequence =  new int[transit.length];
		int min=0, max=0, sum=0;
		int index=0;
		int distance;
		
		for(int i=0;i<transit.length;i++)
		{
			updatedSequence[i]=transit[i];
			tempSequence[i]=transit[i];
			
		}
		distance= Math.abs(updatedSequence[0]-initialPoint);
		
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
			    	  sum+=Math.abs(initialPoint-minCylinder);
			    	  sum+=Math.abs(max-minCylinder);
			    	
			    
			}
		else 
			{
			 
				int m=tempSequence.length-1;
				while(tempSequence[m]>initialPoint)
				{
					startRightIndex=m;
					m--;
				}
			
				 	
					 System.out.println("********************************************" );
					 System.out.println("Head movement sequence for SCAN: " );
					 for(int it=startRightIndex;it<tempSequence.length;it++)
						 System.out.print(tempSequence[it]+", ");
					 System.out.print(maxCylinder+", ");
					 for(int it=startRightIndex-1;it>=0;it--)
						 System.out.print(tempSequence[it]+", ");
				 	
					   sum+=Math.abs(maxCylinder-initialPoint);
					 	sum+=Math.abs(maxCylinder-min);
			 
			
			}
		
		
		sumFromScan=sum;

		  System.out.println();
		  System.out.println("Head movements for SCAN: " + sum);
		  System.out.println("********************************************" );
			 System.out.println();
	}
	
	public static void CSCAN(int[] transit)
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
				 System.out.println("Head movement sequence for CSCAN: " );
				 for(int it=startLeftIndex;it>=0;it--)
					 System.out.print(tempSequence[it]+", ");
				 System.out.print(minCylinder+", ");
				 System.out.print(maxCylinder+", ");
				 for(int it=tempSequence.length-1;it>=startLeftIndex+1;it--)
					 System.out.print(tempSequence[it]+", ");
					 
				 sum+=tempSequence[startLeftIndex];
				 sum+=maxCylinder-tempSequence[startLeftIndex+1];
//			
			    	
			    
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
					 System.out.println("Head movement sequence for CSCAN: " );
					 for(int it=startRightIndex;it<tempSequence.length;it++)
						 System.out.print(tempSequence[it]+", ");
					 System.out.print(maxCylinder+", ");
					 System.out.print(minCylinder+", ");
					 for(int it=0;it<=startRightIndex-1;it++)
						 System.out.print(tempSequence[it]+", ");
				 	
//		    
			 
			
			}

		  System.out.println();
		  System.out.println("Head movements for SCAN: " + sum);
		  System.out.println("********************************************" );
			 System.out.println();
		
		
	}
	
	public static void LOOK(int[] transit)
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
				 System.out.println("Head movement sequence for LOOK: " );
				 for(int it=startLeftIndex;it>=0;it--)
					 System.out.print(tempSequence[it]+", ");
				 System.out.print(minCylinder+", ");
				 for(int it=startLeftIndex+1;it<tempSequence.length;it++)
					 System.out.print(tempSequence[it]+", ");
					 
//			
				  sum+=initialPoint-minCylinder;
		    	  sum+=max-minCylinder;
			    	
			    
			}
		else 
			{
			 
				int m=tempSequence.length-1;
				while(tempSequence[m]>initialPoint)
				{
					startRightIndex=m;
					m--;
				}
			
				 	
					 System.out.println("********************************************" );
					 System.out.println("Head movement sequence for LOOK: " );
					 for(int it=startRightIndex;it<tempSequence.length;it++)
						 System.out.print(tempSequence[it]+", ");
					 System.out.print(maxCylinder+", ");
					 for(int it=startRightIndex-1;it>=0;it--)
						 System.out.print(tempSequence[it]+", ");
				 	
					 sum+=maxCylinder-initialPoint;
					 sum+=maxCylinder-min;
			 
			
			}
		
		

		  System.out.println();
		  System.out.println("Head movements for LOOK: " + sumFromScan);
		  System.out.println("********************************************" );
			 System.out.println();
	
		
	}
	
	public static void CLOOK(int[] transit)
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
				 System.out.println("Head movement sequence for CLOOK: " );
				 for(int it=startLeftIndex;it>=0;it--)
					 System.out.print(tempSequence[it]+", ");
				 for(int it=tempSequence.length-1;it>=startLeftIndex+1;it--)
					 System.out.print(tempSequence[it]+", ");
					 
//			
			    	  sum+=initialPoint-tempSequence[0];
			    	  sum+=tempSequence[tempSequence.length-1]-tempSequence[startLeftIndex+1];
			    	
			    
			}
		else 
			{
			 
				int m=tempSequence.length-1;
				while(tempSequence[m]>initialPoint)
				{
					startRightIndex=m;
					m--;
				}
			
					 System.out.println("********************************************" );
					 System.out.println("Head movement sequence for CLOOK: " );
					 for(int it=startRightIndex;it<tempSequence.length;it++)
						 System.out.print(tempSequence[it]+", ");
					 for(int it=0;it<=startRightIndex-1;it++)
						 System.out.print(tempSequence[it]+", ");
				 	
					 sum+=tempSequence[tempSequence.length-1]-initialPoint;
					 sum+=tempSequence[startRightIndex-1]-tempSequence[0];

			
			}
		
		

		  System.out.println();
		  System.out.println("Head movements for CLOOK: " + sum );
		  System.out.println("********************************************" );
			 System.out.println();
		

		
		
	}
	
	
}
