
import java.util.*;

class Pair {
  int ver, parent;

  public Pair(int ver, int parent) {
    this.ver = ver;
    this.parent = parent;
  }
}

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
    if (!edgeMap.containsKey(b)) {
      edgeMap.put(b, new HashSet<>());
    }
    edgeMap.get(b).add(a);
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

  private boolean dfsdetectCycle(int ver, int parent, Map<Integer, Set<Integer>> edgeMap,
      Map<Integer, Boolean> visited) {
    visited.put(ver, true);

    if (edgeMap.containsKey(ver)) {
      for (int ele : edgeMap.get(ver)) {
        if (!visited.getOrDefault(ele, false)) {
          if (dfsdetectCycle(ele, ver, edgeMap, visited)) {
            return true;
          }
        } else if (ele != parent) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean bfsdetectCycle(int start, Map<Integer, Boolean> visited) {
    Queue<Pair> q = new LinkedList<>();
    q.offer(new Pair(start, -1));
    visited.put(start, true);

    while (!q.isEmpty()) {
      Pair curr = q.poll();
      int ver = curr.ver;
      int parent = curr.parent;

      if (edgeMap.containsKey(ver)) {
        for (int ele : edgeMap.get(ver)) {
          if (!visited.getOrDefault(ele, false)) {
            visited.put(ele, true);
            q.offer(new Pair(ele, parent));
          } else if (ele != parent) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean isCycleDetected() {
    Map<Integer, Boolean> visited = new HashMap<>();

    for (int node : edgeMap.keySet()) {
      if (!visited.getOrDefault(node, false)) {
        // if (dfsdetectCycle(node, -1, edgeMap, visited)) {
        // return true;
        // }

        if (bfsdetectCycle(node, visited)) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String arg[]) {
    Graph g = new Graph();
    g.addEdge(1, 2);
    g.addEdge(2, 5);
    g.addEdge(2, 3);
    g.addEdge(3, 6);
    g.addEdge(3, 7);
    g.addEdge(3, 4);

    // System.out.println(g.getAllPaths(1));

    Graph g2 = new Graph();
    g2.addEdge(1, 2);
    g2.addEdge(2, 3);
    g2.addEdge(2, 4);
    g2.addEdge(4, 5);
    g2.addEdge(2, 5);

    if (g2.isCycleDetected()) {
      System.out.println("Cycle Detected!");
    } else {
      System.out.println("No Cycle!");
    }

  }
}
