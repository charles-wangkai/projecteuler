import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;

public class Solution {
  public static void main(String[] args) throws Throwable {
    String[] triangle =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p067_triangle.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .split("\n"))
            .toArray(String[]::new);

    System.out.println(solve(triangle));
  }

  static int solve(String[] triangle) {
    int[][] values =
        Arrays.stream(triangle)
            .map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray())
            .toArray(int[][]::new);

    int[] sums = {values[0][0]};
    for (int i = 1; i < values.length; ++i) {
      int[] nextSums = new int[i + 1];
      for (int j = 0; j < nextSums.length; ++j) {
        nextSums[j] =
            Math.max(((j == nextSums.length - 1) ? 0 : sums[j]), ((j == 0) ? 0 : sums[j - 1]))
                + values[i][j];
      }

      sums = nextSums;
    }

    return Arrays.stream(sums).max().getAsInt();
  }
}
