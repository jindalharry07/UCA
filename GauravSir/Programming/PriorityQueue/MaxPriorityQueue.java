import java.util.Scanner;
public class MaxPriorityQueue {
  private int [] pq;
  private int n;

  public MaxPriorityQueue( int capacity ) {
    this.pq=new int[capacity+1];
    this.n=0;
  }

  public boolean isEmpty() {
    return this.n == 0;
  }

  public int size() {
    return this.n;
  }

  public void swap( int i, int j) {
    int temp = this.pq[i];
    this.pq[i] = this.pq[j];
    this.pq[j] = temp;
  }

  public boolean less(int i, int j) {
    return this.pq[i] < this.pq[j];
  }

  public void resize(int capacity) {
    int []temp = new int [capacity];
    for (int i = 0; i <= n; i++) {
      temp[i] = this.pq[i];
    }
    this.pq=temp;
  }

  public void insert(int ele) {
    if (this.n == this.pq.length - 1) resize(this.pq.length * 2);

    this.pq[++n] = ele;
    swim(this.n);
  }

  public void swim(int idx) {
    while (idx > 1) {
      int parent = idx / 2;
      if (less(parent,idx)) {
        swap(parent,idx);
	idx = parent;
      }else {
        break;
      }
    }
  }

  public int delMax() {
    if (isEmpty()) {
      System.out.println("Priority Queue is Empty!!");
      return -1;
    }
    int MaxEle = this.pq[1];
    swap(1,this.n);
    this.n--;
    sink(1);

    if (this.n > 0 && this.n == (this.pq.length - 1) / 4) resize(this.pq.length / 2);

    return MaxEle;
  }

  public void sink(int idx) {
    while(2 * idx <= n) {
      int j = 2 * idx;

      if(j < this.n && less(j,j+1)) j++;

      if(less(idx,j)) {
        swap(j,idx);
	idx = j;
      }else {
        break;
      }
    }
  }

  public static void main(String []arg) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter initial capacity of the priority queue: ");
    int capacity = sc.nextInt();

    MaxPriorityQueue maxPq = new MaxPriorityQueue(capacity);

    System.out.println("Enter number of elements to insert:");
    int count = sc.nextInt();

    System.out.println("Enter the elements:");
    for (int i = 0; i < count; i++) {
      int val = sc.nextInt();
      maxPq.insert(val);
    }

    System.out.println("Size after inserts: " + maxPq.size());

    /*
    while (!maxPq.isEmpty()) {
      System.out.println("Max element removed: " + maxPq.delMax());
      System.out.println("Current size: " + maxPq.size());
    }
    */

    System.out.println(maxPq.delMax());
    maxPq.insert(10);
    maxPq.insert(20);
    maxPq.insert(30);
    maxPq.insert(40);
    maxPq.insert(50);
    maxPq.insert(60);
    
    System.out.println("Priority Queue size : "+ maxPq.size());
    while (!maxPq.isEmpty()) {
      System.out.println("Max element removed: " + maxPq.delMax());
      System.out.println("Current size: " + maxPq.size());
    }

    sc.close();
  }

}
