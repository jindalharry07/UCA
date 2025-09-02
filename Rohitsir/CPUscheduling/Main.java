package CPUscheduling;
import CPUscheduling.model.Task;
import CPUscheduling.algos.PriorityScheduler;
import CPUscheduling.algos.RoundRobinScheduler;
import CPUscheduling.algos.ShortestRemaingTime;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        System.out.print("Enter number of tasks: ");
        int n = sc.nextInt();

        // for (int i = 0; i < n; i++) {
        //     System.out.printf("Enter arrivalTime, burstTime, priority for Task %d: ", i + 1);
        //     int arrival = sc.nextInt();
        //     int burst = sc.nextInt();
        //     int priority = sc.nextInt();
        //     taskList.add(new Task(i + 1, arrival, burst, priority));
        // }

        // System.out.print("Enter time quantum: ");
        // int tq = sc.nextInt();

        // taskList.add(new Task(1, 0, 5, n));  // Task 1 arrives at 0, burst time 5
        // taskList.add(new Task(2, 1, 3, n));  // Task 2 arrives at 1, burst time 3
        // taskList.add(new Task(3, 2, 8, n));  // Task 3 arrives at 2, burst time 8
        // taskList.add(new Task(4, 3, 6, n));  // Task 4 arrives at 3, burst time 6

        // Define time quantum
        int timeQuantum = 2;

        taskList.sort((t1, t2) -> t1.getArrivalTime() - t2.getArrivalTime());
        // RoundRobinScheduler rr = new RoundRobinScheduler(tq);
        RoundRobinScheduler rr = new RoundRobinScheduler(timeQuantum);
        rr.execute(taskList);

        // taskList.add(new Task(1, 0, 6));
        // taskList.add(new Task(2, 1, 8));
        // taskList.add(new Task(3, 2, 7));
        // taskList.add(new Task(4, 3, 3));

        // Create scheduler instance
        ShortestRemaingTime srtf = new ShortestRemaingTime();
        // srtf.execute(taskList);

        taskList.add(new Task(1,0,6,2));
        taskList.add(new Task(2,1,8,1));
        taskList.add(new Task(3,2,7,3));
        taskList.add(new Task(4,3,3,2));

        PriorityScheduler pq=new PriorityScheduler();
        pq.execute(taskList);

    }
}
