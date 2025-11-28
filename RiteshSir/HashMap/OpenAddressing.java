
import java.util.*;

public class OpenAddressing {
  private static class Node {
    private Integer key;
    private State state;

    public Node(Integer key) {
      this.key = key;
      this.state = State.Occupied;
    }

    public Node() {
      this.key = null;
      this.state = State.Free;
    }
  }

  private Node[] data;
  private final int size;

  public OpenAddressing(int size) {
    this.size = size;
    data = new Node[size];

    // Arrays.fill(data, new Node());
    for (int i = 0; i < size; i++) {
      data[i] = new Node();
    }
  }

  private enum State {
    Free, Occupied, Deleted;
  }

  private int hashFunction(Integer key, int h) {
    if (h == -1) {
      return key % size;
    }
    return (h + 1) % size;
  }

  public void insert(int key) {
    int h = hashFunction(key, -1);
    int originalH = h;
    System.out.println("Looking at h: " + h);

    while (data[h].state == State.Occupied) {
      h = hashFunction(key, h);
      System.out.println("Looking at h: " + h);
      // System.out.println("------------------------");
      
      if (h == originalH) {
        throw new RuntimeException("Overflow");
      }
    }
    System.out.println("------------------------");
    data[h] = new Node(key);
  }

  public boolean hasKey(int key) {
    int h = hashFunction(key, -1);
    int originalH = h;
    while (true) {
      if (data[h].state == State.Occupied) {
        if (data[h].key == key) {
          return true;
        }
      }

      if (data[h].state == State.Free) {
        return false;
      }

      h = hashFunction(key, h);
      if (h == originalH) {
        return false;
      }
    }
  }

  public void delete(int key) {
    int h = hashFunction(key, -1);
    int originalH = h;
    while (true) {
      if (data[h].state == State.Occupied && data[h].key == key) {
        data[h].state = State.Deleted;
        data[h].key = null;
        return;
      }

      if (data[h].state == State.Free) {
        System.out.println("Key Not found");
        return;
      }

      h = hashFunction(key, h);
      if (h == originalH) {
        System.out.println("Key Not found");
        return;

      }
    }
  }

  public String toString() {
    Integer[] temp = new Integer[size];
    State[] state = new State[size];

    for (int i = 0; i < size; i++) {
      temp[i] = data[i].key;
      state[i] = data[i].state;
    }

    return "{" + "keys = " + Arrays.toString(temp) + "States = " + Arrays.toString(state) + "}";
  }

  public static void main(String[] args) {
    OpenAddressing op = new OpenAddressing(10);
    op.insert(15);
    op.insert(25);
    op.insert(35);
    op.insert(8);
    op.insert(45);
    // op.insert(55);
    op.insert(65);
    System.out.println(op.hasKey(35));
    System.out.println(op.hasKey(65));
    System.out.println(op.hasKey(15));
    // System.out.println(op.delete(65));
    op.delete(65);
    System.out.println(op.toString());
  }
}
