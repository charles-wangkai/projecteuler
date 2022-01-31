import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;

public class Solution {
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
        == 2427;

    int[][] matrix =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p081_matrix.txt"))
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

    int[][] dp = new int[n][n];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        dp[r][c] =
            ((r == 0 && c == 0)
                    ? 0
                    : Math.min(
                        (r == 0) ? Integer.MAX_VALUE : dp[r - 1][c],
                        (c == 0) ? Integer.MAX_VALUE : dp[r][c - 1]))
                + matrix[r][c];
      }
    }

    return dp[n - 1][n - 1];
  }
}