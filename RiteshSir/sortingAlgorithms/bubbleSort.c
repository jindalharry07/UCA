#include<stdio.h>

/*
 * Algorithum: Bubble Sort
 * 1. Compare each adjacent pair of elements.
 * 2. Swap them if they are in the wrong order.
 * 3. Repeat until the array is sorted
 *
 * Time: Best O(n), Average O(n^2), Worst O(n^2)
 * Space: O(1)
 * Stable: Yes
 * Swaps: ~O(n^2)
 */

void bubbleSort(int arr[], int n) {
  for (int i = 0; i < n - 1; i++) {
    int swapped = 0;
    for (int j = 0; j < n - 1;j++) {
      if (arr[j] > arr[j+1]) {
        // swap
	int temp = arr[j];
	arr[j] = arr[j+1];
	arr[j+1] = temp;
	swapped = 1;
      }
    }
    if (swapped == 0) break;
  }
}

int main() {
  int n;
  printf("Enter number of elements: ");
  scanf("%d", &n);

  int arr[n];
  printf("Enter %d elements :\n",n);
  for(int i = 0; i < n; i++) {
    scanf("%d", &arr[i]);
  }
  
  bubbleSort(arr,n);
  printf("Sorted Array: ");
  for( int i = 0; i < n; i++) {
    printf("%d ",arr[i]);
  }
  printf("\n");

  return 0;
}
