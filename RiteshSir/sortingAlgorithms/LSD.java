import java.util.*;

class LSD {
  private void sort(String[] a) {
    int digit = a[0].length() - 1;
    String[] aux = new String[a.length];

    while (digit >= 0) {
      int[] cnt = new int[256];
      for (int i = 0; i < a.length; i++) {
        char ch = a[i].charAt(digit);
        cnt[ch + 1]++;
      }

      for (int i = 1; i < 256; i++) {
        cnt[i] += cnt[i - 1];
      }

      for (int i = 0; i < a.length; i++) {
        char ch = a[i].charAt(digit);
        aux[cnt[ch]++] = a[i];
      }

      for (int i = 0; i < a.length; i++) {
        a[i] = aux[i];
      }

      digit--;
    }
  }

  public static void main(String[] args) {
    String[] input = { "man", "cat", "mat", "mat", "sat", "get", "set", "sat" };

    LSD lsd = new LSD();
    System.out.println(Arrays.toString(input));
    lsd.sort(input);
    System.out.println(Arrays.toString(input));
  }
}