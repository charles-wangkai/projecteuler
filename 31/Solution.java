public class Solution {
  static final int[] COINS = {1, 2, 5, 10, 20, 50, 100, 200};

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int[] dp = new int[201];
    dp[0] = 1;
    for (int coin : COINS) {
      for (int i = coin; i < dp.length; ++i) {
        dp[i] += dp[i - coin];
      }
    }

    return dp[dp.length - 1];
  }
}
