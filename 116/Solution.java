import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
  static final int[] TILES = {2, 3, 4};

  public static void main(String[] args) {
    assert solve(5) == 12;

    System.out.println(solve(50));
  }

  static long solve(int length) {
    return Arrays.stream(TILES)
        .mapToLong(tile -> computeWayNum(length, IntStream.of(1, tile).toArray()) - 1)
        .sum();
  }

  static long computeWayNum(int length, int[] tiles) {
    long[] dp = new long[length + 1];
    dp[0] = 1;
    for (int i = 1; i < dp.length; ++i) {
      for (int tile : tiles) {
        if (i >= tile) {
          dp[i] += dp[i - tile];
        }
      }
    }

    return dp[length];
  }
}