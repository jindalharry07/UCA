public class ConnectedComponents {
  private boolean[] marked;
  private int count;
  private int[] id;
  public ConnectedComponents(Graph g) {
    this.marked = new boolean[g.V()];
    this.id = new int[g.V()];
    this.count = 0;

    for(int v = 0; v < g.V(); v++) {
      if(!marked[v]) {
        dfs(g, v);
        count++;
      }
    }
  }

  public boolean connected(int v, int w) {
    return this.id[v] == this.id[w];
  }

  public int count() {
    return this.count;
  }

  public int id(int v) {
    return this.id[v];
  }
  private void dfs(Graph g, int v) {
    marked[v] = true;
    id[v] = count;
    for(int w : g.adj(v)) {
      if(!marked[w]) {
        dfs(g, w);
      }
    }
  }

  public static void main(String[] args) {
    Graph g =  new Graph(9);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 3);
    g.addEdge(4, 5);
    g.addEdge(4, 6);
    g.addEdge(5, 6);
    g.addEdge(7, 8);

    ConnectedComponents cc = new ConnectedComponents(g);
    assert cc.connected(1, 3) == true;
    assert cc.connected(1, 4) == false;
    assert cc.connected(7, 4) == false;
    assert cc.connected(7, 1) == false;
    assert cc.connected(7, 8) == true;
    assert cc.connected(4, 5) == true;

    assert cc.count() == 4;

  }
}