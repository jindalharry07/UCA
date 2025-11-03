import java.util.Stack;

public class DFS {
  private boolean[] marked;
  private int[] edgeTo;
  int s; // source vertex;

  public DFS(Graph g, int s) {
    this.marked = new boolean[g.V()];
    this.edgeTo = new int[g.V()];
    this.s = s;
    dfs(g, s);
  }

  private void dfs(Graph g, int v) {
    marked[v] = true;

    for (int ele : g.adj(v)) {
      if (marked[ele] == false) {
        edgeTo[ele] = v;
        dfs(g, ele);
      }
    }
  }

  public boolean connected(int v) {
    return marked[v];
  }

  public void printPath(int v) {
    if (!marked[v]) {
      System.out.println("No path from " + s + " to " + v);
      return;
    }

    Stack<Integer> path = new Stack<>();
    for (int x = v; x != s; x = edgeTo[x]) {
      path.push(x);
    }
    path.push(s);

    while (!path.isEmpty()) {
      System.out.print(path.pop() + " ");
    }
  }

  public static void main(String[] args) {
    // Create a graph with 6 vertices
    Graph g = new Graph(6);

    // Add some edges
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 4);
    g.addEdge(3, 5);

    int source = 0;
    DFS dfs = new DFS(g, source);

    // Test connectivity
    for (int v = 0; v < g.V(); v++) {
      System.out.print("Path from " + source + " to " + v + ": ");
      dfs.printPath(v);
      System.out.println();
    }
  }
}
