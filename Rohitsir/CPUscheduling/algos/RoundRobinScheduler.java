
package CPUscheduling.algos;
import CPUscheduling.model.Scheduler;
import CPUscheduling.model.Task;
import java.util.*;

public class RoundRobinScheduler implements Scheduler {
    int timeQuantum;
    Map<Task, Thread> taskThreadMap;

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
        this.taskThreadMap = new HashMap<>();
    }

    @Override
    public void execute(List<Task> taskList) throws InterruptedException {
        Queue<Task> q = new LinkedList<>();

        for (Task t : taskList) {
            Thread thread = new Thread(t);
            thread.start();
            taskThreadMap.put(t, thread);
        }

        int currentTime = 0;
        int completed = 0;
        int idx = 0;

        while (completed < taskThreadMap.size()) {
            while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                q.add(taskList.get(idx));
                idx++;
            }

            if (q.isEmpty()) {
                currentTime++;
                Thread.sleep(100);
                continue;
            }

            Task currentTask = q.poll();
            int units = 0;

            while (units < timeQuantum && !currentTask.isCompleted()) {
                currentTask.resume();
                Thread.sleep(130);
                currentTime++;
                units++;

                while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                    q.add(taskList.get(idx));
                    idx++;
                }
                System.out.printf("Task %d remaining time: %d at time %d\n", currentTask.getTaskId(), currentTask.getRemainingTime(), currentTime);
            }   

            if (currentTask.isCompleted()) {
                currentTask.calculateTime(currentTime);
                completed++;
                System.out.printf("Task %d is completed at this time %d\n", currentTask.getTaskId(), currentTime);
            } else {
                q.offer(currentTask);
            }
        }

        for (Thread t : taskThreadMap.values()) {
            t.join();
        }

        printStatistic(taskList);
    }

    private void printStatistic(List<Task> taskList) {
        System.out.println("\nTask | Completion | Turnaround | Waiting");
        for (Task t : taskList) {
            System.out.printf(" %2d  |     %2d     |     %2d     |    %2d\n",
                    t.getTaskId(), t.getCompletionTime(),
                    t.getTurnaroundTime(), t.getWaitingTime());
        }
    }

    
}
