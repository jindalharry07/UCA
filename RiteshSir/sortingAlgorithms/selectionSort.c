#include <stdio.h>

/*
 * Algorithum: Selection Sort
 * 1. Find the minimum in unsorted part.
 * 2. Swap it with the first unsorted element.
 * 3. Repeat until the array is sorted.
 *
 * Time Complexity: Beat O(n^2) Average O(n^2) Worst O(n^2)
 * Space Complexity: O(1)
 * Stable: No
 * Swaps (n-1)
 */

void selectionSort(int arr[], int n) {
  for (int i = 0; i < n; i++) {
    int minIdx = i;
    for (int j = i + 1; j < n; j++) {
      if (arr[i] > arr[j]) {
        minIdx = j;
      }
    }

    if (minIdx != i) {
      int temp = arr[i];
      arr[i] = arr[minIdx];
      arr[minIdx] = temp;
    }
  }
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

  selectionSort(arr, n);
  printf("Sorted array: ");
  for (int i = 0; i < n; i++) {
    printf("%d ", arr[i]);
  }
  printf("\n");
  return 0;
}
