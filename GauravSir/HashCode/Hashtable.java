package HashCode;

public class Hashtable<Key, Value> {
  static class Node {
    Object key;
    Object value;
    Node next;

    Node(Object key, Object value) {
      this.key = key;
      this.value = value;
    }
  }

  private Node[] ht; // Node size -- hashTable size
  private int size = 0; // number of key-value pairs
  private int capacity = 4; // initial capacity

  public Hashtable() {
    ht = new Node[capacity];
  }

  public int hash(Key key) {
    return (key.hashCode() & 0x7fffffff) % capacity; // hashCode will call parent hasCode()
    // 01111111111111111111111111111111 ->> Dont get negative value
  }

  public void put(Key key, Value val) {
    if (key == null) {
      return;
    }

    int HashCode = hash(key);
    for (Node x = ht[HashCode]; x != null; x = x.next) {
      if (x.key.equals(key)) {
        x.value = val;
        return;
      }
    }

    Node newNode = new Node(key, val);
    newNode.next = ht[HashCode];
    ht[HashCode] = newNode;
    size++;

    if (size > capacity * 0.75) { // 3/4
      resize(capacity * 2);
    }
  }

  public Value get(Key key) {
    int HashCode = hash(key);
    for (Node x = ht[HashCode]; x != null; x = x.next) {
      if (x.key.equals(key)) {
        return (Value) x.value;
      }
    }
    return null;
  }

  public boolean remove(Key key) {
    if (key == null) {
      return false;
    }

    int HashCode = hash(key);
    Node prevKey = null;

    for (Node x = ht[HashCode]; x != null; x = x.next) {
      if (x.key.equals(key)) {
        if (prevKey == null) {
          ht[HashCode] = x.next;
        } else {
          prevKey.next = x.next;
        }
        size--;

        if (capacity > 4 && size < capacity / 4) {
          resize(capacity / 2);
        }

        return true;
      }

      prevKey = x;
    }

    return false;
  }

  private void resize(int newcapacity) {
    Node[] oldht = ht;
    capacity = newcapacity;
    size = 0;
    ht = new Node[capacity];

    for (Node head : oldht) {
      for (Node x = head; x != null; x = x.next) {
        put((Key) x.key, (Value) x.value);
      }
    }
  }

  public int size() {
    return size;
  }

  public int capacity() {
    return capacity;
  }

  public boolean containsKey(Key key) {
    return get(key) != null;
  }

}
