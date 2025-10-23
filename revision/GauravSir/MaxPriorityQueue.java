import java.util.*;

public class MaxPriorityQueue {
  private int[] pq;
  private int n;

  MaxPriorityQueue(int capacity) {
    this.pq = new int[capacity + 1];
    this.n = 0;
  }

  public boolean isEmpty() {
    return this.n == 0;
  }

  private boolean less (int i, int j) {
    return pq[i] < pq[j];
  }

  private void swap (int i, int j) {
    int temp = pq[i];
    pq[i] = pq[j];
    pq[j] = temp;
  }

  private void resize (int size) {
    int[] newpq = new int[size];

    for (int i = 0; i <= n; i++) {
      newpq[i] = pq[i];
    }
    pq = newpq;
  }



  private void swim(int idx) {
    while (idx > 1) {
      int parent = idx / 2;

      if (less(parent, idx)) {
        swap(parent, idx);
	idx = parent;
      } else {
        break;
      }
    }
  }
  public void insert(int ele) {
    if (this.n == pq.length - 1) {
      resize(2 * pq.length);
    }

    pq[++n] = ele;
    swim(this.n);
  }

  private void sink(int idx) {
    while(idx * 2 <= n) {
      int j = idx * 2;

      if (j < n && less(j, j + 1)) {
        j++;
      }

      if(less(idx, j)) {
        swap(idx, j);
	idx = j;
      } else {
        break;
      }
    }
  }

  public int getMax() {
    if(isEmpty()) {
      System.out.println("PriorityQueue is Empty!");
      return -1;
    }
    return pq[1];
  }

  public int deleteMax() {
    if(isEmpty()) {
      System.out.println("Priority Queue is Empty!");
      return -1;
    }
    int maxele = pq[1];
    swap(1, this.n);

    this.n--;
    sink(1);

    if(this.n > 0 && this.n < (pq.length - 1) / 4) {
      resize(pq.length / 2);
    }

    return maxele;
  }

  public static void main(String ...arg) {
    MaxPriorityQueue p = new MaxPriorityQueue(4);

    System.out.println(p.isEmpty());
    p.insert(1);
    p.insert(2);
    p.insert(4);
    p.insert(3);
    p.insert(6);
    p.insert(7);
    p.insert(3);
    p.insert(9);
    System.out.println(p.getMax());
    System.out.println(p.getMax());
    System.out.println(p.deleteMax());
    System.out.println(p.deleteMax());
    System.out.println(p.isEmpty());
    System.out.println(p.getMax());
  }
}
