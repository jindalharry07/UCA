class Queue {
    private int[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public Queue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be greater than 0");
        this.capacity = capacity;
        this.data = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    private void resize(int newCapacity) {
        if (newCapacity < size)
            throw new IllegalArgumentException("New capacity must be >= current size");

        int[] newData = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % capacity];
        }

        data = newData;
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
    }

    public void enqueue(int val) {
        if (size == capacity) {
            resize(capacity * 2); // double capacity when full
        }

        rear = (rear + 1) % capacity;
        data[rear] = val;
        size++;
    }

    public int dequeue() {
        if (size == 0) {
            System.out.println("Queue underflow");
            return -1;
        }

        int val = data[front];
        front = (front + 1) % capacity;
        size--;

        if (size < capacity / 2 && capacity > 4) {
            int newCap = (capacity * 3) / 4;
            resize(newCap);
        }

        return val;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[(front + i) % capacity] + " ");
        }
        System.out.println();
    }

    public void freeQueue() {
        data = null; // in Java, GC will reclaim automatically
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

public class QueueArray {
    public static void main(String[] args) {
        Queue q = new Queue(4);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);
        q.enqueue(60);

        q.print();

        q.dequeue();
        q.dequeue();
        q.print();

        q.freeQueue(); // optional in Java
    }
}
