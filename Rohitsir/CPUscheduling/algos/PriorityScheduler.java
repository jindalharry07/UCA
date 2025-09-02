package CPUscheduling.algos;

import CPUscheduling.model.*;
import CPUscheduling.algos.*;
import java.util.*;

public class PriorityScheduler implements Scheduler {

    public void execute(List<Task> taskList) throws InterruptedException {
        taskList.sort(Comparator.comparingInt(Task::getArrivalTime)); // ✅ ensures arrival order
        PriorityQueue<Task> pq = new PriorityQueue<>((t1, t2) -> Integer.compare(t1.getPriority(), t2.getPriority()));

        // higher number = higher priority
        // PriorityQueue<Task> pq = new PriorityQueue<>(
        //         (t1, t2) -> Integer.compare(t2.getPriority(), t1.getPriority()));

        int currentTime = 0;
        int completed = 0;
        int idx = 0;

        for (Task t : taskList) {
            Thread thread = new Thread(t);
            thread.start();
        }

        while (completed < taskList.size()) {
            while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                pq.add(taskList.get(idx));
                idx++;
            }

            if (pq.isEmpty()) {
                currentTime++;
                continue;
            }

            Task currentTask = pq.poll();
            // while (!currentTask.isCompleted()) {
            //     currentTask.resume();
            //     Thread.sleep(100);
            //     currentTime++;
            // }

            // currentTask.calculateTime(currentTime);
            // System.out.printf("Task %d is completed at time %d\n", currentTask.getTaskId(), currentTime);
            // completed++;

            currentTask.resume();
            Thread.sleep(100);
            currentTime++;
            currentTask.pause();

            // After running, check if task completed
            if (!currentTask.isCompleted()) {
                pq.add(currentTask); // Put it back for later
            } else {
                currentTask.calculateTime(currentTime);
                System.out.printf("Task %d is completed at time %d\n",currentTask.getTaskId(), currentTime);
                completed++;
            }
        }

        printStatistic(taskList);
    }

    private void printStatistic(List<Task> taskList) {
        System.out.println("\n Task | Priority | Completion | Turnaround | Waiting");
        for (Task t : taskList) {
            System.out.printf("%2d | %2d | %2d | %2d | %2d\n",
                    t.getTaskId(), t.getPriority(), t.getCompletionTime(), t.getTurnaroundTime(), t.getWaitingTime());
        }
    }
}
