
import java.util.*;

public class Graph {
  Map<Integer, Set<Integer>> edgeMap;

  public Graph() {
    edgeMap = new HashMap<>();
  }

  void addEdge(int a, int b) {
    if (!edgeMap.containsKey(a)) {
      edgeMap.put(a, new HashSet<>());
    }
    edgeMap.get(a).add(b);
  }

  private boolean isLeaf(int node) {
    return !edgeMap.containsKey(node) || edgeMap.get(node).isEmpty();
  }

  private void findAll(int node, Set<Integer> visited, List<Integer> curr, List<String> res) {
    visited.add(node);
    curr.add(node);

    // res.add(curr.toString());

    if (isLeaf(node)) {
      res.add(curr.toString());
    }

    if (edgeMap.containsKey(node)) {
      for (int ele : edgeMap.get(node)) {
        if (!visited.contains(ele)) {
          findAll(ele, visited, curr, res);
        }
      }
    }

    // res.add(curr.toString());
    curr.remove(curr.size() - 1);
    visited.remove(node);
  }

  private void findAll(int node, boolean[] visited, StringBuilder curr, List<String> res) {
    visited[node] = true;
    curr.append(node);

    res.add(curr.toString());

    if (edgeMap.containsKey(node)) {
      for (int ele : edgeMap.get(node)) {
        if (visited[ele] == false) {
          findAll(ele, visited, curr, res);
        }
      }
    }

    visited[node] = false;
    curr.deleteCharAt(curr.length() - 1);
  }

  List<String> getAllPaths(int startNode) {
    List<String> res = new ArrayList<>();
    if (!edgeMap.containsKey(startNode)) {
      return res;
    }
    // int n = edgeMap.size();
    // boolean[] visited = new boolean[n];
    // findAll(startNode, new HashSet<>(), new ArrayList<>(), res);

    // findAll(startNode, visited, new StringBuilder(), res);
    // return res;

    Queue<List<Integer>> q = new LinkedList<>();
    q.offer(new ArrayList<>(List.of(startNode)));

    while (!q.isEmpty()) {
      List<Integer> path = q.poll();
      int node = path.get(path.size() - 1);

      if (isLeaf(node)) {
        res.add(path.toString());
      }

      if (edgeMap.containsKey(node)) {
        for (int ele : edgeMap.get(node)) {
          if (!path.contains(ele)) {
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(ele);
            q.offer(newPath);
          }
        }
      }
    }
    return res;
  }

  public static void main(String arg[]) {
    Graph g = new Graph();
    g.addEdge(1, 2);
    g.addEdge(2, 5);
    g.addEdge(2, 3);
    g.addEdge(3, 6);
    g.addEdge(3, 7);
    g.addEdge(3, 4);

    System.out.println(g.getAllPaths(1));

  }
}
