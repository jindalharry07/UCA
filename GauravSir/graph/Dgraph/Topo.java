import java.util.*;

public class Topo {
  private boolean[] marked;
  private Stack<Integer> reversePostorder;

  public Topo(DirectedGraph g) {
    reversePostorder = new Stack<>();
    marked = new boolean[g.V()];
    for (int i = 0; i < g.V(); i++) {
      if (!marked[i]) {
        dfs(g, i);
      }
    }
  }

  private void dfs(DirectedGraph g, int v) {
    marked[v] = true;

    for (int w : g.adj[v]) {
      if (!marked[w]) {
        dfs(g, w);
      }
    }
    reversePostorder.push(v);
  }

  public Stack<Integer> order() {
    return reversePostorder;
  }

  public static void main(String[] args) {
    DirectedGraph d = new DirectedGraph(7);
    d.addEdge(0, 1);
    d.addEdge(0, 2);
    d.addEdge(0, 5);
    d.addEdge(1, 4);
    d.addEdge(3, 2);
    d.addEdge(3, 4);
    d.addEdge(3, 5);
    d.addEdge(3, 6);
    d.addEdge(5, 2);
    d.addEdge(6, 0);
    d.addEdge(6, 4);
    Topo t = new Topo(d);
    Stack<Integer> s = t.order();
    while (!s.isEmpty()) {
      if (s.size() == 1) {
        System.out.print(s.pop());

      } else
        System.out.print(s.pop() + "->");

    }
  }

}