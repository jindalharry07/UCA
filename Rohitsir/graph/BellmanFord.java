public class BellmanFord {
  static class Graph {
    Integer[][] adj;
    int N;

    Graph(int N) {
      this.N = N;
      this.adj = new Integer[N][N];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          adj[i][j] = (i == j ? 0 : null);
        }
      }
    }

    public void addEdge(int a, int b, int dis) {
      adj[a][b] = dis;
    }

    // *** Same-style function as Floyd version 1 ***
    public Integer[][] shortestDistanceFromGivenNode() {
      Integer[][] result = new Integer[N][N];

      for (int src = 0; src < N; src++) {
        result[src] = bellmanFord(src);
      }

      return result;
    }

    private Integer[] bellmanFord(int src) {
      Integer[] dist = new Integer[N];
      dist[src] = 0;

      // Relax edges N−1 times
      for (int iter = 0; iter < N - 1; iter++) {
        for (int u = 0; u < N; u++) {
          for (int v = 0; v < N; v++) {

            Integer w = adj[u][v];
            if (w == null || dist[u] == null) {
              continue;
            }

            int via = dist[u] + w;

            if (dist[v] == null || via < dist[v]) {
              dist[v] = via;
            }
          }
        }
      }

      return dist;
    }
  }

  public static void main(String[] args) {
    Graph g = new Graph(3);

    g.addEdge(0, 1, 10);
    g.addEdge(0, 2, 2);
    g.addEdge(2, 1, 3);

    // *** SAME CALL STYLE AS YOUR FLOYD VERSION 1 ***
    Integer[][] res = g.shortestDistanceFromGivenNode();

    for (int i = 0; i < res.length; i++) {
      for (int j = 0; j < res.length; j++) {
        System.out.print(res[i][j] + " ");
      }
      System.out.println();
    }
  }
}
