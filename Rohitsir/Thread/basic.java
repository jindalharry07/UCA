import java.util.*;
public class basic{
    public static void main(String[]arg){
        // System.out.println(Thread.currentThread().getName());

        Thread t1=new Thread(()->{
            int sum=0;
            for(int i=0;i<1000;i++)sum+=i;
            System.out.println(Thread.currentThread().getName());
            System.out.println(sum);
        });
        t1.setName("hello");
        // t1.start();
        t1.run();
        
        System.out.println(Thread.currentThread().getName());


    }
}