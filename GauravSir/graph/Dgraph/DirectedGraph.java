import java.util.LinkedList;

public class DirectedGraph {
  private int V;
  private int E;
  LinkedList<Integer>[] adj;

  public DirectedGraph(int V) {
    this.V = V;
    // Intialising tha array. Size of array is number of Vertices
    this.adj = new LinkedList[V];
    this.E = 0;
    for (int i = 0; i < V; i++) {
      // Initialising each adjency list.
      this.adj[i] = new LinkedList<Integer>();
    }
  }

  public void addEdge(int v, int w) {
    this.adj[v].add(w);
    this.E++;
  }

  public DirectedGraph reverse() {
    return null;
  }

  public int V() {
    return this.V;
  }

  public int E() {
    return this.E;
  }

  public Iterable<Integer> adj(int v) {
    return this.adj[v];
  }
}