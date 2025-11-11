public class EulerUndirected {
  private boolean isConnected(Graph g) {
    boolean[] visited = new boolean[g.V()];
    int start = -1;

    for (int v = 0; v < g.V(); v++) {
      if (g.degree(v) > 0) {
        start = v;
        break;
      }
    }

    if (start == -1) {
      return true;
    }

    dfs(g, start, visited);

    for (int v = 0; v < g.V(); v++) {
      if (visited[v] == false && g.degree(v) > 0) {
        return false;
      }
    }
    return true;
  }

  private void dfs(Graph g, int start, boolean[] visited) {
    visited[start] = true;

    for (int ele : g.adj(start)) {
      if (visited[ele] == false) {
        dfs(g, ele, visited);
      }
    }
  }

  public EulerUndirected(Graph g) {
    if (!isConnected(g)) {
      System.out.println("This graph has no euler path/ circuit!");
      return;
    }

    int oddDegreeCnt = 0;

    for (int v = 0; v < g.V(); v++) {
      if (g.degree(v) % 2 != 0) {
        oddDegreeCnt++;
      }
    }

    // 0 means circuit
    // 1 means only path
    // 2 means nothing

    if (oddDegreeCnt == 0) {
      System.out.println("This graph has an Euler circuit!");
    } else if (oddDegreeCnt == 2) {
      System.out.println("This graph has an Euler path (but not a circuit)!");
    } else {
      System.out.println("This graph has no Euler path or circuit!");
    }o
  }

  public static void main(String[] args) {
    Graph g = new Graph(5);

    // Example 1: Euler circuit (all degrees even)

    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(0, 3);
    g.addEdge(3, 4);
    g.addEdge(4, 0);

    System.out.println("Testing graph 1:");
    new EulerUndirected(g); // prints result

    // Example 2: Euler path only
    /*
     * 0--------1----------2-----------3
     */
    Graph g2 = new Graph(4);
    g2.addEdge(0, 1);
    g2.addEdge(1, 2);
    g2.addEdge(2, 3);

    System.out.println("\nTesting graph 2:");
    new EulerUndirected(g2);

    // Example 3: No Euler circuit

    Graph g3 = new Graph(4);
    g3.addEdge(0, 1);
    g3.addEdge(1, 2);
    g3.addEdge(2, 3);
    g3.addEdge(3, 0);
    g3.addEdge(0, 2);

    System.out.println("\nTesting graph 3:");
    new EulerUndirected(g3);
  }
}
