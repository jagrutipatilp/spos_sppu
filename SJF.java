import java.util.*;
 
public class SJF {
public static void main(String args[])
{
Scanner sc = new Scanner(System.in);
System.out.println ("Enter no of process:");
int n = sc.nextInt();
int pid[] = new int[n];
int at[] = new int[n]; // at means arrival time
int bt[] = new int[n]; // bt means burst time
int ct[] = new int[n]; // ct means complete time
int ta[] = new int[n]; // ta means turn around time
int wt[] = new int[n];  //wt means waiting time
int f[] = new int[n];  // f means it is flag it checks process is completed or not
int st=0, tot=0;
float avgwt=0, avgta=0;
 
for(int i=0;i<n;i++)
{
System.out.println ("Enter process " + (i+1) + " arrival time:");
at[i] = sc.nextInt();
System.out.println ("Enter process " + (i+1) + " brust time:");
bt[i] = sc.nextInt();
pid[i] = i+1;
f[i] = 0;
}
boolean a = true;
while(true)
{
int c=n, min=999;
if (tot == n) 
break;
for (int i=0; i<n; i++)
{

if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
{
min=bt[i];
c=i;
}
}
if (c==n)
st++;
else
{
ct[c]=st+bt[c];
st+=bt[c];
ta[c]=ct[c]-at[c];
wt[c]=ta[c]-bt[c];
f[c]=1;
tot++;
}
}
System.out.println("\npid \t arrivaltime \t brusttime \t turnaroundtime \t waitingtime");
for(int i=0;i<n;i++)
{
avgwt+= wt[i];
avgta+= ta[i];
System.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+ta[i]+"\t\t"+wt[i]);
}
System.out.println ("\nAverage tat is "+ (float)(avgta/n));
System.out.println ("Average wt is "+ (float)(avgwt/n));
sc.close();
}
}
