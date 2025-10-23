class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
    this.next = null;
  }
}

class Queue {
  private Node front;
  private Node rear;

  public Queue() {
    this.front = this.rear = null;
  }

  public boolean isEmpty() {
    return front == null;
  }

  public void enqueue(int data) {
    Node newNode = new Node(data);
    if (rear == null) {
      front = rear = newNode;
    } else {
      rear.next = newNode;
      rear = newNode;
    }
  }

  public int dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue underflow: cannot dequeue from an empty queue");
    }
    int value = front.data;
    front = front.next;
    if (front == null) {
      rear = null;
    }
    return value;
  }

  public int peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty: cannot peek");
    }
    return front.data;
  }

  public void display() {
    Node temp = front;
    while (temp != null) {
      System.out.print(temp.data + " ");
      temp = temp.next;
    }
    System.out.println();
  }
}

public class QueueLinkedList {
  public static void main(String[] args) {
    Queue q = new Queue();

    q.enqueue(10);
    q.enqueue(20);
    q.enqueue(30);
    q.display();

    System.out.println("Dequeued: " + q.dequeue());
    q.display();

    System.out.println("Front element: " + q.peek());
  }
}
