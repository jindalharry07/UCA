import java.util.LinkedList;

public class Graph {
  private int V, E;
  private LinkedList<Integer>[] adj;

  public Graph(int V) {
    this.V = V;
    // Intialising the array. Size of array in number of Vertices.
    this.adj = new LinkedList[V];
    this.E = 0;
    for (int i = 0; i < V; i++) {
      // Intialing each adjency List
      this.adj[i] = new LinkedList<Integer>();
    }
  }

  public void addEdge(int v, int w) {
    this.adj[v].add(w);
    this.adj[w].add(v);
    this.E++;
  }

  public int V() {
    return this.V;
  }

  public int E() {
    return this.E;
  }

  public int degree(int v) {
    return adj[v].size();
  }

  public Iterable<Integer> adj(int v) {
    return this.adj[v];
  }
}
