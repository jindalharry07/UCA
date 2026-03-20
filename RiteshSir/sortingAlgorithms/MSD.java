import java.util.Arrays;

public class MSD {

  private static final int R = 256; // ASCII

  public void sort(String[] a) {
    String[] aux = new String[a.length];
    sort(a, 0, a.length - 1, 0, aux);
  }

  private void sort(String[] a, int low, int high, int digit, String[] aux) {
    if (high <= low) {
      return;
    }

    int[] count = new int[R + 2];

    // Count frequency
    for (int i = low; i <= high; i++) {
      int ch = charAt(a[i], digit);
      count[ch + 2]++;
    }

    // Transform to indices
    for (int r = 0; r < R + 1; r++) {
      count[r + 1] += count[r];
    }

    // Distribute
    for (int i = low; i <= high; i++) {
      int c = charAt(a[i], digit);
      aux[count[c + 1]++] = a[i];
    }

    // Copy back
    for (int i = low; i <= high; i++) {
      a[i] = aux[i - low];
    }

    // Recursively sort for each character
    for (int r = 0; r < R; r++) {
      sort(a, low + count[r], low + count[r + 1] - 1, digit + 1, aux);
    }
  }

  private int charAt(String s, int d) {
    if (d < s.length()) {
      return s.charAt(d);
    } else {
      return -1; // shorter strings come first
    }
  }

  public static void main(String[] args) {
    String[] input = { "man", "cat", "mat", "mat", "sat", "get", "set", "sat" };

    MSD msd = new MSD(); // FIXED
    System.out.println(Arrays.toString(input));
    msd.sort(input);
    System.out.println(Arrays.toString(input));
  }
}