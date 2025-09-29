import java.util.Scanner;

public class PrintCntTillUserWant {
    static volatile int cnt = 1;
    static volatile boolean running = false, exit = false;

    enum Command {
        start, stop, exit, invalid
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Object lock = new Object();

        Thread commandThread = new Thread(() -> {
            while (true) {
                String command = sc.nextLine();
                command = command.trim().toLowerCase();

                Command cmd;
                try {
                    cmd = Command.valueOf(command);
                } catch (Exception e) {
                    cmd = Command.invalid;
                }

                synchronized (lock) {
                    if (command.equals("start")) {
                        running = true;
                        lock.notify();
                    } else if (command.equals("stop")) {
                        running = false;
                    } else if (command.equals("exit")) {
                        exit = true;
                        lock.notify();
                        return;
                    } else {
                        System.out.println("Invalid command! Use start / stop / exit");
                    }

                    // switch (cmd) {
                    // case start:
                    // running=true;
                    // lock.notify();
                    // break;

                    // case stop:
                    // running=false;
                    // break;

                    // case exit:
                    // exit=true;
                    // lock.notify();
                    // return;

                    // default:
                    // break;
                    // }
                }
            }
        });

        Thread counterThread = new Thread(() -> {
            System.out.println("Enter your choice (start / stop / exit): ");
            while (true) {
                synchronized (lock) {
                    while (!running && !exit) {
                        System.out.println("[CounterThread] Waiting... (running=false)");
                        try {
                            lock.wait();
                            System.out.println("[CounterThread] Woke up from wait()");
                        } catch (InterruptedException e) {
                            System.out.println("Error: " + e);
                        }

                    }

                    if (exit) {
                        System.out.println("[CounterThread] Exit detected. Stopping thread.");
                        break;
                    }

                    System.out.println("[CounterThread] Printing count: " + cnt);
                    cnt++;
                }

                try {
                    Thread.sleep(1000);// wait for one second
                } catch (InterruptedException e) {
                    System.out.println("Error: " + e);
                }

            }
        });

        counterThread.start();
        commandThread.start();

    }
}
