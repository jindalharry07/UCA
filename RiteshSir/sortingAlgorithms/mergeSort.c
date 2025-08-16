#include<stdio.h>

/*
 * Algorithum: Merge Sort
 * 1. Divide the array into two equal parts.
 * 2. Recursively sort both halves.
 * 3. Merge the two sorted halves into one.
 *
 * Time Complexity: Best O(n log n) Average O(n log n) Worst O(n log n)
 * Space Complexity: O(n)
 * Stable: Yes
 * Swaps: O(n log n)
 */

void merge(int arr[], int aux[], int left, int mid, int right) {
  int i = left;
  int j = mid + 1;

  for (int k = left; k <= right; k++) {
    if (i > mid) {
      aux[k] = arr[j++];
    }else if (j > right) {
      aux[k] = arr[i++];
    }else if (arr[i] < arr[j]) {
      aux[k] = arr[i++];
    }else {
      aux[k] = arr[j++];
    }
  }

  for (int k = left; k <= right; k++) {
    arr[k] = aux[k];
  }
}

void mergeSort(int arr[], int aux[], int left, int right) {
  if (left >= right) return;

  int mid = left + (right - left) / 2;

  mergeSort(arr,aux,left,mid);
  mergeSort(arr,aux,mid+1,right);
  merge(arr,aux,left,mid,right);
}

int main() {
  int n;
  printf("Enter number of elements: ");
  scanf("%d", &n);
  int arr[n];
  int aux[n];

  printf("Enter %d elements:\n", n);
  for (int i = 0; i < n; i++) {
    scanf("%d", &arr[i]);
  }

  mergeSort(arr, aux, 0, n - 1);

  printf("Sorted array: ");
  for (int i = 0; i < n; i++) {
    printf("%d ", arr[i]);
  }
  printf("\n");

  return 0;
}
