
package CPUscheduling.model;

// 1. TaskId:
// Har task ya process ko uniquely identify karne ke liye ek ID di jati hai, jise hum TaskId kehte hain. Ye number ya string hota hai jo har task ko alag pehchan deta hai, taake hum usko easily track kar sakein.

// 2. Arrival Time:
// Jab koi task system mein submit ya ready hota hai, us waqt ko Arrival Time kehte hain. Matlab, task ne system ko kab request kiya ya kab CPU ke liye queue mein aaya.

// 3. Burst Time:
// Burst Time ka matlab hai kitna time task ko CPU chahiye apna kaam complete karne ke liye. Ye total execution time hota hai jo CPU ko task ko dena hota hai.

// 4. Remaining Time:
// Agar task abhi complete nahi hua hai, to jitna CPU time uske execution ke liye abhi bhi bacha hai, usse Remaining Time kehte hain. Ye original burst time mein se jo time already use ho chuka, usko minus karke milta hai.

// 5. Priority:
// Kabhi kabhi tasks ko unki importance ke hisaab se order diya jata hai. Jitni zyada priority, utna pehle CPU milta hai. Ye ek number hota hai jo task ki urgency ya importance dikhata hai.

// 6. Completion Time:
// Jab task poori tarah CPU use kar ke finish ho jata hai, us waqt ko Completion Time kehte hain. Matlab, task ne system ko kab chhoda ya kaam kab complete kiya.

// 7. Waiting Time:
// Waiting Time wo duration hai jo task CPU ke liye queue mein wait karta hai, matlab jab tak uska turn nahi aata. Ye Arrival Time aur CPU milne ke time ke beech ka farq hota hai.

// 8. Turnaround Time:
// Turnaround Time ka matlab hai total time jo task ne system mein spend kiya, yani Arrival Time se lekar Completion Time tak ka total time. Isme waiting time aur execution time dono shamil hote hain.

public class Task implements Runnable {
    private int taskId;
    private int arrivalTime;
    private int burstTime;
    private int remainingTime;
    private int priority;
    private int completionTime;
    private int waitingTime;
    private int turnaroundTime;

    private final Object lock = new Object();
    private boolean isPaused = true;

    public Task(int taskId, int arrivalTime, int burstTime, int priority) {
        this.taskId = taskId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime; // initialize remaining time

    }

    public Task(int taskId, int arrivalTime, int burstTime) {
        this.taskId = taskId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime; // initialize remaining time
    }

    public void resume() {
        synchronized (lock) {
            isPaused = false;
            lock.notify();
        }
    }

    public void pause() {
        synchronized (lock) {
            isPaused = true;
        }
    }

    @Override
    public void run() {
        try {
            while (remainingTime > 0) {
                synchronized (lock) { // Wait until scheduler resumes this task
                    while (isPaused) {
                        lock.wait();
                    }
                }

                remainingTime--;// “Execute” one time unit
                System.out.printf("Task %d executing... Remaining -%d \n", taskId, remainingTime);

                Thread.sleep(100);
                pause();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isCompleted() {
        return remainingTime == 0;
    }

    public void calculateTime(int currentTime) {
        this.completionTime = currentTime;
        this.turnaroundTime = completionTime - arrivalTime;
        this.waitingTime = turnaroundTime - burstTime;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void executeUnit() {
        if (remainingTime > 0)
            remainingTime--;
    }

    public void setCompletionTime(int time) {
        this.completionTime = time;
    }

    @Override
    public String toString() {
        return String.format("Task %d: Arrival=%d, Burst=%d, Priority=%d, Completion=%d, Waiting=%d, Turnaround=%d",
                taskId, arrivalTime, burstTime, priority, completionTime, waitingTime, turnaroundTime);
    }

}
