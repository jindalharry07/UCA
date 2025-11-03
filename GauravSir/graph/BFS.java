import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {
  private boolean marked[];
  private int[] edgeTo;
  private int s; // source vertex;

  public BFS(Graph g, int s) {
    this.marked = new boolean[g.V()];
    this.edgeTo = new int[g.V()];
    this.s = s;
    bfs(g, s);
  }

  private void bfs(Graph g, int s) {
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
    for (int ele = v; ele != s; ele = edgeTo[ele]) {
      st.add(ele);
    }
    st.add(s);

    while (!st.isEmpty()) {
      System.out.print(st.pop() + " ");
    }
  }

  public static void main(String[] args) {
    Graph g = new Graph(6);

    // Add some edges
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 4);
    g.addEdge(3, 5);

    int source = 0;
    BFS bfs = new BFS(g, source);

    // Test connectivity and print paths
    for (int v = 0; v < g.V(); v++) {
      System.out.print("Path from " + source + " to " + v + ": ");
      bfs.printPath(v);
      System.out.println();
    }
  }
}
