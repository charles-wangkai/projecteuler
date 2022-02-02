import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.stream.IntStream;

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
        == 994;

    int[][] matrix =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p082_matrix.txt"))
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
    for (int c = 0; c < n; ++c) {
      int c_ = c;
      for (int r = 0; r < n; ++r) {
        int r_ = r;
        dp[r][c] =
            IntStream.range(0, n)
                .map(
                    prevR ->
                        IntStream.rangeClosed(Math.min(r_, prevR), Math.max(r_, prevR))
                                .map(i -> matrix[i][c_])
                                .sum()
                            + ((c_ == 0) ? 0 : dp[prevR][c_ - 1]))
                .min()
                .getAsInt();
      }
    }

    return IntStream.range(0, n).map(r -> dp[r][n - 1]).min().getAsInt();
  }
}