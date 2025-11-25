public class AVLTree<Key extends Comparable<Key>, Value> {
  private class Node {
    Key key;
    Value val;
    Node left, right;
    int height;
    
    public Node(Key key, Value val) {
      this.key = key;
      this.val = val;
      this.height = 1;
    }
  }

  private Node root;

  private int height(Node n) {
    return (n == null) ? 0 : n.height;
  }

  private int balance(Node n) {
    if(n == null) {
      return 0;
    }
    return height(n.left) - height(n.right);
  }

  private void insert(Node node, Key key, Value val) {
    
  }
  public void put(Key key, Value val) {
    root = insert(root, key, val);
  }
}
