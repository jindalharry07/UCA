import java.util.*;

public class DirectedBFS {
  private boolean marked[];
  private int[] edgeTo;
  private int s; // source vertex;

  DirectedBFS(DirectedGraph g, int s) {
    this.marked = new boolean[g.V()];
    this.edgeTo = new int[g.V()];
    this.s = s;
    bfs(g, s);
  }

  private void bfs(DirectedGraph g, int v) {
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(v);
    marked[v] = true;
    while (!q.isEmpty()) {
      int w = q.poll();
      for (int z : g.adj(w)) {
        if (marked[z])
          continue;
        marked[z] = true;
        edgeTo[z] = w;
        q.add(z);
      }
    }
  }

  public boolean connected(int v) {
    return this.marked[v];
  }

  public void printPath(int v) {
    if (!connected(v))
      return;
    Stack<Integer> path = new Stack<>();
    for (int w = v; w != s; w = edgeTo[w]) {
      path.add(w);
    }
    path.push(s);
    while (!path.isEmpty()) {
      System.out.print(path.pop() + " -> ");
    }
  }

  public static void main(String[] args) {

    DirectedGraph g = new DirectedGraph(6);

    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 4);
    g.addEdge(3, 5);
    g.addEdge(4, 5);

    DirectedBFS bfs = new DirectedBFS(g, 0);

    bfs.printPath(4);

  }
}