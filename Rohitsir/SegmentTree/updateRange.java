public class updateRange {
  int[] tree, lazy;
  int n;

  updateRange(int[] arr) {
    n = arr.length;
    tree = new int[4 * n];
    lazy = new int[4 * n];
    build(arr, 0, 0, n - 1);
  }

  private void build(int[] arr, int idx, int left, int right) {
    if (left == right) {
      tree[idx] = arr[left];
      return;
    }

    int mid = left + (right - left) / 2;

    build(arr, 2 * idx + 1, left, mid);
    build(arr, 2 * idx + 2, mid + 1, right);

    tree[idx] = tree[2 * idx + 1] + tree[2 * idx + 2];
  }

  // Range update using lazy propagation
  public void updateRange(int idx, int left, int right, int l, int r, int val) {
    if (lazy[idx] != 0) {
      tree[idx] += (right - left + 1) * lazy[idx];

      if (left != right) {
        lazy[2 * idx + 1] += lazy[idx];
        lazy[2 * idx + 2] += lazy[idx];
      }
      lazy[idx] = 0;
    }

    if (r < left || l > right) {
      return;
    }

    if (l <= left && right <= r) {
      tree[idx] += (right - left + 1) * val;

      if (right != left) {
        lazy[2 * idx + 1] += val;
        lazy[2 * idx + 2] += val;
      }
      return;
    }

    int mid = left + (right - left) / 2;
    updateRange(2 * idx + 1, left, mid, l, r, val);
    updateRange(2 * idx + 2, mid + 1, right, l, r, val);

    tree[idx] = tree[2 * idx + 1] + tree[2 * idx + 2];
  }

  public int queryRange(int idx, int left, int right, int l, int r) {
    if (left > right) {
      return 0;
    }

    if (lazy[idx] != 0) {
      tree[idx] += (right - left + 1) * lazy[idx];

      if (left != right) {
        lazy[2 * idx + 1] += lazy[idx];
        lazy[2 * idx + 2] += lazy[idx];
      }
      lazy[idx] = 0;
    }

    if (r < left || l > right) {
      return 0;
    }

    if (l <= left && r >= right) {
      return tree[idx];
    }

    int mid = left + (right - left) / 2;

    int leftChild = queryRange(2 * idx + 1, left, mid, l, r);
    int rightChild = queryRange(2 * idx + 2, mid + 1, right, l, r);

    return leftChild + rightChild;
  }

  public static void main(String[] args) {
    int[] arr = { 4, 6, 10, 11, 12, 13 };

    updateRange st = new updateRange(arr);
    System.out.println("Sum [1,4]: " + st.queryRange(0, 0, arr.length - 1, 1, 4));

    // Add 5 to range [1,3]
    st.updateRange(0, 0, arr.length - 1, 1, 3, 5);

    System.out.println("After update (+5 in range [1,3])");
    System.out.println("Sum [1,4]: " + st.queryRange(0, 0, arr.length - 1, 1, 4));
  }
}
