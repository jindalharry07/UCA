public class BST<Key extends Comparable<Key>, Value> {
  private Node root;

  private class Node {
    Key key;
    Value value;
    Node left, right;

    Node(Key key, Value value) {
      this.key = key;
      this.value = value;
    }
  }

  private Value get(Node root, Key key) {
    if (root == null) {
      return null;
    }

    int cmp = key.compareTo(root.key);
    if (cmp == 0) {
      return root.value;
    } else if (cmp < 0) {
      return get(root.left, key);
    } else {
      return get(root.right, key);
    }
  }

  public Value get(Key key) {
    return get(root, key);
  }

  private Node put(Node root, Key key, Value value) {
    if (root == null) {
      return new Node(key, value);
    }

    int cmp = key.compareTo(root.key);
    if (cmp == 0) {
      root.value = value;
    } else if (cmp < 0) {
      root.left = put(root.left, key, value);
    } else {
      root.right = put(root.right, key, value);
    }
    return root;
  }

  public void put(Key key, Value value) {
    root = put(root, key, value);
  }

  private Node getMin(Node node) {
    if (node.left == null) {
      return node;
    }
    return getMin(node.left);
  }

  public Key min() {
    Node minNode = getMin(root);
    return minNode.key;
  }

  private Node delMin(Node node) {
    if (node.left == null) {
      return node.right;
    }
    node.left = delMin(node.left);
    return node;
  }

  public void delMin() {
    if (root == null) {
      return;
    }
    root = delMin(root);
  }

  private Node getMax(Node node) {
    if (node.right == null) {
      return node;
    }
    return getMax(node.right);
  }

  public Key max() {
    Node maxNode = getMax(root);
    return maxNode.key;
  }

  private Node delMax(Node node) {
    if (node.right == null) {
      return node.left;
    }
    node.right = delMax(node.right);
    return node;
  }

  public void delMax() {
    if (root == null) {
      return;
    }
    root = delMax(root);
  }

  private Node delete(Node node, Key key) {
    if (node == null) {
      return null;
    }

    int cmp = key.compareTo(node.key);
    if (cmp < 0) {
      node.left = delete(node.left, key);
    } else if (cmp > 0) {
      node.right = delete(node.right, key);
    } else {
      if (node.left == null) {
        return node.right;
      }
      if (node.right == null) {
        return node.left;
      }

      Node temp = getMin(node.right);

      node.key = temp.key;
      node.value = temp.value;

      node.right = delMin(node.right);
    }
    return node;
  }

  public void delete(Key key) {
    root = delete(root, key);
  }

  private Node floor(Node node, Key key) {
    if (node == null) {
      return node;
    }
    int cmp = key.compareTo(node.key);
    if (cmp == 0) {
      return node;
    } else if (cmp < 0) {
      return floor(node.left, key);
    } else {
      Node temp = floor(node.right, key);
      if (temp != null) {
        return temp;
      } else {
        return node;
      }
    }
  }

  // largest key smaller than given key
  public Key floor(Key key) {
    Node floorNode = floor(root, key);
    return (floorNode != null) ? floorNode.key : null;
  }

  private Node ceil(Node node, Key key) {
    if (node == null) {
      return null;
    }

    int cmp = key.compareTo(node.key);

    if (cmp == 0) {
      return node;
    } else if (cmp > 0) {
      return ceil(node.right, key);
    } else {
      Node temp = ceil(node.left, key);
      if (temp != null) {
        return temp;
      } else {
        return node;
      }
    }
  }

  // smallest key greater than given key
  public Key ceil(Key key) {
    Node ceilNode = ceil(root, key);
    return (ceilNode != null) ? ceilNode.key : null;
  }

  // how many smaller than eqaul to this key
  private int size(Node node) {
    if (node == null) {
      return 0;
    }
    return 1 + size(node.left) + size(node.right);
  }

  private int rank(Node node, Key key) {
    if (node == null) {
      return 0;
    }
    int cmp = key.compareTo(node.key);
    if (cmp < 0) {
      return rank(node.left, key);
    } else if (cmp > 0) {
      return 1 + size(node.left) + rank(node.right, key);
    } else {
      return size(node.left) + 1;
    }
  }

  public int rank(Key key) {
    return rank(root, key);
  }

  public static void main(String[] args) {
    BST<Integer, String> bst = new BST<>();
    bst.put(5, "A");
    bst.put(1, "X");
    bst.put(3, "Y");
    bst.put(4, "Z");
    bst.put(7, "A");

    // Test get()
    assert "Z".equals(bst.get(4)) : "Expected value Z for key 4";
    assert "A".equals(bst.get(5)) : "Expected value A for key 5";

    // Test updating value
    bst.put(5, "B");
    assert "B".equals(bst.get(5)) : "Expected updated value B for key 5";

    // Test min and max
    assert bst.min() == 1 : "Min key should be 1";
    assert bst.max() == 7 : "Max key should be 7";

    // Test floor()
    assert bst.floor(6) == 5 : "Floor of 6 should be 5";
    assert bst.floor(2) == 1 : "Floor of 2 should be 1";
    assert bst.floor(1) == 1 : "Floor of 1 should be 1";
    assert bst.floor(0) == null : "Floor of 0 should be null";

    // Test ceil()
    assert bst.ceil(6) == 7 : "Ceil of 6 should be 7";
    assert bst.ceil(2) == 3 : "Ceil of 2 should be 3";
    assert bst.ceil(7) == 7 : "Ceil of 7 should be 7";
    assert bst.ceil(8) == null : "Ceil of 8 should be null";

    // Test rank()
    assert bst.rank(0) == 0 : "Rank of 0 should be 0";
    assert bst.rank(1) == 1 : "Rank of 1 should be 1";
    assert bst.rank(2) == 1 : "Rank of 2 should be 1";
    assert bst.rank(3) == 2 : "Rank of 3 should be 2";
    assert bst.rank(4) == 3 : "Rank of 4 should be 3";
    assert bst.rank(5) == 4 : "Rank of 5 should be 4";
    assert bst.rank(6) == 4 : "Rank of 6 should be 4";
    assert bst.rank(7) == 5 : "Rank of 7 should be 5";
    assert bst.rank(8) == 5 : "Rank of 8 should be 5";

    // Test delMin()
    bst.delMin(); // removes key 1
    assert bst.min() == 3 : "After delMin, min should be 3";
    assert bst.get(1) == null : "Key 1 should be deleted";

    bst.delMin(); // removes key 3
    assert bst.min() == 4 : "After second delMin, min should be 4";
    assert bst.get(3) == null : "Key 3 should be deleted";

    // Test delMax()
    bst.delMax(); // removes key 7
    assert bst.max() == 5 : "After delMax, max should be 5";
    assert bst.get(7) == null : "Key 7 should be deleted";

    // Test delete arbitrary key (leaf node)
    bst.put(10, "L");
    bst.delete(10);
    assert bst.get(10) == null : "Key 10 should be deleted";

    // Test delete node with one child
    bst.put(6, "M");
    bst.delete(7); // 7 was deleted already, so no effect
    bst.delete(6);
    assert bst.get(6) == null : "Key 6 should be deleted";

    // Test delete node with two children
    bst.put(2, "N");
    bst.put(3, "O");
    bst.delete(4); // 4 has two children: 2 and 5 subtree after previous deletions
    assert bst.get(4) == null : "Key 4 should be deleted";

    // Check that BST still returns correct min and max
    assert bst.min() == 2 : "Min should now be 2";
    assert bst.max() == 5 : "Max should still be 5";

    bst.delMin();
    assert bst.min() == 3 : "Min should now be 3";

    System.out.println("All tests passed!");
  }
}
