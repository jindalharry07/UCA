import java.util.*;

public class FindMedian {
  
  private PriorityQueue<Integer> maxPq;
  private PriorityQueue<Integer> minPq;

  FindMedian() {
    maxPq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    minPq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
  }

  private void add(int ele) {
    maxPq.add(ele);
    minPq.add(maxPq.poll());

    if(maxPq.size() < minPq.size()) {
      maxPq.add(minPq.poll());
    }
  }

  private double median() {
    int size = maxPq.size() + minPq.size();

    if(size % 2 != 0) {
      return maxPq.peek();
    }
    return (maxPq.peek() + minPq.peek()) / 2.0;
  }

  public static void main(String[] arg) {
    FindMedian fm = new FindMedian();
    fm.add(1);
    fm.add(6);
    fm.add(5);
    fm.add(3);
    fm.add(4);
    fm.add(2);
    // fm.add(0);

    System.out.println(fm.median());
  }
}
