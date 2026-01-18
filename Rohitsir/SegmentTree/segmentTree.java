import java.util.*;

public class segmentTree {
  int[] tree;
  int n;

  segmentTree(int[] arr) {
    n = arr.length;
    tree = new int[4 * n];
    build(arr, 0, 0, n - 1);
  }

  void build(int[] arr, int idx, int low, int high) {
    if (low == high) {
      tree[idx] = arr[low];
      return;
    }

    int mid = low + (high - low) / 2;

    build(arr, 2 * idx + 1, low, mid);
    build(arr, 2 * idx + 2, mid + 1, high);

    tree[idx] = tree[2 * idx + 1] + tree[2 * idx + 2];
  }

  int query(int idx, int start, int end, int l, int r) {
    if (r < start || l > end) {
      return 0;
    }

    if (l <= start && end <= r) {
      return tree[idx];
    }

    int mid = start + (end - start) / 2;
    int leftSide = query(2 * idx + 1, start, mid, l, r);
    int rightSide = query(2 * idx + 2, mid + 1, end, l, r);

    return leftSide + rightSide;
  }

  void update(int node, int start, int end, int idx, int val) {
    if (start == end) {
      tree[node] = val;
      return;
    }
    int mid = start + (end - start) / 2;

    if (idx <= mid) {
      update(2 * node + 1, start, mid, idx, val);
    } else {
      update(2 * node + 2, mid + 1, end, idx, val);
    }

    tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
  }

  public static void main(String[] args) {
    int[] arr = { 4, 6, 10, 11, 12, 13 };

    segmentTree st = new segmentTree(arr);

    System.out.println("Sum from index 1 to 4: " + st.query(0, 0, arr.length - 1, 1, 4));

    st.update(0, 0, arr.length - 1, 2, 20); // update index 2

    System.out.println("After update:");
    System.out.println("Sum from index 1 to 4: " + st.query(0, 0, arr.length - 1, 1, 4));
  }
}