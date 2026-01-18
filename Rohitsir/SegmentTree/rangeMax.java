public class rangeMax {
  int[] maxTree;
  int n;

  rangeMax(int[] arr) {
    n = arr.length;
    maxTree = new int[4 * n];
    build(arr, 0, 0, n - 1);
  }

  void build(int[] arr, int idx, int start, int end) {
    if (start == end) {
      maxTree[idx] = arr[start];
      return;
    }

    int mid = start + (end - start) / 2;

    build(arr, 2 * idx + 1, start, mid);
    build(arr, 2 * idx + 2, mid + 1, end);

    maxTree[idx] = Math.max(maxTree[2 * idx + 1], maxTree[2 * idx + 2]);
  }

  int queryMax(int idx, int start, int end, int l, int r) {
    if (r < start || l > end) {
      return Integer.MIN_VALUE;
    }

    if (l <= start && end <= r) {
      return maxTree[idx];
    }

    int mid = start + (end - start) / 2;

    int leftMax = queryMax(2 * idx + 1, start, mid, l, r);
    int rightMax = queryMax(2 * idx + 1, mid + 1, end, l, r);

    return Math.max(leftMax, rightMax);
  }

  void update(int node, int start, int end, int idx, int val) {
    if (start == end) {
      maxTree[node] = val;
      return;
    }

    int mid = start + (end - start) / 2;

    if (mid <= idx) {
      update(2 * node + 1, start, mid, idx, val);
    } else {
      update(2 * node + 2, mid + 1, end, idx, val);
    }

    maxTree[node] = Math.max(maxTree[2 * node + 1], maxTree[2 * node + 2]);
  }

  public static void main(String[] args) {
    int[] arr = { 4, 6, 10, 11, 12, 13 };

    rangeMax st = new rangeMax(arr);

    System.out.println("Max (1,4): " + st.queryMax(0, 0, arr.length - 1, 1, 4));

    st.update(0, 0, arr.length - 1, 2, 20);

    System.out.println("After update:");
    System.out.println("Max (1,4): " + st.queryMax(0, 0, arr.length - 1, 1, 4));
  }
}
