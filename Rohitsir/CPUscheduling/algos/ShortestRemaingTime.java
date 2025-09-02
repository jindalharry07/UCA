package CPUscheduling.algos;

import CPUscheduling.model.Scheduler;
import CPUscheduling.model.Task;

import java.util.*;

public class ShortestRemaingTime implements Scheduler {
    private void printStatistics(List<Task> taskList) {
        System.out.println("\nTask | Arrival | Burst | Completion | Turnaround | Waiting");
        for (Task t : taskList) {
            System.out.printf(" %2d  |   %2d    |  %2d   |     %2d     |     %2d     |    %2d\n",
                    t.getTaskId(), t.getArrivalTime(), t.getBurstTime(),
                    t.getCompletionTime(), t.getTurnaroundTime(), t.getWaitingTime());
        }
    }

    private Map<Task, Thread> taskThreadMap;
    public ShortestRemaingTime() {
        taskThreadMap = new HashMap<>();
    }

    @Override
    public void execute(List<Task> tasklList) throws InterruptedException {
        int currentTime = 0;
        int completed = 0;
        int n = tasklList.size();
        int idx = 0;

        tasklList.sort(Comparator.comparing(Task::getArrivalTime));// Sort tasks by arrival time so we can add them to PQ as time progresses

        // PriorityQueue ordered by:
        // 1. Remaining time (ascending)
        // 2. Arrival time (ascending) - tie breaker
        // 3. Priority (descending) - optional, if Task has priority

    }

    // public void execute(List<Task> taskList) throws InterruptedException {
    //     int currentTime = 0;
    //     int completed = 0;
    //     int n = taskList.size();

    //     taskList.sort((t1, t2) -> Integer.compare(t1.getArrivalTime(), t2.getArrivalTime()));

    //     while (completed < n) {
    //         Task shortestTask = null;
    //         int minRemaining = Integer.MAX_VALUE;

    //         for (Task t : taskList) {
    //             if (t.getArrivalTime() <= currentTime && !t.isCompleted() && t.getRemainingTime() < minRemaining) {
    //                 shortestTask = t;
    //                 minRemaining = t.getRemainingTime();
    //             }
    //         }

    //         if (shortestTask == null) {
    //             currentTime++;
    //             continue;
    //         }

    //         shortestTask.executeUnit();
    //         currentTime++;
    //         Thread.sleep(100);

    //         if (shortestTask.isCompleted()) {
    //             // shortestTask.setCompletionTime(currentTime);
    //             // completed++;
    //             // System.out.printf("Task %d completed at time %d\n", shortestTask.getTaskId(),
    //             // currentTime);

    //             shortestTask.setCompletionTime(currentTime);

    //             int turnaroundTime = currentTime - shortestTask.getArrivalTime();
    //             int waitingTime = turnaroundTime - shortestTask.getBurstTime();
    //             shortestTask.setTurnaroundTime(turnaroundTime);
    //             shortestTask.setWaitingTime(waitingTime);

    //             completed++;
    //             System.out.printf("Task %d completed at time %d\n", shortestTask.getTaskId(), currentTime);
    //         }

    //     }
    //     printStatistics(taskList); 
    // }
}
