/*
Question 2: Search in Rotated Sorted Array (No Duplicates)
public class SearchInRotatedSortedArray {
  
   * Given a sorted array that is rotated at some pivot (with no duplicate values), find the index of a target value. If the target is not found, return -1.
   *
   * Example: [4, 5, 6, 7, 0, 1, 2], target = 0 -> returns 4.
   *
   * @param nums - Rotated sorted integer array (no duplicates).
   * @param target - The integer to search for.
   * @returns int - Index of target or -1 if not found.
   
  public int search(int[] nums, int target) {
    return -1;
  }

  
   * Main method for testing the SearchInRotatedSortedArray class.
   
  public static void main(String[] args) {
    SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
    int[] arr1 = {4, 5, 6, 7, 0, 1, 2};
    assert s.search(arr1, 0) == 4 : "Test case 1 failed";
    assert s.search(arr1, 3) == -1 : "Test case 2 failed";
    int[] arr2 = {3, 1};
    assert s.search(arr2, 3) == 0 : "Test case 3 failed";
  }
}
*/

/*
Question 3: Search in Rotated Sorted Array (With Duplicates)
public class SearchInRotatedSortedArrayWithDuplicates {
  
   * Given a sorted array that is rotated at some pivot and may contain duplicates, find the index of a target value. If the target is not found, return -1.
   *
   * Example: [2, 5, 6, 0, 0, 1, 2], target = 0 -> returns 3 or 4.
   *
   * @param nums - Rotated sorted integer array (may contain duplicates).
   * @param target - The integer to search for.
   * @returns int - Index of target or -1 if not found.
   
  public int search(int[] nums, int target) {
    return -1;
  }

  
   * Main method for testing the SearchInRotatedSortedArrayWithDuplicates class.
   
  public static void main(String[] args) {
    SearchInRotatedSortedArrayWithDuplicates s = new SearchInRotatedSortedArrayWithDuplicates();
    int[] arr1 = {2, 5, 6, 0, 0, 1, 2};
    assert (s.search(arr1, 0) == 3 || s.search(arr1, 0) == 4) : "Test case 1 failed";
    int[] arr2 = {2, 2, 2, 3, 4, 2};
    assert s.search(arr2, 3) == 3 : "Test case 2 failed";
    assert s.search(arr2, 5) == -1 : "Test case 3 failed";
  }
}

*/

public class SearchInRotatedSortedArray {
  /**
   * Given a sorted array that is rotated at some pivot (with no duplicate
   * values), find the index of a target value. If the target is not found, return
   * -1.
   *
   * Example: [4, 5, 6, 7, 0, 1, 2], target = 0 -> returns 4.
   *
   * @param nums   - Rotated sorted integer array (no duplicates).
   * @param target - The integer to search for.
   * @returns int - Index of target or -1 if not found.
   */
  public int search(int[] nums, int target) {
    int n = nums.length;
    int low = 0, high = n - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) {
        return mid;
      }

      if (nums[mid] >= nums[low]) {
        if (nums[low] <= target && nums[mid] > target) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      } else {
        if (nums[high] <= target && nums[mid] < target) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }
    return -1;
  }

  /**
   * Given a sorted array that is rotated at some pivot and may contain duplicates, find the index of a target value. If the target is not found, return -1.
   *
   * Example: [2, 5, 6, 0, 0, 1, 2], target = 0 -> returns 3 or 4.
   *
   * @param nums - Rotated sorted integer array (may contain duplicates).
   * @param target - The integer to search for.
   * @returns int - Index of target or -1 if not found.
   */
  public int search2(int[] nums, int target) {
    int n = nums.length;
    int low = 0, high = n - 1;

    while(low <= high) {
      int mid = low + (high - low) / 2;
      if(nums[low] == nums[mid] && nums[mid] == nums[high]) {
        low++;
        high--;
        continue;
      }

      if(nums[mid] == target) {
        return mid;
      }

      if(nums[low] <= nums[mid]) {
        if(nums[low] <= target && nums[mid] > target) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      } else {
        if(nums[high] >= target && nums[mid] < target) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }
    return -1;
  }

  /**
   * Main method for testing the SearchInRotatedSortedArray class.
   */
  public static void main(String[] args) {
    SearchInRotatedSortedArray s1 = new SearchInRotatedSortedArray();
    int[] arr1 = { 4, 5, 6, 7, 0, 1, 2 };
    assert s1.search(arr1, 0) == 4 : "Test case 1 failed";
    assert s1.search(arr1, 3) == -1 : "Test case 2 failed";
    int[] arr2 = { 3, 1 };
    assert s1.search(arr2, 3) == 0 : "Test case 3 failed";

    SearchInRotatedSortedArray s2 = new SearchInRotatedSortedArray();
    int[] nums1 = { 2, 5, 6, 0, 0, 1, 2 };
    assert (s2.search2(nums1, 0) == 3 || s2.search2(nums1, 0) == 4) : "Test case 1 failed";
    int[] nums2 = { 2, 2, 2, 3, 4, 2 };
    assert s2.search2(nums2, 3) == 3 : "Test case 2 failed";
    assert s2.search2(nums2, 5) == -1 : "Test case 3 failed";
  }

}
