import java.util.*;

public class LCS {
  private static Map<String, Integer> memo;

  public static int lcs(String m, String n) {
    // brute force
    // Run time O(2^^n)
    memo = new HashMap<>();
    return lcs(m, n, 0, 0);
  }

  private static int lcs(String m, String n, int i, int j) {
    String key = i + "-" + j;
    if (memo.get(key) != null) {
      return memo.get(key);
    }
    if (i >= m.length() || j >= n.length()) {
      return 0;
    }
    if (m.charAt(i) == n.charAt(j)) {
      int res = 1 + lcs(m, n, i + 1, j + 1);
      memo.put(key, res); // o(1)
      return res;
    }
    int res = Math.max(lcs(m, n, i, j + 1), lcs(m, n, i + 1, j));
    memo.put(key, res);
    return res;
  }

  private static int lcst(String m, String n) {
    int[][] tab = new int[m.length() + 1][n.length() + 1];

    // if char matches 1+[i-1][j-1]  otherwise max(i,j-1 or i-1,j)
    for(int i = 1; i <= m.length(); i++) {
      for(int j = 1; j <= n.length(); j++) {
        if(m.charAt(i - 1) == n.charAt(j - 1)) {
          tab[i][j] = 1 + tab[i - 1][j - 1];
        } else {
          tab[i][j] = Math.max(tab[i - 1][j], tab[i][j - 1]);
        }
      }
    }
    return tab[m.length()][n.length()];
  }
  
  private static String printLCS(String m, String n) {
    int[][] tab = new int[m.length() + 1][n.length() + 1];
  
    // if char matches 1+[i-1][j-1]  otherwise max(i,j-1 or i-1,j)
    for(int i = 1; i <= m.length(); i++) {
      for(int j = 1; j <= n.length(); j++) {
        if(m.charAt(i - 1) == n.charAt(j - 1)) {
          tab[i][j] = 1 + tab[i - 1][j - 1];
        } else {
          tab[i][j] = Math.max(tab[i - 1][j], tab[i][j - 1]);
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    int i = m.length(), j = n.length();

    while(i > 0 && j > 0) {
      if(m.charAt(i - 1) == n.charAt(j - 1)) {
        sb.append(m.charAt(i - 1));
        i--;
        j--;
      } else if(tab[i - 1][j] > tab[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }
    return sb.reverse().toString();
  }


  public static void main(String[] args) {
    System.out.println(lcs("abcde", "be")); // 2
    System.out.println(lcs("AGGTAB", "GXTXAYB")); // GTAB -- 4

    System.out.println(lcst("abcde", "be")); // 2
    System.out.println(lcst("AGGTAB", "GXTXAYB")); // GTAB -- 4
    System.out.println(printLCS("AGGTAB", "GXTXAYB")); //GTAB
  }
}
