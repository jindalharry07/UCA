import java.util.*;

public class fibonacci {
  private static Map<Integer, Integer> memo = new HashMap<>();

  private static int getFibb(int i) {
    if (memo.get(i) != null) {
      return memo.get(i);
    }
    if (i == 1 || i == 0) {
      return 1;
    }
    int result = getFibb(i - 1) + getFibb(i - 2);
    memo.put(i, result);
    return result;
  }

  private static long getFibt(int i) {
    if (i == 0 || i == 1) {
      return 1;
    }
    long[] cache = new long[i + 1];
    cache[0] = 1;
    cache[1] = 1;
    for (int j = 2; j <= i; j++) {
      cache[j] = cache[j - 1] + cache[j - 2];
    }
    return cache[i];
  }

  public static void main(String[] args) {
    System.out.println(getFibb(6));
    System.out.println(getFibb(10));
    // System.out.println(new Fib().getFib(50));

    System.out.println(getFibt(6));
    System.out.println(getFibt(10));
    System.out.println(getFibt(50));
  }
}
