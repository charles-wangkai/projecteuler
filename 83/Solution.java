import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public static void main(String[] args) throws Throwable {
    System.out.println(solve());
  }

  static int solve() throws Throwable {
    assert computeMinSum(
            new int[][] {
              {131, 673, 234, 103, 18},
              {201, 96, 342, 965, 150},
              {630, 803, 746, 422, 111},
              {537, 699, 497, 121, 956},
              {805, 732, 524, 37, 331}
            })
        == 2297;

    int[][] matrix =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p083_matrix.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .split("\n"))
            .map(line -> Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray())
            .toArray(int[][]::new);

    return computeMinSum(matrix);
  }

  static int computeMinSum(int[][] matrix) {
    int n = matrix.length;

    int[][] distances = new int[n][n];
    for (int r = 0; r < n; ++r) {
      Arrays.fill(distances[r], -1);
    }

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.distance));
    pq.offer(new Element(0, 0, matrix[0][0]));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.r][head.c] == -1) {
        distances[head.r][head.c] = head.distance;

        for (int i = 0; i < R_OFFSETS.length; ++i) {
          int adjR = head.r + R_OFFSETS[i];
          int adjC = head.c + C_OFFSETS[i];
          if (adjR >= 0 && adjR < n && adjC >= 0 && adjC < n && distances[adjR][adjC] == -1) {
            pq.offer(new Element(adjR, adjC, head.distance + matrix[adjR][adjC]));
          }
        }
      }
    }

    return distances[n - 1][n - 1];
  }
}

class Element {
  int r;
  int c;
  int distance;

  Element(int r, int c, int distance) {
    this.r = r;
    this.c = c;
    this.distance = distance;
  }
}