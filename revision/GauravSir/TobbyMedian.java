import java.util.*;

class TobbyMedian {
  static class Dose implements Comparable<Dose>{
    int time;
    int idx;
    String name;int freq;

    Dose(int time, int freq, int idx, String name) {
      this.time = time;
      this.freq = freq;
      this.idx = idx;
      this.name = name;
    }

    public int compareTo(Dose o) {
      if(this.time != o.time) {
        return Integer.compare(this.time, o.time);
      }

      return Integer.compare(this.idx, o.idx);
    }
  }

  public static void main(String []arg) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();

    while(t-- > 0) {
    int n = sc.nextInt();
    int k = sc.nextInt();

    String[] name = new String[n];
    int[] freq = new int[n];

    for (int i = 0; i < n; i++) {
      name[i] = sc.next();
      freq[i] = sc.nextInt();
    }

    PriorityQueue<Dose> pq = new PriorityQueue<>();
    for (int i = 0; i < n; i++) {
      Dose d = new Dose(freq[i], freq[i], i, name[i]);
      pq.add(d);
    }

    for(int i = 0; i < k; i++) {
      Dose d = pq.poll();
      System.out.println(d.time+" "+d.name);

      d.time = d.time + d.freq;
      pq.add(d);
    }

    }
  }
}
