#include <stdio.h>

/*
 * Algorithum: Quick Sort
 * 1. Choose a pivot element.
 * 2.Partition the array into two sub-arrays: elements less than pivot, elements greater than pivot.
 * 3. Recursively apply the same logic to left and right sub-arrays.
 *
 * Time Complexity: Best O(n log n) Average O(n log n) Worst O(n^2)
 * Space Complexity: O(log n) (du to recursive calls)
 * Stable: No
 * Swaps: Depends on input
 */


void swap(int *a, int *b) {
  int temp = *a;
  *a = *b;
  *b = temp;
}

int partition(int arr[], int low, int high) {
  int pivot = arr[low];
  int i = low + 1;
  int j = high;

  while (i <= j) {
    while (i <= high && arr[i] <= pivot) {
      i++;
    }

    while (j >= low && arr[j] > pivot) {
      j--;
    }

    if (i < j) {
      swap(&arr[i], &arr[j]);
    }

  }

  swap(&arr[low], &arr[j]);
  return j;
}

void quickSort(int arr[], int low, int high) {
  if (low >= high) {
    return;
  }

  int pivot = partition(arr, low, high);
  quickSort(arr, low, pivot - 1);
  quickSort(arr, pivot + 1, high);
}

int main() {
  int n;
  printf("Enter number of elements: ");
  scanf("%d", &n);
  int arr[n];

  printf("Enter %d elements:\n", n);
  for (int i = 0; i < n; i++) {
    scanf("%d", &arr[i]);
  }

  quickSort(arr, 0, n-1);
  printf("Sorted array: ");
  for (int i = 0; i < n; i++) {
    printf("%d ", arr[i]);
  }
  printf("\n");

  return 0;
}
