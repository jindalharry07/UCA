
import javax.swing.tree.TreeNode;

public class trie {
  static class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord = false;
  }

  private TrieNode root;

  public trie() {
    root = new TrieNode();
  }

  // insert
  public void insert(String word) {
    TrieNode curr = root;
    for (char ch : word.toCharArray()) {
      int idx = ch - 'a';
      if (curr.children[idx] == null) {
        curr.children[idx] = new TrieNode();
      }
      curr = curr.children[idx];
    }
    curr.isEndOfWord = true;
  }

  // search
  public boolean search(String word) {
    TrieNode curr = root;
    for (char ch : word.toCharArray()) {
      int idx = ch - 'a';
      if (curr.children[idx] == null) {
        return false;
      }
      curr = curr.children[idx];
    }
    return curr.isEndOfWord;
  }

  public boolean startsWith(String prefix) {
    TrieNode curr = root;
    for (char ch : prefix.toCharArray()) {
      int idx = ch - 'a';
      if (curr.children[idx] == null) {
        return false;
      }
      curr = curr.children[idx];
    }
    return true;
  }

  private TrieNode searchPrefix(String word) {
    TrieNode current = root;
    for (char c : word.toCharArray()) {
      int index = c - 'a';
      if (current.children[index] == null) {
        return null;
      }
      current = current.children[index];
    }
    return current;
  }

  public void delete(String word) {
    delete(root, word, 0);
  }

  private boolean hasNoChildren(TrieNode node) {
    for (TrieNode child : node.children) {
      if (child != null) {
        return false;
      }
    }
    return true;
  }

  private boolean delete(TrieNode curr, String word, int idx) {
    if (idx == word.length()) {
      if (!curr.isEndOfWord) {
        return false;
      }
      curr.isEndOfWord = false;
      return hasNoChildren(curr);
    }

    char ch = word.charAt(idx);
    int chidx = ch - 'a';
    TrieNode child = curr.children[chidx];
    if (child == null) {
      return false;
    }

    boolean shouldDeleteCurrentNode = delete(child, word, idx + 1);

    if (shouldDeleteCurrentNode) {
      curr.children[chidx] = null;
      return !curr.isEndOfWord && hasNoChildren(curr);
    }
    return false;
  }

  public static void main(String[] args) {
    trie t = new trie();

    t.insert("apple");
    t.insert("app");
    t.insert("banana");

    System.out.println(t.search("apple")); // true
    System.out.println(t.search("app")); // true

    t.delete("app");
    System.out.println(t.search("app")); // false
    System.out.println(t.search("apple")); // true

    t.delete("apple");
    System.out.println(t.search("apple")); // false

    System.out.println(t.startsWith("ban")); // true
    t.delete("banana");
    System.out.println(t.search("banana")); // false
  }
}