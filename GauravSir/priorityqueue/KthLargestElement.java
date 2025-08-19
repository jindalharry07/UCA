/**
 * Package priorityqueue contains classes related to priority queue operations.
 */
package priorityqueue;
import java.util.PriorityQueue;

/**
 * Class to find the kth largest element in an array.
 */
public final class KthLargestElement {
  /**
   * Private constructor to prevent instantiation of utility class.
   */
  private KthLargestElement() {
    // Private instantiation
  }

  /**
   *  Finds the kth largest element in the array.
   *
   *  @param arr the array of integer
   *  @param k the kth position
   *  @return the kth largest element
   */
  public static int findkthLargest(final int[] arr, final int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (final int ele : arr) {
      pq.add(ele);
      if (pq.size() > k) {
        pq.poll();
      }
    }
    return pq.peek();
  }

  /**
   * Main method to test the findkthLargest function.
   *
   * @param arg command-line arguments array
   */
  public static void main(final String[] arg) {
    final int[] arr = {3, 2, 1, 5, 6, 4};
    final int k = 2;

    final int kthLargest = findkthLargest(arr, k);
    System.out.println(k + " th largest element is " + kthLargest);
  }
}
