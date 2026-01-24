import java.util.*;

public class FIndBestSum {
  private static Map<Integer, List<Integer>> memo = new HashMap<>();

  private static List<Integer> findSum(int target, int[] arr) {
    if (memo.get(target) != null) {
      return memo.get(target);
    }

    if (target == 0) {
      return new ArrayList<Integer>();
    }
    if (target < 0) {
      return null;
    }
    List<Integer> res = null;

    for (int ele : arr) {
      List<Integer> remainder = findSum(target - ele, arr);
      if (remainder != null) {
        List<Integer> combination = new ArrayList<>(remainder);
        combination.add(ele);

        if (res == null || combination.size() < res.size()) {
          res = combination;
        }
      }
    }

    memo.put(target, res);
    return res;
  }

  public static void main(String[] args) {
    System.out.println(findSum(7, new int[] { 2, 3, 7 })); // 7
    System.out.println(findSum(7, new int[] { 2, 4 })); // null []
    System.out.println(findSum(50, new int[] { 2, 5, 25 })); // [25,25]
    System.out.println(findSum(100, new int[] { 2, 5, 25 })); // [25,25,25,25]
  }
}
