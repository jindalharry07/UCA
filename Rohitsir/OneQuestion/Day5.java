// You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

// Return the length of the longest substring containing the same letter you can get after performing the above operations.

// Example 1:
// Input: s = "ABAB", k = 2
// Output: 4
// Explanation: Replace the two 'A's with two 'B's or vice versa.

// Example 2:
// Input: s = "AABABBA", k = 1
// Output: 4
// Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
// The substring "BBBB" has the longest repeating letters, which is 4.
// There may exists other ways to achieve this answer too.

import java.util.*;

public class Day5 {
  public static int characterReplacement(String s, int k) {
    // HashMap<Character, Integer> mpp = new HashMap<>();
    int[] freqCnt = new int[26];
    int right = 0, left = 0;
    int n = s.length();
    int maxSize = 0;
    int maxfreq = 0;
    while (right < n) {
      // mpp.put(s.charAt(right), mpp.getOrDefault(s.charAt(right), 0) + 1);
      // maxfreq = Math.max(maxfreq, mpp.get(s.charAt(right)));

      freqCnt[s.charAt(right)-'A']++;
      maxfreq=Math.max(maxfreq, freqCnt[s.charAt(right)-'A']);
      if ((right - left + 1) - maxSize > k) {
        // mpp.put(s.charAt(left), mpp.get(s.charAt(left)) - 1);
        freqCnt[s.charAt(left)-'A']--;
        left++;
      }
      maxSize = Math.max(maxSize, right - left + 1);
      right++;
    }
    return maxSize;
  }

  

  public static void main(String[] args) {
    // Example 1
    String s1 = "ABAB";
    int k1 = 2;
    System.out.println("Output: " + characterReplacement(s1, k1)); // Expected: 4

    // Example 2
    String s2 = "AABABBA";
    int k2 = 1;
    System.out.println("Output: " + characterReplacement(s2, k2)); // Expected: 4
  }

}


// string pool