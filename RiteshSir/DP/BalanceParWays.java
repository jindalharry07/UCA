import java.util.HashMap;
import java.util.Map;

public class BalanceParWays {
  private static Map<Integer, Long> memo = new HashMap<>();

  private static long bpw(int n) {
    if (n < 2) {
      return 1;
    }
    if (memo.get(n) != null) {
      return memo.get(n);
    }
    long res = 0;
    for (int i = 0; i < n; i++) {
      res += bpw(i) * bpw(n - i - 1);
    }
    memo.put(n, res);
    return res;
  }

  private static long bpwt(int n) {
    if (n < 2) {
      return 1;
    }
    long[] tab = new long[n + 1];
    tab[0] = 1;
    tab[1] = 1;

    for (int i = 2; i <= n; i++) {
      long res = 0;
      for (int j = 0; j < i; j++) {
        res += tab[j] * tab[i - 1 - j];
      }
      tab[i] = res;
    }
    return tab[n];
  }

  private static long bpwc(int n) {
    if (n < 2) {
      return 1;
    }
    long[] tab = new long[n + 1];
    tab[0] = 1;
    tab[1] = 1;
    for (int i = 2; i <= n; i++) {
      tab[i] = (4L * i - 2) * tab[i - 1] / (i + 1);
    }
    return tab[n];
  }

  public static void main(String[] args) {
    assert 1 == bpw(0);
    assert 1 == bpw(1);
    assert 2 == bpw(2);
    assert 5 == bpw(3);
    assert 14 == bpw(4);

    for (int i = 5; i < 50;) {
      System.out.println("bpw of " + i + " = " + bpw(i));
      i += 5;
    }
    assert 1 == bpwt(0);
    assert 1 == bpwt(1);
    assert 2 == bpwt(2);
    assert 5 == bpwt(3);
    assert 14 == bpwt(4);

    for (int i = 5; i < 50;) {
      System.out.println("bpw of " + i + " = " + bpwt(i));
      i += 5;
    }

    // Catalan
    assert 1 == bpwc(0);
    assert 1 == bpwc(1);
    assert 2 == bpwc(2);
    assert 5 == bpwc(3);
    assert 14 == bpwc(4);

    for (int i = 5; i < 50;) {
      System.out.println("bpw of " + i + " = " + bpwc(i));
      assert bpwt(i) == bpwc(i);
      i += 5;
    }
  }
}
