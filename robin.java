import java.util.*;
import java.io.*;
public class robin
{
    public static void main(String args[])
    {
        int n,sum=0;
        float total_tt=0,total_waiting=0;

          Scanner s=new Scanner(System.in);
          System.out.println("Enter Number Of Process"); 
          n=s.nextInt();
          int arrival[]=new int[n];
          int cpu[]=new int[n];
          int ncpu[]=new int[n];
          int pri[]=new int[n];
          int finish[]=new int[100];
          int turntt[]=new int[n];
          int wait[]=new int[n];
          int process[]=new int[n];
          int t_quantum,difference,temp_sum=0,k=0;
          int seq[]=new int[100];

        
          for(int i=0;i<n;i++)
          {
                System.out.println("Enter arrival time of "+(i+1)+" Process : ");
                arrival[i]=s.nextInt();//input arrival time 
                System.out.println("Enter CPU time of "+(i+1)+" Process : ");
                ncpu[i]=cpu[i]=s.nextInt();// input execution time
                process[i]=i+1;
          }

         System.out.println("Enter time quantum : ");
         t_quantum = s.nextInt();// input time slice

          int tv=0;
          for(int i=0;i<n;i++)
          {
            temp_sum=temp_sum+cpu[i];//calculate total execution time
      }
            while(sum!=temp_sum)
            {
		  for(int i=0;i<n;i++)
		  {
		  	if(ncpu[i]<t_quantum)
		  		{
		  			difference=ncpu[i];
		  			tv=ncpu[i];
		  			ncpu[i]=0;
		  		}
		  	else
		  		{
		  			difference = ncpu[i]-t_quantum;
		  			tv=t_quantum;
		  			ncpu[i]=difference;//calculate remaining time for process
		  		}
		  	if(tv > 0)
		  	{	
		        sum=sum+tv;
		        finish[k]=sum;
		        seq[k]=i;// calculate the sequence of process of execution
		        k++;
		        }
		  }
          }
          System.out.println();
          for(int i=0;i<n;i++)
          {	
          	int carr=0,tt=0;
          	carr=arrival[i];

          	for(int j=0;j<k;j++)
          	{
                	if(seq[j]==i)
                	{
                		tt=tt+(finish[j]-carr);
                		carr=finish[j];
                	}
                }
                turntt[i]=tt;
                total_tt=total_tt+turntt[i];
                wait[i]=turntt[i]-cpu[i];
                total_waiting+=wait[i];
          }
System.out.println("\n\nProcessId\tArrivalTime\tBurstTime\tWaitingTime\tTurnAroundTime");
          for(int i=0;i<n;i++)
          {
   System.out.println(process[i]+"\t\t"+arrival[i]+"\t\t"+cpu[i]+"\t\t"+wait[i]+"\t\t"+turntt[i]);
          }

          System.out.println("\n\n");
          System.out.println("Total turn around time is : "+(total_tt/n));
          System.out.println("Total waiting time is : "+(total_waiting/n));
    }
}
