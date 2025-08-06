package CPUscheduling;
import CPUscheduling.model.Task;
import CPUscheduling.algos.RoundRobinScheduler;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        System.out.print("Enter number of tasks: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.printf("Enter arrivalTime, burstTime, priority for Task %d: ", i + 1);
            int arrival = sc.nextInt();
            int burst = sc.nextInt();
            int priority = sc.nextInt();
            taskList.add(new Task(i + 1, arrival, burst, priority));
        }

        System.out.print("Enter time quantum: ");
        int tq = sc.nextInt();

        taskList.sort((t1, t2) -> t1.getArrivalTime() - t2.getArrivalTime());
        RoundRobinScheduler rr = new RoundRobinScheduler(tq);
        rr.execute(taskList);
    }
}
