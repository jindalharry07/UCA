public class FloydWarshall {
  static class Graph {
    Integer[][] adj;
    int N;

    Graph(int N) {
      this.N = N;
      this.adj = new Integer[N][N];

      for (int i = 0; i < N; i++) {
        adj[i][i] = 0;
      }
    }

    class QueueNode {
      int currNode;
      int currDis;

      QueueNode(int currNode, int currDis) {
        this.currNode = currNode;
        this.currDis = currDis;
      }
    }

    public void addEdge(int a, int b, int dis) {
      adj[a][b] = dis;
    }

    public Integer[] shortestDistanceFromGivenNode(int a) {
      Integer[] shortestDis = new Integer[N];
      shortestDis[a] = 0;

      for(int k = 0; k < N - 1; k++) {
        for(int i = 0; i < N; i++) {
          for(int j = 0; j < N; j++) {
            if(i == j) {
              continue;
            }
            int u = i;
            int v = j;
            Integer dt = adj[i][j];
            if(shortestDis[u] == null || dt == null) {
              continue;
            }
            
            if(shortestDis[u] != null && (shortestDis[v] == null || shortestDis[v] > shortestDis[u] + dt)) {
              shortestDis[v] = shortestDis[u] + dt;
            }
          }
        }
      }

      return shortestDis;
    }
  }

  public static void main(String[] arg) {
    Graph q = new Graph(3);

    q.addEdge(0, 1, 10);
    q.addEdge(0, 2, 2);
    q.addEdge(2, 1, 3);

    Integer[] res = q.shortestDistanceFromGivenNode(0);
    for (int i = 0; i < res.length; i++) {
      System.out.print(res[i] + " ");
    }
  }
}
