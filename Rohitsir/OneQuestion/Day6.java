
/*
Ques 1 Implement the RandomizedSet class:
RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.



Ques 2 Merge Sorted Array
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.
The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

Example 1:
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.



class Solution {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    // complete the code.
  }
}



Ques 3
100 game

In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10. The player who first causes the running total to reach or exceed 100 wins.
What if we change the game so that players cannot re-use integers?
For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.
Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win, otherwise, return false. Assume both players play optimally.


Example 1:

Input: maxChoosableInteger = 10, desiredTotal = 11
Output: false
Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
Example 2:

Input: maxChoosableInteger = 10, desiredTotal = 0
Output: true
Example 3:

Input: maxChoosableInteger = 10, desiredTotal = 1
Output: true
Constraints:

1 <= maxChoosableInteger <= 20
0 <= desiredTotal <= 300

class Solution {
  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
      
  }
}


Ques 4
Valid triangle Number
Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

Example 1:

Input: nums = [2,2,3,4]
Output: 3
Explanation: Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Example 2:

Input: nums = [4,2,3,4]
Output: 4


class Solution {
  public int triangleNumber(int[] nums) {
    
  }
}

Hint : 
For three sides (a, b, c) (where a ≤ b ≤ c) to form a triangle, the condition is:
a + b > c
So the problem reduces to: for each largest side c, 
count how many pairs (a, b) satisfy a + b > c.

*/
import java.util.*;

public class Day6 {
  static class RandomizedSet {
    private HashSet<Integer> set;

    private List<Integer> values;
    private Random ran;
    private HashMap<Integer, Integer> valueIdx;

    public RandomizedSet() {
      set = new HashSet<>();
      ran = new Random();

      values = new ArrayList<>();
      valueIdx = new HashMap<>();
    }

    public boolean insert(int val) {
      // return set.add(val);

      if (valueIdx.containsKey(val)) {
        return false;
      }

      values.add(val);
      valueIdx.put(val, values.size() - 1);
      return true;
    }

    public boolean remove(int val) {
      // return set.remove(val);

      if (!valueIdx.containsKey(val)) {
        return false;
      }

      int idx = valueIdx.get(val);
      int lastEle = values.get(values.size() - 1);

      values.set(idx, lastEle);
      valueIdx.put(lastEle, idx);

      values.remove(values.size() - 1);
      valueIdx.remove(val);
      return true;
    }

    public int getRandom() {
      // List<Integer> list = new ArrayList<>(set);
      // return list.get(ran.nextInt(list.size()));

      int idx = ran.nextInt(values.size());
      return values.get(idx);
    }
  }

  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    // complete the code.

    int idx = m + n - 1;
    int i = m - 1;
    int j = n - 1;

    while (i >= 0 && j >= 0) {
      if (nums1[i] >= nums2[j]) {
        nums1[idx--] = nums1[i--];
      } else {
        nums1[idx--] = nums2[j--];
      }
    }

    while (j >= 0) {
      nums1[idx--] = nums2[j--];
    }
  }

  public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    if (desiredTotal <= maxChoosableInteger) {
      return true;
    }

    return (desiredTotal % 2 != 0) ? false : true;
  }

  public static int triangleNumber(int[] nums) {
    Arrays.sort(nums);

    int n = nums.length;
    int res = 0;
    for (int k = n - 1; k >= 2; k--) {
      int i = 0;
      int j = k - 1;
      while (i < j) {
        if (nums[i] + nums[j] > nums[k]) {
          res += (j - i);
          j--;
        } else {
          i++;
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    // Ques 1
    /*
     * RandomizedSet st1 = new RandomizedSet();
     * st1.insert(0);
     * st1.insert(2);
     * st1.insert(2);
     * st1.insert(1);
     * st1.insert(5);
     * 
     * System.out.println(st1.getRandom());
     * st1.remove(2);
     * st1.remove(3);
     * System.out.println(st1.getRandom());
     */

    // Ques 2
    // int[] nums1 = {1, 3, 5, 0, 0, 0}; // m = 3
    // int[] nums2 = {2, 4, 6}; // n = 3

    // merge(nums1, 3, nums2, 3);

    // System.out.println("Merged Array: " + Arrays.toString(nums1));

    // Ques 3
    System.out.println(canIWin(10, 0));
    System.out.println(canIWin(10, 1));

    // Ques 4
    int[] nums1 = { 2, 2, 3, 4 };
    System.out.println("Test 1 Result: " + triangleNumber(nums1));

    int[] nums2 = { 4, 2, 3, 4 };
    System.out.println("Test 2 Result: " + triangleNumber(nums2));

    int[] nums3 = { 1, 1, 1, 1 };
    System.out.println("Test 3 Result: " + triangleNumber(nums3)); 

    int[] nums4 = { 2, 2, 10 };
    System.out.println("Test 4 Result: " + triangleNumber(nums4));
  }

}
