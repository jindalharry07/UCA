/*
Surprise Question: Search in Bitonic Array
public class SearchInBitonicArray {
  
   * A bitonic array is first strictly increasing then strictly decreasing. 
   * Given such a bitonic array and a target value, return the index of the target if it exists, otherwise return -1.
   *
   * Example: [1, 3, 8, 12, 4, 2], target = 4 -> returns 4.
   *
   * @param nums - Bitonic array of integers (length >= 3).
   * @param target - The integer to search for.
   * @returns int - Index of target or -1 if not found.
   
  public int searchBitonic(int[] nums, int target) {
    return -1;
  }


   * Main method for testing the SearchInBitonicArray class.
   
  public static void main(String[] args) {
    SearchInBitonicArray s = new SearchInBitonicArray();
    int[] arr = {1, 3, 8, 12, 4, 2};
    assert s.searchBitonic(arr, 4) == 4 : "Test case 1 failed";
    assert s.searchBitonic(arr, 12) == 3 : "Test case 2 failed";
    assert s.searchBitonic(arr, 5) == -1 : "Test case 3 failed";
    assert s.searchBitonic(arr, 1) == 0 : "Test case 4 failed";
  }
}
*/

public class SearchInBitonicArray {
  /**
   * A bitonic array is first strictly increasing then strictly decreasing.
   * Given such a bitonic array and a target value, return the index of the target
   * if it exists, otherwise return -1.
   *
   * Example: [1, 3, 8, 12, 4, 2], target = 4 -> returns 4.
   *
   * @param nums   - Bitonic array of integers (length >= 3).
   * @param target - The integer to search for.
   * @returns int - Index of target or -1 if not found.
   */

  private static int findPeak(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return 0;
    }
    if (nums[0] > nums[1]) {
      return 0;
    }
    if (nums[n - 1] > nums[n - 2]) {
      return n - 1;
    }
    int low = 1, high = n - 2;
    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
        return mid;
      }

      if (nums[mid] < nums[mid + 1]) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    return -1;
  }

  private static int binarySearch(int[] nums, int low, int high, int target, boolean ascending) {
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) {
        return mid;
      }

      if (ascending) {
        if (nums[mid] < target) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      } else {
        if (nums[mid] < target) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      }
    }
    return -1;
  }

  public static int searchBitonic(int[] nums, int target) {
    int peak = findPeak(nums);

    // find in left part
    int idx = binarySearch(nums, 0, peak, target, true);
    if (idx != -1) {
      return idx;
    }
    return binarySearch(nums, peak + 1, nums.length - 1, target, false);
  }

  /**
   * Main method for testing the SearchInBitonicArray class.
   */
  public static void main(String[] args) {
    SearchInBitonicArray s = new SearchInBitonicArray();
    int[] arr = { 1, 3, 8, 12, 4, 2 };
    assert s.searchBitonic(arr, 4) == 4 : "Test case 1 failed";
    assert s.searchBitonic(arr, 12) == 3 : "Test case 2 failed";
    assert s.searchBitonic(arr, 5) == -1 : "Test case 3 failed";
    assert s.searchBitonic(arr, 1) == 0 : "Test case 4 failed";
  }

}
