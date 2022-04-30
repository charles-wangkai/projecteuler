public class Solution {
  static final int[] TILES = {1, 2, 3, 4};

  public static void main(String[] args) {
    assert solve(5) == 15;

    System.out.println(solve(50));
  }

  static long solve(int length) {
    long[] dp = new long[length + 1];
    dp[0] = 1;
    for (int i = 1; i < dp.length; ++i) {
      for (int tile : TILES) {
        if (i >= tile) {
          dp[i] += dp[i - tile];
        }
      }
    }

    return dp[length];
  }
}