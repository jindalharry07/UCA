public class AVLTree<Key extends Comparable<Key>, Value> {
  private class Node {
    Key key;
    Value val;
    Node left, right;
    int height;
    int size;

    public Node(Key key, Value val) {
      this.key = key;
      this.val = val;
      this.height = 1;
      this.size = 1;
    }
  }

  private Node root;

  private int height(Node n) {
    return (n == null) ? 0 : n.height;
  }

  public int height() {
    return height(root);
  }

  private int size(Node n) {
    return (n == null) ? 0 : n.size;
  }

  public int size() {
    return size(root);
  }

  private int balance(Node n) {
    if (n == null) {
      return 0;
    }
    return height(n.left) - height(n.right);
  }

  private Node insert(Node node, Key key, Value val) {
    if (node == null) {
      return new Node(key, val);
    }

    int cmp = key.compareTo(node.key);

    if (cmp < 0) {
      node.left = insert(node.left, key, val);
    } else if (cmp > 0) {
      node.right = insert(node.right, key, val);
    } else {
      node.val = val;
      return node;
    }

    node.height = 1 + Math.max(height(node.left), height(node.right));
    node.size = 1 + size(node.left) + size(node.right);

    int diff = balance(node);

    if (diff > 2) {
      if (height(node.left.left) - height(node.left.right) == -1) {
        node.left = leftRotate(node.left);
      }
      node = rightRotate(node);
    } else if (diff == -2) {
      if (height(node.right.left) - height(node.right.right) == 1) {
        node.right = rightRotate(node.right);
      }
      node = leftRotate(node);
    }
    return node;
  }

  private Node leftRotate(Node node) {
    Node temp1 = node.right;
    Node temp2 = temp1.left;

    node.right = temp2;
    temp1.left = node;

    node.height = 1 + Math.max(height(node.left), height(node.right));
    temp1.height = 1 + Math.max(height(temp1.left), height(temp1.right));

    node.size = 1 + size(node.left) + size(node.right);
    temp1.size = 1 + size(temp1.left) + size(temp1.right);

    return temp1;
  }

  private Node rightRotate(Node node) {
    Node temp1 = node.left;
    Node temp2 = temp1.right;

    node.left = temp2;
    temp1.right = node;

    node.height = 1 + Math.max(height(node.left), height(node.right));
    temp1.height = 1 + Math.max(height(temp1.left), height(temp1.right));

    node.size = 1 + size(node.left) + size(node.right);
    temp1.size = 1 + size(temp1.left) + size(temp1.right);

    return temp1;
  }

  public Value get(Key key) {
    Node n = root;
    while (n != null) {
      int cmp = key.compareTo(n.key);

      if (cmp < 0) {
        n = n.left;
      } else if (cmp > 0) {
        n = n.right;
      } else {
        return n.val;
      }
    }
    return null;
  }

  public boolean contains(Key key) {
    return get(key) != null;
  }

  public void put(Key key, Value val) {
    root = insert(root, key, val);
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
    System.out.print(x.key + " ");
    inorder(x.right);
  }

  public static void main(String[] args) {
    AVLTree<Integer, String> tree = new AVLTree<>();

    assert tree.size() == 0 : "Size should be 0 for empty tree";
    assert !tree.contains(10) : "Tree should not contain 10";

    tree.put(10, "ten");
    tree.put(5, "five");
    tree.put(15, "fifteen");
    tree.put(2, "two");
    tree.put(7, "seven");

    assert tree.size() == 5 : "Size should be 5 after inserts";
    System.out.println("The height of AVL tree: " + tree.height());

    assert "ten".equals(tree.get(10));
    assert "five".equals(tree.get(5));
    assert "fifteen".equals(tree.get(15));
    assert "two".equals(tree.get(2));
    assert "seven".equals(tree.get(7));

    assert tree.get(100) == null;
    assert !tree.contains(100);

    tree.put(10, "TEN");
    assert tree.size() == 5 : "Updating key should not change size";
    assert "TEN".equals(tree.get(10));

    assert tree.contains(2);
    assert tree.contains(5);
    assert tree.contains(7);
    assert tree.contains(10);
    assert tree.contains(15);

    System.out.println("All AVLTree tests passed!");
    System.out.print("Inorder traversal: ");
    tree.inorder();
  }
}
