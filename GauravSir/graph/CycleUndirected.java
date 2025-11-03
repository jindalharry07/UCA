import java.util.Arrays;
import java.util.Stack;

public class CycleUndirected {
  private boolean[] marked;
  private int[] edgeTo;
  private Stack<Integer> cycle;

  public CycleUndirected(Graph g) {
    marked = new boolean[g.V()];
    edgeTo = new int[g.V()];
    Arrays.fill(edgeTo, -1);

    for (int v = 0; v < g.V(); v++) {
      if (!marked[v] && cycle == null) {
        dfs(g, v, -1);
      }
    }
  }

  private void dfs(Graph g, int v, int parent) {
    marked[v] = true;
    for (int ele : g.adj(v)) {
      if (cycle != null){
        return; 
      }

      if (!marked[ele]) {
        edgeTo[ele] = v;
        dfs(g, ele, v);
      } else if (ele != parent) {
        cycle = new Stack<>();

        for (int x = v; x != ele; x = edgeTo[x]){
          cycle.push(x);
        }
        cycle.push(ele);
        cycle.push(v);
      }
    }
  }

  public boolean hasCycle() {
    return cycle != null;
  }

  public Iterable<Integer> getCycle() {
    return cycle;
  }

  public static void main(String[] args) {
    Graph g = new Graph(6);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 0); // cycle here
    g.addEdge(3, 4);

    CycleUndirected c = new CycleUndirected(g);

    if (c.hasCycle()) {
      System.out.println("Cycle found: " + c.getCycle());
    } else {
      System.out.println("No cycle found.");
    }
  }
}
