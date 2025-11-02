import java.util.*;

public class BFS {
  private boolean marked[];
  private int[] edgeTo;
  private int s; // source vertex;

  public BFS(graph g, int s) {
    this.marked = new boolean[g.V()];
    this.edgeTo = new int[g.V()];
    this.s = s;
    bfs(g, s);
  }

  private void bfs(graph g, int s) {
    Queue<Integer> q = new LinkedList<>();
    q.add(s);
    marked[s] = true;

    while (!q.isEmpty()) {
      int v = q.poll();

      for (int ele : g.adj(v)) {
        if (marked[ele] == false) {
          marked[ele] = true;
          edgeTo[ele] = v;
          q.add(ele);
        }
      }
    }
  }

  public boolean connected(int v) {
    return this.marked[v];
  }

  public void printPath(int v) {
    if (marked[v] == false) {
      System.out.println("No path from " + s + " to " + v);
      return;
    }

    Stack<Integer> st = new Stack<>();
    for (int ele = v; ele != s; v = edgeTo[ele]) {
      st.add(ele);
    }
    st.add(s);

    while (!st.isEmpty()) {
      System.out.print(st.pop() + " ");
    }
  }
}
