import java.util.*;

public class BipartiteCheck {
  private boolean[] marked;
  private int[] color;
  private boolean isBipartite;
  private List<Integer> red, green;

  public BipartiteCheck(Graph g) {
    marked = new boolean[g.V()];
    color = new int[g.V()]; // 0 -> red, 1 -> green
    isBipartite = true;
    red = new ArrayList<>();
    green = new ArrayList<>();

    for (int v = 0; v < g.V(); v++) {
      if (marked[v] == false) {
        dfs(g, v, 0);
      }
    }
  }

  private void dfs(Graph g, int v, int c) {
    marked[v] = true;
    color[v] = c;

    if (c == 0) {
      red.add(v);
    } else {
      green.add(v);
    }

    for (int ele : g.adj(v)) {
      if (marked[ele] == false) {
        dfs(g, ele, 1 - c);
      } else if (color[ele] == c) {
        isBipartite = false;
      }
    }
  }

  public boolean isBipartite() {
    return isBipartite;
  }

  public List<Integer> getRed() {
    return red;
  }

  public List<Integer> getGreen() {
    return green;
  }

  public static void main(String[] args) {
    Graph g = new Graph(6);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    g.addEdge(4, 5);

    BipartiteCheck bc = new BipartiteCheck(g);

    if (bc.isBipartite()) {
      System.out.println("Graph is bipartite");
      System.out.println("RED vertices: " + bc.getRed());
      System.out.println("BLUE vertices: " + bc.getGreen());
    } else {
      System.out.println("Graph is not bipartite");
    }
  }
}
