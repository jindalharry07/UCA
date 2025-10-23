import java.util.Arrays;

class Stack {
    private int[] stack;
    private int top;
    int capacity;

    public Stack() {
        this.capacity = 4;
        this.stack = new int[capacity];
        this.top = -1;
    }

    private void resize(int newCapacity) {
        if (newCapacity < 4) {
            newCapacity = 4; // minimum capacity
        }
        stack = Arrays.copyOf(stack, newCapacity);
        capacity = newCapacity;
    }

    public void push(int value) {
        if (top == capacity - 1) {
            resize(capacity * 2);
        }
        stack[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow! Can't pop");
            return -1;
        }
        int val = stack[top--];
        if ((top + 1) < capacity / 2 && capacity > 4) {
            int newCapacity = 3 * capacity / 4;
            if (newCapacity < 4) {
                newCapacity = 4;
            }
            resize(newCapacity);
        }
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return -1;
        }
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void freeStack() {
        stack = null;
        capacity = 0;
        top = -1;
    }
}

public class StackArray {
    public static void testStack() {
        Stack s = new Stack();

        assert s.isEmpty() == true;

        s.push(10);
        assert s.isEmpty() == false;
        assert s.peek() == 10;

        s.push(20);
        assert s.peek() == 20;

        int val = s.pop();
        assert val == 20;
        assert s.peek() == 10;

        val = s.pop();
        assert val == 10;
        assert s.isEmpty() == true;

        val = s.pop();
        assert val == -1;

        for (int i = 0; i < 100; i++) {
            s.push(i);
        }
        assert s.capacity >= 100;
        assert s.peek() == 99;

        for (int i = 0; i < 95; i++) {
            s.pop();
        }
        assert s.capacity < 20;

        s.freeStack();
    }

    public static void main(String[] args) {
        testStack();
        System.out.println("All working fine!");
    }
}
