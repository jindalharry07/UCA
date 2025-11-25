import java.util.*;

class dijkstra {
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

      Queue<QueueNode> q = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.currDis, n2.currDis));
      q.add(new QueueNode(a, 0));

      while (!q.isEmpty()) {
        QueueNode qNode = q.poll();

        if (shortestDis[qNode.currNode] == null || shortestDis[qNode.currNode] > qNode.currDis) {
          shortestDis[qNode.currNode] = qNode.currDis;
        } else {
          continue;
        }

        int newNode = qNode.currNode;

        for (int i = 0; i < N; i++) {
          if (newNode != i && this.adj[newNode][i] != null) {
            int newDis = this.adj[newNode][i] + qNode.currDis;
            if (shortestDis[i] == null || newDis < shortestDis[i]) {
              q.add(new QueueNode(i, this.adj[newNode][i] + qNode.currDis));
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
