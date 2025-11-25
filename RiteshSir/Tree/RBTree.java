
import java.util.*;

public class RBTree<Key extends Comparable<Key>, Value> {
  private static final boolean RED = true;
  private static final boolean BLACK = false;

  private class Node {
    Key K;
    Node left, right;
    Value V;
    int N;
    boolean color;

    public Node(Key K, Value V) {
      this.K = K;
      this.V = V;
      this.N = 1;
      this.color = RED;
      this.right = this.left = null;
    }
  }

  private Node root;

  private boolean isRed(Node n) {
    if (n == null) {
      return false;
    }
    return n.color == RED;
  }

  private int size(Node n) {
    if (n == null) {
      return 0;
    }
    return n.N;
  }

  public int size() {
    return size(root);
  }

  // Left rotate
  private Node leftRotate(Node a) {
    Node c = a.right;
    a.right = c.left;
    c.left = a;

    c.color = a.color;
    a.color = RED;

    c.N = a.N;
    a.N = 1 + size(a.left) + size(a.right);
    return c;
  }

  // Right rotate
  private Node rightRotate(Node a) {
    Node b = a.left;
    a.left = b.right;
    b.right = a;

    b.color = a.color;
    a.color = RED;

    b.N = a.N;
    a.N = 1 + size(a.left) + size(a.right);
    return b;
  }

  // Filp Color
  private void flipColor(Node n) {
    n.color = !n.color;
    if (n.left != null) {
      n.left.color = !n.left.color;
    }
    if (n.right != null) {
      n.right.color = !n.right.color;
    }
  }

  private Node put(Node node, Key key, Value val) {
    if (node == null) {
      return new Node(key, val);
    }

    int cmp = key.compareTo(node.K);

    if (cmp < 0) {
      node.left = put(node.left, key, val);
    } else if (cmp > 0) {
      node.right = put(node.right, key, val);
    } else {
      node.V = val;
    }

    // Fix right-leaning reds
    if (isRed(node.right) && !isRed(node.left)) {
      node = leftRotate(node);
    }

    // Fix two reds in a row on left
    if (isRed(node.left) && isRed(node.left.left)) {
      node = rightRotate(node);
    }

    // Split 4-node
    if (isRed(node.left) && isRed(node.right)) {
      flipColor(node);
    }

    // Update subtree size
    node.N = 1 + size(node.left) + size(node.right);
    return node;
  }

  public void put(Key key, Value val) {
    root = put(root, key, val);
    root.color = BLACK;
  }

  public Value get(Key key) {
    Node n = root;
    while (n != null) {
      int cmp = key.compareTo(n.K);

      if (cmp < 0) {
        n = n.left;
      } else if (cmp > 0) {
        n = n.right;
      } else {
        return n.V;
      }
    }

    return null; // not found
  }

  public boolean contains(Key key) {
    return get(key) != null;
  }

  public void inorder() {
    inorder(root);
    System.out.println();
  }

  private void inorder(Node x) {
    if (x == null) {
      return;
    }
    inorder(x.left);
    System.out.print(x.K + " ");
    inorder(x.right);
  }

  private int height(Node n) {
    if (n == null) {
      return 0;
    }

    int leftheight = height(n.left);
    int rightheight = height(n.right);
    return 1 + Math.max(leftheight, rightheight);
  }

  public int height() {
    return height(this.root);
  }

  public static void main(String[] args) {
    RBTree<Integer, String> tree = new RBTree<>();

    // Initially empty
    assert tree.size() == 0 : "Size should be 0 for empty tree";
    assert !tree.contains(10) : "Tree should not contain 10";

    // Insert some keys
    tree.put(10, "ten");
    tree.put(5, "five");
    tree.put(15, "fifteen");
    tree.put(2, "two");
    tree.put(7, "seven");

    // Check size
    assert tree.size() == 5 : "Size should be 5 after 5 inserts";
    System.out.println("The height of tree: " + tree.height());

    // Check basic gets
    assert "ten".equals(tree.get(10)) : "Value for 10 should be 'ten'";
    assert "five".equals(tree.get(5)) : "Value for 5 should be 'five'";
    assert "fifteen".equals(tree.get(15)) : "Value for 15 should be 'fifteen'";
    assert "two".equals(tree.get(2)) : "Value for 2 should be 'two'";
    assert "seven".equals(tree.get(7)) : "Value for 7 should be 'seven'";

    // Non-existing key
    assert tree.get(100) == null : "Value for 100 should be null";
    assert !tree.contains(100) : "Tree should not contain 100";

    // Update existing key
    tree.put(10, "TEN");
    assert tree.size() == 5 : "Size should stay 5 after updating existing key";
    assert "TEN".equals(tree.get(10)) : "Value for 10 should be updated to 'TEN'";

    // Extra: tree should still contain all inserted keys
    assert tree.contains(2);
    assert tree.contains(5);
    assert tree.contains(7);
    assert tree.contains(10);
    assert tree.contains(15);

    // If all asserts passed:
    System.out.println("All RBTree tests passed !");
    System.out.print("Inorder traversal: ");
    tree.inorder();
  }
}
