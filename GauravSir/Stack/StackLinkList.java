class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Stack {
    private Node top;

    public Stack() {
        top = null;
    }

    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
        System.out.println(value + " pushed to stack");
    }

    public void pop() {
        if (top == null) {
            System.out.println("Stack Underflow");
            return;
        }
        System.out.println(top.data + " popped from stack");
        top = top.next;
    }

    public void peek() {
        if (top == null) {
            System.out.println("Stack is Empty!");
        } else {
            System.out.println("Top element is " + top.data);
        }
    }

    public void display() {
        if (top == null) {
            System.out.println("Stack is Empty");
            return;
        }
        Node temp = top;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // For testing purposes: expose the top node
    public Node getTop() {
        return top;
    }
}

public class StackLinkList {

    public static void test() {
        Stack stack = new Stack();

        assert stack.getTop() == null;

        stack.push(10);
        assert stack.getTop() != null && stack.getTop().data == 10;

        stack.push(20);
        assert stack.getTop() != null && stack.getTop().data == 20;

        stack.push(30);
        assert stack.getTop() != null && stack.getTop().data == 30;

        System.out.print("Stack elements: ");
        stack.display();

        stack.peek();

        // Pop element (should pop 30)
        stack.pop();
        assert stack.getTop() != null && stack.getTop().data == 20;

        // Peek again (should be 20)
        stack.peek();

        stack.pop(); // pops 20
        stack.pop(); // pops 10

        // Now stack should be empty
        assert stack.getTop() == null;

        // Pop on empty stack (should print underflow)
        stack.pop();

        // Peek on empty stack (should print stack empty)
        stack.peek();

        System.out.println("All tests passed!");
    }

    public static void main(String[] args) {
        test();
    }
}
