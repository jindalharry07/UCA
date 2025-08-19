package priorityqueue;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Class to schedule Tobby's medications according to priority and frequency.
 */
public final class TobbyMedications {
  // private constructor to prevent instantiation
  private TobbyMedications() {
    // Prevent instantiation
  }

  /**
   * Inner class representing a medication dose event.
   */
  static class Dose implements Comparable<Dose> {
    /**
     * Time at which the dose should be given.
     */
    private int time;

    /**
     * Priority of medication.
     */
    private int priority;

    /**
     * Name of medication.
     */
    private String name;

    /**
     * Frequency of medication.
     */
    private int freq;

    Dose(final int time, final int priority, final String name, final int freq) {
      this.time = time;
      this.priority = priority;
      this.name = name;
      this.freq = freq;
    }

    @Override
    public int compareTo(final Dose other) {
      if (this.time != other.time) {
        return Integer.compare(this.time, other.time);
      }
      return Integer.compare(this.priority, other.priority);
    }

    public int getTime() {
      return time;
    }

    public int getPriority() {
      return priority;
    }
    
    public String getName() {
      return name;
    }
    
    public int getFreq() {
      return freq;
    }

  }

  /**
   * Main method to handle input and run scheduling.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(final String[] arg) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();

    for (int t = 0; t < T; t++) {
      int n = sc.nextInt();
      int k = sc.nextInt();

      String[] names = new String[n];
      int[] freq = new int[n];

      for (int i = 0; i < n; i++) {
        names[i] = sc.next();
	freq[i] = sc.nextInt();
      }
      System.out.println();

      PriorityQueue<Dose> pq = new PriorityQueue<>();
      for (int i = 0; i < n; i++) {
        pq.add(new Dose(freq[i], i, names[i], freq[i]));
      }

      for (int i = 0; i < k; i++) {
        Dose d = pq.poll();
	System.out.println(d.time + " " + d.name);
	pq.add(new Dose(d.time + d.freq, d.priority, d.name, d.freq));
      }
    }
    sc.close();
  }
}
