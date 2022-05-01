public class Solution {
  public static void main(String[] args) {
    assert solve(7) == 17;

    System.out.println(solve(50));
  }

  static long solve(int length) {
    long[] dp = new long[length + 1];
    dp[0] = 1;
    for (int i = 1; i < dp.length; ++i) {
      dp[i] = dp[i - 1];

      if (i >= 3) {
        ++dp[i];
      }

      for (int j = 0; j <= i - 4; ++j) {
        dp[i] += dp[j];
      }
    }

    return dp[length];
  }
}