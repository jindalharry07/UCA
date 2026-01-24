import java.util.HashMap;
import java.util.Map;

public class GridWays {
  private static Map<String, Integer> cache = new HashMap<>();

  private static int findwaysb(int r, int c) {
    String key = r + "--" + c;
    if (cache.get(key) != null) {
      return cache.get(key);
    }

    if (r == 0 || c == 0) {
      return 0;
    }
    if (r == 1 || c == 1) {
      return 1;
    }
    int res = findwaysb(r - 1, c) + findwaysb(r, c - 1);
    cache.put(key, res);
    return res;
  }

  private static int findwayst(int r, int c) {
    int[][] tab = new int[r + 1][c + 1];
    tab[1][1] = 1;

    for (int i = 1; i <= r; i++) {
      for (int j = 1; j <= c; j++) {
        if (i == 1 && j == 1) {
          continue;
        }
        tab[i][j] = tab[i - 1][j] + tab[i][j - 1];
      }
    }
    return tab[r][c];
  }

  public static void main(String[] args) {
    System.out.println(findwaysb(3, 3)); // 6
    System.out.println(findwaysb(1, 1)); // 1
    System.out.println(findwaysb(2, 3)); // 3
    System.out.println(findwaysb(3, 2)); // 3
    System.out.println(findwaysb(20, 20)); // lot of time 2^^40 is huge

    System.out.println(findwayst(3, 3)); // 6
    System.out.println(findwayst(1, 1)); // 1
    System.out.println(findwayst(2, 3)); // 3
    System.out.println(findwayst(3, 2)); // 3
    System.out.println(findwayst(20, 20));
  }
}
