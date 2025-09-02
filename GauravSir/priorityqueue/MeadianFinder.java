import java.util.*;

public class MeadianFinder {
  static class MFinder {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MFinder() {
      maxHeap = new PriorityQueue<>((a,b)->Integer.compare(b,a));
      minHeap = new PriorityQueue<>((a,b)->Integer.compare(a,b));
    }

    public void addNum(int num) {
      maxHeap.add(num);
      minHeap.add(maxHeap.poll());

      if (minHeap.size() > maxHeap.size()) {
        maxHeap.add(minHeap.poll());
      }
    }

    public double findMedian() {
      int totalSize = maxHeap.size() + minHeap.size();

      if (totalSize % 2 == 1) {
        return maxHeap.peek();
      }
      
      int sum = maxHeap.peek() + minHeap.peek();
      return sum / 2.0;
    }
  }

  public static void main(String[] arg) {
    MFinder f = new MFinder();
    f.addNum(1);
    f.addNum(3);
    assert f.findMedian() == 2.0;

    f.addNum(4);
    assert f.findMedian() == 3.0;
  }
}
