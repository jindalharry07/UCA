import java.util.*;

public class StronglyConnectedComponents {

  private int V;
  private List<Integer>[] graph;
  private List<Integer>[] reverseGraph;

  public StronglyConnectedComponents(int V) {
    this.V = V;
    graph = new ArrayList[V];
    reverseGraph = new ArrayList[V];

    for (int i = 0; i < V; i++) {
      graph[i] = new ArrayList<>();
      reverseGraph[i] = new ArrayList<>();
    }
  }

  // Add edge u -> v
  public void addEdge(int u, int v) {
    graph[u].add(v);
    reverseGraph[v].add(u); // reverse edge
  }

  // Step 1: DFS to fill stack by finish time
  private void fillOrder(int node, boolean[] visited, Stack<Integer> stack) {
    visited[node] = true;

    for (int neigh : graph[node]) {
      if (!visited[neigh]) {
        fillOrder(neigh, visited, stack);
      }
    }

    stack.push(node);
  }

  // Step 2: DFS on reversed graph
  private void dfsOnReverse(int node, boolean[] visited, List<Integer> component) {
    visited[node] = true;
    component.add(node);

    for (int neigh : reverseGraph[node]) {
      if (!visited[neigh]) {
        dfsOnReverse(neigh, visited, component);
      }
    }
  }

  // Main function to find SCCs
  public List<List<Integer>> getSCCs() {
    Stack<Integer> st = new Stack<>();
    boolean[] visited = new boolean[V];

    for (int i = 0; i < V; i++) {
      if (visited[i] == false) {
        fillOrder(i, visited, st);
      }
    }

    Arrays.fill(visited, false);
    List<List<Integer>> sccs = new ArrayList<>(); // Strongly connected components

    while (!st.isEmpty()) {
      int node = st.pop();
      if (!visited[node]) {
        List<Integer> temp = new ArrayList<>();

        dfsOnReverse(node, visited, temp);
        sccs.add(temp);
      }
    }
    return sccs;
  }

  // Driver
  public static void main(String[] args) {
    StronglyConnectedComponents scc = new StronglyConnectedComponents(5);

    scc.addEdge(1, 0);
    scc.addEdge(0, 2);
    scc.addEdge(2, 1);
    scc.addEdge(0, 3);
    scc.addEdge(3, 4);

    List<List<Integer>> result = scc.getSCCs();

    System.out.println("Strongly Connected Components:");
    for (List<Integer> comp : result) {
      System.out.println(comp);
    }
  }
}
