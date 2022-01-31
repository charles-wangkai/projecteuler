public class Solution {
  public static void main(String[] args) {
    assert solve(5) == 6;

    System.out.println(solve(100));
  }

  static int solve(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    for (int i = 1; i <= n; ++i) {
      for (int j = i; j < dp.length; ++j) {
        dp[j] += dp[j - i];
      }
    }

    return dp[dp.length - 1] - 1;
  }
}