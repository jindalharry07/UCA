/*
Question 1: Search in a Sorted Array
public class SearchInSortedArray {
  
   * Given a sorted array of distinct integers and a target value, return the index of target if found, or -1 otherwise.
   *
   * @param nums - Sorted integer array (distinct values). 1 <= nums.length <= 10^5
   * @param target - The integer value to search for.
   * @returns int - Index of target or -1 if not found.
   
  public int binarySearch(int[] nums, int target) {
    return -1;
  }

  
   * Main method for testing the SearchInSortedArray class.
   
  public static void main(String[] args) {
    SearchInSortedArray s = new SearchInSortedArray();
    int[] arr = {1, 2, 3, 4, 5};
    assert s.binarySearch(arr, 1) == 0 : "Test case 1 failed";
    assert s.binarySearch(arr, 3) == 2 : "Test case 2 failed";
    assert s.binarySearch(arr, 5) == 4 : "Test case 3 failed";
    assert s.binarySearch(arr, 6) == -1 : "Test case 4 failed";
  }
}
*/

import java.util.*;

public class SearchInArray {

  /**
   * Given a sorted array of distinct integers and a target value, return the
   * index of target if found, or -1 otherwise.
   *
   * @param nums   - Sorted integer array (distinct values). 1 <= nums.length <=
   *               10^5
   * @param target - The integer value to search for.
   * @returns int - Index of target or -1 if not found.
   */
  public int binarySearch(int[] nums, int target) {
    int n = nums.length;
    int low = 0, high = n - 1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (nums[mid] < target) {
        low = mid + 1;
      } else {
        high = mid + 1;
      }
    }
    return (nums[low] == target) ? low : -1;
  }

  /**
   * Main method for testing the SearchInSortedArray class.
   */
  public static void main(String[] args) {
    SearchInArray s = new SearchInArray();
    int[] arr = { 1, 2, 3, 4, 5 };
    assert s.binarySearch(arr, 1) == 0;
    assert s.binarySearch(arr, 3) == 2;
    assert s.binarySearch(arr, 5) == 4;
    assert s.binarySearch(arr, 6) == -1;
  }
}
