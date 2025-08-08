import java.util.Scanner;

public class PrintCntTillUserWant {
    static int cnt = 1;
    static boolean running = false, exit = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Object lock = new Object();

        Thread commandThread = new Thread(() -> {
            while (true) {
                String command = sc.nextLine();
                command = command.trim().toLowerCase();

                synchronized (lock) {
                    if (command.equals("start")) {
                        running = true;
                        lock.notify();
                    } else if (command.equals("stop")) {
                        running = false;
                    } else if (command.equals("exit") ){
                        exit = true;
                        lock.notify();
                        return;
                    } else {
                        System.out.println("Invalid command! Use start / stop / exit");
                    }
                }
            }
        });
        commandThread.start();

        Thread counterThread = new Thread(() -> {
            System.out.println("Enter your choice (start / stop / exit): ");
            while (true) {
                synchronized (lock) {
                    while (!running && !exit) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Error: " + e);
                        }
                    }

                    if(exit)break;

                    System.out.println("Counter : "+cnt);
                    cnt++;

                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        System.out.println("Error: "+e);
                    }
                }
            }
        });
        counterThread.start();
    }
}
