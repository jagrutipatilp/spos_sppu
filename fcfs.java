import java.util.*;
import java.io.*;
public class fcfs
{
                                                                                                                                           
    public static void main(String args[])
    {
        int n,sum=0;
        float total_tt=0,total_waiting=0;

          Scanner p=new Scanner(System.in);
          System.out.println("Enter Number Of Process"); 
          n=p.nextInt();
          int arrival[]=new int[n];
          int cpu[]=new int[n];
          int finish[]=new int[n];
          int turntt[]=new int[n];
          int wait[]=new int[n];
          int process[]=new int[n];

         // int pro[][]=new int[3][3];
          for(int i=0;i<n;i++)
          {
                System.out.println("Enter arrival time of "+(i+1)+" Process : ");
                arrival[i]=p.nextInt();//input arrival time 
                System.out.println("Enter CPU time of "+(i+1)+" Process : ");
                cpu[i]=p.nextInt(); // input execution time

                process[i]=i+1;
          }

           for(int i=0;i<n;i++)
          {
                sum=sum+cpu[i];
                finish[i]=sum; //calculate finish time of each process
          }

          for(int i=0;i<n;i++)
          {
                turntt[i]=finish[i]-arrival[i]; // turnaround time = finish time - arrival time

                total_tt=total_tt+turntt[i];// calculate total turnaround time

                wait[i]=turntt[i]-cpu[i]; // waiting time = turnaround time - execution time

                total_waiting+=wait[i]; // calculate total waiting time
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
