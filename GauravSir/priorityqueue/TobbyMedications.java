
/*
"Rockabye baby, don't you cry
'löbby is very good at, catching the ball, he loves that game so much, that one day he decided to
go out, and play, even though it was raining. He played for a long Lime and in addition Lo catching the
ball many times, he also got a cold, poor Tobby, That is why now his mother will take care of him,
Big doggie momma, singing that beautiful lullaby (rockabye) and giving him the medications in the
moments that. must. be taken.
In the medical prescription sent by the doctor, he specifies the name of the medications and how
often they sli0111d be taken. 'The doctor told him t,llnt if IIC Lakes t,he medications constantly. he, will
be. relieved after taking k medicines. Tobby does not like being sick (in fact, no one, likes to be), so he
promises his mother Lo be constant with Lhe drugs, that is why he now wants to know what are the
first k drugs that he, has to take to fecl better. Can you help Ililll?
Input
Input begins with a line containing an integer T (l T 5), the number of test cases.
For each test case, the medical prescription is written as follows:
Each test case begins with a line containing two integers. n (I 3*103) and k (l < k < IN)
indicating the number of medications sent by the doctor and the minimum number of medicines 'l'obby
must, take to feel bet,t.er.
Thc following n lines will be of the form, •na.mc .frceucnc!/ (I Inxnrncl 15, I
3*103), indicating the name of the medication and how often it should be taken.
The medicines ure listed according to their degree of priority, i.e. thc lirst onc will be the IllosL
important drug and the last one, the least important.
Output
For each test case, the output must have k lines, each of the form, 't m', indicating that in the moment
If there are two or more drugs that must be given at, the same time t, they should be printed
according to their priority.
Sample Input
1
25
Acetaminophen 20
Loratadine 30
Sample Output
20 Acetaminophen
30 Loratadine
40 Acetaminophen
60 Acetaminophen
60 Loratadine
*/

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
