#include <bits/stdc++.h>
using namespace std;

/*
 * Count inversions in an array using a modified merge sort.
 * 
 * An inversion is a pair (i, j) such that i < j and arr[i] > arr[j].
 * This algorithm runs in O(n log n) time by:
 *  1. Dividing the array into halves recursively.
 *  2. Counting inversions in left half and right half.
 *  3. Counting inversions while merging two sorted halves.
 * 
 * @param l      Left index of current subarray.
 * @param mid    Middle index dividing the subarray.
 * @param r      Right index of current subarray.
 * @param arr    Reference to the original array.
 * @param aux    Auxiliary array used for merging.
 * @return       Number of inversions found in this merge step.
 */

int merge(int l, int mid, int r, vector<int> &arr, vector<int> &aux) {
  int i = l, j = mid + 1, inv = 0;

  for (int k = l; k <= r; k++) {
    if (i > mid) {
      aux[k] = arr[j++];
    } else if (j > r) {
      aux[k] = arr[i++];
    } else if (arr[i] > arr[j]) {
      aux[k] = arr[j++];
      // Count how many elements remaining in left half are greater than arr[j]
      inv += (mid - i + 1);
    } else {
      aux[k] = arr[i++];
    }
  }

  for (int k = l; k <= r; k++) {
    arr[k] = aux[k];
  }

  return inv;
}

/*
 * Recursively counts inversions in the array.
 *
 * @param arr  Reference to the array to count inversions in.
 * @param aux  Auxiliary array used for merging.
 * @param l    Left index of current subarray.
 * @param r    Right index of current subarray.
 * @return     Total number of inversions in arr[l..r].
 */

int countInversions(vector<int> &arr, vector<int> &aux, int l, int r) {
  if (l >= r) {
    return 0;
  }

  int mid = l + (r - l) / 2;
  int inv = 0;

  inv += countInversions(arr, aux, l, mid);
  inv += countInversions(arr, aux, mid + 1, r);
  inv += merge(l, mid, r, arr, aux);

  return inv;
}

int main() {
  //vector<int> a = {3, 2, 5, 7, 100, 99, -9, 5, 7, 8, 2, 8, 18, 11};
  vector <int> a = {4 , 3, 2, 1};
  vector<int> aux(a.size());

  int inv = countInversions(a, aux, 0, a.size() - 1);

  cout << inv << "\n";

  return 0;
}

