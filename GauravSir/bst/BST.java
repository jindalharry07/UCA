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

  public static void main(String[] args) {
    BST<Integer, String> bst = new BST<Integer, String>();
    bst.put(5, "A");
    bst.put(1, "X");
    bst.put(3, "Y");
    bst.put(4, "Z");
    bst.put(7, "A");

    assert bst.get(4) == "Z";
    assert bst.get(5) == "A";

    assert "Z".equals(bst.get(4)) : "Expected value Z for key 4";
    assert "A".equals(bst.get(5)) : "Expected value A for key 5";

    bst.put(5, "B");
    assert "B".equals(bst.get(5)) : "Expected updated value B for key 5";

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

    System.out.println("All tests passed!");

  }
}
