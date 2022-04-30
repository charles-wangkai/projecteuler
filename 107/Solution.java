import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
  public static void main(String[] args) throws Throwable {
    System.out.println(solve());
  }

  static int solve() throws Throwable {
    assert computeSaving(
            new int[][] {
              {-1, 16, 12, 21, -1, -1, -1},
              {16, -1, -1, 17, 20, -1, -1},
              {12, -1, -1, 28, -1, 31, -1},
              {21, 17, 28, -1, 18, 19, 23},
              {-1, 20, -1, 18, -1, -1, 11},
              {-1, -1, 31, 19, -1, -1, 27},
              {-1, -1, -1, 23, 11, 27, -1}
            })
        == 150;

    int[][] matrix =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p107_network.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .split("\n"))
            .map(
                line ->
                    Arrays.stream(line.split(","))
                        .mapToInt(part -> part.equals("-") ? -1 : Integer.parseInt(part))
                        .toArray())
            .toArray(int[][]::new);

    return computeSaving(matrix);
  }

  static int computeSaving(int[][] matrix) {
    int totalWeight = 0;
    List<Edge> edges = new ArrayList<>();
    for (int i = 0; i < matrix.length; ++i) {
      for (int j = i + 1; j < matrix[i].length; ++j) {
        if (matrix[i][j] != -1) {
          totalWeight += matrix[i][j];
          edges.add(new Edge(i, j, matrix[i][j]));
        }
      }
    }
    Collections.sort(edges, Comparator.comparing(edge -> edge.weight));

    int weightSum = 0;
    int[] parents = new int[matrix.length];
    Arrays.fill(parents, -1);
    for (Edge edge : edges) {
      int root1 = findRoot(parents, edge.node1);
      int root2 = findRoot(parents, edge.node2);
      if (root1 != root2) {
        weightSum += edge.weight;
        parents[root2] = root1;
      }
    }

    return totalWeight - weightSum;
  }

  static int findRoot(int[] parents, int node) {
    int root = node;
    while (parents[root] != -1) {
      root = parents[root];
    }

    int p = node;
    while (p != root) {
      int next = parents[p];
      parents[p] = root;

      p = next;
    }

    return root;
  }
}

class Edge {
  int node1;
  int node2;
  int weight;

  Edge(int node1, int node2, int weight) {
    this.node1 = node1;
    this.node2 = node2;
    this.weight = weight;
  }
}