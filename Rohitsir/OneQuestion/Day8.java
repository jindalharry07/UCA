// https://docs.google.com/document/d/1S0iI554TzzLTJridigqDrghHJL3tnfoehrXJT_blLM4/edit?tab=t.0
import java.util.*;

/*
import java.util.Arrays;


 * Given an array where every element appears exactly twice except for two
 * unique elements,
 * find those two unique elements.
 * Key constraints: The array contains exactly two unique elements that appear
 * only once,
 * and all other elements appear exactly twice.
 * 
 * @param nums an array of integers
 * @return an integer array of length 2 containing the two non-repeating
 *         elements
 
public class TwoNonRepeatingElements {

  public static int[] findTwoUnique(int[] nums) {
    // method stub
    return new int[] { 0, 0 };
  }

  public static void main(String[] args) {
    int[] result1 = findTwoUnique(new int[] { 1, 2, 3, 2, 1, 4 });
    System.out.println("Expected [3, 4]: " + Arrays.toString(result1));
    int[] result2 = findTwoUnique(new int[] { 2, 2, 3, 5 });
    System.out.println("Expected [3, 5]: " + Arrays.toString(result2));
    int[] result3 = findTwoUnique(new int[] { 0, 0, -1, -1, 9, 7 });
    System.out.println("Expected [7, 9]: " + Arrays.toString(result3));
  }
}
*/

/**
 * Given an array of integers, compute the XOR of XORs of all possible subsets of the array.
 * Key constraints: If the array has more than one element, the result is 0 
 * (since each bit appears in an even number of subset XORs).
 * @param nums an array of integers
 * @return the XOR of the XOR of all subsets
 
public class XorOfAllSubsets {

  public static int xorOfAllSubsets(int[] nums) {
    // method stub
    return 0;
  }

  public static void main(String[] args) {
    System.out.println("Expected 5: " + xorOfAllSubsets(new int[]{5}));
    System.out.println("Expected 0: " + xorOfAllSubsets(new int[]{1, 2}));
    System.out.println("Expected 0: " + xorOfAllSubsets(new int[]{1, 2, 3}));
  }
}
*/

/**
 * Calculates the total Hamming distance between all pairs of integers in the array.
 * The Hamming distance between two integers is the number of bit positions in which they differ.
 * @param nums an array of non-negative integers
 * @return the total Hamming distance between all unique pairs in the array
 
public class TotalHammingDistance {

  public static int totalHammingDistance(int[] nums) {
    // method stub
    return 0;
  }

  public static void main(String[] args) {
    System.out.println("Expected 8: " + totalHammingDistance(new int[]{4, 14, 4, 14}));
    System.out.println("Expected 4: " + totalHammingDistance(new int[]{1, 2, 3}));
    System.out.println("Expected 0: " + totalHammingDistance(new int[]{0, 0, 0}));
  }
}
*/


/**
 * Finds the maximum XOR of any two elements in the array.
 * Key constraints: The array contains at least two non-negative integers.
 * @param nums an array of non-negative integers
 * @return the maximum XOR value of any two distinct elements in the array
 
public class MaximumXorInArray {

  public static int findMaximumXor(int[] nums) {
    // method stub
    return 0;
  }

  public static void main(String[] args) {
    System.out.println("Expected 126: " + findMaximumXor(new int[]{26, 100, 25, 13, 4, 14}));
    System.out.println("Expected 7: " + findMaximumXor(new int[]{1, 2, 3, 4, 5, 6, 7}));
    System.out.println("Expected 0: " + findMaximumXor(new int[]{0, 0, 0}));
  }
}
*/

/**
 * Finds the missing number in an array containing all distinct numbers from 0 to n inclusive with one missing.
 * Key constraints: The array size is n, containing numbers from 0 to n with exactly one missing.
 * @param nums an array containing distinct integers from 0 to n with exactly one missing
 * @return the missing integer in the range [0, n]
 
public class MissingNumberXor {

  public static int findMissingNumber(int[] nums) {
    // method stub
    return 0;
  }

  public static void main(String[] args) {
    System.out.println("Expected 2: " + findMissingNumber(new int[]{3, 0, 1}));
    System.out.println("Expected 3: " + findMissingNumber(new int[]{0, 1, 2, 4}));
    System.out.println("Expected 7: " + findMissingNumber(new int[]{1, 0, 4, 3, 2, 6, 5}));
  }
}
*/

public class Day8 {
  public static int[] findTwoUnique(int[] nums) {
    // method stub
    int xor = 0;
    int n = nums.length;
    for(int i = 0; i < n; i++) {
      xor ^= nums[i];
    }

    int diffBit = xor & (-xor);

    int num1 = 0, num2 = 0;
    for (int i = 0; i < n; i++) {
      if ((diffBit & nums[i]) == 1) {
        num1 ^= nums[i];
      } else {
        num2 ^= nums[i];
      }
    }
    return new int[] { num1, num2 };
  }

  public static int xorOfAllSubsets(int[] nums) {
    // method stub
    if(nums.length == 1) {
      return nums[0];
    }
    return 0;
  }

  // public static int totalHammingDistance(int[] nums) {
  //   // method stub
  //   int total = 0;
  //   int n = nums.length;

  //   for (int i = 0; i < n; i++) {
  //     for (int j = i + 1; j < n; j++) {
  //       int xor = nums[i] ^ nums[j];
  //       total += Integer.bitCount(xor);
  //     }
  //   }

  //   return total;
  // }
  public static int totalHammingDistance(int[] nums) {
    int n = nums.length;
    int total = 0;
    
    // check each of the 32 bits
    for (int bit = 0; bit < 32; bit++) {
      int ones = 0;
      for (int num : nums) {
        if (((num >> bit) & 1) == 1) {
          ones++;
        }
      }
      int zeros = n - ones;
      total += ones * zeros; // contribution from this bit
    }
    
    return total;
  }

  public static int findMaximumXor(int[] nums) {
    // method stub
    int maxXor = 0;
    int n = nums.length;
    for(int i = 0; i < n; i++) {
      for(int j = i + 1; j < n; j++) {
        int xor = nums[i] ^ nums[j];
        maxXor = Math.max(maxXor, xor);
      }
    }

    return maxXor;
  }

  public static int findMissingNumber(int[] nums) {
    // method stub
    int xorNums = 0, xorTotal = 0;
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      xorNums ^= nums[i];
      xorTotal ^= (i + 1);
    }
    return xorNums ^ xorTotal;
  }

  public static void main(String[] args) {

    //Ques 1
    System.out.println("1st Question");
    int[] result1 = findTwoUnique(new int[] { 1, 2, 3, 2, 1, 4 });
    System.out.println("Expected [3, 4]: " + Arrays.toString(result1));
    int[] result2 = findTwoUnique(new int[] { 2, 2, 3, 5 });
    System.out.println("Expected [3, 5]: " + Arrays.toString(result2));
    int[] result3 = findTwoUnique(new int[] { 0, 0, -1, -1, 9, 7 });
    System.out.println("Expected [7, 9]: " + Arrays.toString(result3));

    // Ques 2
    System.out.println("\n\n2nd Question");
    System.out.println("Expected 5: " + xorOfAllSubsets(new int[]{5}));
    System.out.println("Expected 0: " + xorOfAllSubsets(new int[]{1, 2}));
    System.out.println("Expected 0: " + xorOfAllSubsets(new int[]{1, 2, 3}));


    // Ques 3
    System.out.println("\n\n3rd Question");
    System.out.println("Expected 8: " + totalHammingDistance(new int[]{4, 14, 4, 14}));
    System.out.println("Expected 4: " + totalHammingDistance(new int[]{1, 2, 3}));
    System.out.println("Expected 0: " + totalHammingDistance(new int[]{0, 0, 0}));

    // Ques 4
    System.out.println("\n\n4th Question");
    System.out.println("Expected 126: " + findMaximumXor(new int[]{26, 100, 25, 13, 4, 14}));
    System.out.println("Expected 7: " + findMaximumXor(new int[]{1, 2, 3, 4, 5, 6, 7}));
    System.out.println("Expected 0: " + findMaximumXor(new int[]{0, 0, 0}));

    // Ques 5
    System.out.println("\n\n5th Question");
    System.out.println("Expected 2: " + findMissingNumber(new int[]{3, 0, 1}));
    System.out.println("Expected 3: " + findMissingNumber(new int[]{0, 1, 2, 4}));
    System.out.println("Expected 7: " + findMissingNumber(new int[]{1, 0, 4, 3, 2, 6, 5}));
  
  }
}
