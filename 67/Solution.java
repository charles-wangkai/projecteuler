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
    int[][] values =
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
