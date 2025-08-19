#include <stdio.h>
/* Copyright 2025 Harry Jindal */
#define kMaxArraySize 1000

/*
 * Algorithum: Insertion Sort
 * 1. Iterate through array element one by one
 * 2. Place each element in its correct position among the previously sorted elements.
 * 3. Shift to make space when needed.
 *
 * Time Complexity: Best O(n) Average O(n^2) Worst O(n^2)
 * Space Complexity: O(1)
 * Stable: Yes
 * Swaps: Upto O(n^2)
 */

void insertionSort(int arr[], int n) {
  for (int i = 1; i < n; i++) {
    int key = arr[i];
    int j = i - 1;
    while (j >= 0 && arr[j] > key) {
      arr[j+1] = arr[j];
      j--;
    }
    arr[j+1] = key;
  }
}

int main() {
  int n;
  printf("Enter number of elements: ");
  scanf("%d", &n);
  if (n > kMaxArraySize) {
    printf("Error: Maximum allowed size is %d\n", kMaxArraySize);
    return 1;
  }

  int arr[kMaxArraySize];

  printf("Enter %d elements:\n", n);
  for (int i = 0; i < n; i++) {
    scanf("%d", &arr[i]);
  }

  insertionSort(arr, n);

  printf("Sorted array: ");
  for (int i = 0; i < n; i++) {
    printf("%d ", arr[i]);
  }
  printf("\n");
  return 0;
}
