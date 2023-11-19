import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Process {
    int pid;
    int burstTime;
    int priority;
    int waitingTime;
    int turnaroundTime;

    public Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.priority = priority;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }
}

public class priority {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        for (int i = 1; i <= numProcesses; i++) {
            System.out.print("Enter burst time for Process " + i + ": ");
            int burstTime = scanner.nextInt();

            System.out.print("Enter priority for Process " + i + ": ");
            int priority = scanner.nextInt();

            processes.add(new Process(i, burstTime, priority));
        }

        // Sort processes by priority
        Collections.sort(processes, Comparator.comparingInt(p -> p.priority));

        int currentTime = 0;

        for (Process process : processes) {
            process.waitingTime = currentTime;
            currentTime += process.burstTime;
            process.turnaroundTime = process.waitingTime + process.burstTime;
        }

        // Print the schedule
        System.out.println("Process\t\tBurst Time\t\tPriority\t\tWaiting Time\t\tTurnaround Time");
        for (Process process : processes) {
            System.out.println(process.pid + "\t\t" + process.burstTime + "\t\t\t" + process.priority + "\t\t\t"
                    + process.waitingTime + "\t\t\t" + process.turnaroundTime);
        }

        double avgWaitingTime = processes.stream().mapToDouble(p -> p.waitingTime).average().orElse(0);
        double avgTurnaroundTime = processes.stream().mapToDouble(p -> p.turnaroundTime).average().orElse(0);

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }
}
